package com.sos.JSHelper.interfaces;

import java.util.HashMap;

import com.sos.JSHelper.Options.JSJobChain;
import com.sos.JSHelper.Options.SOSOptionAuthenticationMethod;
import com.sos.JSHelper.Options.SOSOptionBoolean;
import com.sos.JSHelper.Options.SOSOptionCommandScript;
import com.sos.JSHelper.Options.SOSOptionCommandScriptFile;
import com.sos.JSHelper.Options.SOSOptionFileName;
import com.sos.JSHelper.Options.SOSOptionFolderName;
import com.sos.JSHelper.Options.SOSOptionHostName;
import com.sos.JSHelper.Options.SOSOptionInFileName;
import com.sos.JSHelper.Options.SOSOptionIniFileName;
import com.sos.JSHelper.Options.SOSOptionInteger;
import com.sos.JSHelper.Options.SOSOptionJadeOperation;
import com.sos.JSHelper.Options.SOSOptionLogFileName;
import com.sos.JSHelper.Options.SOSOptionOutFileName;
import com.sos.JSHelper.Options.SOSOptionPassword;
import com.sos.JSHelper.Options.SOSOptionPortNumber;
import com.sos.JSHelper.Options.SOSOptionProcessID;
import com.sos.JSHelper.Options.SOSOptionRegExp;
import com.sos.JSHelper.Options.SOSOptionString;
import com.sos.JSHelper.Options.SOSOptionTime;
import com.sos.JSHelper.Options.SOSOptionTransferMode;
import com.sos.JSHelper.Options.SOSOptionTransferType;
import com.sos.JSHelper.Options.SOSOptionUserName;

/** @author KB */
public interface ISOSFtpOptions {

    public abstract SOSOptionBoolean CheckServerFeatures();

    /** \brief getaccount : Optional account info for authentication with an
     *
     * \details Optional account info for authentication with an (FTP) server.
     *
     * \return Optional account info for authentication with an */
    public abstract SOSOptionString getaccount();

    /** \brief setaccount : Optional account info for authentication with an
     *
     * \details Optional account info for authentication with an (FTP) server.
     *
     * @param account : Optional account info for authentication with an */
    public abstract void setaccount(SOSOptionString p_account);

    /** \brief getalternative_account : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter account. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * \return Alternative parameter for the primary parameter */
    public abstract SOSOptionString getalternative_account();

    /** \brief setalternative_account : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter account. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * @param alternative_account : Alternative parameter for the primary
     *            parameter */
    public abstract void setalternative_account(SOSOptionString p_alternative_account);

    /** \brief getalternative_host : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter host. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * \return Alternative parameter for the primary parameter */
    public abstract SOSOptionHostName getalternative_host();

    /** \brief setalternative_host : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter host. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * @param alternative_host : Alternative parameter for the primary parameter */
    public abstract void setalternative_host(SOSOptionHostName p_alternative_host);

    /** \brief getalternative_passive_mode : Alternative parameter for the
     * primary parameter
     *
     * \details Alternative parameter for the primary parameter passive_mode.
     * The alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * \return Alternative parameter for the primary parameter */
    public abstract SOSOptionString getalternative_passive_mode();

    /** \brief setalternative_passive_mode : Alternative parameter for the
     * primary parameter
     *
     * \details Alternative parameter for the primary parameter passive_mode.
     * The alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * @param alternative_passive_mode : Alternative parameter for the primary
     *            parameter */
    public abstract void setalternative_passive_mode(SOSOptionString p_alternative_passive_mode);

    /** \brief getalternative_password : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter password. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * \return Alternative parameter for the primary parameter */
    public abstract SOSOptionPassword getalternative_password();

    /** \brief setalternative_password : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter password. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * @param alternative_password : Alternative parameter for the primary
     *            parameter */
    public abstract void setalternative_password(SOSOptionPassword p_alternative_password);

    /** \brief getalternative_port : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter port. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * \return Alternative parameter for the primary parameter */
    public abstract SOSOptionPortNumber getalternative_port();

    /** \brief setalternative_port : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter port. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * @param alternative_port : Alternative parameter for the primary parameter */
    public abstract void setalternative_port(SOSOptionPortNumber p_alternative_port);

    /** \brief getalternative_remote_dir : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter remote_dir. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * \return Alternative parameter for the primary parameter */
    public abstract SOSOptionString getalternative_remote_dir();

    /** \brief setalternative_remote_dir : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter remote_dir. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * @param alternative_remote_dir : Alternative parameter for the primary
     *            parameter */
    public abstract void setalternative_remote_dir(SOSOptionString p_alternative_remote_dir);

    /** \brief getalternative_transfer_mode : Alternative parameter for the
     * primary parameter
     *
     * \details Alternative parameter for the primary parameter transfer_mode.
     * The alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * \return Alternative parameter for the primary parameter */
    public abstract SOSOptionString getalternative_transfer_mode();

    /** \brief setalternative_transfer_mode : Alternative parameter for the
     * primary parameter
     *
     * \details Alternative parameter for the primary parameter transfer_mode.
     * The alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * @param alternative_transfer_mode : Alternative parameter for the primary
     *            parameter */
    public abstract void setalternative_transfer_mode(SOSOptionString p_alternative_transfer_mode);

    /** \brief getalternative_user : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter user. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * \return Alternative parameter for the primary parameter */
    public abstract SOSOptionUserName getalternative_user();

    /** \brief setalternative_user : Alternative parameter for the primary
     * parameter
     *
     * \details Alternative parameter for the primary parameter user. The
     * alternative parameters are used solely should the connection to an
     * FTP/SFTP server fail, e.g. if the server were not available or if invalid
     * credentials were used.
     *
     * @param alternative_user : Alternative parameter for the primary parameter */
    public abstract void setalternative_user(SOSOptionUserName p_alternative_user);

    /** \brief getappend_files : This parameter specifies whether the content of
     * a
     *
     * \details This parameter specifies whether the content of a source file
     * should be appended to the target file should this file exist. The
     * parameter overwrite_files will be ignored if this parameter is specified
     * with the value true.
     *
     * \return This parameter specifies whether the content of a */
    public abstract SOSOptionBoolean getappend_files();

    /** \brief setappend_files : This parameter specifies whether the content of
     * a
     *
     * \details This parameter specifies whether the content of a source file
     * should be appended to the target file should this file exist. The
     * parameter overwrite_files will be ignored if this parameter is specified
     * with the value true.
     *
     * @param append_files : This parameter specifies whether the content of a */
    public abstract void setappend_files(SOSOptionBoolean p_append_files);

    /** \brief getatomic_prefix : This parameter specifies whether target files
     * shou
     *
     * \details This parameter specifies whether target files should be created
     * with a prefix such as "~", and must be renamed to the target file name
     * after the file transfer is completed without errors. This mechanism is
     * useful if the target directory is monitored for incoming files by some
     * application and if files are required to appear atomically instead of
     * being subsequently written to. The temporary prefix is specified as the
     * value of this parameter. This setting is recommended should target
     * directories be monitored by an application or a JobScheduler.
     *
     * \return This parameter specifies whether target files shou */
    public abstract SOSOptionString getatomic_prefix();

    /** \brief setatomic_prefix : This parameter specifies whether target files
     * shou
     *
     * \details This parameter specifies whether target files should be created
     * with a prefix such as "~", and must be renamed to the target file name
     * after the file transfer is completed without errors. This mechanism is
     * useful if the target directory is monitored for incoming files by some
     * application and if files are required to appear atomically instead of
     * being subsequently written to. The temporary prefix is specified as the
     * value of this parameter. This setting is recommended should target
     * directories be monitored by an application or a JobScheduler.
     *
     * @param atomic_prefix : This parameter specifies whether target files shou */
    public abstract void setatomic_prefix(SOSOptionString p_atomic_prefix);

    /** \brief getatomic_suffix : This parameter specifies whether target files
     * shou
     *
     * \details This parameter specifies whether target files should be created
     * with a suffix such as "~", and should be renamed to the target file name
     * after the file transfer is completed. This mechanism is useful if the
     * target directory is monitored for incoming files by some application and
     * if files are required to appear atomically instead of being subsequently
     * written to. The temporary suffix is specified as the value of this
     * parameter. This setting is recommended should target directories be
     * monitored by an application or a Job Scheduler.
     *
     * \return This parameter specifies whether target files shou */
    public abstract SOSOptionString getatomic_suffix();

    /** \brief setatomic_suffix : This parameter specifies whether target files
     * shou
     *
     * \details This parameter specifies whether target files should be created
     * with a suffix such as "~", and should be renamed to the target file name
     * after the file transfer is completed. This mechanism is useful if the
     * target directory is monitored for incoming files by some application and
     * if files are required to appear atomically instead of being subsequently
     * written to. The temporary suffix is specified as the value of this
     * parameter. This setting is recommended should target directories be
     * monitored by an application or a Job Scheduler.
     *
     * @param atomic_suffix : This parameter specifies whether target files shou */
    public abstract void setatomic_suffix(SOSOptionString p_atomic_suffix);

    /** \brief getbanner_footer : Name der Template-Datei f�r Protokoll-Ende This
     * p
     *
     * \details This program logs output to stdout or to a file that has been
     * specified by the parameter log_filename. A template can be used in order
     * to organize the output that is created. The output is grouped into
     * header, file list and footer. This parameter specifies a template file
     * for footer output. Templates can use internal variables and parameters as
     * placeholders in the form %{placeholder}. The standard footer template
     * looks like this:
     * *************************************************************************
     * execution status = %{status} successful transfers =
     * %{successful_transfers} failed transfers = %{failed_transfers} last error
     * = %{last_error}
     * *************************************************************************
     *
     * \return Name der Template-Datei f�r Protokoll-Ende This p */
    public abstract SOSOptionInFileName getbanner_footer();

    /** \brief setbanner_footer : Name der Template-Datei f�r Protokoll-Ende This
     * p
     *
     * \details This program logs output to stdout or to a file that has been
     * specified by the parameter log_filename. A template can be used in order
     * to organize the output that is created. The output is grouped into
     * header, file list and footer. This parameter specifies a template file
     * for footer output. Templates can use internal variables and parameters as
     * placeholders in the form %{placeholder}. The standard footer template
     * looks like this:
     * *************************************************************************
     * execution status = %{status} successful transfers =
     * %{successful_transfers} failed transfers = %{failed_transfers} last error
     * = %{last_error}
     * *************************************************************************
     *
     * @param banner_footer : Name der Template-Datei f�r Protokoll-Ende This p */
    public abstract void setbanner_footer(SOSOptionInFileName p_banner_footer);

    /** \brief getbanner_header : Name of Template-File for log-File
     *
     * \details This program logs output to stdout or to a file that has been
     * specified by the parameter log_filename. A template can be used in order
     * to organize the output that is created. The output is grouped into
     * header, file list and footer. This parameter specifies a template file
     * for header output. Templates can use internal variables and parameters as
     * placeholders in the form %{placeholder}. The standard header template
     * looks like this:
     * *************************************************************************
     * * * * SOSFTP - Managed File Transfer Utility * *
     * -------------------------------------- * * *
     * *************************************************************************
     * version = %{version} date = %{date} %{time} operation = %{operation}
     * protocol = %{protocol} file specification = %{file_spec} file path =
     * %{file_path} source host = %{localhost} (%{local_host_ip}) local
     * directory = %{local_dir} jump host = %{jump_host} target host = %{host}
     * (%{host_ip}) target directory = %{remote_dir} pid = %{current_pid} ppid =
     * %{ppid}
     * *************************************************************************
     *
     * \return Name of Template-File for log-File */
    public abstract SOSOptionInFileName getbanner_header();

    /** \brief setbanner_header : Name of Template-File for log-File
     *
     * \details This program logs output to stdout or to a file that has been
     * specified by the parameter log_filename. A template can be used in order
     * to organize the output that is created. The output is grouped into
     * header, file list and footer. This parameter specifies a template file
     * for header output. Templates can use internal variables and parameters as
     * placeholders in the form %{placeholder}. The standard header template
     * looks like this:
     * *************************************************************************
     * * * * SOSFTP - Managed File Transfer Utility * *
     * -------------------------------------- * * *
     * *************************************************************************
     * version = %{version} date = %{date} %{time} operation = %{operation}
     * protocol = %{protocol} file specification = %{file_spec} file path =
     * %{file_path} source host = %{localhost} (%{local_host_ip}) local
     * directory = %{local_dir} jump host = %{jump_host} target host = %{host}
     * (%{host_ip}) target directory = %{remote_dir} pid = %{current_pid} ppid =
     * %{ppid}
     * *************************************************************************
     *
     * @param banner_header : Name of Template-File for log-File */
    public abstract void setbanner_header(SOSOptionInFileName p_banner_header);

    /** \brief getcheck_interval : This parameter specifies the interval in
     * seconds
     *
     * \details This parameter specifies the interval in seconds between two
     * file transfer trials, if repeated transfer of files has been configured
     * using the check_retry parameter.
     *
     * \return This parameter specifies the interval in seconds */
    public abstract SOSOptionInteger getcheck_interval();

    /** \brief setcheck_interval : This parameter specifies the interval in
     * seconds
     *
     * \details This parameter specifies the interval in seconds between two
     * file transfer trials, if repeated transfer of files has been configured
     * using the check_retry parameter.
     *
     * @param check_interval : This parameter specifies the interval in seconds */
    public abstract void setcheck_interval(SOSOptionInteger p_check_interval);

    /** \brief getcheck_retry : This parameter specifies whether a file transfer
     *
     * \details This parameter specifies whether a file transfer should be
     * repeated in order to ensure that the file was complete when the transfer
     * started. This is relevant for Unix systems that allow read and write
     * access to a file at the same time. This parameter causes the size of the
     * current file transfer and of the previous file transfer to be compared
     * and repeats transferring one file up to the number of trials specified by
     * this parameter. Should the file size of both transfers be the same, then
     * it is assumed that the file was complete at the FTP/SFTP server. The
     * interval between two trials to transfer a file is configured using the
     * check_interval parameter.
     *
     * \return This parameter specifies whether a file transfer */
    public abstract SOSOptionInteger getcheck_retry();

    /** \brief setcheck_retry : This parameter specifies whether a file transfer
     *
     * \details This parameter specifies whether a file transfer should be
     * repeated in order to ensure that the file was complete when the transfer
     * started. This is relevant for Unix systems that allow read and write
     * access to a file at the same time. This parameter causes the size of the
     * current file transfer and of the previous file transfer to be compared
     * and repeats transferring one file up to the number of trials specified by
     * this parameter. Should the file size of both transfers be the same, then
     * it is assumed that the file was complete at the FTP/SFTP server. The
     * interval between two trials to transfer a file is configured using the
     * check_interval parameter.
     *
     * @param check_retry : This parameter specifies whether a file transfer */
    public abstract void setcheck_retry(SOSOptionInteger p_check_retry);

    /** \brief getclasspath_base : The parameter is used during installation of
     * this
     *
     * \details The parameter is used during installation of this program on a
     * remote server with the parameter operation=installcode>. This parameter
     * specifies the path of the Java Runtime Environment (JRE) at the remote
     * server and is used if on the remote server a JRE is not included in the
     * system path. The path of the specified JRE is added to the start script
     * at the remote server (sosftp.cmd respectively sosftp.sh).
     *
     * \return The parameter is used during installation of this */
    public abstract SOSOptionFolderName getclasspath_base();

    /** \brief setclasspath_base : The parameter is used during installation of
     * this
     *
     * \details The parameter is used during installation of this program on a
     * remote server with the parameter operation=installcode>. This parameter
     * specifies the path of the Java Runtime Environment (JRE) at the remote
     * server and is used if on the remote server a JRE is not included in the
     * system path. The path of the specified JRE is added to the start script
     * at the remote server (sosftp.cmd respectively sosftp.sh).
     *
     * @param classpath_base : The parameter is used during installation of this */
    public abstract void setclasspath_base(SOSOptionFolderName p_classpath_base);

    /** \brief getcurrent_pid : This parameter is used for Unix systems and - as
     * o
     *
     * \details This parameter is used for Unix systems and - as opposed to
     * other parameters - is usually specified in the start script sosftp.sh.
     * The value of the environment variable $$ is assigned, that contains the
     * current process id (PID). The process id is used when writing an entry to
     * a history file for each transfer (see parameter history).
     *
     * \return This parameter is used for Unix systems and - as o */
    public abstract SOSOptionProcessID getcurrent_pid();

    /** \brief setcurrent_pid : This parameter is used for Unix systems and - as
     * o
     *
     * \details This parameter is used for Unix systems and - as opposed to
     * other parameters - is usually specified in the start script sosftp.sh.
     * The value of the environment variable $$ is assigned, that contains the
     * current process id (PID). The process id is used when writing an entry to
     * a history file for each transfer (see parameter history).
     *
     * @param current_pid : This parameter is used for Unix systems and - as o */
    public abstract void setcurrent_pid(SOSOptionProcessID p_current_pid);

    /** \brief getfile_path : This parameter is used alternatively to the parame
     *
     * \details This parameter is used alternatively to the parameter file_spec
     * to specify a single file for transfer. When receiving files the following
     * applies: This parameter accepts the absolute name and path of file at the
     * FTP/SFTP server that should be transferred. The file name has to include
     * both name and path of the file at the FTP/SFTP server. The file will be
     * stored unter its name in the directory that is specified by the parameter
     * local_dir. The following parameters are ignored should this parameter be
     * used: file_spec and remote_dir. When sending files the following applies:
     * This parameter accepts the absolute name and path of file that should be
     * transferred. An absolute path has to be specified. The file will be
     * stored under its name in the directory at the FTP/SFTP server that has
     * been specified by the parameter remote_dir. The following parameters are
     * ignored should this parameter be used: file_spec and local_dir.
     *
     * \return This parameter is used alternatively to the parame */
    public abstract SOSOptionFileName getfile_path();

    /** \brief setfile_path : This parameter is used alternatively to the parame
     *
     * \details This parameter is used alternatively to the parameter file_spec
     * to specify a single file for transfer. When receiving files the following
     * applies: This parameter accepts the absolute name and path of file at the
     * FTP/SFTP server that should be transferred. The file name has to include
     * both name and path of the file at the FTP/SFTP server. The file will be
     * stored unter its name in the directory that is specified by the parameter
     * local_dir. The following parameters are ignored should this parameter be
     * used: file_spec and remote_dir. When sending files the following applies:
     * This parameter accepts the absolute name and path of file that should be
     * transferred. An absolute path has to be specified. The file will be
     * stored under its name in the directory at the FTP/SFTP server that has
     * been specified by the parameter remote_dir. The following parameters are
     * ignored should this parameter be used: file_spec and local_dir.
     *
     * @param file_path : This parameter is used alternatively to the parame */
    public abstract void setfile_path(SOSOptionFileName p_file_path);

    /** \brief getfile_spec : file_spec This parameter expects a regular expressi
     *
     * \details This parameter expects a regular expression to select files from
     * a local directory or from an FTP/SFTP server (depending on the operation
     * parameter values send or receive) to be transferred. For the operations
     * send and receive either this parameter has to be specified or the
     * parameter file_path or a list of file names as additional parameters.
     *
     * \return file_spec This parameter expects a regular expressi */
    public abstract SOSOptionRegExp getfile_spec();

    /** \brief setfile_spec : file_spec This parameter expects a regular expressi
     *
     * \details This parameter expects a regular expression to select files from
     * a local directory or from an FTP/SFTP server (depending on the operation
     * parameter values send or receive) to be transferred. For the operations
     * send and receive either this parameter has to be specified or the
     * parameter file_path or a list of file names as additional parameters.
     *
     * @param file_spec : file_spec This parameter expects a regular expressi */
    public abstract void setfile_spec(SOSOptionRegExp p_file_spec);

    /** \brief getfile_spec2 : In addition to what is stated for the parameter fi
     *
     * \details In addition to what is stated for the parameter file_spec
     * additional parameters can be specified for up to 9 file sets like this:
     * -file_spec=.*\.gif$ -local_dir=/tmp/1 -remote_dir=/tmp/1
     * -file_spec2=.*\.exe$::param_set_2 -param_set_2=
     * "transfer_mode=binary::remote_dir=/tmp/2::local_dir=/tmp/2" Within the
     * file_spec2 parameter value the regular expression is separated by :: from
     * the name of a file set. This name can freely be chosen, it consists of
     * the characters 0-9, a-z and _. The name of the file set is used as a
     * separate parameter in the command line. This parameter is assigend the
     * list of parameters that should be valid for the specific file set.
     * Therefore the names and values of individual parameters are specified in
     * the form name=value::name2=value2 .... Such parameters are exclusively
     * valid for the specific file set. The above sample causes all files with
     * the extension .gif to be transferred from the local directory /tmp/1 to a
     * directory with the same name on the target host. For files with the
     * extension .exe a file set param_set_2 is specified that contains
     * parameters that are specific for this file set, as binary transfer and
     * different source and target directories. Please, consider that parameter
     * file sets cannot specify parameters that control the connection to a
     * target host, i.e. all files are transferred between the same local and
     * remote hosts. However, the transfer direction can be changed, e.g. by
     * specifying a different operation parameter for a file set.
     *
     * \return In addition to what is stated for the parameter fi */
    public abstract SOSOptionRegExp getfile_spec2();

    /** \brief setfile_spec2 : In addition to what is stated for the parameter fi
     *
     * \details In addition to what is stated for the parameter file_spec
     * additional parameters can be specified for up to 9 file sets like this:
     * -file_spec=.*\.gif$ -local_dir=/tmp/1 -remote_dir=/tmp/1
     * -file_spec2=.*\.exe$::param_set_2 -param_set_2=
     * "transfer_mode=binary::remote_dir=/tmp/2::local_dir=/tmp/2" Within the
     * file_spec2 parameter value the regular expression is separated by :: from
     * the name of a file set. This name can freely be chosen, it consists of
     * the characters 0-9, a-z and _. The name of the file set is used as a
     * separate parameter in the command line. This parameter is assigend the
     * list of parameters that should be valid for the specific file set.
     * Therefore the names and values of individual parameters are specified in
     * the form name=value::name2=value2 .... Such parameters are exclusively
     * valid for the specific file set. The above sample causes all files with
     * the extension .gif to be transferred from the local directory /tmp/1 to a
     * directory with the same name on the target host. For files with the
     * extension .exe a file set param_set_2 is specified that contains
     * parameters that are specific for this file set, as binary transfer and
     * different source and target directories. Please, consider that parameter
     * file sets cannot specify parameters that control the connection to a
     * target host, i.e. all files are transferred between the same local and
     * remote hosts. However, the transfer direction can be changed, e.g. by
     * specifying a different operation parameter for a file set.
     *
     * @param file_spec2 : In addition to what is stated for the parameter fi */
    public abstract void setfile_spec2(SOSOptionRegExp p_file_spec2);

    /** \brief getforce_files : This parameter specifies whether an error should
     * b
     *
     * \details This parameter specifies whether an error should be raised if no
     * files could be found for transfer. The number of files to be transferred
     * is determined by the file_spec or file_path parameters and can be
     * restricted by the overwrite_files parameter should this be specified with
     * the value false.
     *
     * \return This parameter specifies whether an error should b */
    public abstract SOSOptionBoolean getforce_files();

    /** \brief setforce_files : This parameter specifies whether an error should
     * b
     *
     * \details This parameter specifies whether an error should be raised if no
     * files could be found for transfer. The number of files to be transferred
     * is determined by the file_spec or file_path parameters and can be
     * restricted by the overwrite_files parameter should this be specified with
     * the value false.
     *
     * @param force_files : This parameter specifies whether an error should b */
    public abstract void setforce_files(SOSOptionBoolean p_force_files);

    /** \brief gethistory : This parameter causes a history file to be written
     *
     * \details This parameter causes a history file to be written in CSV
     * format. The path and name of the history file is specified as value for
     * this parameter. A history record is created for each file that has been
     * transferred. A history file contains the following columns: guid A unique
     * identifier for the history entry. This identifier is used for checking of
     * duplicate entries in combination with Job Scheduler for Managed File
     * Transfer. mandator A character that denominates the mandator of a file
     * transfer, see respective parameter. transfer_timestamp The point in time
     * when the transfer took place. pid The process id of the current process
     * that executes the file transfer, see parameter current_pid. ppid The
     * process id of the parent of the process that executes the file transfer,
     * see respective parameter. operation One of the operations send or
     * receive, see respective parameter. localhost The name of the host on
     * which this program is executed. localhost_ip The IP address of the host
     * on which this program is executed. local_user The name of the user
     * account for which this program is executed. remote_host The name of the
     * host to/from which a transfer is executed, see parameter host.
     * remote_host_ip The IP address of the host to/from which a transfer is
     * executed, see parameter host. remote_user The name of the user account
     * for the remote host, see parameter user. protocol The protocol can be
     * either ftp, sftp or ftps, see respective parameter. port The port on the
     * remote host, see respective parameter. local_dir The local directory
     * to/from which a file has been transferred, see respective parameter.
     * remote_dir The remote directory to/from which a file has been
     * transferred, see respective parameter. local_filename For send operations
     * this is the original file name on the local host. For receive operations
     * this is the resulting file name on the local host optionally having
     * applied replacement operations, see parameter replacing. remote_filename
     * For send operations this is the resulting file name on the remote host
     * optionally having applied replacement operations, see parameter
     * replacing. For receive operations this is the original file name on the
     * remote host. file_size The size of the transferred file in bytes. md5 The
     * value of the MD5 hash that is created from the file that was transferred.
     * status The status can be either success or error. last_error_message
     * Should an error have occurred then the last error message will be given
     * in this column. log_filename The name of the log file, see respective
     * parameter.
     *
     * \return This parameter causes a history file to be written */
    public abstract SOSOptionOutFileName gethistory();

    /** \brief sethistory : This parameter causes a history file to be written
     *
     * \details This parameter causes a history file to be written in CSV
     * format. The path and name of the history file is specified as value for
     * this parameter. A history record is created for each file that has been
     * transferred. A history file contains the following columns: guid A unique
     * identifier for the history entry. This identifier is used for checking of
     * duplicate entries in combination with Job Scheduler for Managed File
     * Transfer. mandator A character that denominates the mandator of a file
     * transfer, see respective parameter. transfer_timestamp The point in time
     * when the transfer took place. pid The process id of the current process
     * that executes the file transfer, see parameter current_pid. ppid The
     * process id of the parent of the process that executes the file transfer,
     * see respective parameter. operation One of the operations send or
     * receive, see respective parameter. localhost The name of the host on
     * which this program is executed. localhost_ip The IP address of the host
     * on which this program is executed. local_user The name of the user
     * account for which this program is executed. remote_host The name of the
     * host to/from which a transfer is executed, see parameter host.
     * remote_host_ip The IP address of the host to/from which a transfer is
     * executed, see parameter host. remote_user The name of the user account
     * for the remote host, see parameter user. protocol The protocol can be
     * either ftp, sftp or ftps, see respective parameter. port The port on the
     * remote host, see respective parameter. local_dir The local directory
     * to/from which a file has been transferred, see respective parameter.
     * remote_dir The remote directory to/from which a file has been
     * transferred, see respective parameter. local_filename For send operations
     * this is the original file name on the local host. For receive operations
     * this is the resulting file name on the local host optionally having
     * applied replacement operations, see parameter replacing. remote_filename
     * For send operations this is the resulting file name on the remote host
     * optionally having applied replacement operations, see parameter
     * replacing. For receive operations this is the original file name on the
     * remote host. file_size The size of the transferred file in bytes. md5 The
     * value of the MD5 hash that is created from the file that was transferred.
     * status The status can be either success or error. last_error_message
     * Should an error have occurred then the last error message will be given
     * in this column. log_filename The name of the log file, see respective
     * parameter.
     *
     * @param history : This parameter causes a history file to be written */
    public abstract void sethistory(SOSOptionOutFileName p_history);

    /** \brief gethistory_repeat : The parameter is used in order to synchronize
     * para
     *
     * \details The parameter is used in order to synchronize parallel write
     * access to the history file by multiple instances of this program. This
     * parameter specifies the maximum number of repeat intervals when trying to
     * write to the history file if the history file is locked due to parallel
     * instances of this program.
     *
     * \return The parameter is used in order to synchronize para */
    public abstract SOSOptionInteger gethistory_repeat();

    /** \brief sethistory_repeat : The parameter is used in order to synchronize
     * para
     *
     * \details The parameter is used in order to synchronize parallel write
     * access to the history file by multiple instances of this program. This
     * parameter specifies the maximum number of repeat intervals when trying to
     * write to the history file if the history file is locked due to parallel
     * instances of this program.
     *
     * @param history_repeat : The parameter is used in order to synchronize
     *            para */
    public abstract void sethistory_repeat(SOSOptionInteger p_history_repeat);

    /** \brief gethistory_repeat_interval : The parameter is used in order to
     * synchronize para
     *
     * \details The parameter is used in order to synchronize parallel write
     * access to the history file by multiple instances of this program. This
     * parameter specifies the the interval in seconds of repeated trials to
     * write to the history file if the history file is locked due to parallel
     * instances of this program.
     *
     * \return The parameter is used in order to synchronize para */
    public abstract SOSOptionInteger gethistory_repeat_interval();

    /** \brief sethistory_repeat_interval : The parameter is used in order to
     * synchronize para
     *
     * \details The parameter is used in order to synchronize parallel write
     * access to the history file by multiple instances of this program. This
     * parameter specifies the the interval in seconds of repeated trials to
     * write to the history file if the history file is locked due to parallel
     * instances of this program.
     *
     * @param history_repeat_interval : The parameter is used in order to
     *            synchronize para */
    public abstract void sethistory_repeat_interval(SOSOptionInteger p_history_repeat_interval);

    /** \brief gethost : Host-Name This parameter specifies th
     *
     * \details This parameter specifies the hostname or IP address of the
     * server to which a connection has to be made.
     *
     * \return Host-Name This parameter specifies th */
    public abstract SOSOptionHostName gethost();

    /** \brief sethost : Host-Name This parameter specifies th
     *
     * \details This parameter specifies the hostname or IP address of the
     * server to which a connection has to be made.
     *
     * @param host : Host-Name This parameter specifies th */
    public abstract void sethost(SOSOptionHostName p_host);

    /** \brief gethttp_proxy_host : The value of this parameter is the host name
     * or th
     *
     * \details The value of this parameter is the host name or the IP address
     * of a proxy used in order to establish a connection to the SSH server via
     * SSL/TLS. The use of a proxy is optional and exclusively considered if the
     * parameter protocol=ftps is used.
     *
     * \return The value of this parameter is the host name or th */
    public abstract SOSOptionString gethttp_proxy_host();

    /** \brief sethttp_proxy_host : The value of this parameter is the host name
     * or th
     *
     * \details The value of this parameter is the host name or the IP address
     * of a proxy used in order to establish a connection to the SSH server via
     * SSL/TLS. The use of a proxy is optional and exclusively considered if the
     * parameter protocol=ftps is used.
     *
     * @param http_proxy_host : The value of this parameter is the host name or
     *            th */
    public abstract void sethttp_proxy_host(SOSOptionString p_http_proxy_host);

    /** \brief gethttp_proxy_port : This parameter specifies the port of a proxy
     * that
     *
     * \details This parameter specifies the port of a proxy that is used in
     * order to establish a connection to the SSH server via SSL/TLS, see
     * parameter http_proxy_host.
     *
     * \return This parameter specifies the port of a proxy that */
    public abstract SOSOptionString gethttp_proxy_port();

    /** \brief sethttp_proxy_port : This parameter specifies the port of a proxy
     * that
     *
     * \details This parameter specifies the port of a proxy that is used in
     * order to establish a connection to the SSH server via SSL/TLS, see
     * parameter http_proxy_host.
     *
     * @param http_proxy_port : This parameter specifies the port of a proxy
     *            that */
    public abstract void sethttp_proxy_port(SOSOptionString p_http_proxy_port);

    /** \brief getjump_command : This parameter specifies a command that is to be
     * e
     *
     * \details This parameter specifies a command that is to be executed on the
     * SSH server. Multiple commands can be separated by the command delimiter
     * that is specified using the jump_command_delimiter parameter.
     *
     * \return This parameter specifies a command that is to be e */
    public abstract SOSOptionString getjump_command();

    /** \brief setjump_command : This parameter specifies a command that is to be
     * e
     *
     * \details This parameter specifies a command that is to be executed on the
     * SSH server. Multiple commands can be separated by the command delimiter
     * that is specified using the jump_command_delimiter parameter.
     *
     * @param jump_command : This parameter specifies a command that is to be e */
    public abstract void setjump_command(SOSOptionString p_jump_command);

    /** \brief getjump_command_delimiter : Command delimiter characters are
     * specified using t
     *
     * \details Command delimiter characters are specified using this parameter.
     * These delimiters can then be used in the jump_command parameter to
     * seperate multiple commands. These commands are then excecuted in separate
     * SSH sessions.
     *
     * \return Command delimiter characters are specified using t */
    public abstract SOSOptionString getjump_command_delimiter();

    /** \brief setjump_command_delimiter : Command delimiter characters are
     * specified using t
     *
     * \details Command delimiter characters are specified using this parameter.
     * These delimiters can then be used in the jump_command parameter to
     * seperate multiple commands. These commands are then excecuted in separate
     * SSH sessions.
     *
     * @param jump_command_delimiter : Command delimiter characters are
     *            specified using t */
    public abstract void setjump_command_delimiter(SOSOptionString p_jump_command_delimiter);

    /** \brief getjump_command_script : This parameter can be used as an
     * alternative to ju
     *
     * \details This parameter can be used as an alternative to jump_command,
     * jump_command_delimiter and jump_command_script_file. It contains script
     * code which will be transferred to the remote host as a file and will then
     * be executed there.
     *
     * \return This parameter can be used as an alternative to ju */
    public abstract SOSOptionCommandScript getjump_command_script();

    /** \brief setjump_command_script : This parameter can be used as an
     * alternative to ju
     *
     * \details This parameter can be used as an alternative to jump_command,
     * jump_command_delimiter and jump_command_script_file. It contains script
     * code which will be transferred to the remote host as a file and will then
     * be executed there.
     *
     * @param jump_command_script : This parameter can be used as an alternative
     *            to ju */
    public abstract void setjump_command_script(SOSOptionCommandScript p_jump_command_script);

    /** \brief getjump_command_script_file : This parameter can be used as an
     * alternative to ju
     *
     * \details This parameter can be used as an alternative to jump_command,
     * jump_command_delimiter and jump_command_script. It contains the name of a
     * script file, which will be transferred to the remote host and will then
     * be executed there.
     *
     * \return This parameter can be used as an alternative to ju */
    public abstract SOSOptionCommandScriptFile getjump_command_script_file();

    /** \brief setjump_command_script_file : This parameter can be used as an
     * alternative to ju
     *
     * \details This parameter can be used as an alternative to jump_command,
     * jump_command_delimiter and jump_command_script. It contains the name of a
     * script file, which will be transferred to the remote host and will then
     * be executed there.
     *
     * @param jump_command_script_file : This parameter can be used as an
     *            alternative to ju */
    public abstract void setjump_command_script_file(SOSOptionCommandScriptFile p_jump_command_script_file);

    /** \brief getjump_host : When using a jump_host then files are first transf
     *
     * \details When using a jump_host then files are first transferred to this
     * host and then to the target system. Different protocols (FTP/SFTP) can be
     * used for these transfer operations. Host or IP address of the jump_host
     * from which or to which files should be transferred in a first operation.
     *
     * \return When using a jump_host then files are first transf */
    public abstract SOSOptionHostName getjump_host();

    /** \brief setjump_host : When using a jump_host then files are first transf
     *
     * \details When using a jump_host then files are first transferred to this
     * host and then to the target system. Different protocols (FTP/SFTP) can be
     * used for these transfer operations. Host or IP address of the jump_host
     * from which or to which files should be transferred in a first operation.
     *
     * @param jump_host : When using a jump_host then files are first transf */
    public abstract void setjump_host(SOSOptionHostName p_jump_host);

    /** \brief getjump_ignore_error : Should the value true be specified, then
     * execution
     *
     * \details Should the value true be specified, then execution errors caused
     * by commands on the SSH server are ignored. Otherwise such execution
     * errors will be reported.
     *
     * \return Should the value true be specified, then execution */
    public abstract SOSOptionBoolean getjump_ignore_error();

    /** \brief setjump_ignore_error : Should the value true be specified, then
     * execution
     *
     * \details Should the value true be specified, then execution errors caused
     * by commands on the SSH server are ignored. Otherwise such execution
     * errors will be reported.
     *
     * @param jump_ignore_error : Should the value true be specified, then
     *            execution */
    public abstract void setjump_ignore_error(SOSOptionBoolean p_jump_ignore_error);

    /** \brief getjump_ignore_signal : Should the value true be specified, t
     *
     * \details Should the value true be specified, then on Unix systems all
     * signals will be ignored that terminate the execution of a command on the
     * SSH server - if for example a command is terminated using kill. Note that
     * by default errors will be reported for commands that are terminated by
     * signals.
     *
     * \return Should the value true be specified, t */
    public abstract SOSOptionBoolean getjump_ignore_signal();

    /** \brief setjump_ignore_signal : Should the value true be specified, t
     *
     * \details Should the value true be specified, then on Unix systems all
     * signals will be ignored that terminate the execution of a command on the
     * SSH server - if for example a command is terminated using kill. Note that
     * by default errors will be reported for commands that are terminated by
     * signals.
     *
     * @param jump_ignore_signal : Should the value true be specified, t */
    public abstract void setjump_ignore_signal(SOSOptionBoolean p_jump_ignore_signal);

    /** \brief getjump_ignore_stderr : This job checks if any output to stderr
     * has been c
     *
     * \details This job checks if any output to stderr has been created by a
     * command that is being executed on the SSH server and reports this as an
     * error. Should the value true be specified for this parameter, then output
     * to stderr will not be reported as an error by the Job Scheduler.
     *
     * \return This job checks if any output to stderr has been c */
    public abstract SOSOptionBoolean getjump_ignore_stderr();

    /** \brief setjump_ignore_stderr : This job checks if any output to stderr
     * has been c
     *
     * \details This job checks if any output to stderr has been created by a
     * command that is being executed on the SSH server and reports this as an
     * error. Should the value true be specified for this parameter, then output
     * to stderr will not be reported as an error by the Job Scheduler.
     *
     * @param jump_ignore_stderr : This job checks if any output to stderr has
     *            been c */
    public abstract void setjump_ignore_stderr(SOSOptionBoolean p_jump_ignore_stderr);

    /** \brief getjump_password : Password for authentication with the jump_host.
     *
     * \details Password for authentication with the jump_host.
     *
     * \return Password for authentication with the jump_host. */
    public abstract SOSOptionPassword getjump_password();

    /** \brief setjump_password : Password for authentication with the jump_host.
     *
     * \details Password for authentication with the jump_host.
     *
     * @param jump_password : Password for authentication with the jump_host. */
    public abstract void setjump_password(SOSOptionPassword p_jump_password);

    /** \brief getjump_port : Port on the jump_host by which files should be tra
     *
     * \details Port on the jump_host by which files should be transferred. For
     * FTP this is usually port 21, for SFTP this is usually port 22.
     *
     * \return Port on the jump_host by which files should be tra */
    public abstract SOSOptionPortNumber getjump_port();

    /** \brief setjump_port : Port on the jump_host by which files should be tra
     *
     * \details Port on the jump_host by which files should be transferred. For
     * FTP this is usually port 21, for SFTP this is usually port 22.
     *
     * @param jump_port : Port on the jump_host by which files should be tra */
    public abstract void setjump_port(SOSOptionPortNumber p_jump_port);

    /** \brief getjump_protocol : When using a jump_host then files are first
     * transf
     *
     * \details When using a jump_host then files are first transferred to this
     * host and then to the target system. Different protocols (FTP/SFTP) can be
     * used for these transfer operations. This parameter expects ftp, sftp or
     * ftps to be specified. If sftp is used, then the jump_ssh_* parameters
     * will be considered.
     *
     * \return When using a jump_host then files are first transf */
    public abstract SOSOptionString getjump_protocol();

    /** \brief setjump_protocol : When using a jump_host then files are first
     * transf
     *
     * \details When using a jump_host then files are first transferred to this
     * host and then to the target system. Different protocols (FTP/SFTP) can be
     * used for these transfer operations. This parameter expects ftp, sftp or
     * ftps to be specified. If sftp is used, then the jump_ssh_* parameters
     * will be considered.
     *
     * @param jump_protocol : When using a jump_host then files are first transf */
    public abstract void setjump_protocol(SOSOptionString p_jump_protocol);

    /** \brief getjump_proxy_host : The value of this parameter is the host name
     * or th
     *
     * \details The value of this parameter is the host name or the IP address
     * of a proxy used in order to establish a connection to the jump host. The
     * use of a proxy is optional.
     *
     * \return The value of this parameter is the host name or th */
    public abstract SOSOptionString getjump_proxy_host();

    /** \brief setjump_proxy_host : The value of this parameter is the host name
     * or th
     *
     * \details The value of this parameter is the host name or the IP address
     * of a proxy used in order to establish a connection to the jump host. The
     * use of a proxy is optional.
     *
     * @param jump_proxy_host : The value of this parameter is the host name or
     *            th */
    public abstract void setjump_proxy_host(SOSOptionString p_jump_proxy_host);

    /** \brief getjump_proxy_password : This parameter specifies the password for
     * the prox
     *
     * \details This parameter specifies the password for the proxy server user
     * account, should a proxy be used in order to connect to the jump host, see
     * parameter jump_proxy_host.
     *
     * \return This parameter specifies the password for the prox */
    public abstract SOSOptionString getjump_proxy_password();

    /** \brief setjump_proxy_password : This parameter specifies the password for
     * the prox
     *
     * \details This parameter specifies the password for the proxy server user
     * account, should a proxy be used in order to connect to the jump host, see
     * parameter jump_proxy_host.
     *
     * @param jump_proxy_password : This parameter specifies the password for
     *            the prox */
    public abstract void setjump_proxy_password(SOSOptionString p_jump_proxy_password);

    /** \brief getjump_proxy_port : This parameter specifies the port of a proxy
     * that
     *
     * \details This parameter specifies the port of a proxy that is used in
     * order to establish a connection to the jump host, see parameter
     * jump_proxy_host.
     *
     * \return This parameter specifies the port of a proxy that */
    public abstract SOSOptionString getjump_proxy_port();

    /** \brief setjump_proxy_port : This parameter specifies the port of a proxy
     * that
     *
     * \details This parameter specifies the port of a proxy that is used in
     * order to establish a connection to the jump host, see parameter
     * jump_proxy_host.
     *
     * @param jump_proxy_port : This parameter specifies the port of a proxy
     *            that */
    public abstract void setjump_proxy_port(SOSOptionString p_jump_proxy_port);

    /** \brief getjump_proxy_user : The value of this parameter specifies the
     * user acc
     *
     * \details The value of this parameter specifies the user account for
     * authentication by the proxy server should a proxy be used in order to
     * connect to the jump host, see parameter jump_proxy_host.
     *
     * \return The value of this parameter specifies the user acc */
    public abstract SOSOptionUserName getjump_proxy_user();

    /** \brief setjump_proxy_user : The value of this parameter specifies the
     * user acc
     *
     * \details The value of this parameter specifies the user account for
     * authentication by the proxy server should a proxy be used in order to
     * connect to the jump host, see parameter jump_proxy_host.
     *
     * @param jump_proxy_user : The value of this parameter specifies the user
     *            acc */
    public abstract void setjump_proxy_user(SOSOptionUserName p_jump_proxy_user);

    /** \brief getjump_simulate_shell : Should the value true be specified for
     * this parame
     *
     * \details Should the value true be specified for this parameter, then a
     * login to a shell is simulated to execute commands. Some scripts may cause
     * problems if no shell is present.
     *
     * \return Should the value true be specified for this parame */
    public abstract SOSOptionBoolean getjump_simulate_shell();

    /** \brief setjump_simulate_shell : Should the value true be specified for
     * this parame
     *
     * \details Should the value true be specified for this parameter, then a
     * login to a shell is simulated to execute commands. Some scripts may cause
     * problems if no shell is present.
     *
     * @param jump_simulate_shell : Should the value true be specified for this
     *            parame */
    public abstract void setjump_simulate_shell(SOSOptionBoolean p_jump_simulate_shell);

    /** \brief getjump_simulate_shell_inactivity_timeout : If no new characters
     * are written to stdout or stde
     *
     * \details If no new characters are written to stdout or stderr after the
     * given number of milliseconds, then it is assumed that the command has
     * been carried out and that the shell is waiting for the next command.
     *
     * \return If no new characters are written to stdout or stde */
    public abstract SOSOptionInteger getjump_simulate_shell_inactivity_timeout();

    /** \brief setjump_simulate_shell_inactivity_timeout : If no new characters
     * are written to stdout or stde
     *
     * \details If no new characters are written to stdout or stderr after the
     * given number of milliseconds, then it is assumed that the command has
     * been carried out and that the shell is waiting for the next command.
     *
     * @param jump_simulate_shell_inactivity_timeout : If no new characters are
     *            written to stdout or stde */
    public abstract void setjump_simulate_shell_inactivity_timeout(SOSOptionInteger p_jump_simulate_shell_inactivity_timeout);

    /** \brief getjump_simulate_shell_login_timeout : If no new characters are
     * written to stdout or stde
     *
     * \details If no new characters are written to stdout or stderr after the
     * given number of milliseconds, then it is assumed that the login has been
     * carried out and that the shell is waiting for the next command.
     *
     * \return If no new characters are written to stdout or stde */
    public abstract SOSOptionInteger getjump_simulate_shell_login_timeout();

    /** \brief setjump_simulate_shell_login_timeout : If no new characters are
     * written to stdout or stde
     *
     * \details If no new characters are written to stdout or stderr after the
     * given number of milliseconds, then it is assumed that the login has been
     * carried out and that the shell is waiting for the next command.
     *
     * @param jump_simulate_shell_login_timeout : If no new characters are
     *            written to stdout or stde */
    public abstract void setjump_simulate_shell_login_timeout(SOSOptionInteger p_jump_simulate_shell_login_timeout);

    /** \brief getjump_simulate_shell_prompt_trigger : The expected command line
     * prompt. Using this promp
     *
     * \details The expected command line prompt. Using this prompt the program
     * tries to find out if commands may be entered or have been carried out. If
     * no prompt can be configured, then timeout parameters have to be used in
     * order to check if the shell is ready to accept commands.
     *
     * \return The expected command line prompt. Using this promp */
    public abstract SOSOptionString getjump_simulate_shell_prompt_trigger();

    /** \brief setjump_simulate_shell_prompt_trigger : The expected command line
     * prompt. Using this promp
     *
     * \details The expected command line prompt. Using this prompt the program
     * tries to find out if commands may be entered or have been carried out. If
     * no prompt can be configured, then timeout parameters have to be used in
     * order to check if the shell is ready to accept commands.
     *
     * @param jump_simulate_shell_prompt_trigger : The expected command line
     *            prompt. Using this promp */
    public abstract void setjump_simulate_shell_prompt_trigger(SOSOptionString p_jump_simulate_shell_prompt_trigger);

    /** \brief getjump_ssh_auth_file : This parameter specifies the path and name
     * of a us
     *
     * \details This parameter specifies the path and name of a user's private
     * key file used for login to the SSH server of the jump_host. This
     * parameter must be specified if the publickey authentication method has
     * been specified in the jump_ssh_auth_method parameter. Should the private
     * key file be secured by a password, then this password has to be specified
     * using the jump_password parameter.
     *
     * \return This parameter specifies the path and name of a us */
    public abstract SOSOptionInFileName getjump_ssh_auth_file();

    /** \brief setjump_ssh_auth_file : This parameter specifies the path and name
     * of a us
     *
     * \details This parameter specifies the path and name of a user's private
     * key file used for login to the SSH server of the jump_host. This
     * parameter must be specified if the publickey authentication method has
     * been specified in the jump_ssh_auth_method parameter. Should the private
     * key file be secured by a password, then this password has to be specified
     * using the jump_password parameter.
     *
     * @param jump_ssh_auth_file : This parameter specifies the path and name of
     *            a us */
    public abstract void setjump_ssh_auth_file(SOSOptionInFileName p_jump_ssh_auth_file);

    /** \brief getjump_ssh_auth_method : This parameter specifies the
     * authentication method
     *
     * \details This parameter specifies the authentication method for the SSH
     * server - the publickey and password methods are supported. When the
     * publickey authentication method is used, then the path name of the
     * private key file must be set in the jump_ssh_auth_file parameter. Should
     * the private key file be secured by a passphrase then this passphrase has
     * to be specified by the jump_password parameter. For the password
     * authentication method the password for the user account has to be
     * specified using the jump_password parameter. The authentication methods
     * that are enabled depend on the SSH server configuration. Not all SSH
     * servers are configured for password authentication.
     *
     * \return This parameter specifies the authentication method */
    public abstract SOSOptionAuthenticationMethod getjump_ssh_auth_method();

    /** \brief setjump_ssh_auth_method : This parameter specifies the
     * authentication method
     *
     * \details This parameter specifies the authentication method for the SSH
     * server - the publickey and password methods are supported. When the
     * publickey authentication method is used, then the path name of the
     * private key file must be set in the jump_ssh_auth_file parameter. Should
     * the private key file be secured by a passphrase then this passphrase has
     * to be specified by the jump_password parameter. For the password
     * authentication method the password for the user account has to be
     * specified using the jump_password parameter. The authentication methods
     * that are enabled depend on the SSH server configuration. Not all SSH
     * servers are configured for password authentication.
     *
     * @param jump_ssh_auth_method : This parameter specifies the authentication
     *            method */
    public abstract void setjump_ssh_auth_method(SOSOptionAuthenticationMethod p_jump_ssh_auth_method);

    /** \brief getjump_user : User name for authentication with the jump_host.
     *
     * \details User name for authentication with the jump_host.
     *
     * \return User name for authentication with the jump_host. */
    public abstract SOSOptionUserName getjump_user();

    /** \brief setjump_user : User name for authentication with the jump_host.
     *
     * \details User name for authentication with the jump_host.
     *
     * @param jump_user : User name for authentication with the jump_host. */
    public abstract void setjump_user(SOSOptionUserName p_jump_user);

    /** \brief getlocal_dir : local_dir Local directory into which or from which
     *
     * \details Local directory into which or from which files should be
     * transferred. By default the current working directory is used. Besides
     * paths in the local file system UNC path names are supported that could be
     * used to address remote server systems: \\somehost\somedirectory can be
     * used in the same way as //somehost/somedirectory to transfer files from
     * an FTP/SFTP server to a different remote server system. Moreover, you
     * could specify URIs for a file schema as in
     * file:////somehost/somedirectory. Please, consider the required number of
     * slashes. file URIs are subject to the following limitations due to
     * constraints of the underlying Java JRE: File names and path names must
     * not contain any spaces. Authentication by authority strings as in
     * file:////user:password@somehost/somedirectory is not supported.
     *
     * \return local_dir Local directory into which or from which */
    public abstract SOSOptionFolderName getlocal_dir();

    public abstract SOSOptionFolderName SourceDir();

    public abstract SOSOptionFolderName TargetDir();

    /** \brief setlocal_dir : local_dir Local directory into which or from which
     *
     * \details Local directory into which or from which files should be
     * transferred. By default the current working directory is used. Besides
     * paths in the local file system UNC path names are supported that could be
     * used to address remote server systems: \\somehost\somedirectory can be
     * used in the same way as //somehost/somedirectory to transfer files from
     * an FTP/SFTP server to a different remote server system. Moreover, you
     * could specify URIs for a file schema as in
     * file:////somehost/somedirectory. Please, consider the required number of
     * slashes. file URIs are subject to the following limitations due to
     * constraints of the underlying Java JRE: File names and path names must
     * not contain any spaces. Authentication by authority strings as in
     * file:////user:password@somehost/somedirectory is not supported.
     *
     * @param local_dir : local_dir Local directory into which or from which */
    public abstract void setlocal_dir(SOSOptionFolderName p_local_dir);

    /** \brief getlog_filename : Name (and Pathname) of the logging-file This pa
     *
     * \details This parameter specifies the location of a file to which the log
     * output will be written. Should the file not exist then it will be
     * created. If the already exists then all log output will be appended.
     * Without specifying this parameter all log output will be written to
     * stdout.
     *
     * \return Name (and Pathname) of the logging-file This pa */
    public abstract SOSOptionLogFileName getlog_filename();

    /** \brief setlog_filename : Name (and Pathname) of the logging-file This pa
     *
     * \details This parameter specifies the location of a file to which the log
     * output will be written. Should the file not exist then it will be
     * created. If the already exists then all log output will be appended.
     * Without specifying this parameter all log output will be written to
     * stdout.
     *
     * @param log_filename : Name (and Pathname) of the logging-file This pa */
    public abstract void setlog_filename(SOSOptionLogFileName p_log_filename);

    /** \brief getmandator : This parameter specifies the mandator for which a
     *
     * \details This parameter specifies the mandator for which a file transfer
     * is effected. The mandator is added to an optional history file, see
     * parameter history and has no technical relevance for the transfer.
     *
     * \return This parameter specifies the mandator for which a */
    public abstract SOSOptionString getmandator();

    /** \brief setmandator : This parameter specifies the mandator for which a
     *
     * \details This parameter specifies the mandator for which a file transfer
     * is effected. The mandator is added to an optional history file, see
     * parameter history and has no technical relevance for the transfer.
     *
     * @param mandator : This parameter specifies the mandator for which a */
    public abstract void setmandator(SOSOptionString p_mandator);

    /** \brief getoperation : Operation to be executed send, receive, remove,
     *
     * \details send, receive, remove, execute or install. send - transfer files
     * by FTP/SFTP to a remote server receive - transfer files by FTP/SFTP from
     * a remote server remove - remove files by FTP/SFTP on a remote server
     * execute - execute a command by SSH on a remote server install - install
     * SOSFTP on a remote server
     *
     * \return Operation to be executed send, receive, remove, */
    public abstract SOSOptionJadeOperation getoperation();

    /** \brief setoperation : Operation to be executed send, receive, remove,
     *
     * \details send, receive, remove, execute or install. send - transfer files
     * by FTP/SFTP to a remote server receive - transfer files by FTP/SFTP from
     * a remote server remove - remove files by FTP/SFTP on a remote server
     * execute - execute a command by SSH on a remote server install - install
     * SOSFTP on a remote server
     *
     * @param operation : Operation to be executed send, receive, remove, */
    public abstract void setoperation(SOSOptionJadeOperation p_operation);

    /** \brief getoverwrite_files : This parameter specifies if existing files
     * should
     *
     * \details This parameter specifies if existing files should be
     * overwritten. Should this parameter be used with force_files und should no
     * files be transferred due to overwrite protection then an error will be
     * raised stating that "no matching files" could be found.
     *
     * \return This parameter specifies if existing files should */
    public abstract SOSOptionBoolean getoverwrite_files();

    /** \brief setoverwrite_files : This parameter specifies if existing files
     * should
     *
     * \details This parameter specifies if existing files should be
     * overwritten. Should this parameter be used with force_files und should no
     * files be transferred due to overwrite protection then an error will be
     * raised stating that "no matching files" could be found.
     *
     * @param overwrite_files : This parameter specifies if existing files
     *            should */
    public abstract void setoverwrite_files(SOSOptionBoolean p_overwrite_files);

    /** \brief getpassive_mode : passive_mode Passive mode for FTP is often used
     * wit
     *
     * \details Passive mode for FTP is often used with firewalls. Valid values
     * are 0 or 1.
     *
     * \return passive_mode Passive mode for FTP is often used wit */
    public abstract SOSOptionBoolean getpassive_mode();

    /** \brief setpassive_mode : passive_mode Passive mode for FTP is often used
     * wit
     *
     * \details Passive mode for FTP is often used with firewalls. Valid values
     * are 0 or 1.
     *
     * @param passive_mode : passive_mode Passive mode for FTP is often used wit */
    public abstract void setpassive_mode(SOSOptionBoolean p_passive_mode);

    /** \brief getpassword : Password for UserID Password for a
     *
     * \details Password for authentication at the FTP/SFTP server. For SSH/SFTP
     * connections that make use of public/private key authentication the
     * password parameter is specified for the passphrase that optionally
     * secures a private key.
     *
     * \return Password for UserID Password for a */
    public abstract SOSOptionPassword getpassword();

    /** \brief setpassword : Password for UserID Password for a
     *
     * \details Password for authentication at the FTP/SFTP server. For SSH/SFTP
     * connections that make use of public/private key authentication the
     * password parameter is specified for the passphrase that optionally
     * secures a private key.
     *
     * @param password : Password for UserID Password for a */
    public abstract void setpassword(SOSOptionPassword p_password);

    /** \brief getpoll_interval : This parameter specifies the interval in
     * seconds
     *
     * \details This parameter specifies the interval in seconds, how often a
     * file is polled for within the duration that is specified by the parameter
     * poll_timeout.
     *
     * \return This parameter specifies the interval in seconds */
    public abstract SOSOptionTime getpoll_interval();

    /** \brief setpoll_interval : This parameter specifies the interval in
     * seconds
     *
     * \details This parameter specifies the interval in seconds, how often a
     * file is polled for within the duration that is specified by the parameter
     * poll_timeout.
     *
     * @param poll_interval : This parameter specifies the interval in seconds */
    public abstract void setpoll_interval(SOSOptionTime p_poll_interval);

    /** \brief getpoll_minfiles : This parameter specifies the number of files
     * tha
     *
     * \details This parameter specifies the number of files that have to be
     * found during the polling period in order to cause the transfer to start.
     * This parameter is used exclusively with the parameters poll_timeout.
     *
     * \return This parameter specifies the number of files tha */
    public abstract SOSOptionInteger getpoll_minfiles();

    /** \brief setpoll_minfiles : This parameter specifies the number of files
     * tha
     *
     * \details This parameter specifies the number of files that have to be
     * found during the polling period in order to cause the transfer to start.
     * This parameter is used exclusively with the parameters poll_timeout.
     *
     * @param poll_minfiles : This parameter specifies the number of files tha */
    public abstract void setpoll_minfiles(SOSOptionInteger p_poll_minfiles);

    /** \brief getpoll_timeout : This parameter specifies the time in minutes,
     * how
     *
     * \details This parameter specifies the time in minutes, how long a file is
     * polled for. If a file becomes available within the time specified then it
     * will be transferred, otherwise an error "no matching files" will be
     * raised.
     *
     * \return This parameter specifies the time in minutes, how */
    public abstract SOSOptionInteger getpoll_timeout();

    /** \brief setpoll_timeout : This parameter specifies the time in minutes,
     * how
     *
     * \details This parameter specifies the time in minutes, how long a file is
     * polled for. If a file becomes available within the time specified then it
     * will be transferred, otherwise an error "no matching files" will be
     * raised.
     *
     * @param poll_timeout : This parameter specifies the time in minutes, how */
    public abstract void setpoll_timeout(SOSOptionInteger p_poll_timeout);

    /** \brief getport : Port-Number to be used for Data-Transfer
     *
     * \details Port by which files should be transferred. For FTP this is
     * usually port 21, for SFTP this is usually port 22.
     *
     * \return Port-Number to be used for Data-Transfer */
    public abstract SOSOptionPortNumber getport();

    /** \brief setport : Port-Number to be used for Data-Transfer
     *
     * \details Port by which files should be transferred. For FTP this is
     * usually port 21, for SFTP this is usually port 22.
     *
     * @param port : Port-Number to be used for Data-Transfer */
    public abstract void setport(SOSOptionPortNumber p_port);

    /** \brief getppid : This parameter is used for Unix systems and - as o
     *
     * \details This parameter is used for Unix systems and - as opposed to
     * other parameters - is usually specified in the start script sosftp.sh.
     * The value of the environment variable $PPID is assigned, that contains
     * the process id of the current parent process (PPID). The parent process
     * id is used when writing an entry to a history file for each transfer (see
     * parameter history).
     *
     * \return This parameter is used for Unix systems and - as o */
    public abstract SOSOptionProcessID getppid();

    /** \brief setppid : This parameter is used for Unix systems and - as o
     *
     * \details This parameter is used for Unix systems and - as opposed to
     * other parameters - is usually specified in the start script sosftp.sh.
     * The value of the environment variable $PPID is assigned, that contains
     * the process id of the current parent process (PPID). The parent process
     * id is used when writing an entry to a history file for each transfer (see
     * parameter history).
     *
     * @param ppid : This parameter is used for Unix systems and - as o */
    public abstract void setppid(SOSOptionProcessID p_ppid);

    /** \brief getprofile : The Name of a Profile-Section to be executed
     *
     * \details If a configuration file is being used (see parameter settings),
     * then this parameter specifies a name of a section within the
     * configuration file. Such sections, i.e. profiles, specify parameters as
     * pairs of names and values that otherwise would be specified by command
     * line parameters. At the command line the name of the configuration file
     * and the profile are specified like this: sosftp.sh -operation=send
     * -settings=settings.ini -profile=sample_transfer ...
     *
     * \return The Name of a Profile-Section to be executed */
    public abstract SOSOptionString getprofile();

    /** \brief setprofile : The Name of a Profile-Section to be executed
     *
     * \details If a configuration file is being used (see parameter settings),
     * then this parameter specifies a name of a section within the
     * configuration file. Such sections, i.e. profiles, specify parameters as
     * pairs of names and values that otherwise would be specified by command
     * line parameters. At the command line the name of the configuration file
     * and the profile are specified like this: sosftp.sh -operation=send
     * -settings=settings.ini -profile=sample_transfer ...
     *
     * @param profile : The Name of a Profile-Section to be executed */
    public abstract void setprofile(SOSOptionString p_profile);

    /** \brief getprotocol : Type of requested Datatransfer The values ftp, sftp
     *
     * \details The values ftp, sftp or ftps are valid for this parameter. If
     * sftp is used, then the ssh_* parameters will be applied.
     *
     * \return Type of requested Datatransfer The values ftp, sftp */
    public abstract SOSOptionTransferType getprotocol();

    /** \brief setprotocol : Type of requested Datatransfer The values ftp, sftp
     *
     * \details The values ftp, sftp or ftps are valid for this parameter. If
     * sftp is used, then the ssh_* parameters will be applied.
     *
     * @param protocol : Type of requested Datatransfer The values ftp, sftp */
    public abstract void setprotocol(SOSOptionTransferType p_protocol);

    /** \brief getrecursive : This parameter specifies if files from subdirector
     *
     * \details This parameter specifies if files from subdirectories should be
     * transferred recursively. Recursive processing is specified by one of the
     * values yes or true. Regular expression matches apply to files from
     * subdirectories as specified by the parameter file_spec.
     *
     * \return This parameter specifies if files from subdirector */
    public abstract SOSOptionBoolean getrecursive();

    /** \brief setrecursive : This parameter specifies if files from subdirector
     *
     * \details This parameter specifies if files from subdirectories should be
     * transferred recursively. Recursive processing is specified by one of the
     * values yes or true. Regular expression matches apply to files from
     * subdirectories as specified by the parameter file_spec.
     *
     * @param recursive : This parameter specifies if files from subdirector */
    public abstract void setrecursive(SOSOptionBoolean p_recursive);

    /** \brief getremote_dir : remote_dir Directory at the FTP/SFTP server from
     * wh
     *
     * \details Directory at the FTP/SFTP server from which or to which files
     * should be transferred. By default the home directory of the user at the
     * FTP/SFTP server is used.
     *
     * \return remote_dir Directory at the FTP/SFTP server from wh */
    public abstract SOSOptionFolderName getremote_dir();

    /** \brief setremote_dir : remote_dir Directory at the FTP/SFTP server from
     * wh
     *
     * \details Directory at the FTP/SFTP server from which or to which files
     * should be transferred. By default the home directory of the user at the
     * FTP/SFTP server is used.
     *
     * @param remote_dir : remote_dir Directory at the FTP/SFTP server from wh */
    public abstract void setremote_dir(SOSOptionFolderName p_remote_dir);

    /** \brief getremove_files : This parameter specifies whether files on the
     * FTP/
     *
     * \details This parameter specifies whether files on the FTP/SFTP server
     * should be removed after transfer.
     *
     * \return This parameter specifies whether files on the FTP/ */
    public abstract SOSOptionBoolean getremove_files();

    /** \brief setremove_files : This parameter specifies whether files on the
     * FTP/
     *
     * \details This parameter specifies whether files on the FTP/SFTP server
     * should be removed after transfer.
     *
     * @param remove_files : This parameter specifies whether files on the FTP/ */
    public abstract void setremove_files(SOSOptionBoolean p_remove_files);

    /** \brief getreplacement : String for replacement of matching character
     * seque
     *
     * \details String for replacement of matching character sequences within
     * file names that are specified with the value of the parameter replacing.
     * If multiple "capturing groups" shall be replaced then one replacement
     * string per group has to be specified. These strings are separated by a
     * semicolon ";": replacement: aa;[filename:];bb Supports masks for
     * substitution in the file name with format strings that are enclosed with
     * [ and ] . The following format strings are supported: [date: date format
     * ] date format must be a valid Java data format string, e.g.
     * yyyyMMddHHmmss , yyyy-MM-dd.HHmmss etc. [filename:] Will be substituted
     * by the original file name including the file extension.
     * [filename:lowercase] Will be substituted by the original file name
     * including the file extension with all characters converted to lower case.
     * [filename:uppercase] Will be substituted by the original file name
     * including the file extension with all characters converted to upper case.
     * Requires the parameter replacing to be specified.
     *
     * \return String for replacement of matching character seque */
    public abstract SOSOptionString getreplacement();

    /** \brief setreplacement : String for replacement of matching character
     * seque
     *
     * \details String for replacement of matching character sequences within
     * file names that are specified with the value of the parameter replacing.
     * If multiple "capturing groups" shall be replaced then one replacement
     * string per group has to be specified. These strings are separated by a
     * semicolon ";": replacement: aa;[filename:];bb Supports masks for
     * substitution in the file name with format strings that are enclosed with
     * [ and ] . The following format strings are supported: [date: date format
     * ] date format must be a valid Java data format string, e.g.
     * yyyyMMddHHmmss , yyyy-MM-dd.HHmmss etc. [filename:] Will be substituted
     * by the original file name including the file extension.
     * [filename:lowercase] Will be substituted by the original file name
     * including the file extension with all characters converted to lower case.
     * [filename:uppercase] Will be substituted by the original file name
     * including the file extension with all characters converted to upper case.
     * Requires the parameter replacing to be specified.
     *
     * @param replacement : String for replacement of matching character seque */
    public abstract void setreplacement(SOSOptionString p_replacement);

    /** \brief getreplacing : Regular expression for filename replacement with
     *
     * \details Regular expression for filename replacement with the value of
     * the parameter replacement. If the expression matches the filename then
     * the groups found are replaced. a) For replacement "capturing groups" are
     * used. Only the content of the capturing groups is replaced. Replacements
     * are separated by a semicolon ";". Example: replacing : (1)abc(12)def(.*)
     * replacement : A;BB;CCC Input file: 1abc12def123.txt Output file:
     * AabcBBdefCCC b) If no "capturing groups" are specified then the entire
     * match is replaced. Example: replacing : Hello replacement : 1234 Input
     * file: Hello_World.txt Output file: 1234_World.txt Requires the parameter
     * replacement to be specified.
     *
     * \return Regular expression for filename replacement with */
    public abstract SOSOptionRegExp getreplacing();

    /** \brief setreplacing : Regular expression for filename replacement with
     *
     * \details Regular expression for filename replacement with the value of
     * the parameter replacement. If the expression matches the filename then
     * the groups found are replaced. a) For replacement "capturing groups" are
     * used. Only the content of the capturing groups is replaced. Replacements
     * are separated by a semicolon ";". Example: replacing : (1)abc(12)def(.*)
     * replacement : A;BB;CCC Input file: 1abc12def123.txt Output file:
     * AabcBBdefCCC b) If no "capturing groups" are specified then the entire
     * match is replaced. Example: replacing : Hello replacement : 1234 Input
     * file: Hello_World.txt Output file: 1234_World.txt Requires the parameter
     * replacement to be specified.
     *
     * @param replacing : Regular expression for filename replacement with */
    public abstract void setreplacing(SOSOptionRegExp p_replacing);

    /** \brief getroot : The parameter specifies the directory in which thi
     *
     * \details The parameter specifies the directory in which this program is
     * allowed to create temporary files. Temporary files are required if due to
     * the parameter setting jump_host files have to be stored on an
     * intermediary server and will be removed after completion of the transfer.
     * Without this parameter the temporary directory is used as provided by the
     * operating system.
     *
     * \return The parameter specifies the directory in which thi */
    public abstract SOSOptionFolderName getroot();

    /** \brief setroot : The parameter specifies the directory in which thi
     *
     * \details The parameter specifies the directory in which this program is
     * allowed to create temporary files. Temporary files are required if due to
     * the parameter setting jump_host files have to be stored on an
     * intermediary server and will be removed after completion of the transfer.
     * Without this parameter the temporary directory is used as provided by the
     * operating system.
     *
     * @param root : The parameter specifies the directory in which thi */
    public abstract void setroot(SOSOptionFolderName p_root);

    /** \brief getscheduler_host : This parameter specifies the host name or IP
     * addre
     *
     * \details This parameter specifies the host name or IP address of a server
     * for which Job Scheduler is operated for Managed File Transfer. The
     * contents of an optional history file (see parameter history), is added to
     * a central database by Job Scheduler. This parameter causes the transfer
     * of the history entries for the current transfer by UDP to Job Scheduler.
     * Should Job Scheduler not be accessible then no errors are reported,
     * instead, the contents of the history will automaticall be processed later
     * on.
     *
     * \return This parameter specifies the host name or IP addre */
    public abstract SOSOptionHostName getscheduler_host();

    /** \brief setscheduler_host : This parameter specifies the host name or IP
     * addre
     *
     * \details This parameter specifies the host name or IP address of a server
     * for which Job Scheduler is operated for Managed File Transfer. The
     * contents of an optional history file (see parameter history), is added to
     * a central database by Job Scheduler. This parameter causes the transfer
     * of the history entries for the current transfer by UDP to Job Scheduler.
     * Should Job Scheduler not be accessible then no errors are reported,
     * instead, the contents of the history will automaticall be processed later
     * on.
     *
     * @param scheduler_host : This parameter specifies the host name or IP
     *            addre */
    public abstract void setscheduler_host(SOSOptionHostName p_scheduler_host);

    /** \brief getscheduler_job_chain : The name of a job chain for Managed File
     * Transfer
     *
     * \details The name of a job chain for Managed File Transfer with Job
     * Scheduler, see parameter scheduler_host. The job chain accepts history
     * entries and performs an import into a central database.
     *
     * \return The name of a job chain for Managed File Transfer */
    public abstract JSJobChain getscheduler_job_chain();

    /** \brief setscheduler_job_chain : The name of a job chain for Managed File
     * Transfer
     *
     * \details The name of a job chain for Managed File Transfer with Job
     * Scheduler, see parameter scheduler_host. The job chain accepts history
     * entries and performs an import into a central database.
     *
     * @param scheduler_job_chain : The name of a job chain for Managed File
     *            Transfer */
    public abstract void setscheduler_job_chain(JSJobChain p_scheduler_job_chain);

    /** \brief getscheduler_port : The port for which a Job Scheduler for Managed
     * File Trans
     *
     * \details The port for which a Job Scheduler for Managed File Transfer is
     * operated, see parameter scheduler_host.
     *
     * \return The port for which a Job Scheduler for Managed File Trans */
    public abstract SOSOptionPortNumber getscheduler_port();

    /** \brief setscheduler_port : The port for which a Job Scheduler for Managed
     * File Trans
     *
     * \details The port for which a Job Scheduler for Managed File Transfer is
     * operated, see parameter scheduler_host.
     *
     * @param scheduler_port : The port for which a Job Scheduler for Managed
     *            File Trans */
    public abstract void setscheduler_port(SOSOptionPortNumber p_scheduler_port);

    /** \brief getsettings : Name of INI-File which contains the profiles to
     * execute
     *
     * \details A configuration (INI-) file can be specified that contains
     * profiles, i.e. sections, with parameters specified as pairs of names and
     * values in a plain text format like this: [sample_transfer] protocol = ftp
     * host = localhost port = 21 local_dir = /tmp ... At the command line the
     * name of the configuration file and the profile are specified like this:
     * sosftp.sh -operation=send -settings=settings.ini -profile=sample_transfer
     * ... A profile can reference the contents of other profiles like this:
     * [default] history = /sosftp/transfer_history.csv mandator = SOS
     * scheduler_host = localhost scheduler_port = 4444 [sample_transfer]
     * include = default protocol = ftp host = www.sos-berlin.com port = 21
     * local_dir = /tmp ... With this sample the profile sample_transfer
     * includes the default profile via the include directive and thus applies
     * the file transfer history settings.
     *
     * \return Name of INI-File which contains the profiles to execute */
    public abstract SOSOptionIniFileName getsettings();

    /** \brief setsettings : Name of INI-File which contains the profiles to
     * execute
     *
     * \details A configuration (INI-) file can be specified that contains
     * profiles, i.e. sections, with parameters specified as pairs of names and
     * values in a plain text format like this: [sample_transfer] protocol = ftp
     * host = localhost port = 21 local_dir = /tmp ... At the command line the
     * name of the configuration file and the profile are specified like this:
     * sosftp.sh -operation=send -settings=settings.ini -profile=sample_transfer
     * ... A profile can reference the contents of other profiles like this:
     * [default] history = /sosftp/transfer_history.csv mandator = SOS
     * scheduler_host = localhost scheduler_port = 4444 [sample_transfer]
     * include = default protocol = ftp host = www.sos-berlin.com port = 21
     * local_dir = /tmp ... With this sample the profile sample_transfer
     * includes the default profile via the include directive and thus applies
     * the file transfer history settings.
     *
     * @param settings : Name of INI-File which contains the profiles to execute */
    public abstract void setsettings(SOSOptionIniFileName p_settings);

    /** \brief getskip_transfer : If this Parameter is set to true then
     *
     * \details If this Parameter is set to true then all operations except for
     * the transfer itself will be performed. This can be used to just trigger
     * for files or to only delete files on the FTP/SFTP server.
     *
     * \return If this Parameter is set to true then */
    public abstract SOSOptionBoolean getskip_transfer();

    /** \brief setskip_transfer : If this Parameter is set to true then
     *
     * \details If this Parameter is set to true then all operations except for
     * the transfer itself will be performed. This can be used to just trigger
     * for files or to only delete files on the FTP/SFTP server.
     *
     * @param skip_transfer : If this Parameter is set to true then */
    public abstract void setskip_transfer(SOSOptionBoolean p_skip_transfer);

    /** \brief getssh_auth_file : This parameter specifies the path and name of a
     * us
     *
     * \details This parameter specifies the path and name of a user's private
     * key file that is used for authentication with an SSH server. This
     * parameter has to be specified should the publickey authentication method
     * have been specified in the ssh_auth_method parameter. Should the private
     * key file be secured by a passphrase, then the passphrase has to be
     * specified using the password parameter.
     *
     * \return This parameter specifies the path and name of a us */
    public abstract SOSOptionInFileName getssh_auth_file();

    /** \brief setssh_auth_file : This parameter specifies the path and name of a
     * us
     *
     * \details This parameter specifies the path and name of a user's private
     * key file that is used for authentication with an SSH server. This
     * parameter has to be specified should the publickey authentication method
     * have been specified in the ssh_auth_method parameter. Should the private
     * key file be secured by a passphrase, then the passphrase has to be
     * specified using the password parameter.
     *
     * @param ssh_auth_file : This parameter specifies the path and name of a us */
    public abstract void setssh_auth_file(SOSOptionInFileName p_ssh_auth_file);

    /** \brief getssh_auth_method : This parameter specifies the authentication
     * method
     *
     * \details This parameter specifies the authentication method for the SSH
     * server - the publickey and password methods are supported. Should the
     * publickey authentication method be used, then the path name of the
     * private key file has to be specified with the ssh_auth_file parameter.
     * Should the private key file be secured by a passphrase then the
     * passphrase has to be specified with the password parameter. For the
     * password authentication method the password for the user account has to
     * be specified using the password parameter. The authentication methods
     * that are enabled depend on the SSH server configuration. Not all SSH
     * servers are configured for password authentication.
     *
     * \return This parameter specifies the authentication method */
    public abstract SOSOptionAuthenticationMethod getssh_auth_method();

    /** \brief setssh_auth_method : This parameter specifies the authentication
     * method
     *
     * \details This parameter specifies the authentication method for the SSH
     * server - the publickey and password methods are supported. Should the
     * publickey authentication method be used, then the path name of the
     * private key file has to be specified with the ssh_auth_file parameter.
     * Should the private key file be secured by a passphrase then the
     * passphrase has to be specified with the password parameter. For the
     * password authentication method the password for the user account has to
     * be specified using the password parameter. The authentication methods
     * that are enabled depend on the SSH server configuration. Not all SSH
     * servers are configured for password authentication.
     *
     * @param ssh_auth_method : This parameter specifies the authentication
     *            method */
    public abstract void setssh_auth_method(SOSOptionAuthenticationMethod p_ssh_auth_method);

    /** \brief getssh_proxy_host : The value of this parameter is the host name
     * or th
     *
     * \details The value of this parameter is the host name or the IP address
     * of a proxy that is used in order to establish a connection to the SSH
     * server. The use of a proxy is optional.
     *
     * \return The value of this parameter is the host name or th */
    public abstract SOSOptionString getssh_proxy_host();

    /** \brief setssh_proxy_host : The value of this parameter is the host name
     * or th
     *
     * \details The value of this parameter is the host name or the IP address
     * of a proxy that is used in order to establish a connection to the SSH
     * server. The use of a proxy is optional.
     *
     * @param ssh_proxy_host : The value of this parameter is the host name or
     *            th */
    public abstract void setssh_proxy_host(SOSOptionString p_ssh_proxy_host);

    /** \brief getssh_proxy_password : This parameter specifies the password for
     * the prox
     *
     * \details This parameter specifies the password for the proxy server user
     * account, should a proxy be used in order to connect to the SSH server.
     *
     * \return This parameter specifies the password for the prox */
    public abstract SOSOptionString getssh_proxy_password();

    /** \brief setssh_proxy_password : This parameter specifies the password for
     * the prox
     *
     * \details This parameter specifies the password for the proxy server user
     * account, should a proxy be used in order to connect to the SSH server.
     *
     * @param ssh_proxy_password : This parameter specifies the password for the
     *            prox */
    public abstract void setssh_proxy_password(SOSOptionString p_ssh_proxy_password);

    /** \brief getssh_proxy_port : This parameter specifies the port number of
     * the pr
     *
     * \details This parameter specifies the port number of the proxy, should a
     * proxy be used in order to establish a connection to the SSH server.
     *
     * \return This parameter specifies the port number of the pr */
    public abstract SOSOptionString getssh_proxy_port();

    /** \brief setssh_proxy_port : This parameter specifies the port number of
     * the pr
     *
     * \details This parameter specifies the port number of the proxy, should a
     * proxy be used in order to establish a connection to the SSH server.
     *
     * @param ssh_proxy_port : This parameter specifies the port number of the
     *            pr */
    public abstract void setssh_proxy_port(SOSOptionString p_ssh_proxy_port);

    /** \brief getssh_proxy_user : The value of this parameter specifies the user
     * acc
     *
     * \details The value of this parameter specifies the user account for
     * authentication by the proxy server should a proxy be used in order to
     * connect to the SSH server.
     *
     * \return The value of this parameter specifies the user acc */
    public abstract SOSOptionString getssh_proxy_user();

    /** \brief setssh_proxy_user : The value of this parameter specifies the user
     * acc
     *
     * \details The value of this parameter specifies the user account for
     * authentication by the proxy server should a proxy be used in order to
     * connect to the SSH server.
     *
     * @param ssh_proxy_user : The value of this parameter specifies the user
     *            acc */
    public abstract void setssh_proxy_user(SOSOptionString p_ssh_proxy_user);

    /** \brief gettransactional : This parameter specifies if file transfers
     * should
     *
     * \details This parameter specifies if file transfers should be operated
     * within a single transaction, i.e. either all files are successfully
     * transferred or none. Should an error occur during a transfer operation
     * then all transfers will be rolled back. When specifying the value true
     * then the following applies: The parameter atomic_suffix has to be
     * specified that causes target files to be created with a suffix such as
     * "~" and that causes the respective files to be renamed to their target
     * file name after the transfer of all files has been successfully
     * completed. If at least one file out of a set of files cannot be
     * transferred successfully then no files will be renamed, instead the
     * temporarily created files are removed from the target system. The
     * parameter remove_files that causes files to be removed after successful
     * transfer will be effective only after all files have been successfully
     * transferred. Otherwise no files will be removed.
     *
     * \return This parameter specifies if file transfers should */
    public abstract SOSOptionBoolean gettransactional();

    /** \brief settransactional : This parameter specifies if file transfers
     * should
     *
     * \details This parameter specifies if file transfers should be operated
     * within a single transaction, i.e. either all files are successfully
     * transferred or none. Should an error occur during a transfer operation
     * then all transfers will be rolled back. When specifying the value true
     * then the following applies: The parameter atomic_suffix has to be
     * specified that causes target files to be created with a suffix such as
     * "~" and that causes the respective files to be renamed to their target
     * file name after the transfer of all files has been successfully
     * completed. If at least one file out of a set of files cannot be
     * transferred successfully then no files will be renamed, instead the
     * temporarily created files are removed from the target system. The
     * parameter remove_files that causes files to be removed after successful
     * transfer will be effective only after all files have been successfully
     * transferred. Otherwise no files will be removed.
     *
     * @param transactional : This parameter specifies if file transfers should */
    public abstract void settransactional(SOSOptionBoolean p_transactional);

    /** \brief gettransfer_mode : Type of Character-Encoding Transfe
     *
     * \details Transfer mode is used for FTP exclusively and can be either
     * ascii or binary.
     *
     * \return Type of Character-Encoding Transfe */
    public abstract SOSOptionTransferMode gettransfer_mode();

    /** \brief settransfer_mode : Type of Character-Encoding Transfe
     *
     * \details Transfer mode is used for FTP exclusively and can be either
     * ascii or binary.
     *
     * @param transfer_mode : Type of Character-Encoding Transfe */
    public abstract void settransfer_mode(SOSOptionTransferMode p_transfer_mode);

    /** \brief getuser : UserID of user in charge User name
     *
     * \details User name for authentication at the (FTP/SFTP) server.
     *
     * \return UserID of user in charge User name */
    public abstract SOSOptionUserName getuser();

    /** \brief setuser : UserID of user in charge User name
     *
     * \details User name for authentication at the (FTP/SFTP) server.
     *
     * @param user : UserID of user in charge User name */
    public abstract void setuser(SOSOptionUserName p_user);

    /** \brief getverbose : The granuality of (Debug-)Messages The verbosit
     *
     * \details The verbosity level specifies the intensity of log entries. A
     * value between 1 and 9 can be specified. Higher values cause more detailed
     * information to be logged. Log output is written to stdout or to a file
     * that has been specified with the parameter log_filename.
     *
     * \return The granuality of (Debug-)Messages The verbosit */
    public abstract SOSOptionInteger getverbose();

    /** \brief setverbose : The granuality of (Debug-)Messages The verbosit
     *
     * \details The verbosity level specifies the intensity of log entries. A
     * value between 1 and 9 can be specified. Higher values cause more detailed
     * information to be logged. Log output is written to stdout or to a file
     * that has been specified with the parameter log_filename.
     *
     * @param verbose : The granuality of (Debug-)Messages The verbosit */
    public abstract void setverbose(SOSOptionInteger p_verbose);

    /** \brief setAllOptions - �bernimmt die OptionenWerte aus der HashMap
     *
     * \details In der als Parameter anzugebenden HashMap sind Schl�ssel (Name)
     * und Wert der jeweiligen Option als Paar angegeben. Ein Beispiel f�r den
     * Aufbau einer solchen HashMap findet sich in der Beschreibung dieser
     * Klasse (\ref TestData "setJobSchedulerSSHJobOptions"). In dieser Routine
     * werden die Schl�ssel analysiert und, falls gefunden, werden die
     * dazugeh�rigen Werte den Properties dieser Klasse zugewiesen.
     *
     * Nicht bekannte Schl�ssel werden ignoriert.
     *
     * \see JSOptionsClass::getItem
     *
     * @param pobjJSSettings
     * @throws Exception */
    public abstract void setAllOptions(HashMap<String, String> pobjJSSettings) throws Exception; // public
                                                                                                 // void
                                                                                                 // setAllOptions
                                                                                                 // (HashMap
                                                                                                 // <String,
                                                                                                 // String>
                                                                                                 // JSSettings)

    /** \brief CheckMandatory - pr�ft alle Muss-Optionen auf Werte
     *
     * \details
     * 
     * @throws Exception
     *
     * @throws Exception - wird ausgel�st, wenn eine mandatory-Option keinen
     *             Wert hat */
    public abstract void CheckMandatory() throws com.sos.JSHelper.Exceptions.JSExceptionMandatoryOptionMissing //
    ; // public void CheckMandatory ()

    /** \brief CommandLineArgs - �bernehmen der Options/Settings aus der
     * Kommandozeile
     *
     * \details Die in der Kommandozeile beim Starten der Applikation
     * angegebenen Parameter werden hier in die HashMap �bertragen und danach
     * den Optionen als Wert zugewiesen.
     *
     * \return void
     *
     * @param pstrArgs
     * @throws Exception */
    public abstract void CommandLineArgs(String[] pstrArgs) throws Exception;

    public abstract SOSOptionHostName getHost();

    public abstract SOSOptionPortNumber getPort();

    public abstract SOSOptionString getProxy_host();

    public abstract SOSOptionPassword getProxy_password();

    public abstract SOSOptionPortNumber getProxy_port();

    public abstract SOSOptionUserName getProxy_user();

    public abstract void setHost(SOSOptionHostName host);

    public abstract void setPort(SOSOptionPortNumber port);

    public abstract void setProxy_host(SOSOptionString proxyHost);

    public abstract void setProxy_password(SOSOptionPassword proxyPassword);

    public abstract void setProxy_port(SOSOptionPortNumber proxyPort);

    public abstract void setProxy_user(SOSOptionUserName proxyUser);

    public abstract SOSOptionInFileName getAuth_file();

    public abstract SOSOptionAuthenticationMethod getAuth_method();

    public abstract SOSOptionPassword getPassword();

    public abstract SOSOptionUserName getUser();

    public abstract void setAuth_file(SOSOptionInFileName authFile);

    public abstract void setAuth_method(SOSOptionAuthenticationMethod authMethod);

    public abstract void setPassword(SOSOptionPassword password);

    public abstract void setUser(SOSOptionUserName user);

    public abstract boolean isAtomicTransfer();
}