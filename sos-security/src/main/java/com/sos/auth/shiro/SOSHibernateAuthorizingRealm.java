package com.sos.auth.shiro;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.sos.auth.shiro.db.SOSUserDBItem;
import com.sos.auth.shiro.db.SOSUserDBLayer;

public class SOSHibernateAuthorizingRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(SOSHibernateAuthorizingRealm.class);
    private String hibernateConfigurationFile;
    private ISOSAuthorizing authorizing;
    private UsernamePasswordToken authToken;

    public void setHibernateConfigurationFile(String filename) {
        this.hibernateConfigurationFile = filename;
    }

    public boolean supports(AuthenticationToken token) {
        SOSHibernateAuthorizing authorizing = new SOSHibernateAuthorizing();
        authorizing.setConfigurationFileName(hibernateConfigurationFile);
        setAuthorizing(authorizing);
        return true;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authzInfo = null;
        if (authorizing != null) {
            authzInfo = authorizing.setRoles(authzInfo, principalCollection);
            authzInfo = authorizing.setPermissions(authzInfo, principalCollection);
        }
        return authzInfo;
    }

    public String getMD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            //
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        authToken = (UsernamePasswordToken) authcToken;
        SOSUserDBLayer sosUserDBLayer;
        try {
            sosUserDBLayer = new SOSUserDBLayer(hibernateConfigurationFile);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
        sosUserDBLayer.getFilter().setUserName(authToken.getUsername());
        List<SOSUserDBItem> sosUserList = null;
        try {
            sosUserList = sosUserDBLayer.getSOSUserList(0);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        SOSUserDBItem sosUserDBItem = sosUserList.get(0);
        String s = sosUserDBItem.getSosUserPassword();
        String pw = String.valueOf(authToken.getPassword());
        if (s.equals(getMD5(pw))) {
            return new SimpleAuthenticationInfo(authToken.getUsername(), authToken.getPassword(), getName());
        } else {
            return null;
        }
    }

    public void setAuthorizing(ISOSAuthorizing authorizing) {
        this.authorizing = authorizing;
    }

}