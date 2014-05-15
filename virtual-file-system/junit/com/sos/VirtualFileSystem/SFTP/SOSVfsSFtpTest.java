package com.sos.VirtualFileSystem.SFTP;

import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.Basics.JSToolBox;
import com.sos.JSHelper.Logging.Log4JHelper;
import com.sos.JSHelper.Options.SOSOptionJadeOperation.enuJadeOperations;
import com.sos.JSHelper.Options.SOSOptionPortNumber;
import com.sos.VirtualFileSystem.FTP.SOSVfsFtpTest;
import com.sos.VirtualFileSystem.Factory.VFSFactory;
import com.sos.VirtualFileSystem.Interfaces.ISOSVFSHandler;
import com.sos.VirtualFileSystem.Interfaces.ISOSVfsFileTransfer;
import com.sos.VirtualFileSystem.Options.SOSConnection2OptionsAlternate;
import com.sos.VirtualFileSystem.Options.SOSFTPOptions;

/**
* \class SOSVfsSFtpTest
*
* \brief SOSVfsSFtpTest -
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
* @version $Id$10.11.2010
* \see reference
*
* Created on 10.11.2010 15:34:42
 */

public class SOSVfsSFtpTest extends JSToolBox {

	@SuppressWarnings("unused")
	private final String			conClassName			= "SOSVfsSFtpTest";

	@SuppressWarnings("unused")
	protected static Logger			logger					= Logger.getLogger(SOSVfsFtpTest.class);
	protected static Log4JHelper	objLogger				= null;
	protected SOSFTPOptions			objOptions				= null;

	protected ISOSVFSHandler		objVFS					= null;
	protected ISOSVfsFileTransfer	ftpClient				= null;

	// siehe setUp
	protected String				dynamicClassNameSource	= null;
	protected String				dynamicClassNameTarget	= null;

	protected final String			LOCAL_BASE_PATH			= "R:\\nobackup\\junittests\\testdata\\SFTP\\";
	protected final String			REMOTE_BASE_PATH		= "/home/kb/";

	public SOSVfsSFtpTest() {
		//
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		objLogger = new Log4JHelper("./log4j.properties"); //$NON-NLS-1$
		objLogger.setLevel(Level.DEBUG);

		objOptions = new SOSFTPOptions();
		objOptions.protocol.Value("sftp");
		objVFS = VFSFactory.getHandler(objOptions.protocol.Value());
		ftpClient = (ISOSVfsFileTransfer) objVFS;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConnect() throws Exception {

		objOptions.host.Value("wilma.sos");
		objOptions.port.value(SOSOptionPortNumber.getStandardSFTPPort());

		// objOptions.local_dir.Value("/temp");
		SOSConnection2OptionsAlternate objSource = objOptions.getConnectionOptions().Source();
		setDynamicClassNameSource(objSource);
		objSource.host.Value("wilma.sos");
		objSource.port.value(SOSOptionPortNumber.getStandardSFTPPort());
		objSource.user.Value("kb");
		objSource.protocol.Value("sftp");
		objSource.ssh_auth_method.isPassword(true);

		objOptions.operation.Value("send");
		// objOptions.CheckMandatory();
//		VFSFactory.setConnectionOptions(objSource);
		objVFS = VFSFactory.getHandler(objOptions.protocol.Value());
		ftpClient = (ISOSVfsFileTransfer) objVFS;

		objVFS.Connect(objOptions.getConnectionOptions().Source());

		// ftpClient.disconnect();
	}

	private void setDynamicClassNameSource(final SOSConnection2OptionsAlternate objSource) {
		if (isNotEmpty(dynamicClassNameSource)) {
			objSource.loadClassName.Value(dynamicClassNameSource);
		}
	}

	private void setDynamicClassNameTarget(final SOSConnection2OptionsAlternate objTarget) {
		if (isNotEmpty(dynamicClassNameTarget)) {
			objTarget.loadClassName.Value(dynamicClassNameTarget);
		}
	}

	@Test
	public void testConnectOpenSSH() throws Exception {
		objOptions.host.Value("wilma.sos");
		objOptions.port.value(SOSOptionPortNumber.getStandardSFTPPort());
		// objOptions.local_dir.Value("/temp");
		SOSConnection2OptionsAlternate objSource = objOptions.getConnectionOptions().Source();
		setDynamicClassNameSource(objSource);
		objSource.host.Value("wilma.sos");
		objSource.port.value(SOSOptionPortNumber.getStandardSFTPPort());
		// objSource.user.Value("kb");
		objSource.protocol.Value("sftp");
		objSource.ssh_auth_method.isPassword(false);
		// objSource.ssh_auth_file.Value("C:/Users/KB/kb-ssh2-rsa.ppk");
		// objSource.ssh_auth_file.Value("C:/Users/KB/golf-spielen-privkey.ppk");

		objOptions.operation.Value("send");
		// objOptions.CheckMandatory();
//		VFSFactory.setConnectionOptions(objSource);
		objVFS = VFSFactory.getHandler(objOptions.protocol.Value());
		ftpClient = (ISOSVfsFileTransfer) objVFS;
		objVFS.Connect(objOptions.getConnectionOptions().Source());
		// ftpClient.disconnect();
	}

	private void connect() throws RuntimeException, Exception {
		objOptions.host.Value("wilma.sos");
		objOptions.port.value(SOSOptionPortNumber.getStandardSFTPPort());
		// objOptions.local_dir.Value("/temp");
		SOSConnection2OptionsAlternate objSource = objOptions.getConnectionOptions().Source();
		setDynamicClassNameSource(objSource);
		objSource.host.Value("wilma.sos");
		objSource.port.value(SOSOptionPortNumber.getStandardSFTPPort());
		objSource.user.Value("kb");
		objSource.protocol.Value("sftp");
		objSource.ssh_auth_method.isPassword(true);

		objOptions.operation.Value("send");
//		VFSFactory.setConnectionOptions(objSource);
		objVFS = VFSFactory.getHandler(objOptions.protocol.Value());
		ftpClient = (ISOSVfsFileTransfer) objVFS;
		objVFS.Connect(objOptions.getConnectionOptions().Source());

	}

	@Test(expected = com.sos.JSHelper.Exceptions.JobSchedulerException.class)
	public void testConnectWithWrongPortNumber() throws Exception {
		objOptions.host.Value("wilma.sos");
		objOptions.port.value(45678);
		// objOptions.local_dir.Value("/temp");
		SOSConnection2OptionsAlternate objSource = objOptions.getConnectionOptions().Source();
		setDynamicClassNameSource(objSource);
		objSource.host.Value("wilma.sos");
		objSource.port.value(45678);
		objSource.user.Value("kb");
		objSource.protocol.Value("sftp");
		objSource.ssh_auth_method.isPassword(true);

		objOptions.operation.Value(enuJadeOperations.send);
		// objOptions.CheckMandatory();
//		VFSFactory.setConnectionOptions(objSource);
		objVFS = VFSFactory.getHandler(objOptions.protocol.Value());
		ftpClient = (ISOSVfsFileTransfer) objVFS;
		objVFS.Connect(objOptions.getConnectionOptions().Source());
		// ftpClient.disconnect();
	}

	@Test
	public void testAuthenticate() throws Exception {
		testConnect();
		authenticate();
		ftpClient.disconnect();
	}

	private void authenticate() throws Exception {
		SOSConnection2OptionsAlternate objSource = objOptions.getConnectionOptions().Source();
		objSource.host.Value("wilma.sos");
		objSource.port.value(SOSOptionPortNumber.getStandardSFTPPort());
		objSource.user.Value("kb");
		objSource.password.Value("kb");
		objSource.protocol.Value("sftp");
		objSource.ssh_auth_method.isPassword(true);
		// objSource.ssh_auth_file.Value("C:/Users/KB/kb-ssh2-rsa.ppk");
		// objSource.ssh_auth_file.Value("C:\\Users\\KB\\golf-spielen-openssh.id_dsa");

		objVFS.Authenticate(objSource);
	}

	@Test
	public void testMkdir() throws Exception {
		connect();
		authenticate();

		ftpClient.mkdir("test1");
		ftpClient.rmdir("test1");

		ftpClient.disconnect();
	}

	@Test
	public void testMkdirMultiple() throws Exception {
		connect();
		authenticate();
		// ftpClient.rmdir("test1/test2");
		// ftpClient.rmdir("test1");
		ftpClient.mkdir("test1/test2/test3/");
		ftpClient.rmdir("test1/test2/test3/");

		ftpClient.disconnect();

	}

	@Test
	public void testRmdirString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testNListString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testIsNotHiddenFile() {
		// fail("Not yet implemented");
	}

	@Test
	public void testNListStringBoolean() {
		// fail("Not yet implemented");
	}

	@Test
	public void testNList() {
		// fail("Not yet implemented");
	}

	@Test
	public void testNListBoolean() throws Exception {
		connect();
		authenticate();

		ftpClient.changeWorkingDirectory("/home/re/Documents");

		Vector<String> v = ftpClient.nList(true);
		for(String item:v){
			System.out.println("item = "+item);
		}

		ftpClient.disconnect();
	}

	@Test
	public void testDirString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testDirStringInt() {
		// fail("Not yet implemented");
	}

	@Test
	public void testListNames() {
		// fail("Not yet implemented");
	}

	@Test
	public void testDir() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetResponse() {
		// fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetFileStringStringBoolean() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetFileStringString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testPut() {
		// fail("Not yet implemented");
	}

	@Test
	public void testPutFileStringString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testPutFileStringOutputStream() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetClient() {
		// fail("Not yet implemented");
	}

	@Test
	public void testAppendFile() {
		// fail("Not yet implemented");
	}

	@Test
	public void testCd() throws Exception {
		connect();
		authenticate();

		ftpClient.changeWorkingDirectory("/home/re");

		ftpClient.disconnect();
	}

	@Test
	public void testChangeWorkingDirectory() throws Exception {
		testCd();
	}

	@Test
	public void testDelete() throws Exception {
		connect();
		authenticate();

		ftpClient.put(LOCAL_BASE_PATH + "sos-net-src.zip", REMOTE_BASE_PATH + "tmp123.zip");
		ftpClient.delete(REMOTE_BASE_PATH + "tmp123.zip");

		ftpClient.disconnect();
	}

	@Test
	public void testLogin() throws Exception {
		connect();

		ftpClient.login("kb", "kb");

		ftpClient.disconnect();
	}

	@Test
	public void testDisconnect() throws Exception {
		connect();

		ftpClient.login("kb", "kb");

		ftpClient.disconnect();
	}

	@Test
	public void testGetReplyString() throws Exception {
		connect();

		ftpClient.login("kb", "kb");

		logger.info("Replay = " + ftpClient.getReplyString());

		ftpClient.disconnect();
	}

	@Test
	public void testIsConnected() throws Exception {
		connect();

		// ftpClient.login("kb","kb");

		logger.debug("IS CONNECTED = " + ftpClient.isConnected());

		ftpClient.disconnect();
	}

	@Test
	public void testLogout() throws Exception {
		connect();

		ftpClient.login("kb", "kb");

		ftpClient.logout();

		// ftpClient.disconnect();
	}

	@Test
	public void testRename() throws Exception {
		connect();
		authenticate();

		ftpClient.put(LOCAL_BASE_PATH + "sos-net-src.zip", REMOTE_BASE_PATH + "tmp123.zip");
		ftpClient.rename(REMOTE_BASE_PATH + "tmp123.zip", REMOTE_BASE_PATH + "tmp1234.zip");
		ftpClient.delete(REMOTE_BASE_PATH + "tmp1234.zip");

		ftpClient.disconnect();
	}

	@Test
	public void testGetHandler() throws Exception {
		connect();
		authenticate();

		logger.debug("HANDLER = " + ftpClient.getHandler());

		ftpClient.disconnect();
	}

	@Test
	public void testExecuteCommand() throws Exception {
		connect();
		authenticate();

		String lineSeparator = "\n";

		objVFS.ExecuteCommand("cd /home/test" + lineSeparator + "cd /home/kb");

		ftpClient.disconnect();
	}

	@Test
	public void testCreateScriptFile() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetExitCode() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetExitSignal() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testCloseConnection() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testConnect1() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testConnectSOSConnection2OptionsAlternate() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testConnectISOSConnectionOptions() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testConnectStringInt() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testCloseSession() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testOpenSession() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testTransferMode() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetNewVirtualFile() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testMkdirSOSFolderName() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testRmdirSOSFolderName() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetConnection() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetSession() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testDirSOSFolderName() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetStdErr() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetStdOut() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testRemoteIsWindowsShell() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testSetJSJobUtilites() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetFileHandle() throws Exception {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetFilelist() throws Exception {
		connect();
		authenticate();

		String[] result = ftpClient.getFilelist(REMOTE_BASE_PATH, "", 0, false);
		for (String element : result) {
			logger.info(element);
		}

		ftpClient.disconnect();
	}
}
