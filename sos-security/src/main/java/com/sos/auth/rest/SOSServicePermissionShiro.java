package com.sos.auth.rest;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;

import com.sos.auth.rest.permission.model.ObjectFactory;
import com.sos.auth.rest.permission.model.SOSPermissionCommands;
import com.sos.auth.rest.permission.model.SOSPermissionJocCockpit;
import com.sos.auth.rest.permission.model.SOSPermissionListCommands;
import com.sos.auth.rest.permission.model.SOSPermissionListJoc;
import com.sos.auth.rest.permission.model.SOSPermissionRoles;
import com.sos.auth.rest.permission.model.SOSPermissionShiro;
import com.sos.auth.rest.permission.model.SOSPermissions;
import com.sos.auth.shiro.SOSlogin;
import com.sos.hibernate.classes.SOSHibernateFactory;
import com.sos.joc.Globals;
import com.sos.joc.classes.JOCDefaultResponse;
import com.sos.joc.classes.JobSchedulerUser;
import com.sos.joc.classes.JocCockpitProperties;
import com.sos.joc.classes.WebserviceConstants;
import com.sos.joc.classes.audit.JocAuditLog;
import com.sos.joc.classes.audit.SecurityAudit;
import com.sos.joc.exceptions.JocError;
import com.sos.joc.exceptions.JocException;

@Path("/security")
public class SOSServicePermissionShiro {

    private static final String ROLE_ALL = "all";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String UTC = "UTC";
    private static final String UNKNOWN_USER = "*Unknown User*";
    private static final String EMPTY_STRING = "";
    private static final String USER_IS_NULL = "user is null";
    private static final String AUTHORIZATION_HEADER_WITH_BASIC_BASED64PART_EXPECTED = "Authorization Header with Basic based64part expected";
    private static final String ROLE_API_USER = "api_user";
    private static final String ROLE_BUSINESS_USER = "business_user";
    private static final String ROLE_INCIDENT_MANAGER = "incident_manager";
    private static final String ROLE_IT_OPERATOR = "it_operator";
    private static final String ROLE_APPLICATION_MANAGER = "application_manager";
    private static final String ROLE_ADMINISTRATOR = "administrator";
    private static final String ROLE_EVENTS = "events";
    private static final String ROLE_JOC = "joc";
    private static final String ROLE_JOE = "joe";
    private static final String ROLE_JID = "jid";
    private static final String ROLE_WORKINGPLAN = "workingplan";
    private static final String ROLE_CONTROLLER = "controller";
    private static final String ROLE_JOBEDITOR = "jobeditor";
    private static final String ROLE_JOC_ADMIN = "joc_admin";
    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_SUPER = "super";
    private static final Logger LOGGER = Logger.getLogger(SOSServicePermissionShiro.class);

    private SOSShiroCurrentUser currentUser;

    public JOCDefaultResponse getJocCockpitPermissions(String accessToken, String user, String pwd) {
        this.setCurrentUserfromAccessToken(accessToken, user, pwd);
        return JOCDefaultResponse.responseStatus200(createJocCockpitPermissionObject(accessToken, user, pwd));
    }

    private JOCDefaultResponse getCommandPermissions(String accessToken, String user, String pwd) {
        this.setCurrentUserfromAccessToken(accessToken, user, pwd);
        return JOCDefaultResponse.responseStatus200(createCommandsPermissionObject(accessToken, user, pwd));
    }

    @GET
    @Path("/joc_cockpit_permissions")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public JOCDefaultResponse getJocCockpitPermissions(@HeaderParam(ACCESS_TOKEN) String accessTokenFromHeader, @QueryParam(ACCESS_TOKEN) String accessTokenFromQuery,
            @QueryParam("user") String user, @QueryParam("pwd") String pwd) {
        String accessToken = getAccessToken(accessTokenFromHeader, accessTokenFromQuery);
        return getJocCockpitPermissions(accessToken, user, pwd);
    }

    @POST
    @Path("/joc_cockpit_permissions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public JOCDefaultResponse postJocCockpitPermissions(@HeaderParam(ACCESS_TOKEN) String accessTokenFromHeader) {

        SOSWebserviceAuthenticationRecord sosWebserviceAuthenticationRecord = new SOSWebserviceAuthenticationRecord();

        try {

            if (!EMPTY_STRING.equals(accessTokenFromHeader)) {
                sosWebserviceAuthenticationRecord.setAccessToken(accessTokenFromHeader);
            }

            setCurrentUserfromAccessToken(sosWebserviceAuthenticationRecord.getAccessToken(), sosWebserviceAuthenticationRecord.getUser(), sosWebserviceAuthenticationRecord
                    .getPassword());

            if (currentUser == null) {
                LOGGER.debug(USER_IS_NULL + " " + AUTHORIZATION_HEADER_WITH_BASIC_BASED64PART_EXPECTED);
                return JOCDefaultResponse.responseStatusJSError(USER_IS_NULL + " " + AUTHORIZATION_HEADER_WITH_BASIC_BASED64PART_EXPECTED);
            }

            return JOCDefaultResponse.responseStatus200(currentUser.getSosPermissionJocCockpit());
        } catch (org.apache.shiro.session.ExpiredSessionException e) {
            LOGGER.error(e.getMessage());
            SOSShiroCurrentUserAnswer sosShiroCurrentUserAnswer = createSOSShiroCurrentUserAnswer(accessTokenFromHeader, sosWebserviceAuthenticationRecord.getUser(), e
                    .getMessage());
            return JOCDefaultResponse.responseStatus440(sosShiroCurrentUserAnswer);
        }

        catch (Exception ee) {
            LOGGER.error(ee.getMessage());
            return JOCDefaultResponse.responseStatusJSError(ee.getMessage());
        }
    }

    @GET
    @Path("/command_permissions")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public JOCDefaultResponse getCommandPermissions(@HeaderParam(ACCESS_TOKEN) String accessTokenFromHeader, @QueryParam(ACCESS_TOKEN) String accessTokenFromQuery,
            @QueryParam("user") String user, @QueryParam("pwd") String pwd) {
        String accessToken = getAccessToken(accessTokenFromHeader, accessTokenFromQuery);
        return getCommandPermissions(accessToken, user, pwd);
    }

    @POST
    @Path("/command_permissions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public JOCDefaultResponse postCommandPermissions(@HeaderParam(ACCESS_TOKEN) String accessTokenFromHeader) {

        SOSWebserviceAuthenticationRecord sosWebserviceAuthenticationRecord = new SOSWebserviceAuthenticationRecord();

        try {

            if (!EMPTY_STRING.equals(accessTokenFromHeader)) {
                sosWebserviceAuthenticationRecord.setAccessToken(accessTokenFromHeader);
            }

            setCurrentUserfromAccessToken(sosWebserviceAuthenticationRecord.getAccessToken(), sosWebserviceAuthenticationRecord.getUser(), sosWebserviceAuthenticationRecord
                    .getPassword());

            if (currentUser == null) {
                LOGGER.debug(USER_IS_NULL + " " + AUTHORIZATION_HEADER_WITH_BASIC_BASED64PART_EXPECTED);
                return JOCDefaultResponse.responseStatusJSError(USER_IS_NULL + " " + AUTHORIZATION_HEADER_WITH_BASIC_BASED64PART_EXPECTED);
            }

            return JOCDefaultResponse.responseStatus200(currentUser.getSosPermissionCommands());
        } catch (org.apache.shiro.session.ExpiredSessionException e) {
            LOGGER.error(e.getMessage());
            SOSShiroCurrentUserAnswer sosShiroCurrentUserAnswer = createSOSShiroCurrentUserAnswer(accessTokenFromHeader, sosWebserviceAuthenticationRecord.getUser(), e
                    .getMessage());
            return JOCDefaultResponse.responseStatus440(sosShiroCurrentUserAnswer);
        }

        catch (Exception ee) {
            LOGGER.error(ee.getMessage());
            return JOCDefaultResponse.responseStatusJSError(ee.getMessage());
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public JOCDefaultResponse loginPost(@HeaderParam("Authorization") String basicAuthorization, @QueryParam("user") String user, @QueryParam("pwd") String pwd) {
        return login(basicAuthorization, user, pwd);
    }

    private SOSShiroCurrentUserAnswer logout(String accessTokenFromHeader, String accessTokenFromQuery) {

        String accessToken = this.getAccessToken(accessTokenFromHeader, accessTokenFromQuery);

        if (Globals.jocWebserviceDataContainer.getCurrentUsersList() != null) {
            currentUser = Globals.jocWebserviceDataContainer.getCurrentUsersList().getUser(accessToken);
        }
        String user = "";
        String comment = "";
        if (currentUser != null) {
            user = currentUser.getUsername();
        }
        LOGGER.debug(String.format("Method: %s, User: %s, access_token: %s", "logout", user, accessToken));
        try {
            currentUser.getCurrentSubject().getSession().getTimeout();
        } catch (InvalidSessionException e) {
            comment = "Session time out";
        }

        if (currentUser != null) {
            JocAuditLog jocAuditLog = new JocAuditLog(user, "./logout");
            SecurityAudit s = new SecurityAudit(comment);
            jocAuditLog.logAuditMessage(s);
            jocAuditLog.storeAuditLogEntry(s);
        } else {
            LOGGER.warn(String.format("Unknown User --> Method: %s, access_token: %s", "logout", accessToken));
        }

        try {
            Globals.forceClosingHttpClients(currentUser.getCurrentSubject().getSession(false));
            currentUser.getCurrentSubject().getSession().getTimeout();
            currentUser.getCurrentSubject().getSession().stop();

        } catch (Exception e) {
        }

        SOSShiroCurrentUserAnswer sosShiroCurrentUserAnswer = new SOSShiroCurrentUserAnswer(EMPTY_STRING);
        if (currentUser != null) {
            sosShiroCurrentUserAnswer.setUser(UNKNOWN_USER);
            sosShiroCurrentUserAnswer = new SOSShiroCurrentUserAnswer(currentUser.getUsername());
        }
        sosShiroCurrentUserAnswer.setIsAuthenticated(false);
        sosShiroCurrentUserAnswer.setHasRole(false);
        sosShiroCurrentUserAnswer.setIsPermitted(false);
        sosShiroCurrentUserAnswer.setAccessToken(EMPTY_STRING);
        Globals.jocWebserviceDataContainer.getCurrentUsersList().removeUser(accessToken);

        return sosShiroCurrentUserAnswer;
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public SOSShiroCurrentUserAnswer logoutPost(@HeaderParam(ACCESS_TOKEN) String accessTokenFromHeader) {
        return logout(accessTokenFromHeader, EMPTY_STRING);
    }

    @POST
    @Path("/db_refresh")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public JOCDefaultResponse dbRefresh() {
        try {
            if (Globals.sosHibernateFactory != null) {
                Globals.sosHibernateFactory.close();
                Globals.sosHibernateFactory.build();

            }
            if (Globals.sosSchedulerHibernateFactories != null) {
                for (SOSHibernateFactory sosHibernateFactory : Globals.sosSchedulerHibernateFactories.values()) {
                    sosHibernateFactory.close();
                    sosHibernateFactory.build();
                }
            }

            return JOCDefaultResponse.responseStatus200("Db connections reconnected");
        } catch (Exception e) {
            return JOCDefaultResponse.responseStatusJSError(e);
        }
    }

    @GET
    @Path("/role")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public SOSShiroCurrentUserAnswer hasRole(@HeaderParam(ACCESS_TOKEN) String accessTokenFromHeader, @QueryParam(ACCESS_TOKEN) String accessTokenFromQuery,
            @QueryParam("user") String user, @QueryParam("pwd") String pwd, @QueryParam("role") String role) {

        String accessToken = getAccessToken(accessTokenFromHeader, accessTokenFromQuery);

        setCurrentUserfromAccessToken(accessToken, user, pwd);

        SOSShiroCurrentUserAnswer sosShiroCurrentUserAnswer = new SOSShiroCurrentUserAnswer(currentUser.getUsername());
        sosShiroCurrentUserAnswer.setRole(role);
        sosShiroCurrentUserAnswer.setIsAuthenticated(currentUser.isAuthenticated());
        sosShiroCurrentUserAnswer.setHasRole(currentUser.hasRole(role));
        sosShiroCurrentUserAnswer.setAccessToken(currentUser.getAccessToken());

        return sosShiroCurrentUserAnswer;

    }

    @GET
    @Path("/permission")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public SOSShiroCurrentUserAnswer isPermitted(@HeaderParam(ACCESS_TOKEN) String accessTokenFromHeader, @QueryParam(ACCESS_TOKEN) String accessTokenFromQuery,
            @QueryParam("user") String user, @QueryParam("pwd") String pwd, @QueryParam("permission") String permission) {

        String accessToken = getAccessToken(accessTokenFromHeader, accessTokenFromQuery);
        setCurrentUserfromAccessToken(accessToken, user, pwd);

        SOSShiroCurrentUserAnswer sosShiroCurrentUserAnswer = new SOSShiroCurrentUserAnswer(currentUser.getUsername());
        sosShiroCurrentUserAnswer.setPermission(permission);
        sosShiroCurrentUserAnswer.setIsAuthenticated(currentUser.isAuthenticated());
        sosShiroCurrentUserAnswer.setIsPermitted(currentUser.isPermitted(permission));

        sosShiroCurrentUserAnswer.setAccessToken(currentUser.getAccessToken());

        return sosShiroCurrentUserAnswer;

    }

    private void setCurrentUserfromAccessToken(String accessToken, String user, String pwd) {
        if (Globals.jocWebserviceDataContainer.getCurrentUsersList() != null && accessToken != null && accessToken.length() > 0) {
            currentUser = Globals.jocWebserviceDataContainer.getCurrentUsersList().getUser(accessToken);
            LOGGER.debug(String.format("Method: %s, access_token: %s", "setCurrentUserfromAccessToken", accessToken));
        } else {
            if (user != null && user.length() > 0 && pwd != null && pwd.length() > 0) {
                LOGGER.debug(String.format("Method: %s, User: %s, access_token: %s", "setCurrentUserfromAccessToken", user, accessToken));
                currentUser = new SOSShiroCurrentUser(user, pwd);
                try {
                    createUser();
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }
        resetTimeOut();
    }

    @GET
    @Path("/permissions")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public SOSPermissionShiro getPermissions(@HeaderParam(ACCESS_TOKEN) String accessTokenFromHeader, @QueryParam(ACCESS_TOKEN) String accessTokenFromQuery,
            @QueryParam("user") String user, @QueryParam("pwd") String pwd) {

        String accessToken = this.getAccessToken(accessTokenFromHeader, accessTokenFromQuery);
        this.setCurrentUserfromAccessToken(accessToken, user, pwd);

        ObjectFactory o = new ObjectFactory();
        SOSPermissionShiro sosPermissionShiro = o.createSOSPermissionShiro();

        if (currentUser != null && currentUser.getCurrentSubject() != null) {

            sosPermissionShiro.setAuthenticated(currentUser.isAuthenticated());
            sosPermissionShiro.setAccessToken(currentUser.getAccessToken());
            sosPermissionShiro.setUser(currentUser.getUsername());

            SOSPermissionRoles roles = o.createSOSPermissionRoles();
            addRole(roles.getSOSPermissionRole(), ROLE_SUPER);
            addRole(roles.getSOSPermissionRole(), ROLE_ADMIN);
            addRole(roles.getSOSPermissionRole(), ROLE_JOC_ADMIN);
            addRole(roles.getSOSPermissionRole(), ROLE_JOBEDITOR);
            addRole(roles.getSOSPermissionRole(), ROLE_CONTROLLER);
            addRole(roles.getSOSPermissionRole(), ROLE_WORKINGPLAN);
            addRole(roles.getSOSPermissionRole(), ROLE_JID);
            addRole(roles.getSOSPermissionRole(), ROLE_JOE);
            addRole(roles.getSOSPermissionRole(), ROLE_JOC);
            addRole(roles.getSOSPermissionRole(), ROLE_EVENTS);
            addRole(roles.getSOSPermissionRole(), ROLE_ADMINISTRATOR);
            addRole(roles.getSOSPermissionRole(), ROLE_APPLICATION_MANAGER);
            addRole(roles.getSOSPermissionRole(), ROLE_IT_OPERATOR);
            addRole(roles.getSOSPermissionRole(), ROLE_INCIDENT_MANAGER);
            addRole(roles.getSOSPermissionRole(), ROLE_BUSINESS_USER);
            addRole(roles.getSOSPermissionRole(), ROLE_API_USER);

            SOSPermissions sosPermissions = o.createSOSPermissions();

            SOSPermissionListJoc sosPermissionJoc = o.createSOSPermissionListJoc();

            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:view:parameter");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_master:view:mainlog");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:restart:terminate");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:restart:abort");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:pause");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:continue");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:terminate");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:abort");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:stop");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:commands:jobscheduler_master:manage_categories");

            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_master_cluster:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_master_cluster:execute:terminate_fail_safe");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_master_cluster:execute:restart");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_master_cluster:execute:terminate");

            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_universal_agent:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_universal_agent:execute:restart:abort");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_universal_agent:execute:restart:terminate");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_universal_agent:execute:abort");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:jobscheduler_universal_agent:execute:terminate");

            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:daily_plan:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:history:view");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:view:configuration");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:view:order_log");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:change:start_and_end_node");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:change:time_for_adhoc_orders");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:change:parameter");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:change:run_time");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:change:state");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:change:hot_folder");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:execute:start");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:execute:update");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:execute:suspend");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:execute:resume");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order::execute:reset");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:execute:remove_setback");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:delete:permanent");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:order:delete:temporary");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:view:configuration");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:view:history");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:execute:stop");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:execute:unstop");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:execute:add_order");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:execute:skip_jobchain_node");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:execute:process_jobchain_node");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:execute:stop_jobchain_node");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job_chain:change:hot_folder");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:view:task_log");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:view:configuration");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:view:history");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:change:run_time");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:change:hot_folder");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:execute:start");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:execute:stop");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:execute:unstop");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:execute:terminate");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:execute:unstop");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:execute:kill");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:execute:end_all_tasks");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:execute:suspend_all_tasks");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:job:execute:continue_all_tasks");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:process_class:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:process_class:view:configuration");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:process_class:modify_hot_folder");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:schedule:view:configuration");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:schedule:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:schedule:add_substitute");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:schedule:change");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:schedule:modify_hot_folder");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:lock:view:configuration");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:lock:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:lock:modify_hot_folder");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:event:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:event:delete");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:event_action:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:event_action:create_event_manually");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:holiday_calendar:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:maintenance_window:view:status");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:maintenance_window:enable_disable_maintenance_window");

            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:audit_log:view:status");

            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:customization:share:view");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:customization:share:change:delete");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:customization:share:change:edit_content");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:customization:share:change:shared_status:make_private");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:customization:share:change:shared_status:make_share");

            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:customization:private:make_shared");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:customization:private:delete");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:customization:private:edit_content");
            addPermission(sosPermissionJoc.getSOSPermission(), "sos:products:joc_cockpit:customization:private:view");

            sosPermissions.setSOSPermissionListJoc(sosPermissionJoc);
            SOSPermissionListCommands sosPermissionCommands = o.createSOSPermissionListCommands();
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:view:status");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:view:parameter");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:restart:terminate");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:restart:abort");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:pause");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:continue");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:terminate");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:abort");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:execute:stop");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master:manage_categories");

            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master_cluster:execute:terminate_fail_safe");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master_cluster:execute:restart");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:jobscheduler_master_cluster:execute:terminate");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:history:view");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:view:status");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:change:start_and_end_node");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:change:time_for_adhoc_orders");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:change:parameter");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:change:run_time");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:change:state");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:change:other");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:change:hot_folder");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:execute:start");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:execute:update");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:execute:suspend");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:execute:resume");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order::execute:reset");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:execute:remove_setback");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:order:delete");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job_chain:view:status");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job_chain:execute:stop");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job_chain:execute:unstop");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job_chain:execute:add_order");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job_chain:execute:skip_jobchain_node");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job_chain:execute:process_jobchain_node");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job_chain:execute:stop_jobchain_node");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job_chain:change:hot_folder");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:view:status");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:change:run_time");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:change:hot_folder");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:execute:start");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:execute:stop");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:execute:unstop");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:execute:terminate");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:execute:unstop");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:execute:kill");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:execute:end_all_tasks");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:execute:suspend_all_tasks");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:job:execute:continue_all_tasks");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:process_class:view:status");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:process_class:edit:change");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:process_class:edit:remove");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:process_class:modify_hot_folder");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:schedule:view:status");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:schedule:add_substitute");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:schedule:modify_hot_folder");
            addPermission(sosPermissionCommands.getSOSPermission(), "sos:products:commands:lock:view:status");

            sosPermissions.setSOSPermissionListCommands(sosPermissionCommands);

            sosPermissionShiro.setSOSPermissionRoles(roles);
            sosPermissionShiro.setSOSPermissions(sosPermissions);
        }
        return sosPermissionShiro;

    }

    private String getAccessToken(String accessTokenFromHeader, String accessTokenFromQuery) {
        if (accessTokenFromHeader != null && !EMPTY_STRING.equals(accessTokenFromHeader)) {
            accessTokenFromQuery = accessTokenFromHeader;
        }
        return accessTokenFromQuery;
    }

    private SOSPermissionRoles getRoles() {

        ObjectFactory o = new ObjectFactory();

        SOSPermissionRoles roles = o.createSOSPermissionRoles();
        addRole(roles.getSOSPermissionRole(), ROLE_SUPER);
        addRole(roles.getSOSPermissionRole(), ROLE_ALL);
        addRole(roles.getSOSPermissionRole(), ROLE_ADMIN);
        addRole(roles.getSOSPermissionRole(), ROLE_JOC_ADMIN);
        addRole(roles.getSOSPermissionRole(), ROLE_JOBEDITOR);
        addRole(roles.getSOSPermissionRole(), ROLE_CONTROLLER);
        addRole(roles.getSOSPermissionRole(), ROLE_WORKINGPLAN);
        addRole(roles.getSOSPermissionRole(), ROLE_JID);
        addRole(roles.getSOSPermissionRole(), ROLE_JOE);
        addRole(roles.getSOSPermissionRole(), ROLE_JOC);
        addRole(roles.getSOSPermissionRole(), ROLE_EVENTS);
        addRole(roles.getSOSPermissionRole(), ROLE_ADMINISTRATOR);
        addRole(roles.getSOSPermissionRole(), ROLE_APPLICATION_MANAGER);
        addRole(roles.getSOSPermissionRole(), ROLE_IT_OPERATOR);
        addRole(roles.getSOSPermissionRole(), ROLE_INCIDENT_MANAGER);
        addRole(roles.getSOSPermissionRole(), ROLE_BUSINESS_USER);
        addRole(roles.getSOSPermissionRole(), ROLE_API_USER);
        return roles;
    }

    private SOSPermissionJocCockpit getSosPermissionJocCockpit() {

        ObjectFactory o = new ObjectFactory();

        SOSPermissionJocCockpit sosPermissionJocCockpit = o.createSOSPermissionJocCockpit();

        if (currentUser != null && currentUser.getCurrentSubject() != null) {

            sosPermissionJocCockpit.setIsAuthenticated(currentUser.isAuthenticated());
            sosPermissionJocCockpit.setAccessToken(currentUser.getAccessToken());
            sosPermissionJocCockpit.setUser(currentUser.getUsername());

            sosPermissionJocCockpit.setJobschedulerMaster(o.createSOSPermissionJocCockpitJobschedulerMaster());
            sosPermissionJocCockpit.setJobschedulerMasterCluster(o.createSOSPermissionJocCockpitJobschedulerMasterCluster());
            sosPermissionJocCockpit.setJobschedulerUniversalAgent(o.createSOSPermissionJocCockpitJobschedulerUniversalAgent());
            sosPermissionJocCockpit.setDailyPlan(o.createSOSPermissionJocCockpitDailyPlan());
            sosPermissionJocCockpit.setHistory(o.createSOSPermissionJocCockpitHistory());
            sosPermissionJocCockpit.setOrder(o.createSOSPermissionJocCockpitOrder());
            sosPermissionJocCockpit.setJobChain(o.createSOSPermissionJocCockpitJobChain());
            sosPermissionJocCockpit.setJob(o.createSOSPermissionJocCockpitJob());
            sosPermissionJocCockpit.setProcessClass(o.createSOSPermissionJocCockpitProcessClass());
            sosPermissionJocCockpit.setSchedule(o.createSOSPermissionJocCockpitSchedule());
            sosPermissionJocCockpit.setLock(o.createSOSPermissionJocCockpitLock());
            sosPermissionJocCockpit.setEvent(o.createSOSPermissionJocCockpitEvent());
            sosPermissionJocCockpit.setEventAction(o.createSOSPermissionJocCockpitEventAction());
            sosPermissionJocCockpit.setHolidayCalendar(o.createSOSPermissionJocCockpitHolidayCalendar());
            sosPermissionJocCockpit.setAuditLog(o.createSOSPermissionJocCockpitAuditLog());
            sosPermissionJocCockpit.setMaintenanceWindow(o.createSOSPermissionJocCockpitMaintenanceWindow());

            sosPermissionJocCockpit.setJOCConfigurations(o.createSOSPermissionJocCockpitJOCConfigurations());
            sosPermissionJocCockpit.getJOCConfigurations().setShare(o.createSOSPermissionJocCockpitJOCConfigurationsShare());
            sosPermissionJocCockpit.getJOCConfigurations().getShare().setChange(o.createSOSPermissionJocCockpitJOCConfigurationsShareChange());
            sosPermissionJocCockpit.getJOCConfigurations().getShare().getChange().setSharedStatus(o.createSOSPermissionJocCockpitJOCConfigurationsShareChangeSharedStatus());

            sosPermissionJocCockpit.getJobschedulerMaster().setView(o.createSOSPermissionJocCockpitJobschedulerMasterView());
            sosPermissionJocCockpit.getJobschedulerMaster().setRestart(o.createSOSPermissionJocCockpitJobschedulerMasterRestart());
            sosPermissionJocCockpit.getJobschedulerMasterCluster().setView(o.createSOSPermissionJocCockpitJobschedulerMasterClusterView());
            sosPermissionJocCockpit.getJobschedulerUniversalAgent().setView(o.createSOSPermissionJocCockpitJobschedulerUniversalAgentView());
            sosPermissionJocCockpit.getJobschedulerUniversalAgent().setRestart(o.createSOSPermissionJocCockpitJobschedulerUniversalAgentRestart());

            sosPermissionJocCockpit.getDailyPlan().setView(o.createSOSPermissionJocCockpitDailyPlanView());
            sosPermissionJocCockpit.getOrder().setView(o.createSOSPermissionJocCockpitOrderView());
            sosPermissionJocCockpit.getOrder().setChange(o.createSOSPermissionJocCockpitOrderChange());
            sosPermissionJocCockpit.getOrder().setDelete(o.createSOSPermissionJocCockpitOrderDelete());
            sosPermissionJocCockpit.getJobChain().setView(o.createSOSPermissionJocCockpitJobChainView());
            sosPermissionJocCockpit.getJob().setView(o.createSOSPermissionJocCockpitJobView());
            sosPermissionJocCockpit.getProcessClass().setView(o.createSOSPermissionJocCockpitProcessClassView());
            sosPermissionJocCockpit.getSchedule().setView(o.createSOSPermissionJocCockpitScheduleView());
            sosPermissionJocCockpit.getLock().setView(o.createSOSPermissionJocCockpitLockView());
            sosPermissionJocCockpit.getEvent().setView(o.createSOSPermissionJocCockpitEventView());
            sosPermissionJocCockpit.getEventAction().setView(o.createSOSPermissionJocCockpitEventActionView());
            sosPermissionJocCockpit.getHolidayCalendar().setView(o.createSOSPermissionJocCockpitHolidayCalendarView());
            sosPermissionJocCockpit.getAuditLog().setView(o.createSOSPermissionJocCockpitAuditLogView());
            sosPermissionJocCockpit.getMaintenanceWindow().setView(o.createSOSPermissionJocCockpitMaintenanceWindowView());

            sosPermissionJocCockpit.getJobschedulerMaster().getView().setStatus(haveRight("sos:products:joc_cockpit:jobscheduler_master:view:status"));
            sosPermissionJocCockpit.getJobschedulerMaster().getView().setMainlog(haveRight("sos:products:joc_cockpit:jobscheduler_master:view:mainlog"));
            sosPermissionJocCockpit.getJobschedulerMaster().getView().setParameter(haveRight("sos:products:joc_cockpit:jobscheduler_master:view:parameter"));
            sosPermissionJocCockpit.getJobschedulerMaster().getRestart().setAbort(haveRight("sos:products:joc_cockpit:jobscheduler_master:execute:restart:terminate"));
            sosPermissionJocCockpit.getJobschedulerMaster().getRestart().setTerminate(haveRight("sos:products:joc_cockpit:jobscheduler_master:execute:restart:abort"));
            sosPermissionJocCockpit.getJobschedulerMaster().setPause(haveRight("sos:products:joc_cockpit:jobscheduler_master:execute:pause"));
            sosPermissionJocCockpit.getJobschedulerMaster().setContinue(haveRight("sos:products:joc_cockpit:jobscheduler_master:execute:continue"));
            sosPermissionJocCockpit.getJobschedulerMaster().setTerminate(haveRight("sos:products:joc_cockpit:jobscheduler_master:execute:terminate"));
            sosPermissionJocCockpit.getJobschedulerMaster().setAbort(haveRight("sos:products:joc_cockpit:jobscheduler_master:execute:abort"));
            sosPermissionJocCockpit.getJobschedulerMaster().setManageCategories(haveRight("sos:products:joc_cockpit:jobscheduler_master:manage_categories"));

            sosPermissionJocCockpit.getJobschedulerMasterCluster().getView().setStatus(haveRight("sos:products:joc_cockpit:jobscheduler_master_cluster:view:status"));
            sosPermissionJocCockpit.getJobschedulerMasterCluster().setTerminateFailSafe(haveRight(
                    "sos:products:joc_cockpit:jobscheduler_master_cluster:execute:terminate_fail_safe"));
            sosPermissionJocCockpit.getJobschedulerMasterCluster().setRestart(haveRight("sos:products:joc_cockpit:jobscheduler_master_cluster:execute:restart"));
            sosPermissionJocCockpit.getJobschedulerMasterCluster().setTerminate(haveRight("sos:products:joc_cockpit:jobscheduler_master_cluster:execute:terminate"));

            sosPermissionJocCockpit.getJobschedulerUniversalAgent().getView().setStatus(haveRight("sos:products:joc_cockpit:jobscheduler_universal_agent:view:status"));
            sosPermissionJocCockpit.getJobschedulerUniversalAgent().getRestart().setAbort(haveRight("sos:products:joc_cockpit:jobscheduler_universal_agent:execute:restart:abort"));
            sosPermissionJocCockpit.getJobschedulerUniversalAgent().getRestart().setTerminate(haveRight(
                    "sos:products:joc_cockpit:jobscheduler_universal_agent:execute:restart:terminate"));
            sosPermissionJocCockpit.getJobschedulerUniversalAgent().setAbort(haveRight("sos:products:joc_cockpit:jobscheduler_universal_agent:execute:abort"));
            sosPermissionJocCockpit.getJobschedulerUniversalAgent().setTerminate(haveRight("sos:products:joc_cockpit:jobscheduler_universal_agent:execute:terminate"));

            sosPermissionJocCockpit.getDailyPlan().getView().setStatus(haveRight("sos:products:joc_cockpit:daily_plan:view:status"));

            sosPermissionJocCockpit.getHistory().setView(haveRight("sos:products:joc_cockpit:history:view"));

            sosPermissionJocCockpit.getOrder().getView().setStatus(haveRight("sos:products:joc_cockpit:order:view:status"));
            sosPermissionJocCockpit.getOrder().getView().setConfiguration(haveRight("sos:products:joc_cockpit:order:view:configuration"));
            sosPermissionJocCockpit.getOrder().getView().setOrderLog(haveRight("sos:products:joc_cockpit:order:view:order_log"));
            sosPermissionJocCockpit.getOrder().getChange().setStartAndEndNode(haveRight("sos:products:joc_cockpit:order:change:start_and_end_node"));
            sosPermissionJocCockpit.getOrder().getChange().setTimeForAdhocOrder(haveRight("sos:products:joc_cockpit:order:change:time_for_adhoc_orders"));
            sosPermissionJocCockpit.getOrder().getChange().setParameter(haveRight("sos:products:joc_cockpit:order:change:parameter"));
            sosPermissionJocCockpit.getOrder().setSetRunTime(haveRight("sos:products:joc_cockpit:order:change:run_time"));
            sosPermissionJocCockpit.getOrder().setSetState(haveRight("sos:products:joc_cockpit:order:change:state"));
            sosPermissionJocCockpit.getOrder().setModifyHotFolder(haveRight("sos:products:joc_cockpit:order:change:hot_folder"));
            sosPermissionJocCockpit.getOrder().setStart(haveRight("sos:products:joc_cockpit:order:execute:start"));
            sosPermissionJocCockpit.getOrder().setUpdate(haveRight("sos:products:joc_cockpit:order:execute:update"));
            sosPermissionJocCockpit.getOrder().setSuspend(haveRight("sos:products:joc_cockpit:order:execute:suspend"));
            sosPermissionJocCockpit.getOrder().setResume(haveRight("sos:products:joc_cockpit:order:execute:resume"));
            sosPermissionJocCockpit.getOrder().setReset(haveRight("sos:products:joc_cockpit:order::execute:reset"));
            sosPermissionJocCockpit.getOrder().setRemoveSetback(haveRight("sos:products:joc_cockpit:order:execute:remove_setback"));
            sosPermissionJocCockpit.getOrder().getDelete().setPermanent(haveRight("sos:products:joc_cockpit:order:delete:permanent"));
            sosPermissionJocCockpit.getOrder().getDelete().setTemporary(haveRight("sos:products:joc_cockpit:order:delete:temporary"));

            sosPermissionJocCockpit.getJobChain().getView().setConfiguration(haveRight("sos:products:joc_cockpit:job_chain:view:configuration"));
            sosPermissionJocCockpit.getJobChain().getView().setHistory(haveRight("sos:products:joc_cockpit:job_chain:view:history"));
            sosPermissionJocCockpit.getJobChain().getView().setStatus(haveRight("sos:products:joc_cockpit:job_chain:view:status"));
            sosPermissionJocCockpit.getJobChain().setStop(haveRight("sos:products:joc_cockpit:job_chain:execute:stop"));
            sosPermissionJocCockpit.getJobChain().setUnstop(haveRight("sos:products:joc_cockpit:job_chain:execute:unstop"));
            sosPermissionJocCockpit.getJobChain().setAddOrder(haveRight("sos:products:joc_cockpit:job_chain:execute:add_order"));
            sosPermissionJocCockpit.getJobChain().setSkipJobChainNode(haveRight("sos:products:joc_cockpit:job_chain:execute:skip_jobchain_node"));
            sosPermissionJocCockpit.getJobChain().setProcessJobChainNode(haveRight("sos:products:joc_cockpit:job_chain:execute:process_jobchain_node"));
            sosPermissionJocCockpit.getJobChain().setStopJobChainNode(haveRight("sos:products:joc_cockpit:job_chain:execute:stop_jobchain_node"));

            sosPermissionJocCockpit.getJob().getView().setStatus(haveRight("sos:products:joc_cockpit:job:view:status"));
            sosPermissionJocCockpit.getJob().getView().setTaskLog(haveRight("sos:products:joc_cockpit:job:view:task_log"));
            sosPermissionJocCockpit.getJob().getView().setConfiguration(haveRight("sos:products:joc_cockpit:job:view:configuration"));
            sosPermissionJocCockpit.getJob().getView().setHistory(haveRight("sos:products:joc_cockpit:job:view:history"));
            sosPermissionJocCockpit.getJob().setSetRunTime(haveRight("sos:products:joc_cockpit:job:change:run_time"));
            sosPermissionJocCockpit.getJob().setStart(haveRight("sos:products:joc_cockpit:job:execute:start"));
            sosPermissionJocCockpit.getJob().setStop(haveRight("sos:products:joc_cockpit:job:execute:stop"));
            sosPermissionJocCockpit.getJob().setUnstop(haveRight("sos:products:joc_cockpit:job:execute:unstop"));
            sosPermissionJocCockpit.getJob().setTerminate(haveRight("sos:products:joc_cockpit:job:execute:terminate"));
            sosPermissionJocCockpit.getJob().setKill(haveRight("sos:products:joc_cockpit:job:execute:unstop"));
            sosPermissionJocCockpit.getJob().setUnstop(haveRight("sos:products:joc_cockpit:job:execute:kill"));
            sosPermissionJocCockpit.getJob().setEndAllTasks(haveRight("sos:products:joc_cockpit:job:execute:end_all_tasks"));
            sosPermissionJocCockpit.getJob().setSuspendAllTasks(haveRight("sos:products:joc_cockpit:job:execute:suspend_all_tasks"));
            sosPermissionJocCockpit.getJob().setContinueAllTasks(haveRight("sos:products:joc_cockpit:job:execute:continue_all_tasks"));

            sosPermissionJocCockpit.getProcessClass().getView().setStatus(haveRight("sos:products:joc_cockpit:process_class:view:status"));
            sosPermissionJocCockpit.getProcessClass().getView().setConfiguration(haveRight("sos:products:joc_cockpit:process_class:view:configuration"));

            sosPermissionJocCockpit.getSchedule().getView().setConfiguration(haveRight("sos:products:joc_cockpit:schedule:view:configuration"));
            sosPermissionJocCockpit.getSchedule().getView().setStatus(haveRight("sos:products:joc_cockpit:schedule:view:status"));
            sosPermissionJocCockpit.getSchedule().setEdit(haveRight("sos:products:joc_cockpit:schedule:change"));
            sosPermissionJocCockpit.getSchedule().setAddSubstitute(haveRight("sos:products:joc_cockpit:schedule:add_substitute"));

            sosPermissionJocCockpit.getLock().getView().setConfiguration(haveRight("sos:products:joc_cockpit:lock:view:configuration"));
            sosPermissionJocCockpit.getLock().getView().setStatus(haveRight("sos:products:joc_cockpit:lock:view:status"));

            sosPermissionJocCockpit.getEvent().getView().setStatus(haveRight("sos:products:joc_cockpit:event:view:status"));
            sosPermissionJocCockpit.getEvent().setDelete(haveRight("sos:products:joc_cockpit:event:delete"));

            sosPermissionJocCockpit.getEventAction().getView().setStatus(haveRight("sos:products:joc_cockpit:event_action:view:status"));
            sosPermissionJocCockpit.getEventAction().setCreateEventsManually(haveRight("sos:products:joc_cockpit:event_action:create_event_manually"));

            sosPermissionJocCockpit.getHolidayCalendar().getView().setStatus(haveRight("sos:products:joc_cockpit:holiday_calendar:view:status"));
            sosPermissionJocCockpit.getAuditLog().getView().setStatus(haveRight("sos:products:joc_cockpit:audit_log:view:status"));

            sosPermissionJocCockpit.getJOCConfigurations().getShare().setView(haveRight("sos:products:joc_cockpit:customization:share:view"));
            sosPermissionJocCockpit.getJOCConfigurations().getShare().getChange().setDelete(haveRight("sos:products:joc_cockpit:customization:share:change:delete"));
            sosPermissionJocCockpit.getJOCConfigurations().getShare().getChange().setEditContent(haveRight("sos:products:joc_cockpit:customization:share:change:edit_content"));
            sosPermissionJocCockpit.getJOCConfigurations().getShare().getChange().getSharedStatus().setMakePrivate(haveRight(
                    "sos:products:joc_cockpit:customization:share:change:shared_status:make_private"));
            sosPermissionJocCockpit.getJOCConfigurations().getShare().getChange().getSharedStatus().setMakeShared(haveRight(
                    "sos:products:joc_cockpit:customization:share:change:shared_status:make_share"));

            sosPermissionJocCockpit.getMaintenanceWindow().getView().setStatus(haveRight("sos:products:joc_cockpit:maintenance_window:view:status"));
            sosPermissionJocCockpit.getMaintenanceWindow().setEnableDisableMaintenanceWindow(haveRight(
                    "sos:products:joc_cockpit:maintenance_window:enable_disable_maintenance_window"));

        }
        return sosPermissionJocCockpit;
    }

    private SOSPermissionCommands getSosPermissionCommands() {

        ObjectFactory o = new ObjectFactory();

        SOSPermissionCommands sosPermissionCommands = o.createSOSPermissionCommands();

        if (currentUser != null && currentUser.getCurrentSubject() != null) {

            sosPermissionCommands.setIsAuthenticated(currentUser.isAuthenticated());
            sosPermissionCommands.setAccessToken(currentUser.getAccessToken());
            sosPermissionCommands.setUser(currentUser.getUsername());

            sosPermissionCommands.setJobschedulerMaster(o.createSOSPermissionCommandsJobschedulerMaster());
            sosPermissionCommands.setJobschedulerMasterCluster(o.createSOSPermissionCommandsJobschedulerMasterCluster());
            sosPermissionCommands.setHistory(o.createSOSPermissionCommandsHistory());
            sosPermissionCommands.setOrder(o.createSOSPermissionCommandsOrder());
            sosPermissionCommands.setJobChain(o.createSOSPermissionCommandsJobChain());
            sosPermissionCommands.setJob(o.createSOSPermissionCommandsJob());
            sosPermissionCommands.setProcessClass(o.createSOSPermissionCommandsProcessClass());
            sosPermissionCommands.setSchedule(o.createSOSPermissionCommandsSchedule());
            sosPermissionCommands.setLock(o.createSOSPermissionCommandsLock());

            sosPermissionCommands.getJobschedulerMaster().setView(o.createSOSPermissionCommandsJobschedulerMasterView());
            sosPermissionCommands.getJobschedulerMaster().setRestart(o.createSOSPermissionCommandsJobschedulerMasterRestart());

            sosPermissionCommands.getOrder().setView(o.createSOSPermissionCommandsOrderView());
            sosPermissionCommands.getOrder().setChange(o.createSOSPermissionCommandsOrderChange());
            sosPermissionCommands.getJobChain().setView(o.createSOSPermissionCommandsJobChainView());
            sosPermissionCommands.getJob().setView(o.createSOSPermissionCommandsJobView());
            sosPermissionCommands.getProcessClass().setView(o.createSOSPermissionCommandsProcessClassView());
            sosPermissionCommands.getSchedule().setView(o.createSOSPermissionCommandsScheduleView());
            sosPermissionCommands.getLock().setView(o.createSOSPermissionCommandsLockView());

            sosPermissionCommands.getJobschedulerMaster().getView().setStatus(haveRight("sos:products:commands:jobscheduler_master:view:status"));
            sosPermissionCommands.getJobschedulerMaster().getView().setParameter(haveRight("sos:products:commands:jobscheduler_master:view:parameter"));
            sosPermissionCommands.getJobschedulerMaster().getRestart().setAbort(haveRight("sos:products:commands:jobscheduler_master:execute:restart:terminate"));
            sosPermissionCommands.getJobschedulerMaster().getRestart().setTerminate(haveRight("sos:products:commands:jobscheduler_master:execute:restart:abort"));
            sosPermissionCommands.getJobschedulerMaster().setPause(haveRight("sos:products:commands:jobscheduler_master:execute:pause"));
            sosPermissionCommands.getJobschedulerMaster().setContinue(haveRight("sos:products:commands:jobscheduler_master:execute:continue"));
            sosPermissionCommands.getJobschedulerMaster().setTerminate(haveRight("sos:products:commands:jobscheduler_master:execute:terminate"));
            sosPermissionCommands.getJobschedulerMaster().setAbort(haveRight("sos:products:commands:jobscheduler_master:execute:abort"));
            sosPermissionCommands.getJobschedulerMaster().setStop(haveRight("sos:products:commands:jobscheduler_master:execute:stop"));
            sosPermissionCommands.getJobschedulerMaster().setManageCategories(haveRight("sos:products:commands:jobscheduler_master:manage_categories"));

            sosPermissionCommands.getJobschedulerMasterCluster().setTerminateFailSafe(haveRight("sos:products:commands:jobscheduler_master_cluster:execute:terminate_fail_safe"));
            sosPermissionCommands.getJobschedulerMasterCluster().setRestart(haveRight("sos:products:commands:jobscheduler_master_cluster:execute:restart"));
            sosPermissionCommands.getJobschedulerMasterCluster().setTerminate(haveRight("sos:products:commands:jobscheduler_master_cluster:execute:terminate"));

            sosPermissionCommands.getHistory().setView(haveRight("sos:products:commands:history:view"));

            sosPermissionCommands.getOrder().getView().setStatus(haveRight("sos:products:commands:order:view:status"));
            sosPermissionCommands.getOrder().getChange().setStartAndEndNode(haveRight("sos:products:commands:order:change:start_and_end_node"));
            sosPermissionCommands.getOrder().getChange().setTimeForAdhocOrder(haveRight("sos:products:commands:order:change:time_for_adhoc_orders"));
            sosPermissionCommands.getOrder().getChange().setParameter(haveRight("sos:products:commands:order:change:parameter"));
            sosPermissionCommands.getOrder().getChange().setOther(haveRight("sos:products:joc_cockpit:order:change:other"));
            sosPermissionCommands.getOrder().setSetRunTime(haveRight("sos:products:commands:order:change:run_time"));
            sosPermissionCommands.getOrder().setSetState(haveRight("sos:products:commands:order:change:state"));
            sosPermissionCommands.getOrder().setModifyHotFolder(haveRight("sos:products:commands:order:change:hot_folder"));
            sosPermissionCommands.getOrder().setStart(haveRight("sos:products:commands:order:execute:start"));
            sosPermissionCommands.getOrder().setUpdate(haveRight("sos:products:commands:order:execute:update"));
            sosPermissionCommands.getOrder().setSuspend(haveRight("sos:products:commands:order:execute:suspend"));
            sosPermissionCommands.getOrder().setResume(haveRight("sos:products:commands:order:execute:resume"));
            sosPermissionCommands.getOrder().setReset(haveRight("sos:products:commands:order::execute:reset"));
            sosPermissionCommands.getOrder().setRemoveSetback(haveRight("sos:products:commands:order:execute:remove_setback"));
            sosPermissionCommands.getOrder().setDelete(haveRight("sos:products:commands:order:delete"));

            sosPermissionCommands.getJobChain().getView().setStatus(haveRight("sos:products:commands:job_chain:view:status"));
            sosPermissionCommands.getJobChain().setStop(haveRight("sos:products:commands:job_chain:execute:stop"));
            sosPermissionCommands.getJobChain().setUnstop(haveRight("sos:products:commands:job_chain:execute:unstop"));
            sosPermissionCommands.getJobChain().setAddOrder(haveRight("sos:products:commands:job_chain:execute:add_order"));
            sosPermissionCommands.getJobChain().setSkipJobChainNode(haveRight("sos:products:commands:job_chain:execute:skip_jobchain_node"));
            sosPermissionCommands.getJobChain().setProcessJobChainNode(haveRight("sos:products:commands:job_chain:execute:process_jobchain_node"));
            sosPermissionCommands.getJobChain().setStopJobChainNode(haveRight("sos:products:commands:job_chain:execute:stop_jobchain_node"));
            sosPermissionCommands.getJobChain().setModifyHotFolder(haveRight("sos:products:commands:job_chain:change:hot_folder"));

            sosPermissionCommands.getJob().getView().setStatus(haveRight("sos:products:commands:job:view:status"));
            sosPermissionCommands.getJob().setSetRunTime(haveRight("sos:products:commands:job:change:run_time"));
            sosPermissionCommands.getJob().setModifyHotFolder(haveRight("sos:products:commands:job:change:hot_folder"));
            sosPermissionCommands.getJob().setStart(haveRight("sos:products:commands:job:execute:start"));
            sosPermissionCommands.getJob().setStop(haveRight("sos:products:commands:job:execute:stop"));
            sosPermissionCommands.getJob().setUnstop(haveRight("sos:products:commands:job:execute:unstop"));
            sosPermissionCommands.getJob().setTerminate(haveRight("sos:products:commands:job:execute:terminate"));
            sosPermissionCommands.getJob().setKill(haveRight("sos:products:commands:job:execute:unstop"));
            sosPermissionCommands.getJob().setUnstop(haveRight("sos:products:commands:job:execute:kill"));
            sosPermissionCommands.getJob().setEndAllTasks(haveRight("sos:products:commands:job:execute:end_all_tasks"));
            sosPermissionCommands.getJob().setSuspendAllTasks(haveRight("sos:products:commands:job:execute:suspend_all_tasks"));
            sosPermissionCommands.getJob().setContinueAllTasks(haveRight("sos:products:commands:job:execute:continue_all_tasks"));

            sosPermissionCommands.getProcessClass().getView().setStatus(haveRight("sos:products:commands:process_class:view:status"));
            sosPermissionCommands.getProcessClass().setChange(haveRight("sos:products:commands:process_class:edit:change"));
            sosPermissionCommands.getProcessClass().setRemove(haveRight("sos:products:commands:process_class:edit:remove"));
            sosPermissionCommands.getProcessClass().setModifyHotFolder(haveRight("sos:products:commands:process_class:modify_hot_folder"));

            sosPermissionCommands.getSchedule().getView().setStatus(haveRight("sos:products:commands:schedule:view:status"));
            sosPermissionCommands.getSchedule().setAddSubstitute(haveRight("sos:products:commands:schedule:add_substitute"));
            sosPermissionCommands.getSchedule().setModifyHotFolder(haveRight("sos:products:commands:schedule:modify_hot_folder"));

            sosPermissionCommands.getLock().getView().setStatus(haveRight("sos:products:commands:lock:view:status"));
            sosPermissionCommands.getLock().setModifyHotFolder(haveRight("sos:products:commands:lock:modify_hot_folder"));
        }
        return sosPermissionCommands;
    }

    private SOSPermissionJocCockpit createJocCockpitPermissionObject(String accessToken, String user, String pwd) {

        SOSPermissionJocCockpit sosPermissionJocCockpit = getSosPermissionJocCockpit();
        sosPermissionJocCockpit.setSOSPermissionRoles(getRoles());

        currentUser.setSosPermissionJocCockpit(sosPermissionJocCockpit);
        Globals.jocWebserviceDataContainer.getCurrentUsersList().addUser(currentUser);
        return sosPermissionJocCockpit;
    }

    private SOSPermissionCommands createCommandsPermissionObject(String accessToken, String user, String pwd) {

        SOSPermissionJocCockpit sosPermissionJocCockpit = getSosPermissionJocCockpit();
        SOSPermissionCommands sosPermissionCommands = getSosPermissionCommands();
        sosPermissionJocCockpit.setSOSPermissionRoles(getRoles());

        currentUser.setSosPermissionJocCockpit(sosPermissionJocCockpit);
        currentUser.setSosPermissionCommands(sosPermissionCommands);
        Globals.jocWebserviceDataContainer.getCurrentUsersList().addUser(currentUser);
        return sosPermissionCommands;
    }

    private boolean isPermitted(String permission) {
        return (currentUser != null && currentUser.isPermitted(permission) && currentUser.isAuthenticated());
    }

    private boolean haveRight(String permission) {
        return isPermitted(permission);
    }

    private void addPermission(List<String> sosPermission, String permission) {
        if (currentUser != null && currentUser.isPermitted(permission) && currentUser.isAuthenticated()) {
            sosPermission.add(permission);
        }

    }

    private void addRole(List<String> sosRoles, String role) {
        if (currentUser != null && currentUser.hasRole(role) && currentUser.isAuthenticated()) {
            sosRoles.add(role);
        }
    }

    private void createUser() throws Exception {
        if (Globals.jocWebserviceDataContainer.getCurrentUsersList() == null) {
            Globals.jocWebserviceDataContainer.setCurrentUsersList(new SOSShiroCurrentUsersList());
        }

        SOSlogin sosLogin = new SOSlogin();
        sosLogin.setInifile(Globals.getShiroIniInClassPath());
        sosLogin.login(currentUser.getUsername(), currentUser.getPassword());

        currentUser.setCurrentSubject(sosLogin.getCurrentUser());

        if (sosLogin.getCurrentUser() == null) {
            JocError error = new JocError();
            error.setMessage(String.format("%s: Could not login with user: %s password:*******", sosLogin.getMsg(), currentUser.getUsername()));
            throw new JocException(error);
        }
        Session session = sosLogin.getCurrentUser().getSession();
        String accessToken = session.getId().toString();

        currentUser.setAccessToken(accessToken);
        Globals.jocWebserviceDataContainer.getCurrentUsersList().addUser(currentUser);

        SOSPermissionJocCockpit sosPermissionJocCockpit = createJocCockpitPermissionObject(accessToken, EMPTY_STRING, EMPTY_STRING);
        currentUser.setSosPermissionJocCockpit(sosPermissionJocCockpit);

        SOSPermissionCommands sosPermissionCommands = createCommandsPermissionObject(accessToken, EMPTY_STRING, EMPTY_STRING);
        currentUser.setSosPermissionCommands(sosPermissionCommands);

        if (Globals.sosShiroProperties == null) {
            Globals.sosShiroProperties = new JocCockpitProperties();
        }

    }

    private SOSShiroCurrentUser getUserPwdFromHeaderOrQuery(String basicAuthorization, String user, String pwd) throws UnsupportedEncodingException, JocException {
        String authorization = EMPTY_STRING;

        if (basicAuthorization != null) {
            String[] authorizationParts = basicAuthorization.split(" ");
            if (authorizationParts.length > 1) {
                authorization = new String(Base64.getDecoder().decode(authorizationParts[1].getBytes("UTF-8")), "UTF-8");
            }
        } else {
            JocError error = new JocError();
            error.setMessage("The Header Authorization with the Base64 encoded authorization string is missing");
            throw new JocException(error);
        }

        String[] authorizationParts = authorization.split(":");
        if (authorizationParts.length == 2) {
            user = authorizationParts[0];
            pwd = authorizationParts[1];
        }
        return new SOSShiroCurrentUser(user, pwd, authorization);
    }

    private SOSShiroCurrentUserAnswer authenticate() throws Exception {

        createUser();

        SOSShiroCurrentUserAnswer sosShiroCurrentUserAnswer = new SOSShiroCurrentUserAnswer(currentUser.getUsername());
        sosShiroCurrentUserAnswer.setIsAuthenticated(currentUser.getCurrentSubject().isAuthenticated());
        sosShiroCurrentUserAnswer.setAccessToken(currentUser.getAccessToken());
        sosShiroCurrentUserAnswer.setUser(currentUser.getUsername());
        sosShiroCurrentUserAnswer.setSessionTimeout(currentUser.getCurrentSubject().getSession().getTimeout());

        boolean enableTouch = "true".equals(Globals.sosShiroProperties.getProperty(WebserviceConstants.ENABLE_SESSION_TOUCH, WebserviceConstants.ENABLE_SESSION_TOUCH_DEFAULT));
        sosShiroCurrentUserAnswer.setEnableTouch(enableTouch);

        return sosShiroCurrentUserAnswer;

    }

    private String getRolesAsString() {
        String roles = "  ";
        SOSPermissionRoles listOfRoles = getRoles();
        for (int i = 0; i < listOfRoles.getSOSPermissionRole().size(); i++) {
            roles = roles + listOfRoles.getSOSPermissionRole().get(i) + ",";
        }
        return roles.substring(0, roles.length() - 1).trim();
    }

    private JOCDefaultResponse login(String basicAuthorization, String user, String pwd) {

        try {
            Globals.sosShiroProperties = new JocCockpitProperties();
            Globals.setProperties();
            TimeZone.setDefault(TimeZone.getTimeZone(UTC));

            currentUser = getUserPwdFromHeaderOrQuery(basicAuthorization, user, pwd);

            if (currentUser == null || currentUser.getAuthorization() == null) {
                return JOCDefaultResponse.responseStatusJSError(USER_IS_NULL + " " + AUTHORIZATION_HEADER_WITH_BASIC_BASED64PART_EXPECTED);
            }

            currentUser.setAuthorization(basicAuthorization);
            SOSShiroCurrentUserAnswer sosShiroCurrentUserAnswer = authenticate();
            LOGGER.debug(String.format("Method: %s, User: %s, access_token: %s", "login", currentUser.getUsername(), currentUser.getAccessToken()));

            JocAuditLog jocAuditLog = new JocAuditLog(currentUser.getUsername(), "./login");
            SecurityAudit s = new SecurityAudit(getRolesAsString());
            jocAuditLog.logAuditMessage(s);
            jocAuditLog.storeAuditLogEntry(s);

            if (!sosShiroCurrentUserAnswer.isAuthenticated()) {
                return JOCDefaultResponse.responseStatus401(sosShiroCurrentUserAnswer);
            } else {
                return JOCDefaultResponse.responseStatus200WithHeaders(sosShiroCurrentUserAnswer, sosShiroCurrentUserAnswer.getAccessToken(), currentUser.getCurrentSubject()
                        .getSession().getTimeout());
            }

        } catch (UnsupportedEncodingException e) {
            return JOCDefaultResponse.responseStatusJSError(AUTHORIZATION_HEADER_WITH_BASIC_BASED64PART_EXPECTED);

        } catch (Exception e) {
            return JOCDefaultResponse.responseStatusJSError(e);
        }
    }

    private void resetTimeOut() {

        if (currentUser != null) {
            Session curSession = currentUser.getCurrentSubject().getSession(false);
            if (curSession != null) {
                curSession.touch();
            } else {
                throw new org.apache.shiro.session.InvalidSessionException("Session doesn't exist");
            }
        } else {
            LOGGER.error(USER_IS_NULL);
        }
    }

    private SOSShiroCurrentUserAnswer createSOSShiroCurrentUserAnswer(String accessToken, String user, String message) {
        SOSShiroCurrentUserAnswer sosShiroCurrentUserAnswer = new SOSShiroCurrentUserAnswer();
        sosShiroCurrentUserAnswer.setAccessToken(accessToken);
        sosShiroCurrentUserAnswer.setUser(user);
        sosShiroCurrentUserAnswer.setHasRole(false);
        sosShiroCurrentUserAnswer.setIsAuthenticated(false);
        sosShiroCurrentUserAnswer.setIsPermitted(false);
        sosShiroCurrentUserAnswer.setMessage(message);
        return sosShiroCurrentUserAnswer;
    }

}