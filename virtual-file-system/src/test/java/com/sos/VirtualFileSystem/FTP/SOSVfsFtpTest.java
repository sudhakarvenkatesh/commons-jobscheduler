package com.sos.VirtualFileSystem.FTP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import junit.framework.Assert;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.Logging.Log4JHelper;
import com.sos.JSHelper.io.Files.JSFile;
import com.sos.VirtualFileSystem.Factory.VFSFactory;
import com.sos.VirtualFileSystem.Interfaces.ISOSVFSHandler;
import com.sos.VirtualFileSystem.Interfaces.ISOSVfsFileTransfer;
import com.sos.VirtualFileSystem.Options.SOSFTPOptions;
import com.sos.VirtualFileSystem.common.SOSVfsMessageCodes;
import com.sos.i18n.annotation.I18NResourceBundle;

/**
* \class SOSVfsFtpTest
*
* \brief SOSVfsFtpTest -
*
* \details
*
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
* @version $Id$15.08.2010
* \see reference
*
* Created on 15.08.2010 16:25:08
 */
@I18NResourceBundle(baseName = "SOSVirtualFileSystem", defaultLocale = "en")
public class SOSVfsFtpTest {
	@SuppressWarnings("unused")
	private final String		conClassName			= "SOSVfsFtpTest";
	@SuppressWarnings("unused")
	private static Logger		logger					= Logger.getLogger(SOSVfsFtpTest.class);
	private static Log4JHelper	objLogger				= null;
	private SOSFTPOptions		objOptions				= null;
	private ISOSVFSHandler		objVFS					= null;
	private ISOSVfsFileTransfer	ftpClient				= null;
	@SuppressWarnings("unused")
	private final String		strAPrefix				= "~~";

	private final String		strTestFileName			= "text.txt";
	private final String		strTestPathName			= "R:/nobackup/junittests/testdata/JADE";
	private final String		strKBHome				= "/home/kb/";

	String						constrSettingsTestFile	= strTestPathName + "/SOSDEx-test.ini";

	public SOSVfsFtpTest() {
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
		Log4JHelper.flgUseJobSchedulerLog4JAppender = false;
		objLogger = new Log4JHelper("./log4j.properties"); //$NON-NLS-1$
		objLogger.setLevel(Level.DEBUG);
		objOptions = new SOSFTPOptions();
		// objOptions.protocol.Value(enuTransferTypes.ftps.Text());
		objVFS = VFSFactory.getHandler(objOptions.protocol.Value());
		ftpClient = (ISOSVfsFileTransfer) objVFS;
	}

	@After
	public void tearDown() throws Exception {
	}

	// @Test
	public void testSOSVfsFtp() {
		fail("Not yet implemented");
	}

	@Test
	public void testisIncludeDirective() {
		boolean flgR = objOptions.isIncludeDirective("include");
		Assert.assertTrue("include", flgR);
		flgR = objOptions.isIncludeDirective("source_include");
		Assert.assertTrue("source_include", flgR);
	}

	@Test
	public void testgetIncludePrefix() {
		String strR = objOptions.getIncludePrefix("include");
		Assert.assertEquals("include", "", strR);

		strR = objOptions.getIncludePrefix("source_include");
		Assert.assertEquals("source_include", "source_", strR);

	}

	@Test
	public void testOptionOperation() throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::testOptionOperation";
		HashMap<String, String> objHsh = new HashMap<String, String>();
		objHsh.put("operation", "rename");
		objOptions = new SOSFTPOptions(objHsh);
		assertEquals("", "rename", objOptions.operation.Value());
	} // private void testOptionOperation

	@Test
	public void testHashMapSettings() throws Exception {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::testHashMapSettings";

		HashMap<String, String> objHsh = new HashMap<String, String>();
		objHsh.put("source_host", "wilma.sos");
		objHsh.put("target_host", "tux.sos");
		objOptions = new SOSFTPOptions();
		objOptions.setAllOptions(objHsh);
		assertEquals("", "wilma.sos", objOptions.getConnectionOptions().Source().host.Value());
		assertEquals("", "tux.sos", objOptions.getConnectionOptions().Target().host.Value());

	} // private void testHashMapSettings

	@Test
	public void testIniFile1() throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::CreateIniFile";
		logger.info("*********************************************** " + conMethodName + "******************");

		CreateIniFile();
		objOptions.settings.Value(constrSettingsTestFile);
		objOptions.profile.Value("globals");
		objOptions.ReadSettingsFile();

		Assert.assertEquals("User ID", "kb", objOptions.user.Value());
		Assert.assertEquals("password", "kb", objOptions.password.Value());
	}

	@Test
	public void testIniFileUsingCmdLine() throws Exception {
		final String conMethodName = conClassName + "::CreateIniFile";
		logger.info("*********************************************** " + conMethodName + "******************");
		String[] strCmdLineParameters = new String[] { "-settings=" + constrSettingsTestFile, "-profile=globals" };
		CreateIniFile();

		objOptions.CommandLineArgs(strCmdLineParameters);

		Assert.assertEquals("User ID", "kb", objOptions.user.Value());
		Assert.assertEquals("password", "kb", objOptions.password.Value());
	}

	@Test(expected = com.sos.JSHelper.Exceptions.JobSchedulerException.class)
	public void testIniFile2() throws Exception {
		final String conMethodName = conClassName + "::testIniFile2";

		logger.info("*********************************************** " + conMethodName + "******************");

		CreateIniFile();
		objOptions.settings.Value(constrSettingsTestFile);
		objOptions.profile.Value("include-TestTest");
		objOptions.ReadSettingsFile();

		Assert.assertEquals("User ID", "kb", objOptions.user.Value());
		Assert.assertEquals("password", "kb", objOptions.password.Value());
	}

	@Test
	public void testIniFile3() throws Exception {
		final String conMethodName = conClassName + "::testIniFile2";

		logger.info("*********************************************** " + conMethodName + "******************");

		CreateIniFile();
		objOptions.settings.Value(constrSettingsTestFile);
		objOptions.profile.Value("include-Test");
		objOptions.ReadSettingsFile();

		objOptions.local_dir.Value(".");
		Assert.assertEquals("User ID", "kb", objOptions.user.Value());
		Assert.assertEquals("password", "kb", objOptions.password.Value());
		Assert.assertEquals("Hostname", "hostFromInclude1", objOptions.host.Value());
		Assert.assertEquals("port", 88, objOptions.port.value());
		Assert.assertEquals("protocol", "scp", objOptions.protocol.Value());
		objOptions.CheckMandatory();
	}

	@Test(expected = com.sos.JSHelper.Exceptions.JobSchedulerException.class)
	public void testIniFile4() throws Exception {
		final String conMethodName = conClassName + "::testIniFile4";

		logger.info("*********************************************** " + conMethodName + "******************");

		CreateIniFile();
		objOptions.settings.Value(constrSettingsTestFile);
		objOptions.profile.Value("include-TestWithNonexistenceInclude");
		objOptions.ReadSettingsFile();

		Assert.assertEquals("User ID", "kb", objOptions.user.Value());
		Assert.assertEquals("password", "kb", objOptions.password.Value());
	}

	@Test
	public void testIniFile5() throws Exception {
		final String conMethodName = conClassName + "::testIniFile5";

		logger.info("*********************************************** " + conMethodName + "******************");

		CreateIniFile();
		objOptions.settings.Value(constrSettingsTestFile);
		objOptions.profile.Value("substitute-Test");
		objOptions.ReadSettingsFile();

		String strComputerName = System.getenv("computername");
		Assert.assertEquals("User ID", System.getenv("username"), objOptions.user.Value());
		Assert.assertEquals("Hostname", strComputerName, objOptions.host.Value());
		Assert.assertEquals("Hostnameon Target ", strComputerName + "-abc", objOptions.getConnectionOptions().Target().HostName.Value());
	}

	private void CreateIniFile() throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::CreateIniFile";

		JSFile objIni = new JSFile(constrSettingsTestFile);
		if (objIni.exists() == true) {
			return;
		}

		objIni.WriteLine("[globals]");
		objIni.WriteLine("user=kb");
		objIni.WriteLine("password=kb");

		objIni.WriteLine("[include1]");
		objIni.WriteLine("host=hostFromInclude1");

		objIni.WriteLine("[include2]");
		objIni.WriteLine("port=88");

		objIni.WriteLine("[include3]");
		objIni.WriteLine("protocol=scp");

		objIni.WriteLine("[include-Test]");
		objIni.WriteLine("include=include1,include2,include3");

		objIni.WriteLine("[include-TestWithNonexistenceInclude]");
		objIni.WriteLine("include=include1,includeabcd2,include3");

		objIni.WriteLine("[substitute-Test]");
		objIni.WriteLine("user=${USERNAME}");
		objIni.WriteLine("host=${COMPUTERNAME}");
		objIni.WriteLine("cannotsubstitutet=${waltraut}");
		objIni.WriteLine("target_host=${host}-abc");
		objIni.WriteLine("alternate_target_host=${host}-abc");
	}

	@Test
	// (expected = java.lang.Exception.class)
	public void testConnect() throws Exception {
		objOptions.host.Value("wilma.sos");
		objOptions.user.Value("kbxsy");
		objVFS.Connect(objOptions);
	}

	@Test (expected = com.sos.JSHelper.Exceptions.JobSchedulerException.class)
	public void testConnectFailed() throws Exception {
		objOptions.host.Value("wilmaxxx.sos");
		objVFS.Options(objOptions);
		objVFS.Connect(objOptions);
	}

	@Test (expected = com.sos.JSHelper.Exceptions.JobSchedulerException.class)
	public void testConnectAlternateFailed() throws Exception {
		objOptions.host.Value("wilmaxxx.sos");
		objOptions.alternative_host.Value("kwkwkwk.sos");
		objVFS.Options(objOptions);
		objVFS.Connect(objOptions);
	}

	@Test
	public void testOH() throws Exception {
		objOptions.host.Value("homer.sos");
		objOptions.user.Value("test");
		objOptions.port.value(21);
		objOptions.password.Value("12345");

		SOSVfsFtp ftp = new SOSVfsFtp();
		ftp.Connect(objOptions);
		ftp.Authenticate(objOptions);
		//		String curDir = ftp.DoPWD();
		FTPFile objFTPFile = ftp.getFTPFile(".");
		objFTPFile = ftp.getFTPFile("/home/test/scheduler.current32");
		//		objFTPFile =ftp.getFTPFile("/home/test/scheduler.current32/unknown.txt");
		objFTPFile = ftp.getFTPFile("/home/test/scheduler.current32/licence.txt");
		//		objFTPFile =ftp.getFTPFile("unknown.txt");
		ftp.disconnect();
	}

	@Test
	public void testMkdir() throws Exception {
		testConnect();
		authenticate();
		ftpClient.mkdir("test1");
		ftpClient.rmdir("test1");

		ftpClient.disconnect();
	}

	@Test
	public void testMkdirMultiple() throws Exception {
		testConnect();
		authenticate();
		// ftpClient.rmdir("test1/test2");
		// ftpClient.rmdir("test1");
		ftpClient.mkdir("test1/test2/test3/");
		ftpClient.rmdir("test1/test2/test3/");

		ftpClient.disconnect();

	}

	// // @Test
	public void testRmdirString() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testPassive() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testNListString() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testDoPWD() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testIsNotHiddenFile() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testNListStringBoolean() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testNList() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testNListBoolean() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testDirString() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testDirStringInt() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testDir() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetResponse() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testSize() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGet() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetFileStringString() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetFileStringStringBoolean() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testPut() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testPutFileStringString() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testPutFileStringOutputStream() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testAppendFile() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testAscii() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testBinary() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testCd() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testChangeWorkingDirectory() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testDisconnect() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetReplyString() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testIsConnected() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testListNames() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testLogout() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testRename() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetHandler() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteCommand() throws Exception {
		objOptions.host.Value("8of9.sos");
		objOptions.host.Value("wilma.sos");
		//		objOptions.protocol.Value("ftps");
		//		objOptions.host.Value("localhost");
		objOptions.user.Value("kb");
		objOptions.password.Value("kb");
		objVFS.Connect(objOptions);
		objVFS.Authenticate(objOptions);
		objVFS.ExecuteCommand("SYST");
		objVFS.ExecuteCommand("FEAT");
		objVFS.ExecuteCommand("OPTS");
		objVFS.ExecuteCommand("OPTS UTF8 NLST");
		objVFS.ExecuteCommand("OPTS UTF-8 NLST");
		objVFS.ExecuteCommand("OPTS UTF8 OFF");
		objVFS.ExecuteCommand("OPTS UTF8 ON");
		// OPTS MLST Type;Size;Modify;UNIX.mode;UNIX.owner;UNIX.group;
		objVFS.ExecuteCommand("OPTS MLST Type;Size;Modify;UNIX.mode;UNIX.owner;UNIX.group;");
		objVFS.ExecuteCommand("MLST /B�ttner.dat");
		objVFS.ExecuteCommand("OPTS UTF8 OFF");
		objVFS.ExecuteCommand("MLST /B�ttner.dat");


		objVFS.ExecuteCommand("PORT 127,0,0,1,6,81");
		objVFS.ExecuteCommand("LIST");
		objVFS.ExecuteCommand("MLSD");
	}

	// // @Test
	public void testCreateScriptFile() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetExitCode() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetExitSignal() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuthenticate() throws Exception {
		objOptions.host.Value("wilma.sos");
		objVFS.Connect(objOptions);
		String strR = ftpClient.getReplyString();
		// Der ReplayCode wird immer mit 0A 0D am Ende geliefert. Ignorieren wir das
		assertEquals("Connect message", "220 (vsFTPd 2.0.1)", strR.substring(0, strR.length() - 2));
		objOptions.user.Value("kb");
		objOptions.password.Value("kb");
		objVFS.Authenticate(objOptions);
		strR = ftpClient.getReplyString();
		// char[] bteB = strR.toCharArray();
		// for (int i = 1; i <= bteB.length; i++) {
		// logger.debug(String.format("char %1$d = %2$s", i, (byte) (bteB[i])));
		// }
		assertEquals("Login message", "230 Login successful.", strR.substring(0, strR.length() - 2));
		objVFS.CloseSession();
		strR = ftpClient.getReplyString();
		assertEquals("Login message", "221 Goodbye.", strR.substring(0, strR.length() - 2));
		objVFS.CloseConnection();
	}

	private void authenticate() throws Exception {
		objOptions.host.Value("wilma.sos");
		objVFS.Connect(objOptions);
		String strR = ftpClient.getReplyString();
		// Der ReplayCode wird immer mit 0A 0D am Ende geliefert. Ignorieren wir das
		if (strR != null) {
			assertEquals("Connect message", "220 (vsFTPd 2.0.1)", strR.substring(0, strR.length() - 2));
		}
		objOptions.user.Value("kb");
		objOptions.password.Value("kb");
		objVFS.Authenticate(objOptions);

	}

	// // @Test
	public void testCloseConnection() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testConnect1() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testConnectISOSConnectionOptions() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testConnectStringInt() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testCloseSession() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testOpenSession() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testFileExists() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testTransferMode() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testDeleteFile() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetFileString() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetFilePermissions() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetFileSize() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testIsDirectory() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testPutFileFile() {
		fail("Not yet implemented");
	}

	private void CreateTestFile() {
		JSFile objFile = new JSFile(strTestPathName + strTestFileName);
		objFile.deleteOnExit();
		try {
			objFile.WriteLine("Das ist eine Testdatei. Weiter nichts");
			objFile.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPutFileString() throws Exception {
		CreateTestFile();
		objOptions.host.Value("wilma.sos");
		objVFS.Connect(objOptions);
		String strR = ftpClient.getReplyString();
		// Der ReplyCode wird immer mit 0A 0D am Ende geliefert. Ignorieren wir das
		assertEquals("Connect message", "220 (vsFTPd 2.0.1)", strR.substring(0, strR.length() - 2));
		objOptions.user.Value("kb");
		objOptions.password.Value("kb");
		objVFS.Authenticate(objOptions);
		strR = ftpClient.getReplyString();
		assertEquals("Login message", "230 Login successful.", strR.substring(0, strR.length() - 2));
		// objOptions.file_path.Value("c:/temp/test.txt");
		ftpClient.putFile(strTestPathName + strTestFileName, strTestFileName);
		objVFS.CloseSession();
		strR = ftpClient.getReplyString();
		assertEquals("Login message", "221 Goodbye.", strR.substring(0, strR.length() - 2));
		objVFS.CloseConnection();
	}

	// // @Test
	public void testSetFilePermissions() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testMkdirSOSFolderName() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testRmdirSOSFolderName() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetConnection() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetSession() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testDirSOSFolderName() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetStdErr() {
		fail("Not yet implemented");
	}

	// // @Test
	public void testGetStdOut() {
		fail("Not yet implemented");
	}

	// @Test
	public void testRemoteIsWindowsShell() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInputStream() {
	}

	@Test
	public void MessageTest() {
		String strM = SOSVfsMessageCodes.SOSVfs_E_0010.get();
		logger.debug(strM);
		// objMsg.setLocale(Locale.ENGLISH);
		objOptions.Locale.setLocale(Locale.ENGLISH);
		strM = SOSVfsMessageCodes.SOSVfs_E_0010.get();
		logger.debug(strM);
		objOptions.Locale.setLocale(Locale.GERMAN);
		strM = SOSVfsMessageCodes.SOSVfs_E_0010.get();
		logger.debug(strM);
		objOptions.Locale.setLocale(Locale.FRENCH);
		strM = SOSVfsMessageCodes.SOSVfs_E_0010.get();
		logger.debug(strM);
	}
}