package com.sos.auth.shiro.db;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.sos.hibernate.layer.SOSHibernateDBLayer;

/** \class SOSUserDBLayer \brief SOSUserDBLayer -
 * 
 * \details
 * 
 * \section SOSUserDBLayer.java_intro_sec Introduction
 * 
 * \section SOSUserDBLayer.java_samples Some Samples
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
 * \author Uwe Risse \version 13.09.2011 \see reference
 * 
 * Created on 13.09.2011 14:40:18 */

public class SOSUserDBLayer extends SOSHibernateDBLayer {

    private Logger logger = Logger.getLogger(SOSUserDBLayer.class);

    private SOSUserFilter filter = null;

    public SOSUserDBLayer(String configurationFileName) {
        super();
        this.setConfigurationFileName(configurationFileName);
        this.initConnection(this.getConfigurationFileName());
        resetFilter();
    }

    public void resetFilter() {
        filter = new SOSUserFilter();
        filter.setUserName("");
    }

    public int delete() {
        if (connection == null) {
            initConnection(getConfigurationFileName());
        }
        String hql = "delete from SOSUserDBItem " + getWhere();
        Query query = null;
        int row = 0;
        try {
            connection.connect();
            connection.beginTransaction();
            query = connection.createQuery(hql);
            if (filter.getUserName() != null && !filter.getUserName().equals("")) {
                query.setParameter("sosUserName", filter.getUserName());
            }
            row = query.executeUpdate();
        } catch (Exception e) {
            logger.error("Error occurred executing query: ", e);
        }
        return row;
    }

    private String getWhere() {
        String where = "";
        String and = "";
        if (filter.getUserName() != null && !filter.getUserName().equals("")) {
            where += and + " sosUserName = :sosUserName";
            and = " and ";
        }
        if (!where.trim().equals("")) {
            where = "where " + where;
        }
        return where;
    }

    public List<SOSUserDBItem> getSOSUserList(final int limit) {
        initConnection(getConfigurationFileName());
        List<SOSUserDBItem> sosUserList = null;
        try {
            connection.connect();
            connection.beginTransaction();
            Query query = connection.createQuery("from SOSUserDBItem " + getWhere() + filter.getOrderCriteria() + filter.getSortMode());
            if (filter.getUserName() != null && !filter.getUserName().equals("")) {
                query.setParameter("sosUserName", filter.getUserName());
            }
            if (limit > 0) {
                query.setMaxResults(limit);
            }
            sosUserList = query.list();
        } catch (Exception e) {
            logger.error("Error occurred executing query: ", e);
        }
        return sosUserList;
    }

    public void setFilter(SOSUserFilter filter) {
        this.filter = filter;
    }

    public SOSUserFilter getFilter() {
        return filter;
    }

}
