package com.sos.VirtualFileSystem.TransferHistoryExport;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.Logging.Log4JHelper;
import com.sos.JSHelper.io.Files.JSFile;
import com.sos.VirtualFileSystem.Options.SOSFTPOptions;

/**
* \class SOSVfsXmlExportTest 
* 
* \brief SOSVfsXmlExportTest - 
* 
* \details
*
* \section SOSVfsXmlExportTest.java_intro_sec Introduction
*
* \section SOSVfsXmlExportTest.java_samples Some Samples
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
* \author oh
* @version $Id$
* \see reference
*
* Created on 18.10.2011 15:53:22
 */
public class SOSVfsXmlExportTest {
	@SuppressWarnings("unused")
	private final String		conClassName	= "SOSVfsXmlExportTest";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(SOSVfsXmlExportTest.class);
	private static Log4JHelper	objLogger		= null;
	
	private SOSVfsXmlExport objExp = null;
	private SOSFTPOptions objO = null;
	private JadeTransferDetailHistoryExportDataTest objJadeDetail = null; 
	private JadeTransferHistoryExportDataTest objJade = null;
	private JSFile objF = null;

	public SOSVfsXmlExportTest() {
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
		objLogger = new Log4JHelper("./log4j.properties");
		objLogger.setLevel(Level.DEBUG);
		objExp = new SOSVfsXmlExport();
		objO = new SOSFTPOptions();
		objJadeDetail = new JadeTransferDetailHistoryExportDataTest();
		objJade = new JadeTransferHistoryExportDataTest();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	public void close() {
		objF = new JSFile(objExp.getFileName());
		objExp.close();
		logger.debug(objF.getContent());
	}

//	@Test
//	public final void testDoExportDetail() {
//		objExp.setData(objO);
//		objExp.doExportDetail();
//		JSFile objF = new JSFile(objExp.getFileName());
//		objExp.close();
//		
//		logger.debug(objF.getContent());
//	}
//
//	@Test
//	public final void testDoExportDetail2 () throws Exception {
//		
//		HashMap<String, String> objHsh = new HashMap<String, String>();
//
//        objHsh.put("ftp_host"                , "wilma");
//        objHsh.put("ftp_port"                , "21");
//        objHsh.put("ftp_user"                , "test");
//        objHsh.put("ftp_password"            , "12345");
//        objHsh.put("ftp_transfer_mode"       , "binary");
//        objHsh.put("ftp_passive_mode"        , "0");
//        objHsh.put("ftp_local_dir"           , "//tuvix/j/scheduler.test/testsuite_files/files/ftp_in/sosdex");
//        objHsh.put("ftp_file_spec"           , "^renamed_");
//        objHsh.put("ftp_remote_dir"          , "/home/test/temp/test/sosdex");
//        objHsh.put("operation"               , "receive");
//        objHsh.put("replacing"               , "^renamed_");
//        objHsh.put("replacement"             , "" );
//
//		objO = new SOSFTPOptions();
//		objO.setAllOptions(objO.DeletePrefix(objHsh, "ftp_"));
//		objO.CheckMandatory();
//		testDoExportDetail();
//	}
	
	@Test
	public final void testDoExportDetail () throws Exception {
		objExp.setData(objO);
		objExp.setJadeTransferData(objJade);
		objExp.doTransferSummary();
		for(int i=0; i < 2; i++) {
			objExp.setJadeTransferDetailData(objJadeDetail);
			objExp.doTransferDetail();
		}
		close();
	}
	
	@Test
	public final void testDoExportSummary() {
		objExp.setData(objO);
		objExp.setJadeTransferData(objJade);
		objExp.doTransferSummary();
		close();
	}

	@Test
	public final void testSetData() {
//		fail("Not yet implemented"); // TODO
	}
}