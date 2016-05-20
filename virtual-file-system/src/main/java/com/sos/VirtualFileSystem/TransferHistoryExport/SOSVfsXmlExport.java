package com.sos.VirtualFileSystem.TransferHistoryExport;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.io.Files.JSXMLFile;
import com.sos.VirtualFileSystem.Interfaces.IJadeTransferDetailHistoryData;
import com.sos.VirtualFileSystem.Interfaces.IJadeTransferHistoryData;
import com.sos.VirtualFileSystem.Interfaces.ISOSTransferHistory;
import com.sos.VirtualFileSystem.Options.SOSFTPOptions;
import com.sos.VirtualFileSystem.common.SOSVfsMessageCodes;

/** \class SOSVfsXmlExport
 * 
 * \brief SOSVfsXmlExport -
 * 
 * \details
 *
 * \section SOSVfsXmlExport.java_intro_sec Introduction
 *
 * \section SOSVfsXmlExport.java_samples Some Samples
 *
 * \code .... code goes here ... \endcode
 *
 * <p style="text-align:center">
 * <br />
 * --------------------------------------------------------------------------- <br />
 * APL/Software GmbH - Berlin <br />
 * ##### generated by ClaviusXPress (http://www.sos-berlin.com) ######### <br />
 * ---------------------------------------------------------------------------
 * </p>
 * \author oh
 * 
 * @version $Id$ \see reference
 *
 *          Created on 18.10.2011 13:51:13 */
/** @author oh */
public class SOSVfsXmlExport extends SOSVfsMessageCodes implements ISOSTransferHistory {

    private final String conClassName = "SOSVfsXmlExport";
    @SuppressWarnings("unused")
    private static final String conSVNVersion = "$Id$";
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(SOSVfsXmlExport.class);
    private SOSFTPOptions objOptions = null;
    private JSXMLFile objXMLFile = null;
    private final String rootElementName = "transfer_history";
    private final String exportElementName = "summary";
    private final String exportDetailsElementName = "items";
    private IJadeTransferHistoryData jadeTransferExportData = null;
    private IJadeTransferDetailHistoryData jadeTransferDetailExportData = null;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SOSVfsXmlExport() {
        //
    }

    /** \brief SOSVfsXmlExport
     *
     * \details
     *
     * @param pstrResourceBundleName */
    public SOSVfsXmlExport(final String pstrResourceBundleName) {
        super(pstrResourceBundleName);
    }

    // /**
    // * \brief doExportDetail
    // *
    // * \details
    // *
    // * \return
    // *
    // */
    // @Override
    // public void doExportDetail() {
    // final String conMethodName = conClassName + "::doExportDetail";
    // if (objXMLFile == null) {
    // try {
    // String strXMLFileName = getXMLFileName();
    // objXMLFile = new JSXMLFile(strXMLFileName);
    // if (isOpened == false) {
    // objXMLFile.writeXMLDeclaration().newTag(rootElementName);
    // isOpened = true;
    // }
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // throw new JobSchedulerException("Error in " + conMethodName, e);
    // }
    // }
    //
    // // private String historyFields =
    // "guid;mandator;transfer_timestamp;pid;ppid;operation;
    // //
    // localhost;localhost_ip;local_user;remote_host;remote_host_ip;remote_user;
    // //
    // protocol;port;local_dir;remote_dir;local_filename;remote_filename;file_size;md5;status;last_error_message;log_filename";
    //
    // try {
    // SOSOptionElement.gflgCreateShortXML = true;
    // objXMLFile.newTag("items");
    // objXMLFile.addAttribute("name", "guid").addAttribute("value",
    // "").newTag("item", ""); //TODO
    // objXMLFile.Write(objOptions.mandator.toXml()); // 0-
    // objXMLFile.addAttribute("name",
    // "transfer_timestamp").addAttribute("value", "").newTag("item", "");
    // //TODO
    // objXMLFile.addAttribute("name", "pid").addAttribute("value",
    // objOptions.getPid()).newTag("item", "");
    // objXMLFile.addAttribute("name", "ppid").addAttribute("value",
    // "").newTag("item", ""); //TODO
    // objXMLFile.Write(objOptions.operation.toXml()); // 4- operation:
    // send|receive
    // SOSConnection2OptionsAlternate objS =
    // objOptions.getConnectionOptions().Source();
    // objXMLFile.Write(objS.host.toXml("local_host")); // 5- local host
    // objXMLFile.Write(objOptions.UserName.toXml()); // 6- local user
    // SOSConnection2OptionsAlternate objT =
    // objOptions.getConnectionOptions().Target();
    // objXMLFile.Write(objT.host.toXml("remote_host")); // 7- remote host
    // objXMLFile.Write(objT.user.toXml("remote_user")); // 8- remote host user
    // objXMLFile.Write(objT.protocol.toXml()); // 9- protocol
    // objXMLFile.Write(objT.port.toXml()); // 10- port
    // objXMLFile.Write(objOptions.local_dir.toXml()); // 10- port
    // objXMLFile.Write(objOptions.log_filename.toXml());
    // objXMLFile.endTag("items");
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // throw new JobSchedulerException("Error in " + conMethodName, e);
    // }
    // }
    /** \brief doExportDetail
     * 
     * \details
     *
     * \return */
    @Override
    public void doTransferDetail() {
        final String conMethodName = conClassName + "::doExportDetail";
        if (isNull(jadeTransferDetailExportData)) {
            throw new JobSchedulerException(SOSVfs_E_261.params(conMethodName));
        }
        open();
        try {
            if (isNotEmpty(exportDetailsElementName)) {
                objXMLFile.newTag(exportDetailsElementName);
            }
            writeItem("transfer_details_id", jadeTransferDetailExportData.getTransferDetailsId());
            writeItem("transfer_id", jadeTransferDetailExportData.getTransferId());
            writeItem("file_size", jadeTransferDetailExportData.getFileSize());
            writeItem("command_type", jadeTransferDetailExportData.getCommandType());
            writeItem("command", jadeTransferDetailExportData.getCommand());
            writeItem("pid", jadeTransferDetailExportData.getPid());
            writeItem("last_error_message", jadeTransferDetailExportData.getLastErrorMessage());
            writeItem("target_filename", jadeTransferDetailExportData.getTargetFilename());
            writeItem("source_filename", jadeTransferDetailExportData.getSourceFilename());
            writeItem("md5", jadeTransferDetailExportData.getMd5());
            writeItem("status", jadeTransferDetailExportData.getStatus());
            writeItem("status_text", jadeTransferDetailExportData.getStatusText());
            writeItem("start_time", jadeTransferDetailExportData.getStartTime());
            writeItem("end_time", jadeTransferDetailExportData.getEndTime());
            writeItem("modified_by", jadeTransferDetailExportData.getModifiedBy());
            writeItem("modified", jadeTransferDetailExportData.getModified());
            writeItem("created_by", jadeTransferDetailExportData.getCreatedBy());
            writeItem("created", jadeTransferDetailExportData.getCreated());
            writeItem("size_value", jadeTransferDetailExportData.getSizeValue());
            if (isNotEmpty(exportDetailsElementName)) {
                objXMLFile.endTag(exportDetailsElementName);
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new JobSchedulerException(SOSVfs_E_260.params(conMethodName), e);
        }
    }

    /** \brief doExportSummary
     * 
     * \details
     *
     * \return */
    @Override
    public void doTransferSummary() {
        final String conMethodName = conClassName + "::doExportSummary";
        if (isNull(jadeTransferExportData)) {
            throw new JobSchedulerException(SOSVfs_E_261.params(conMethodName));
        }
        open();
        try {
            if (isNotEmpty(exportElementName)) {
                objXMLFile.newTag(exportElementName);
            }
            writeItem("transfer_id", jadeTransferExportData.getTransferId());
            writeItem("mandator", jadeTransferExportData.getMandator());
            writeItem("file_size", jadeTransferExportData.getFileSize());
            writeItem("source_host", jadeTransferExportData.getSourceHost());
            writeItem("source_host_ip", jadeTransferExportData.getSourceHostIp());
            writeItem("source_user", jadeTransferExportData.getSourceUser());
            writeItem("source_dir", jadeTransferExportData.getSourceDir());
            writeItem("target_host", jadeTransferExportData.getTargetHost());
            writeItem("target_host_ip", jadeTransferExportData.getTargetHostIp());
            writeItem("target_user", jadeTransferExportData.getTargetUser());
            writeItem("target_dir", jadeTransferExportData.getTargetDir());
            writeItem("protocol_type", jadeTransferExportData.getProtocolType());
            writeItem("port", jadeTransferExportData.getPort().toString());
            writeItem("files_count", jadeTransferExportData.getFilesCount());
            writeItem("profile_name", jadeTransferExportData.getProfileName());
            writeItem("profile", jadeTransferExportData.getProfile());
            writeItem("command_type", jadeTransferExportData.getCommandType());
            writeItem("command", jadeTransferExportData.getCommand());
            writeItem("log", jadeTransferExportData.getLog());
            writeItem("status", jadeTransferExportData.getStatus());
            writeItem("status_text", jadeTransferExportData.getStatusText());
            writeItem("start_time", jadeTransferExportData.getStartTime());
            writeItem("end_time", jadeTransferExportData.getEndTime());
            writeItem("last_error_message", jadeTransferExportData.getLastErrorMessage());
            if (isNotEmpty(exportElementName)) {
                objXMLFile.endTag(exportElementName);
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new JobSchedulerException(SOSVfs_E_260.params(conMethodName), e);
        }
    }

    /** \brief close
     * 
     * \details
     *
     * \return
     * 
     * @throws Exception */
    @Override
    public void close() {
        final String conMethodName = conClassName + "::close";
        if (isNotNull(objXMLFile)) {
            try {
                if (objXMLFile.isOpened() == true) {
                    objXMLFile.endTag(rootElementName);
                    objXMLFile.close();
                }
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                throw new JobSchedulerException(SOSVfs_E_260.params(conMethodName), e);
            }
            objXMLFile = null;
        }
    }

    @Override
    public String getFileName() {
        if (isNotNull(objXMLFile)) {
            return objXMLFile.getAbsolutePath();
        } else {
            return null;
        }
    }

    @Override
    public void setData(final SOSFTPOptions pobjOptions) {
        objOptions = pobjOptions;
    }

    @Override
    public void setJadeTransferData(final IJadeTransferHistoryData jadeTransferHistoryExportData) {
        jadeTransferExportData = jadeTransferHistoryExportData;
    }

    @Override
    public void setJadeTransferDetailData(final IJadeTransferDetailHistoryData jadeTransferDetailHistoryExportData) {
        jadeTransferDetailExportData = jadeTransferDetailHistoryExportData;
    }

    /** \brief writeItem
     * 
     * \details
     *
     * @throws Exception */
    private void writeItem(final String name, final String value) throws Exception {
        if (isNotNull(value)) {
            objXMLFile.addAttribute("name", name).addAttribute("value", value).newTag("item", "");
        }
    }

    private void writeItem(final String name, final Integer value) throws Exception {
        if (isNotNull(value)) {
            writeItem(name, value.toString());
        }
    }

    private void writeItem(final String name, final Long value) throws Exception {
        if (isNotNull(value)) {
            writeItem(name, value.toString());
        }
    }

    private void writeItem(final String name, final Date value) throws Exception {
        if (isNotNull(value)) {
            writeItem(name, dateFormatter.format(value));
        }
    }

    /** \brief getXMLFileName
     * 
     * \details
     *
     * \return String - filename of xml file in which the transfer history will
     * be written */
    private String getXMLFileName() {
        String sectionName = null;
        if (isNotNull(jadeTransferExportData)) {
            sectionName = jadeTransferExportData.getProfile();
        }
        if (isEmpty(sectionName)) {
            sectionName = objOptions.profile.getValue().trim();
        }
        if (isNull(sectionName)) {
            sectionName = "";
        }
        if (isNotEmpty(sectionName)) {
            sectionName += "-";
        }
        return objOptions.getTempDir() + rootElementName + "-" + sectionName + getDateTimeFormatted("yyyyMMddHHmmssSSSS") + ".xml";
    }

    private void open() {
        final String conMethodName = conClassName + "::open";
        if (objXMLFile == null) {
            try {
                String strXMLFileName = getXMLFileName();
                objXMLFile = new JSXMLFile(strXMLFileName);
                if (objXMLFile.isOpened() == false) {
                    objXMLFile.writeXMLDeclaration().newTag(rootElementName);
                }
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
                throw new JobSchedulerException(SOSVfs_E_260.params(conMethodName), e);
            }
        }
    }
}
