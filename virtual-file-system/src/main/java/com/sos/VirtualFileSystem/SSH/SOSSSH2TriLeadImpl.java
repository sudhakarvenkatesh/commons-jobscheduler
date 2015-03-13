package com.sos.VirtualFileSystem.SSH;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Vector;

import org.apache.log4j.Logger;

import sos.spooler.Variable_set;

import com.sos.JSHelper.Basics.JSJobUtilities;
import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.interfaces.ISOSConnectionOptions;
import com.sos.JSHelper.interfaces.ISOSDataProviderOptions;
import com.sos.VirtualFileSystem.DataElements.SOSFileList;
import com.sos.VirtualFileSystem.DataElements.SOSFolderName;
import com.sos.VirtualFileSystem.Interfaces.ISOSAuthenticationOptions;
import com.sos.VirtualFileSystem.Interfaces.ISOSConnection;
import com.sos.VirtualFileSystem.Interfaces.ISOSSession;
import com.sos.VirtualFileSystem.Interfaces.ISOSShell;
import com.sos.VirtualFileSystem.Interfaces.ISOSShellOptions;
import com.sos.VirtualFileSystem.Interfaces.ISOSVFSHandler;
import com.sos.VirtualFileSystem.Interfaces.ISOSVirtualFileSystem;
import com.sos.VirtualFileSystem.Interfaces.ISOSVirtualFolder;
import com.sos.VirtualFileSystem.Options.SOSConnection2OptionsAlternate;
import com.sos.VirtualFileSystem.common.SOSVfsBaseClass;
import com.sos.i18n.Msg;
import com.sos.i18n.Msg.BundleBaseName;
import com.sos.i18n.annotation.I18NResourceBundle;
import com.trilead.ssh2.ChannelCondition;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.HTTPProxyData;
import com.trilead.ssh2.SFTPException;
import com.trilead.ssh2.SFTPv3Client;
import com.trilead.ssh2.SFTPv3FileAttributes;
import com.trilead.ssh2.SFTPv3FileHandle;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;

/**
* \class SOSSSH2SuperClass 
* 
* \brief SOSSSH2SuperClass - 
*  
* \details
*
* \code
*   .... code goes here ...
* \endcode
*
* <p style="text-align:center">
* <br />---------------------------------------------------------------------------
* <br /> APL/Software GmbH - Berlin
* <br />##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
* <br />---------------------------------------------------------------------------
* </p>
* \author KB
* @version $Id$16.05.2010
* \see reference
*
* Created on 16.05.2010 19:17:53
 */
 
@I18NResourceBundle(baseName = "SOSVirtualFileSystem", defaultLocale = "en")
public class SOSSSH2TriLeadImpl extends SOSVfsBaseClass implements ISOSShell, ISOSVFSHandler, ISOSVirtualFileSystem, ISOSConnection, ISOSSession {

	private final String	conClassName	= "SOSSSH2TriLeadImpl";

	private final Logger			logger			= Logger.getLogger(SOSSSH2TriLeadImpl.class);
	final private String	strEndOfLine	= System.getProperty("line.separator");

	protected Msg			objMsg			= new Msg(new BundleBaseName(this.getClass().getAnnotation(I18NResourceBundle.class).baseName()));

	public SOSSSH2TriLeadImpl() {
		//
	}
	
	private ISOSConnectionOptions		objCO					= null;
	private ISOSAuthenticationOptions	objAO					= null;
	private ISOSShellOptions			objSO					= null;

	boolean								isAuthenticated			= false;
	boolean								isConnected				= false;

	/** Line currently being displayed on the shell **/
	protected String					strCurrentLine			= "";

	/** ssh connection object */
	protected Connection				sshConnection			= null;

	/** ssh session object */
	protected Session					sshSession				= null;

	/** Inputstreams for stdout and stderr **/
	protected InputStream				ipsStdOut;
	protected InputStream				ipsStdErr;

	private boolean						flgIsRemoteOSWindows	= false;
	private SFTPv3Client				sftpClient				= null;

	private RemoteConsumer				stdoutConsumer			= null;
	private RemoteConsumer				stderrConsumer			= null;

	/** Output from stdout and stderr **/
	protected StringBuffer				strbStdoutOutput;
	protected StringBuffer				strbStderrOutput;

	/** timestamp of the last text from stdin or stderr **/
	protected long						lngLastTime				= 0;

	private OutputStream				stdin;
	private OutputStreamWriter			stdinWriter				= null;

	private Integer						exitStatus				= null;
	private String						exitSignal				= null;

	private Vector<String>				vecFilesToDelete		= new Vector<String>();

	private Vector<String> getFilesToDelete() {

		if (vecFilesToDelete == null) {
			vecFilesToDelete = new Vector<String>();
		}
		return vecFilesToDelete;
	}

	@Override
	public ISOSConnection Connect(final String pstrHostName, final int pintPortNumber) throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Connect";

		try {
			isConnected = false;
			this.setSshConnection(new Connection(pstrHostName, pintPortNumber));

		}
		catch (Exception e) {
			if (this.getSshConnection() != null)
				try {
					this.getSshConnection().close();
					this.setSshConnection(null);
				}
				catch (Exception ex) {
				}
			throw new Exception(e.getMessage());
		}
		return this;
	}

	/**
	 * 
	 * \brief Connect
	 * 
	 * \details
	 *
	 * \return 
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public ISOSConnection Connect() throws Exception {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Connect";

		if (objCO == null) {
			throw new JobSchedulerException(SOSVfs_F_102.get());
		}

		try {
			isConnected = false;
			String strHostName = objCO.getHost().Value();
			int intPortNo = objCO.getPort().value();
			this.setSshConnection(new Connection(strHostName, intPortNo));

			if (objCO.getProxy_host().IsNotEmpty()) {
				HTTPProxyData objProxy = null;
				if (objCO.getProxy_user().IsEmpty()) {
					objProxy = new HTTPProxyData(objCO.getProxy_host().Value(), objCO.getProxy_port().value());
				}
				else {
					objProxy = new HTTPProxyData(objCO.getProxy_host().Value(), objCO.getProxy_port().value(), objCO.getProxy_user().Value(),
							objCO.getProxy_password().Value());
				}
				this.getSshConnection().setProxyData(objProxy);
			}
			this.getSshConnection().connect();
			isConnected = true;
			logger.debug(SOSVfs_D_0102.params(strHostName, intPortNo));
		}
		catch (Exception e) {
			try {
				this.setSshConnection(null);
			}
			catch (Exception ex) {
			}
			throw e;
		}

		return this;
	}

	/**
	 * Check existence of a file or directory
	 * 
	 * @param sftpClient
	 * @param filename
	 * @return true, if file exists
	 * @throws Exception
	 */
	protected boolean sshFileExists(final SFTPv3Client psftpClient, final String filename) {

		try {
			SFTPv3FileAttributes attributes = psftpClient.stat(filename);

			if (attributes != null) {
				return attributes.isRegularFile() || attributes.isDirectory();
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if file is a directory
	 * 
	 * @param sftpClient
	 * @param filename
	 * @return true, if filename is a directory
	 */
	protected boolean isDirectory(final SFTPv3Client psftpClient, final String filename) {
		try {
			return psftpClient.stat(filename).isDirectory();
		}
		catch (Exception e) {
		}
		return false;
	}

	/**
	 * Returns the file size of a file
	 * 
	 * @param sftpClient
	 * @param filename
	 * @return the size of the file
	 * @throws Exception
	 */
	protected long getFileSize(final SFTPv3Client psftpClient, final String filename) throws Exception {
		return psftpClient.stat(filename).size.longValue();
	}

	/**
	 * Check existence of a file or directory
	 * 
	 * @param sftpClient
	 * @param filename
	 * @return integer representation of file permissions
	 * @throws Exception
	 */
	protected int sshFilePermissions(final SFTPv3Client psftpClient, final String filename) {

		try {
			SFTPv3FileAttributes attributes = psftpClient.stat(filename);

			if (attributes != null) {
				return attributes.permissions.intValue();
			}
			else {
				return 0;
			}
		}
		catch (Exception e) {
			return 0;
		}
	}

	/**
	 * normalize / to \ and remove trailing slashes from a path
	 * 
	 * @param pstrPathName
	 * @return normalized path
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private String normalizePath(final String pstrPathName) throws Exception {

		String normalizedPath = pstrPathName.replaceAll("\\\\", "/");
		while (normalizedPath.endsWith("\\") || normalizedPath.endsWith("/")) {
			normalizedPath = normalizedPath.substring(0, normalizedPath.length() - 1);
		}

		return normalizedPath;
	}

	/**
	 * @return Returns the sshConnection.
	 */
	protected Connection getSshConnection() {
		return sshConnection;
	}

	protected void setSshConnection(final Connection psshConnection) {

		if (psshConnection == null) {  // null: close connection ???
			isConnected = false;
			if (sftpClient != null) {
				if (this.getFilesToDelete() != null) {
					for (String strFileNameToDelete : vecFilesToDelete) {
						try {
							this.deleteFile(strFileNameToDelete);
							logger.debug(SOSVfs_I_0113.params(strFileNameToDelete));
						}
						catch (Exception e) {
							logger.error(e.getLocalizedMessage());
						}
					}
					vecFilesToDelete = null;
				}
				sftpClient.close();
				sftpClient = null;
				logger.debug(SOSVfs_D_232.params("sftpClient"));
			}

			if (stderrConsumer != null) {
				stderrConsumer.end();
				stderrConsumer = null;
				logger.debug(SOSVfs_D_232.params("stderrConsumer"));
			}

			if (stdoutConsumer != null) {
				stdoutConsumer.end();
				stdoutConsumer = null;
				logger.debug(SOSVfs_D_232.params("stdoutConsumer"));
			}

			if (sshSession != null) {
				sshSession.close();
				sshSession = null;
				logger.debug(SOSVfs_D_232.params("sshSession"));
			}

			if (sshConnection != null) {
				sshConnection.close();
				logger.debug(SOSVfs_D_232.params("sshConnection"));
			}
		}
		sshConnection = psshConnection;
	}

	/**
	 * @return Returns the sshSession.
	 */
	public Session getSshSession() {
		return sshSession;
	}

	/**
	 * @param sshSession The sshSession to set.
	 */
	public void setSshSession(final Session psshSession) {
		sshSession = psshSession;
	}

	/**
	 * \todo
	 * TODO Variable_set mu� hier raus und in den Adapter f�r den JobScheduler
	 * 
	 */
	private Variable_set	params	= null;

	/**
	 * 
	 * \brief setJSParam
	 * 
	 * \details
	 *
	 * \return SOSSSH2SuperClass
	 *
	 * @param pstrKey
	 * @param pstrValue
	 * @return
	 */
	public SOSSSH2TriLeadImpl setJSParam(final String pstrKey, final String pstrValue) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::setJSParam";

		if (params != null) {
			params.set_var(pstrKey, pstrValue);
		}

		return this;
	} // private SOSSSH2SuperClass setJSParam

	public SOSSSH2TriLeadImpl setParameters(final Variable_set pVariableSet) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::setParameters";

		params = pVariableSet;

		return this;
	} // private void setParameters

	/**
	 * 
	 * \brief createCommandScript
	 * 
	 * \details
	 *
	 * \return File
	 *
	 * @param isWindows
	 * @return
	 * @throws Exception
	 */
	@Override
	public String createScriptFile(final String pstrContent) throws Exception {
		try {
			String commandScript = pstrContent;
			logger.info("pstrContent = " + pstrContent);
			if (flgIsRemoteOSWindows == false) {
				commandScript = commandScript.replaceAll("(?m)\r", "");
			}
			logger.info(SOSVfs_I_233.params(pstrContent));
			// TODO solve via callback
			replaceSchedulerVars(flgIsRemoteOSWindows, commandScript);
			// TODO script file prefix as option
			File fleTempScriptFile = File.createTempFile("sos-sshscript", getScriptFileNameSuffix());
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fleTempScriptFile)));
			out.write(commandScript);
			out.flush();
			out.close();
			fleTempScriptFile.deleteOnExit();
			putFile(fleTempScriptFile);
// http://www.sos-berlin.com/jira/browse/JITL-17
			
			String strFileName2Return = fleTempScriptFile.getName();
			if (flgIsRemoteOSWindows == false) {
				strFileName2Return = "./" + strFileName2Return;
			}
			Add2Files2Delete(strFileName2Return);
			logger.info(SOSVfs_I_253.params(fleTempScriptFile.getAbsolutePath()));
			return strFileName2Return;
		}
		catch (Exception e) {
			throw e;
		}
	}

	private void Add2Files2Delete(final String pstrFilenName2Delete) {
		this.getFilesToDelete().add(pstrFilenName2Delete);
		logger.debug(String.format(SOSVfs_D_254.params(pstrFilenName2Delete)));
	}
	/**
	 * 
	 * \brief replaceSchedulerVars
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @param isWindows
	 */
	protected String replaceSchedulerVars(final boolean isWindows, final String pstrString2Modify) {
		String strTemp = pstrString2Modify;
		if (params != null) {
			logger.debug(SOSVfs_D_255.get());
			String[] paramNames = params.names().split(";");
			for (String name : paramNames) {
				SignalDebug(SOSVfs_D_256.params(name));
				String regex = "(?i)";
				/**
				 * \todo
				 * TODO os-abh�ngigkeit besser herstellen als hier gemacht
				 * 
				 * TODO Es ist doch eigentlich viel besser, wenn die Variablen
				 * (zus�tzlich) als Environment-variablen gesetzt werden.
				 */
				if (isWindows) {
					regex += "%SCHEDULER_PARAM_" + name + "%";
				}
				else {
					regex += "\\$\\{?SCHEDULER_PARAM_" + name + "\\}?";
				}
				strTemp = myReplaceAll(strTemp, regex, params.value(name));
			}
		}
		return strTemp;
	}

	/**
	 * 
	 * \brief myReplaceAll
	 * 
	 * \details
	 *
	 * \return String
	 *
	 * @param source
	 * @param what
	 * @param replacement
	 * @return
	 */
	public String myReplaceAll(final String source, final String what, final String replacement) {

		String newReplacement = replacement.replaceAll("\\$", "\\\\\\$");
		return source.replaceAll("(?m)" + what, newReplacement);
	}

	@Override
	public ISOSConnection getConnection() {
		return this;
	}

	@Override
	public ISOSSession getSession() {
		return this;
	}

	@Override
	public ISOSConnection Connect(final ISOSConnectionOptions pobjConnectionOptions) throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Connect";
		objCO = pobjConnectionOptions;
		if (objCO != null) {
			this.Connect();
		}
		return this;
	}

	@Override
	public void CloseConnection() throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::CloseConnection";

		this.setSshConnection(null);
	}

	/**
	 * 
	 * \brief Authenticate
	 * 
	 * \details
	 *
	 * \return 
	 *
	 * @param pobjAO
	 * @return
	 * @throws Exception
	 */
	@Override
	public ISOSConnection Authenticate(final ISOSAuthenticationOptions pobjAO) throws Exception {

		final String conMethodName = conClassName + "::Authenticate";

		objAO = pobjAO;

		if (objAO.getAuth_method().isPublicKey()) {
			/**
			 * \todo
			 * TODO File-Handling in der Option-Klasse abhandeln. Return vom Type JSFile einbauen
			 */
			File authenticationFile = new File(objAO.getAuth_file().Value());
			if (authenticationFile.exists() == false)
				throw new JobSchedulerException(SOSVfs_E_257.params(authenticationFile.getCanonicalPath()));
			if (authenticationFile.canRead() == false)
				throw new JobSchedulerException(SOSVfs_E_258.params(authenticationFile.getCanonicalPath()));

			isAuthenticated = this.getSshConnection().authenticateWithPublicKey(objAO.getUser().Value(), authenticationFile, objAO.getPassword().Value());
		}
		else
			if (objAO.getAuth_method().isPassword()) {
				isAuthenticated = getSshConnection().authenticateWithPassword(objAO.getUser().Value(), objAO.getPassword().Value());
			}

		if (isAuthenticated == false) {
			throw new JobSchedulerException(SOSVfs_E_235.params(conMethodName, objAO.toString()));
		}

		logger.info(SOSVfs_D_133.params(objAO.getUser().Value()));
		return this;
	}

	@Override
	public ISOSVFSHandler getHandler() {
		return this;
	}

	/**
	 * 
	 * \brief remoteIsWindowsShell
	 * 
	 * \details
	 *
	 * \return boolean
	 *
	 * @return
	 */
	@Override
	public boolean remoteIsWindowsShell() {
		Session objSSHSession = null;
		flgIsRemoteOSWindows = false;

		try {
			// TODO the testcommand should be defined by an option
			String checkShellCommand = "echo %ComSpec%";
			logger.debug(SOSVfs_D_236.get());
			objSSHSession = this.getSshConnection().openSession();
			logger.debug(SOSVfs_D_0151.params(checkShellCommand));
			objSSHSession.execCommand(checkShellCommand);

			logger.debug(SOSVfs_D_163.params("stdout", checkShellCommand));
			ipsStdOut = new StreamGobbler(objSSHSession.getStdout());
			ipsStdErr = new StreamGobbler(objSSHSession.getStderr());
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(ipsStdOut));
			String stdOut = "";
			while (true) {
				String line = stdoutReader.readLine();
				if (line == null)
					break;
				logger.debug(line);
				stdOut += line;
			}
			logger.debug(SOSVfs_D_163.params("stderr", checkShellCommand));
			BufferedReader stderrReader = new BufferedReader(new InputStreamReader(ipsStdErr));
			while (true) {
				String line = stderrReader.readLine();
				if (line == null)
					break;
				logger.debug(line);
			}
			// TODO The expected result-string for testing the os should be defined by an option
			if (stdOut.indexOf("cmd.exe") > -1) {
				logger.debug(SOSVfs_D_237.get());
				flgIsRemoteOSWindows = true;
				return true;
			}
		}
		catch (Exception e) {
			logger.debug(SOSVfs_D_239.params(e));
		}
		finally {
			if (objSSHSession != null)
				try {
					objSSHSession.close();
				}
				catch (Exception e) {
					logger.debug(SOSVfs_D_240.params(e));
				}
		}
		return false;
	}

	private String getScriptFileNameSuffix() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::getScriptFileNameSuffix";
		// TODO �ber eine Option steuern
		String strSuffix = flgIsRemoteOSWindows ? ".cmd" : ".sh";

		return strSuffix;
	} // private String getScriptFileNameSuffix

	/**
	 * 
	 * \brief transferCommandScript
	 * 
	 * \details
	 *
	 * \return File
	 *
	 * @param pfleCommandFile
	 * @param isWindows
	 * @return
	 * @throws Exception
	 */
	public void putFile(File pfleCommandFile) throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::putFile";
		String suffix = getScriptFileNameSuffix();
		String strFileName = pfleCommandFile.getName();

		try {
			boolean exists = true;
			// check if File already exists
			while (exists) {
				try {
					FtpClient().stat(strFileName);
				}
				catch (SFTPException e) {
					logger.debug(SOSVfs_E_241.params(e.getServerErrorCode()));
					exists = false;
				}
				if (exists) {
					logger.debug(SOSVfs_D_242.get());
					/**
					 * \todo
					 * TODO Tempfile-NamePrefix variable via options
					 */
					File fleResultFile = File.createTempFile("sos", suffix);
					fleResultFile.delete();
					pfleCommandFile.renameTo(fleResultFile);
					pfleCommandFile = fleResultFile;
				}
			}

			// set execute permissions for owner
			SFTPv3FileHandle fileHandle = this.getFileHandle(strFileName, new Integer(0700));

			FileInputStream fis = null;
			long offset = 0;
			try {
				fis = new FileInputStream(pfleCommandFile);
				// TODO BufferSize as an Option
				byte[] buffer = new byte[1024];
				while (true) {
					int len = fis.read(buffer, 0, buffer.length);
					if (len <= 0)
						break;
					sftpClient.write(fileHandle, offset, buffer, 0, len);
					offset += len;
				}
				fis.close();
				fis = null;
			}
			catch (Exception e) {
				throw e;
			}
			finally {
				if (fis != null)
					try {
						fis.close();
						fis = null;
					}
					catch (Exception ex) {
					}
			}

			logger.debug(SOSVfs_D_243.params(sftpClient.canonicalPath(strFileName)));
			sftpClient.closeFile(fileHandle);

			fileHandle = null;
		}
		catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * \brief getFileHandle
	 * 
	 * \details
	 *
	 * \return SFTPv3FileHandle
	 *
	 * @param pstrFileName
	 * @param pintPermissions
	 * @return
	 * @throws Exception
	 */
	public SFTPv3FileHandle getFileHandle(final String pstrFileName, final Integer pintPermissions) throws Exception {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::setFilePermissions";

		SFTPv3FileAttributes attr = new SFTPv3FileAttributes();
		attr.permissions = pintPermissions;
		SFTPv3FileHandle fileHandle = this.FtpClient().createFileTruncate(pstrFileName, attr);
		return fileHandle;
	} // private void setFilePermissions

	/**
	 * 
	 * \brief setFilePermissions
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @param pstrFileName
	 * @param pintPermissions
	 * @throws Exception
	 */
	public void setFilePermissions(final String pstrFileName, final Integer pintPermissions) throws Exception {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::setFilePermissions";

		SFTPv3FileAttributes attr = new SFTPv3FileAttributes();
		attr.permissions = pintPermissions;
		@SuppressWarnings("unused")
		SFTPv3FileHandle fileHandle = this.FtpClient().createFileTruncate(pstrFileName, attr);
	} // private void setFilePermissions

	/**
	 * 
	 * \brief deleteCommandScript
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @param pstrCommandFile
	 * @throws Exception
	 */
	public void deleteFile(final String pstrCommandFile) throws Exception {
		try {
			if (isNotEmpty(pstrCommandFile)) {
				this.FtpClient().rm(pstrCommandFile);
				logger.debug(SOSVfs_I_0113.params(pstrCommandFile));
			}
		}
		catch (Exception e) {
			logger.error(SOSVfs_E_244.params(e));
			throw new JobSchedulerException(e);
		}
	}

	private SFTPv3Client FtpClient() throws Exception {
		if (sftpClient == null) {
			sftpClient = new SFTPv3Client(this.getSshConnection());
		}
		return sftpClient;
	}

	/**
	 * 
	 * \brief CloseSession
	 * 
	 * \details
	 *
	 * \return 
	 *
	 * @throws Exception
	 */
	@Override
	public void CloseSession() throws Exception {

	}

	/**
	 * 
	 * \brief OpenSession
	 * 
	 * \details
	 *
	 * \return 
	 *
	 * @param pobjShellOptions
	 * @return
	 * @throws Exception
	 */
	@Override
	public ISOSSession OpenSession(final ISOSShellOptions pobjShellOptions) throws Exception {

		objSO = pobjShellOptions;
		if (objSO == null) {
			throw new JobSchedulerException(SOSVfs_E_245.get());
		}

		long loginTimeout = objSO.getSimulate_shell_login_timeout().value();
		String strPromptTrigger = objSO.getSimulate_shell_prompt_trigger().Value();

		this.setSshSession(this.getSshConnection().openSession());
		if (objSO.getSimulate_shell().value() == true) {
			logger.debug(SOSVfs_D_246.params("PTY"));
			this.getSshSession().requestDumbPTY();
			logger.debug(SOSVfs_D_247.params("shell"));
			this.getSshSession().startShell();
			ipsStdOut = getSshSession().getStdout();
			ipsStdErr = getSshSession().getStderr();
			strbStdoutOutput = new StringBuffer();
			strbStderrOutput = new StringBuffer();

			stdoutConsumer = new RemoteConsumer(strbStdoutOutput, true, ipsStdOut);
			stderrConsumer = new RemoteConsumer(strbStderrOutput, false, ipsStdErr);
			stdoutConsumer.start();
			stderrConsumer.start();
			stdin = getSshSession().getStdin();
			stdinWriter = new OutputStreamWriter(stdin);
			logger.debug(SOSVfs_D_248.get());
			boolean loggedIn = false;
			while (!loggedIn) {
				if (lngLastTime > 0) {
					loggedIn = Check4TimeOutOrPrompt(loginTimeout, strPromptTrigger);
				}
			}
		}
		else {
			if (objSO.getIgnore_hangup_signal().value() == false) {
				// TODO Terminaltype must be an option
				sshSession.requestPTY("vt100");
			}
		}

		return this;
	}

	private boolean Check4TimeOutOrPrompt(final long plngLoginTimeOut, final String pstrPromptTrigger) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Check4TimeOutOrPrompt";

		long now = System.currentTimeMillis();
		if (plngLoginTimeOut > 0 && lngLastTime + plngLoginTimeOut < now) {// kommt nichts mehr
			return true;
		}
		
		logger.debug("strCurrentLine=" + strCurrentLine + " pstrPromptTrigger=" + pstrPromptTrigger);
		if (pstrPromptTrigger.length() > 0 && strCurrentLine.indexOf(pstrPromptTrigger) != -1) {
			logger.debug("strCurrentLine=" + strCurrentLine + " pstrPromptTrigger=" + pstrPromptTrigger);
			logger.debug("Found login prompt " + pstrPromptTrigger);
			strCurrentLine = "";
			return true;
		}

		return false;
	} // private boolean Check4TimeOutOrPrompt

	/**
	 * 
	 * \brief ExecuteCommand
	 * 
	 * \details
	 * Executes a command which is given as parameter.
	 * \return 
	 *
	 * @param pstrCmd
	 * @throws Exception
	 */
	@Override
	public void ExecuteCommand(final String pstrCmd) throws Exception {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::ExecuteCommand";

		exitStatus = null;
		exitSignal = null;
		int retval=0;
		String strCmd = pstrCmd;

		long loginTimeout = objSO.getSimulate_shell_login_timeout().value();
		String strPromptTrigger = objSO.getSimulate_shell_prompt_trigger().Value();
		strbStderrOutput = new StringBuffer();
		strbStdoutOutput = new StringBuffer();
	 

		if (objSO.getSimulate_shell().value() == true) {
			
			logger.debug("executing: " + strCmd);
			
			stdinWriter.write(strCmd + strEndOfLine);
			stdinWriter.flush();
			boolean prompt = false;
			while (!prompt) {
				prompt = Check4TimeOutOrPrompt(loginTimeout, strPromptTrigger);
			}
			strCurrentLine = "";
			
			logger.debug(SOSVfs_D_163.params("stdout", strCmd));
			logger.debug(strbStdoutOutput.toString());		
			stdoutConsumer.end();
			stderrConsumer.end();
			exitStatus = 0;
			}
		else { 
			// tempor�r eingebaut um zu pr�fen ob das so mit VMS geht. ur 21.6.2013
			if (flgIsRemoteOSWindows == false && !strCmd.startsWith("@") && !strCmd.startsWith("run ")) {
				strCmd = "echo $$ && " + strCmd;
			}

			this.getSshSession().execCommand(strCmd);

			logger.debug(SOSVfs_D_163.params("stdout", strCmd));
			ipsStdOut = new StreamGobbler(this.getSshSession().getStdout());
			ipsStdErr = new StreamGobbler(this.getSshSession().getStderr());
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(ipsStdOut));
 			while (true) {
				String line = stdoutReader.readLine();
				if (line == null)
					break;
				//oh 2013-10-16: Wenn SignalInfo, dann loggt SSH-Job das Stdout zweimal (hier und in sos.net.ssh.SOSSSHJob2.java:440 (checkStdOut))
				//siehe auch http://www.sos-berlin.com/jira/browse/JITL-66
				//SignalInfo(line);
				logger.debug(line);
				strbStdoutOutput.append(line + strEndOfLine);

			}

			logger.debug(SOSVfs_D_163.params("stderr", strCmd));
			BufferedReader stderrReader = new BufferedReader(new InputStreamReader(ipsStdErr));
			strbStderrOutput = new StringBuffer();
			while (true) {
				String line = stderrReader.readLine();
				if (line == null)
					break;
				logger.debug(line);
				strbStderrOutput.append(line + strEndOfLine);
			}
		
 
		// give the session some time to end
		// TODO waitForCondition as an Option
		@SuppressWarnings("unused")
		int res = getSshSession().waitForCondition(ChannelCondition.EOF, 30 * 1000);
		long timeout = (1 * 10) * 1000;

	    retval = getSshSession().waitForCondition(ChannelCondition.EXIT_STATUS, timeout);
		
		exitStatus = this.getSshSession().getExitStatus();
		
		if ((retval & ChannelCondition.TIMEOUT) != 0 ){
			logger.debug("Timeout reached");
			throw new java.util.concurrent.TimeoutException();
		} else {
			try {
				exitStatus = this.getSshSession().getExitStatus();
			}
			catch (Exception e) {
				logger.info(SOSVfs_I_250.params("exit status"));
			}
		}
		
		
		try {
			exitSignal = this.getSshSession().getExitSignal();
		}
		catch (Exception e) {
			logger.info(SOSVfs_I_250.params("exit signal"));
		}
		}
	}

	@Override
	public Integer getExitCode() {
		if (exitStatus == null) {
		    throw new RuntimeException( "Error reading exit code from SSH-Server. No exit code is available.");
	    }
		return exitStatus;
	}

	@Override
	public String getExitSignal() {
		return exitSignal;
	}

	@Override
	public StringBuffer getStdErr() throws Exception {
		return strbStderrOutput;
	}

	@Override
	public StringBuffer getStdOut() throws Exception {
		return strbStdoutOutput;
	}

	/**
	 * This thread consumes output from the remote server puts it into fields of
	 * the main class
	 */
	class RemoteConsumer extends Thread {
		private final StringBuffer	sbuf;
		private boolean			writeCurrentline	= false;
		private final InputStream		stream;
		boolean					end					= false;

		RemoteConsumer(final StringBuffer buffer, final boolean writeCurr, final InputStream str) {
			sbuf = buffer;
			writeCurrentline = true;
			stream = str;
		}

		/**
		 * 
		 * \brief addText
		 * 
		 * \details
		 *
		 * \return void
		 *
		 * @param data
		 * @param len
		 */
		private void addText(final byte[] data, final int len) {
			lngLastTime = System.currentTimeMillis();
			String outstring = new String(data).substring(0, len);
			logger.debug("--> outstring: " + outstring);
			
			sbuf.append(outstring);
			if (writeCurrentline) {
				int newlineIndex = outstring.indexOf(strEndOfLine);
				if (newlineIndex > -1) {
					String stringAfterNewline = outstring.substring(newlineIndex);
					strCurrentLine = stringAfterNewline;
				}
				else {
					strCurrentLine += outstring;
				}
			}
		}

		/**
		 * 
		 * \brief run
		 * 
		 * \details
		 *
		 * \return 
		 *
		 */
		@Override
		public void run() {
			byte[] buff = new byte[64];

			try {
				logger.debug("run loop startet");
				while (!end) {
					buff = new byte[8];
					int len = stream.read(buff);
					logger.debug("run loop len:" + len);
					if (len == -1)
						return;
					addText(buff, len);
					logger.debug("run loop:" + strCurrentLine);
				} 
				logger.debug("run loop ended");
			}
			catch (Exception e) {
 			}
		}

		/**
		 * 
		 * \brief end
		 * 
		 * \details
		 *
		 * \return void
		 *
		 */
		public synchronized void end() {
			end = true;
		}
	} // RemoteConsumer

	@Override
	public SOSFileList dir(final SOSFolderName pobjFolderName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SOSFileList dir(final String pathname, final int flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISOSVirtualFolder mkdir(final SOSFolderName pobjFolderName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean rmdir(final SOSFolderName pobjFolderName) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setJSJobUtilites(final JSJobUtilities pobjJSJobUtilities) {
		// TODO Auto-generated method stub

	}

	@Override
	public ISOSConnection Connect(final SOSConnection2OptionsAlternate pobjConnectionOptions) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doPostLoginOperations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISOSConnection Connect(final ISOSDataProviderOptions pobjConnectionOptions) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
