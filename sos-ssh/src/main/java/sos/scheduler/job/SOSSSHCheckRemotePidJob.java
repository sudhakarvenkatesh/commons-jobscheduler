package sos.scheduler.job;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import sos.net.ssh.SOSSSHJob2;
import sos.net.ssh.SOSSSHJobJSch;
import sos.net.ssh.exceptions.SSHConnectionError;
import sos.net.ssh.exceptions.SSHExecutionError;

import com.sos.VirtualFileSystem.Options.SOSConnection2OptionsAlternate;
import com.sos.VirtualFileSystem.common.SOSVfsMessageCodes;
import com.sos.i18n.annotation.I18NResourceBundle;

@I18NResourceBundle(baseName = "com_sos_net_messages", defaultLocale = "en")
public class SOSSSHCheckRemotePidJob extends SOSSSHJobJSch{
  private final Logger logger = Logger.getLogger(this.getClass());
  private List<Integer> availablePidsToKill = new ArrayList<Integer>();
  private static final String PARAM_PIDS_TO_KILL = "PIDS_TO_KILL";

  private void openSession() {
    try {
      if (!vfsHandler.isConnected()) {
        SOSConnection2OptionsAlternate postAlternateOptions = getAlternateOptions(objOptions);
        postAlternateOptions.raise_exception_on_error.value(false);
        vfsHandler.Connect(postAlternateOptions);
      }
      vfsHandler.Authenticate(objOptions);
      logger.debug("connection for kill commands established");
    } catch (Exception e) {
      throw new SSHConnectionError("Error occured during connection/authentication: " + e.getLocalizedMessage(), e);
    }
    vfsHandler.setJSJobUtilites(objJSJobUtilities);
  }

  @Override
  public SOSSSHJob2 Connect() {
    getVFS();
    Options().CheckMandatory();
    try {
      SOSConnection2OptionsAlternate alternateOptions = getAlternateOptions(objOptions);
      vfsHandler.Connect(alternateOptions);
      vfsHandler.Authenticate(objOptions);
      logger.debug("connection established");
    } catch (Exception e) {
      throw new SSHConnectionError("Error occured during connection/authentication: " + e.getLocalizedMessage(), e);
    }
    flgIsWindowsShell = vfsHandler.remoteIsWindowsShell();
    isConnected = true;
    return this;
  } 

  @Override
  public SOSSSHJob2 Execute() {
    vfsHandler.setJSJobUtilites(objJSJobUtilities);
    openSession();
    Map<Integer, PsEfLine> remoteRunningPids = new HashMap<Integer, PsEfLine>();
    try {
      if (isConnected == false) {
        this.Connect();
      }
      vfsHandler.ExecuteCommand("/bin/ps -ef | grep " + objOptions.user.Value());
      // check if command was processed correctly
      if (vfsHandler.getExitCode() == 0) {
        // read stdout of the read-temp-file statement per line
        if (vfsHandler.getStdOut().toString().length() > 0) {
          BufferedReader reader = new BufferedReader(new StringReader(new String(vfsHandler.getStdOut())));
          String line = null;
          logger.debug(SOSVfsMessageCodes.SOSVfs_D_284.getFullMessage());
          boolean firstLine = true;
          while ((line = reader.readLine()) != null) {
            if(firstLine){
              // The first line is the Header so we skip that
              firstLine = false;
              continue;
            }
            if (line == null) break;
            line = line.trim();
            String[] fields = line.split(" +", 8);
            PsEfLine psOutputLine = new PsEfLine();
            psOutputLine.user = fields[0];
            psOutputLine.pid = Integer.parseInt(fields[1]);
            psOutputLine.parentPid = Integer.parseInt(fields[2]);
            psOutputLine.pidCommand = fields[7];
            remoteRunningPids.put(new Integer(psOutputLine.pid), psOutputLine);
          }
          Iterator psOutputLineIterator = remoteRunningPids.values().iterator();
          while (psOutputLineIterator.hasNext()) {
            PsEfLine current = (PsEfLine) psOutputLineIterator.next();
            PsEfLine parent = (PsEfLine) remoteRunningPids.get(new Integer(current.parentPid));
            if (parent != null) {
//              logger.debug("Child of " + parent.pid + " is " + current.pid);
              parent.children.add(new Integer(current.pid));
            }
          }
        } else {
          logger.debug("no stdout received from remote host");
        }
      }else{
        logger.error("error occured executing command /bin/ps -ef");
      }
    } catch (Exception e) {
      if(objOptions.raise_exception_on_error.value()){
        if(objOptions.ignore_error.value()){
          if(objOptions.ignore_stderr.value()){
            logger.debug(this.StackTrace2String(e));
          }else{
            logger.error(this.StackTrace2String(e));
            throw new SSHExecutionError("Exception raised: " + e, e);
          }
        }else{
          logger.error(this.StackTrace2String(e));
          throw new SSHExecutionError("Exception raised: " + e, e);
        }
      }
    } finally {
      if (remoteRunningPids != null && remoteRunningPids.size() > 0){
        // receive pids from Order params here
        List<Integer> pidsToKillFromOrder = getPidsToKill();
        availablePidsToKill = new ArrayList<Integer>();
        for(Integer pidToKill : pidsToKillFromOrder){
          // then check if the pids are still running on the remote host
          PsEfLine psLine;
          if((psLine = remoteRunningPids.get(pidToKill)) != null){
            logger.debug("Added to available PIDs: " + psLine.pid);
            availablePidsToKill.add(pidToKill);
          } else{
            logger.debug("PID " + pidToKill + " not found" );
          }
        }
        // and override the order param with the resulting (still running) pids only to later kill them
        StringBuilder strb = new StringBuilder();
        if (availablePidsToKill.size() > 0){
          logger.debug("Overriding param " + PARAM_PIDS_TO_KILL);
          boolean first = true;
          // create a String with the comma separated pids to put in one Param 
          for (Integer pid : availablePidsToKill){
            if (first){
              strb.append(pid.toString());
              first = false;
            }else{
              strb.append(",").append(pid.toString());
            }
          }
          logger.debug("still running PIDs to kill: " + strb.toString());
        }
        objJSJobUtilities.setJSParam(PARAM_PIDS_TO_KILL, strb.toString());
      }
    }
    return this;
  }

  private List<Integer> getPidsToKill(){
    logger.debug("PIDs to kill From Order: " + objOptions.getItem(PARAM_PIDS_TO_KILL));
    String[] pidsFromOrder = null;
    if(objOptions.getItem(PARAM_PIDS_TO_KILL) != null && objOptions.getItem(PARAM_PIDS_TO_KILL).length() > 0){
      pidsFromOrder = objOptions.getItem(PARAM_PIDS_TO_KILL).split(",");
    }
    List<Integer> pidsToKill = new ArrayList<Integer>();
    if (pidsFromOrder != null) {
      for (String pid : pidsFromOrder) {
        if (pid != null && pid.length() > 0) {
          pidsToKill.add(Integer.parseInt(pid));
        } else {
          logger.debug("PID is empty!");
        }
      }
    }
    return pidsToKill;
  }

  private class PsEfLine implements Comparable {
    public String user;
    public Integer pid;
    public Integer parentPid;
    public String pidCommand;
    public List children = new ArrayList();

    /* (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object) */
    public int compareTo(Object o) {
      PsEfLine other = (PsEfLine) o;
      return pid - other.pid;
    }
  }

}
