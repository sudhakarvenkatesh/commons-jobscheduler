package com.sos.VirtualFileSystem.TransferHistoryExport;

import java.util.Date;

import com.sos.VirtualFileSystem.Interfaces.IJadeTransferHistoryData;

/**
* \class JadeTransferHistoryExportDataTest 
* 
* \brief JadeTransferHistoryExportDataTest - 
* 
* \details
*
* \section JadeTransferHistoryExportDataTest.java_intro_sec Introduction
*
* \section JadeTransferHistoryExportDataTest.java_samples Some Samples
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
* \author Uwe Risse
* \version 20.10.2011
* \see reference
*
* Created on 20.10.2011 11:55:47
 */

public class JadeTransferHistoryExportDataTest implements IJadeTransferHistoryData {

	@SuppressWarnings("unused")
	private final String	conClassName	= "JadeTransferHistoryExportDataTest";

	public JadeTransferHistoryExportDataTest() {
		//
	}

	@Override
	public Integer getTransferId() {
		// TODO Auto-generated method stub
		return 1;
	}


	@Override
	public String getMandator() {
		return "mandator";
	}

	@Override
	public Long getFileSize() {
		return new Long(2);
	}

	@Override
	public String getSourceHost() {
		return "host";
	}
	@Override
	public String getSourceHostIp() {
		return "hostIP";
	}

	@Override
	public String getSourceUser() {
		return "SourceUser";
	}

	@Override
	public String getSourceDir() {
		return "sourceDir";
	}

	@Override
	public String getTargetHost() {
		return "TargetDir";
	}


	@Override
	public String getTargetHostIp() {
		return "TargetHostIp";
	}


	@Override
	public String getTargetUser() {
		return "TargetUser";
	}

	@Override
	public String getTargetDir() {
		return "TargetDir";
	}


	@Override
	public Integer getProtocolType() {
		return 3;
	}
	@Override
	public Integer getPort() {
		return 4;
	}

	@Override
	public Integer getFilesCount() {
		return 5;
	}


	@Override
	public String getProfileName() {
		return "ProfileName";
	}

	
	@Override
	public String getProfile() {
		return "Profile";
	}

	
	@Override
	public Integer getCommandType() {
		return 6;
	}

	
	@Override
	public String getCommand() {
		return "command";
	}

	@Override
	public String getLog() {
		return "log";
	}


	@Override
	public Integer getStatus() {
		return 7;
	}

	@Override
	public String getLastErrorMessage() {
		return "lastErrorMessage";
	}


	@Override
	public Date getStartTime() {
		return new Date();
	}

	@Override
	public Date getEndTime() {
		return new Date();
	}

	@Override
	public String getStatusText() {
		return "statusText";
	}

	@Override
	public String getModifiedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCreatedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getCreated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getModified() {
		// TODO Auto-generated method stub
		return null;
	}



}
