package com.sos.hibernate.classes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.NonUniqueResultException;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.internal.SessionImpl;
import org.hibernate.internal.StatelessSessionImpl;
import org.hibernate.jdbc.Work;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sos.hibernate.exceptions.SOSHibernateConfigurationException;
import com.sos.hibernate.exceptions.SOSHibernateConnectionException;
import com.sos.hibernate.exceptions.SOSHibernateException;
import com.sos.hibernate.exceptions.SOSHibernateInvalidSessionException;
import com.sos.hibernate.exceptions.SOSHibernateLockAcquisitionException;
import com.sos.hibernate.exceptions.SOSHibernateObjectOperationException;
import com.sos.hibernate.exceptions.SOSHibernateOpenSessionException;
import com.sos.hibernate.exceptions.SOSHibernateQueryException;
import com.sos.hibernate.exceptions.SOSHibernateQueryNonUniqueResultException;
import com.sos.hibernate.exceptions.SOSHibernateSessionException;
import com.sos.hibernate.exceptions.SOSHibernateTransactionException;

import sos.util.SOSDate;
import sos.util.SOSString;

public class SOSHibernateSession implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SOSHibernateSession.class);
    private static final long serialVersionUID = 1L;
    private Object currentSession;
    private FlushMode defaultHibernateFlushMode = FlushMode.ALWAYS;
    private final SOSHibernateFactory factory;
    private String identifier;
    private boolean isGetCurrentSession = false;
    private boolean isStatelessSession = false;
    private SOSHibernateSQLExecutor sqlExecutor;

    /** use factory.openSession(), factory.openStatelessSession() or factory.getCurrentSession() */
    protected SOSHibernateSession(SOSHibernateFactory hibernateFactory) {
        this.factory = hibernateFactory;
    }

    /** @throws SOSHibernateOpenSessionException */
    protected void openSession() throws SOSHibernateOpenSessionException {
        String method = getMethodName("openSession");
        if (currentSession != null) {
            LOGGER.debug(String.format("%s: close currentSession", method));
            closeSession();
        }
        LOGGER.debug(String.format("%s: isStatelessSession=%s, isGetCurrentSession=%s", method, isStatelessSession, isGetCurrentSession));
        try {
            if (isStatelessSession) {
                currentSession = factory.getSessionFactory().openStatelessSession();
            } else {
                Session session = null;
                if (isGetCurrentSession) {
                    session = factory.getSessionFactory().getCurrentSession();
                } else {
                    session = factory.getSessionFactory().openSession();
                }
                if (defaultHibernateFlushMode != null) {
                    session.setHibernateFlushMode(defaultHibernateFlushMode);
                }
                currentSession = session;
            }
            onOpenSession();
        } catch (IllegalStateException e) {
            throw new SOSHibernateOpenSessionException(e);
        } catch (PersistenceException e) {
            throw new SOSHibernateOpenSessionException(e);
        }
    }

    protected void setIsGetCurrentSession(boolean val) {
        isGetCurrentSession = val;
    }

    protected void setIsStatelessSession(boolean val) {
        isStatelessSession = val;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateTransactionException */
    public void beginTransaction() throws SOSHibernateException {
        String method = getMethodName("beginTransaction");
        try {
            if (getFactory().getAutoCommit()) {
                LOGGER.debug(String.format("%s: skip (autoCommit is true)", method));
                return;
            }
        } catch (SOSHibernateConfigurationException e) {
            throw new SOSHibernateTransactionException("can't get configured autocommit", e);
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        LOGGER.debug(String.format("%s", method));
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                session.beginTransaction();
            } else {
                Session session = ((Session) currentSession);
                session.beginTransaction();
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateTransactionException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateTransactionException(e));
        }
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateSessionException */
    public void clearSession() throws SOSHibernateException {
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        String method = getMethodName("clearSession");
        LOGGER.debug(String.format("%s", method));
        try {
            if (!isStatelessSession) {
                Session session = (Session) currentSession;
                session.clear();
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateSessionException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateSessionException(e));
        }
    }

    public void close() {
        String method = getMethodName("close");
        LOGGER.debug(String.format("%s", method));
        closeTransaction();
        closeSession();
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateTransactionException */
    public void commit() throws SOSHibernateException {
        String method = getMethodName("commit");
        try {
            if (getFactory().getAutoCommit()) {
                LOGGER.debug(String.format("%s: skip (autoCommit is true)", method));
                return;
            }
        } catch (SOSHibernateConfigurationException e) {
            throw new SOSHibernateTransactionException("can't get configured autocommit", e);
        }
        LOGGER.debug(String.format("%s", method));
        Transaction tr = getTransaction();
        if (tr == null) {
            throw new SOSHibernateTransactionException("transaction is NULL");
        }
        try {
            if (!isStatelessSession) {
                ((Session) currentSession).flush();
            }
            tr.commit();
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateTransactionException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateTransactionException(e));
        }
    }

    /** @deprecated
     * 
     *             use factory.openSession() or factory.openStatelessSession(); */
    @Deprecated
    public void connect() throws SOSHibernateConfigurationException, SOSHibernateOpenSessionException {
        String method = getMethodName("connect");
        openSession();
        String connFile = (factory.getConfigFile().isPresent()) ? factory.getConfigFile().get().toAbsolutePath().toString() : "without config file";
        int isolationLevel = getFactory().getTransactionIsolation();
        LOGGER.debug(String.format("%s: autocommit=%s, transaction isolation=%s, %s", method, getFactory().getAutoCommit(), SOSHibernateFactory
                .getTransactionIsolationName(isolationLevel), connFile));

    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    public <T> NativeQuery<T> createNativeQuery(String sql) throws SOSHibernateException {
        return createNativeQuery(sql, null);
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    @SuppressWarnings("unchecked")
    public <T> NativeQuery<T> createNativeQuery(String sql, Class<T> entityClass) throws SOSHibernateException {
        if (SOSString.isEmpty(sql)) {
            throw new SOSHibernateQueryException("sql statement is empty");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL", sql);
        }
        String method = getMethodName("createNativeQuery");
        LOGGER.debug(String.format("%s: sql=%s", method, sql));
        NativeQuery<T> q = null;
        try {
            if (isStatelessSession) {
                if (entityClass == null) {
                    q = ((StatelessSession) currentSession).createNativeQuery(sql);
                } else {
                    q = ((StatelessSession) currentSession).createNativeQuery(sql, entityClass);
                }
            } else {
                if (entityClass == null) {
                    q = ((Session) currentSession).createNativeQuery(sql);
                } else {
                    q = ((Session) currentSession).createNativeQuery(sql, entityClass);
                }
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, sql));
        } catch (IllegalArgumentException e) {
            throw new SOSHibernateQueryException(e, sql);
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, sql));
        }
        return q;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    public <T> Query<T> createQuery(String hql) throws SOSHibernateException {
        return createQuery(hql, null);
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    @SuppressWarnings("unchecked")
    public <T> Query<T> createQuery(String hql, Class<T> entityClass) throws SOSHibernateException {
        if (SOSString.isEmpty(hql)) {
            throw new SOSHibernateQueryException("hql statement is empty");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL", hql);
        }

        String method = getMethodName("createQuery");
        LOGGER.debug(String.format("%s: hql=%s", method, hql));
        Query<T> q = null;
        try {
            if (isStatelessSession) {
                if (entityClass == null) {
                    q = ((StatelessSession) currentSession).createQuery(hql);
                } else {
                    q = ((StatelessSession) currentSession).createQuery(hql, entityClass);
                }
            } else {
                if (entityClass == null) {
                    q = ((Session) currentSession).createQuery(hql);
                } else {
                    q = ((Session) currentSession).createQuery(hql, entityClass);
                }
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, hql));
        } catch (IllegalArgumentException e) {
            throw new SOSHibernateQueryException(e, hql);
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, hql));
        }
        return q;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateObjectOperationException */
    public void delete(Object item) throws SOSHibernateException {
        if (item == null) {
            throw new SOSHibernateObjectOperationException("item is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        String method = getMethodName("delete");
        LOGGER.debug(String.format("%s: item=%s", method, item));
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                session.delete(item);
            } else {
                Session session = ((Session) currentSession);
                session.delete(item);
                session.flush();
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        }
    }

    /** @deprecated
     * 
     *             use close(); */
    @Deprecated
    public void disconnect() {
        close();
    }

    /** execute an update or delete (NativeQuery or Query)
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    @SuppressWarnings("deprecation")
    public int executeUpdate(Query<?> query) throws SOSHibernateException {
        if (query == null) {
            throw new SOSHibernateQueryException("query is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        String method = getMethodName("executeUpdate");
        LOGGER.debug(String.format("%s: query[%s]", method, query.getQueryString()));
        try {
            return query.executeUpdate();
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, query));
            return 0;
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, query));
            return 0;
        }
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    public int executeUpdate(String hql) throws SOSHibernateException {
        return executeUpdate(createQuery(hql));
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    public int executeUpdateNativeQuery(String sql) throws SOSHibernateException {
        return executeUpdate(createNativeQuery(sql));
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateObjectOperationException */
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> entityClass, Serializable id) throws SOSHibernateException {
        if (entityClass == null) {
            throw new SOSHibernateObjectOperationException("entityClass is NULL");
        }
        if (id == null) {
            throw new SOSHibernateObjectOperationException("id is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        String method = getMethodName("get");
        LOGGER.debug(String.format("%s: entityClass=%s", method, entityClass.getName()));
        try {
            if (isStatelessSession) {
                return (T) ((StatelessSession) currentSession).get(entityClass, id);
            } else {
                return ((Session) currentSession).get(entityClass, id);
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
            return null;
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
            return null;
        }
    }

    public CacheMode getCacheMode() {
        if (!isStatelessSession && currentSession != null) {
            Session session = (Session) currentSession;
            return session.getCacheMode();
        }
        return null;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateConnectionException */
    public Connection getConnection() throws SOSHibernateException {
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        try {
            if (isStatelessSession) {
                StatelessSessionImpl sf = (StatelessSessionImpl) currentSession;
                return sf.connection();
            } else {
                SessionImpl sf = (SessionImpl) currentSession;
                return sf.connection();
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateConnectionException(e));
            return null;
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateConnectionException(e));
            return null;
        }
    }

    public Object getCurrentSession() {
        return currentSession;
    }

    public SOSHibernateFactory getFactory() {
        return factory;
    }

    public FlushMode getHibernateFlushMode() {
        if (!isStatelessSession && currentSession != null) {
            Session session = (Session) currentSession;
            return session.getHibernateFlushMode();
        }
        return null;
    }

    public String getIdentifier() {
        return identifier;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public String getLastSequenceValue(String sequenceName) throws SOSHibernateException {
        String stmt = factory.getSequenceLastValString(sequenceName);
        return stmt == null ? null : getSingleValueNativeQuery(stmt);
    }

    /** execute a SELECT query(NativeQuery or Query)
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    @SuppressWarnings("deprecation")
    public <T> List<T> getResultList(Query<T> query) throws SOSHibernateException {
        if (query == null) {
            throw new SOSHibernateQueryException("query is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL", query);
        }
        String method = getMethodName("getResultList");
        LOGGER.debug(String.format("%s: query[%s]", method, query.getQueryString()));
        try {
            return query.getResultList();
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, query));
            return null;
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, query));
            return null;
        }
    }

    /** execute a SELECT query(Query)
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    public <T> List<T> getResultList(String hql) throws SOSHibernateException {
        Query<T> query = createQuery(hql);
        return getResultList(query);
    }

    /** execute a SELECT query(NativeQuery)
     * 
     * return a list of rows represented by Map<String,Object>:
     * 
     * Map key - column name (lower case), Map value - object (null value as NULL)
     * 
     * 
     * setResultTransformer is deprecated (see below), but currently without alternative
     * 
     * excerpt from Query.setResultTransformer comment:
     * 
     * deprecated (since 5.2), todo develop a new approach to result transformers
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public <T> List<Map<String, Object>> getResultListAsMaps(NativeQuery<T> nativeQuery) throws SOSHibernateException {
        if (nativeQuery == null) {
            throw new SOSHibernateQueryException("nativeQuery is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL", nativeQuery);
        }
        String method = getMethodName("getResultListAsMaps");
        LOGGER.debug(String.format("%s: nativeQuery[%s]", method, nativeQuery.getQueryString()));
        try {
            nativeQuery.setResultTransformer(getNativeQueryResultToMapTransformer(false, null));
            return (List<Map<String, Object>>) nativeQuery.getResultList();
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, nativeQuery));
            return null;
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, nativeQuery));
            return null;
        }
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    public <T> List<Map<String, String>> getResultListAsStringMaps(NativeQuery<T> nativeQuery) throws SOSHibernateException {
        return getResultListAsStringMaps(nativeQuery, null);
    }

    /** execute a SELECT query(NativeQuery)
     * 
     * return a list of rows represented by Map<String,String>:
     * 
     * Map key - column name (lower case), Map value - string representation (null value as an empty string)
     * 
     * 
     * setResultTransformer is deprecated (see below), but currently without alternative
     * 
     * excerpt from Query.setResultTransformer comment:
     * 
     * deprecated (since 5.2), todo develop a new approach to result transformers
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public <T> List<Map<String, String>> getResultListAsStringMaps(NativeQuery<T> nativeQuery, String dateTimeFormat) throws SOSHibernateException {
        if (nativeQuery == null) {
            throw new SOSHibernateQueryException("nativeQuery is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL", nativeQuery);
        }
        String method = getMethodName("getResultListAsStringMaps");
        LOGGER.debug(String.format("%s: nativeQuery[%s], dateTimeFormat=%s", method, nativeQuery.getQueryString(), dateTimeFormat));
        try {
            nativeQuery.setResultTransformer(getNativeQueryResultToMapTransformer(true, dateTimeFormat));
            return (List<Map<String, String>>) nativeQuery.getResultList();
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, nativeQuery));
            return null;
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, nativeQuery));
            return null;
        }
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    public <T> List<T> getResultListNativeQuery(String sql) throws SOSHibernateException {
        return getResultList(createNativeQuery(sql));
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    public <T> List<Map<String, Object>> getResultListNativeQueryAsMaps(String sql) throws SOSHibernateException {
        return getResultListAsMaps(createNativeQuery(sql));
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> List<Map<String, String>> getResultListNativeQueryAsStringMaps(String sql) throws SOSHibernateException {
        return getResultListAsStringMaps(createNativeQuery(sql), null);
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> List<Map<String, String>> getResultListNativeQueryAsStringMaps(String sql, String dateTimeFormat) throws SOSHibernateException {
        return getResultListAsStringMaps(createNativeQuery(sql), dateTimeFormat);
    }

    /** execute a SELECT query(NativeQuery or Query)
     * 
     * return a single row or null
     * 
     * difference to Query.getSingleResult - not throw NoResultException
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    @SuppressWarnings("deprecation")
    public <T> T getSingleResult(Query<T> query) throws SOSHibernateException {
        if (query == null) {
            throw new SOSHibernateQueryException("query is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL", query);
        }
        String method = getMethodName("getSingleResult");
        LOGGER.debug(String.format("%s: query[%s]", method, query.getQueryString()));
        T result = null;
        try {
            result = query.getSingleResult();
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, query));
        } catch (NoResultException e) {
            result = null;
        } catch (NonUniqueResultException e) {
            throw new SOSHibernateQueryNonUniqueResultException(e, query);
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, query));
        }
        return result;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> T getSingleResult(String hql) throws SOSHibernateException {
        return getSingleResult(createQuery(hql));
    }

    /** execute a SELECT query(NativeQuery)
     * 
     * return a single row represented by Map<String,Object> or null
     * 
     * Map key - column name (lower case), Map value - object (null value as NULL)
     * 
     * see getResultListAsMaps
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public <T> Map<String, Object> getSingleResultAsMap(NativeQuery<T> nativeQuery) throws SOSHibernateException {
        if (nativeQuery == null) {
            throw new SOSHibernateQueryException("nativeQuery is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL", nativeQuery);
        }
        String method = getMethodName("getSingleResultAsMap");
        LOGGER.debug(String.format("%s: nativeQuery[%s]", method, nativeQuery.getQueryString()));
        nativeQuery.setResultTransformer(getNativeQueryResultToMapTransformer(false, null));
        Map<String, Object> result = null;
        try {
            result = (Map<String, Object>) nativeQuery.getSingleResult();
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, nativeQuery));
        } catch (NoResultException e) {
            result = null;
        } catch (NonUniqueResultException e) {
            throw new SOSHibernateQueryNonUniqueResultException(e, nativeQuery);
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, nativeQuery));
        }
        return result;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> Map<String, String> getSingleResultAsStringMap(NativeQuery<T> query) throws SOSHibernateException {
        return getSingleResultAsStringMap(query, null);
    }

    /** execute a SELECT query(NativeQuery)
     * 
     * return a single row represented by Map<String,String> or null
     * 
     * Map key - column name (lower case), Map value - string representation (null value as an empty string)
     * 
     * see getResultListAsStringMaps
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public <T> Map<String, String> getSingleResultAsStringMap(NativeQuery<T> nativeQuery, String dateTimeFormat) throws SOSHibernateException {
        if (nativeQuery == null) {
            throw new SOSHibernateQueryException("nativeQuery is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL", nativeQuery);
        }
        String method = getMethodName("getSingleResultAsMap");
        LOGGER.debug(String.format("%s: nativeQuery[%s], dateTimeFormat=%s", method, nativeQuery.getQueryString(), dateTimeFormat));
        nativeQuery.setResultTransformer(getNativeQueryResultToMapTransformer(true, dateTimeFormat));
        Map<String, String> result = null;
        try {
            result = (Map<String, String>) nativeQuery.getSingleResult();
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, nativeQuery));
        } catch (NoResultException e) {
            result = null;
        } catch (NonUniqueResultException e) {
            throw new SOSHibernateQueryNonUniqueResultException(e, nativeQuery);
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, nativeQuery));
        }
        return result;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> T getSingleResultNativeQuery(String sql) throws SOSHibernateException {
        return getSingleResult(createNativeQuery(sql));
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> Map<String, Object> getSingleResultNativeQueryAsMap(String sql) throws SOSHibernateException {
        return getSingleResultAsMap(createNativeQuery(sql));
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> Map<String, String> getSingleResultNativeQueryAsStringMap(String sql) throws SOSHibernateException {
        return getSingleResultAsStringMap(createNativeQuery(sql), null);
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> Map<String, String> getSingleResultNativeQueryAsStringMap(String sql, String dateTimeFormat) throws SOSHibernateException {
        return getSingleResultAsStringMap(createNativeQuery(sql), dateTimeFormat);
    }

    /** execute a SELECT query(NativeQuery or Query)
     * 
     * return a single field value or null
     * 
     * difference to Query.getSingleResult - not throw NoResultException, return single value as string
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    @SuppressWarnings("deprecation")
    public <T> T getSingleValue(Query<T> query) throws SOSHibernateException {
        if (query == null) {
            throw new SOSHibernateQueryException("query is NULL");
        }
        String method = getMethodName("getSingleValue");
        LOGGER.debug(String.format("%s: query[%s]", method, query.getQueryString()));
        T result = getSingleResult(query);
        if (result != null) {
            if (query instanceof NativeQuery<?>) {
                if (result instanceof Object[]) {
                    throw new SOSHibernateQueryNonUniqueResultException("query return a row and not a unique field result", query);
                }
            } else {
                if (result.getClass().getAnnotation(Entity.class) != null) {
                    throw new SOSHibernateQueryNonUniqueResultException("query return an entity object and not a unique field result", query);
                }
            }
            return result;
        }
        return null;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> T getSingleValue(String hql) throws SOSHibernateException {
        return getSingleValue(createQuery(hql));
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> String getSingleValueAsString(Query<T> query) throws SOSHibernateException {
        T result = getSingleValue(query);
        if (result != null) {
            return result + "";
        }
        return null;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> String getSingleValueAsString(String hql) throws SOSHibernateException {
        T result = getSingleValue(hql);
        if (result != null) {
            return result + "";
        }
        return null;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> T getSingleValueNativeQuery(String sql) throws SOSHibernateException {
        return getSingleValue(createNativeQuery(sql));
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException,
     *             SOSHibernateQueryNonUniqueResultException */
    public <T> String getSingleValueNativeQueryAsString(String sql) throws SOSHibernateException {
        T result = getSingleValueNativeQuery(sql);
        if (result != null) {
            return result + "";
        }
        return null;
    }

    public SOSHibernateSQLExecutor getSQLExecutor() {
        if (sqlExecutor == null) {
            sqlExecutor = new SOSHibernateSQLExecutor(this);
        }
        return sqlExecutor;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateTransactionException */
    public Transaction getTransaction() throws SOSHibernateException {
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        Transaction tr = null;
        try {
            if (isStatelessSession) {
                StatelessSession s = ((StatelessSession) currentSession);
                tr = s.getTransaction();
            } else {
                Session s = ((Session) currentSession);
                tr = s.getTransaction();
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateTransactionException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateTransactionException(e));
        }
        return tr;
    }

    public boolean isConnected() {
        if (currentSession != null) {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                return session.isConnected();
            } else {
                Session session = (Session) currentSession;
                return session.isConnected();
            }
        }
        return false;
    }

    public boolean isGetCurrentSession() {
        return isGetCurrentSession;
    }

    public boolean isOpen() {
        if (currentSession != null) {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                return session.isOpen();
            } else {
                Session session = (Session) currentSession;
                return session.isOpen();
            }
        }
        return false;
    }

    public boolean isStatelessSession() {
        return isStatelessSession;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateSessionException */
    public void refresh(Object item) throws SOSHibernateException {
        refresh(null, item);
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateObjectOperationException */
    public void refresh(String entityName, Object item) throws SOSHibernateException {
        if (item == null) {
            throw new SOSHibernateObjectOperationException("item is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        String method = getMethodName("refresh");
        LOGGER.debug(String.format("%s: entityName=%s", method, entityName));
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                if (entityName == null) {
                    session.refresh(item);
                } else {
                    session.refresh(entityName, item);
                }
            } else {
                Session session = (Session) currentSession;
                if (entityName == null) {
                    session.refresh(item);
                } else {
                    session.refresh(entityName, item);
                }
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        }
    }

    /** @throws SOSHibernateOpenSessionException */
    public void reopen() throws SOSHibernateOpenSessionException {
        String method = getMethodName("reopen");
        LOGGER.debug(String.format("%s: isStatelessSession=%s", method, isStatelessSession));
        closeSession();
        openSession();
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateTransactionException */
    public void rollback() throws SOSHibernateException {
        String method = getMethodName("rollback");
        try {
            if (getFactory().getAutoCommit()) {
                LOGGER.debug(String.format("%s: skip (autoCommit is true)", method));
                return;
            }
        } catch (SOSHibernateConfigurationException e) {
            throw new SOSHibernateTransactionException("can't get configured autocommit", e);
        }
        LOGGER.debug(String.format("%s", method));
        Transaction tr = getTransaction();
        if (tr == null) {
            throw new SOSHibernateTransactionException("transaction is NULL");
        }
        try {
            tr.rollback();
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateTransactionException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateTransactionException(e));
        }
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateObjectOperationException */
    public void save(Object item) throws SOSHibernateException {
        if (item == null) {
            throw new SOSHibernateObjectOperationException("item is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        String method = getMethodName("save");
        LOGGER.debug(String.format("%s: item=%s", method, item));
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                session.insert(item);
            } else {
                Session session = ((Session) currentSession);
                session.save(item);
                session.flush();
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        }
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateObjectOperationException */
    public Object saveOrUpdate(Object item) throws SOSHibernateException {
        if (item == null) {
            throw new SOSHibernateObjectOperationException("item is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        String method = getMethodName("saveOrUpdate");
        LOGGER.debug(String.format("%s: item=%s", method, item));
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                Object id = null;
                try {
                    id = SOSHibernate.getId(item);
                    if (id == null) {
                        throw new SOSHibernateException(String.format("not found @Id annotated public getter method [%s]", item.getClass()
                                .getName()));
                    }
                } catch (SOSHibernateException e) {
                    throw new SOSHibernateObjectOperationException(e.getMessage(), e.getCause());
                }
                Object dbItem = get(item.getClass(), (Serializable) id);
                if (dbItem == null) {
                    session.insert(item);
                } else {
                    session.update(item);
                }
            } else {
                Session session = ((Session) currentSession);
                session.saveOrUpdate(item);
                session.flush();
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        }
        return item;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    public <T> ScrollableResults scroll(Query<T> query) throws SOSHibernateException {
        return scroll(query, ScrollMode.FORWARD_ONLY);
    }

    /** execute a SELECT query(NativeQuery or Query)
     * 
     * @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateQueryException */
    @SuppressWarnings("deprecation")
    public <T> ScrollableResults scroll(Query<T> query, ScrollMode scrollMode) throws SOSHibernateException {
        if (query == null) {
            throw new SOSHibernateQueryException("query is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL", query);
        }
        String method = getMethodName("scroll");
        LOGGER.debug(String.format("%s: query[%s], scrollMode=%s", method, query.getQueryString(), scrollMode));
        try {
            return query.scroll(scrollMode);
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateQueryException(e, query));
            return null;
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateQueryException(e, query));
            return null;
        }
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateSessionException */
    public void sessionDoWork(Work work) throws SOSHibernateException {
        if (work == null) {
            throw new SOSHibernateSessionException("work is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        String method = getMethodName("sessionDoWork");
        LOGGER.debug(String.format("%s", method));
        try {
            if (!isStatelessSession) {
                Session session = (Session) currentSession;
                session.doWork(work);
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateSessionException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateSessionException(e));
        }
    }

    public void setCacheMode(CacheMode cacheMode) {
        if (currentSession instanceof Session) {
            Session session = (Session) currentSession;
            session.setCacheMode(cacheMode);
        }
    }

    public void setHibernateFlushMode(FlushMode flushMode) {
        if (currentSession instanceof Session) {
            Session session = (Session) currentSession;
            session.setHibernateFlushMode(flushMode);
        }
    }

    public void setIdentifier(String val) {
        identifier = val;
    }

    /** @throws SOSHibernateException : SOSHibernateInvalidSessionException, SOSHibernateLockAcquisitionException, SOSHibernateObjectOperationException */
    public void update(Object item) throws SOSHibernateException {
        if (item == null) {
            throw new SOSHibernateObjectOperationException("item is NULL");
        }
        if (currentSession == null) {
            throw new SOSHibernateInvalidSessionException("currentSession is NULL");
        }
        String method = getMethodName("update");
        LOGGER.debug(String.format("%s: item=%s", method, item));
        try {
            if (isStatelessSession) {
                StatelessSession session = ((StatelessSession) currentSession);
                session.update(item);
            } else {
                Session session = ((Session) currentSession);
                session.update(item);
                session.flush();
            }
        } catch (IllegalStateException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        } catch (PersistenceException e) {
            throwException(e, new SOSHibernateObjectOperationException(e));
        }
    }

    private void closeSession() {
        String method = getMethodName("closeSession");
        LOGGER.debug(String.format("%s", method));
        try {
            if (currentSession != null) {
                if (isStatelessSession) {
                    StatelessSession s = (StatelessSession) currentSession;
                    s.close();
                } else {
                    Session session = (Session) currentSession;
                    if (session.isOpen()) {
                        session.close();
                    }
                }
            }
        } catch (Throwable e) {
        }
        currentSession = null;
    }

    private void closeTransaction() {
        String method = getMethodName("closeTransaction");
        try {
            if (currentSession != null) {
                Transaction tr = getTransaction();
                if (tr != null) {
                    LOGGER.debug(String.format("%s: rollback", method));
                    tr.rollback();
                } else {
                    LOGGER.debug(String.format("%s: skip rollback (transaction is null)", method));
                }
            }
        } catch (Throwable ex) {
            //
        }

    }

    private String getMethodName(String name) {
        String prefix = identifier == null ? "" : String.format("[%s] ", identifier);
        return String.format("%s%s", prefix, name);
    }

    private ResultTransformer getNativeQueryResultToMapTransformer(boolean asString, String dateTimeFormat) {
        return new ResultTransformer() {

            private static final long serialVersionUID = 1L;

            @SuppressWarnings("rawtypes")
            @Override
            public List<?> transformList(List collection) {
                return collection;
            }

            @Override
            public Object transformTuple(Object[] tuple, String[] aliases) {
                if (aliases.length == 0) {
                    return tuple;
                }
                Map<String, Object> result = new HashMap<String, Object>(tuple.length);
                for (int i = 0; i < tuple.length; i++) {
                    String alias = aliases[i];
                    if (alias != null) {
                        Object origValue = tuple[i];
                        if (asString) {
                            String value = "";
                            if (origValue != null) {
                                value = origValue + "";
                                if (dateTimeFormat != null && origValue instanceof java.sql.Timestamp) {
                                    try {
                                        value = SOSDate.getDateTimeAsString(value, dateTimeFormat);
                                    } catch (Exception e) {
                                    }
                                }
                            }
                            result.put(alias.toLowerCase(), value);
                        } else {
                            result.put(alias.toLowerCase(), origValue);
                        }
                    }
                }
                return result;
            }
        };
    }

    private void onOpenSession() {
        String method = getMethodName("onOpenSession");
        try {
            if (getFactory().getDbms().equals(SOSHibernateFactory.Dbms.MSSQL)) {
                String value = getFactory().getConfiguration().getProperties().getProperty(SOSHibernate.HIBERNATE_SOS_PROPERTY_MSSQL_LOCK_TIMEOUT);
                if (value != null && value != "-1") {
                    getSQLExecutor().execute("set LOCK_TIMEOUT " + value);
                }
            }
        } catch (Exception e) {
            LOGGER.warn(String.format("%s: %s", method, e.toString()));
        }
    }

    private void throwException(IllegalStateException cause, SOSHibernateException ex) throws SOSHibernateException {
        if (cause.getCause() == null) {
            throw new SOSHibernateInvalidSessionException(cause, ex.getStatement());
        }
        throw ex;
    }

    private void throwException(PersistenceException cause, SOSHibernateException ex) throws SOSHibernateException {
        Throwable e = cause;
        while (e != null) {
            if (e instanceof JDBCConnectionException) {
                throw new SOSHibernateInvalidSessionException((JDBCConnectionException) e, ex.getStatement());
            } else if (e instanceof SQLNonTransientConnectionException) {// can occur without hibernate JDBCConnectionException
                throw new SOSHibernateInvalidSessionException((SQLNonTransientConnectionException) e, ex.getStatement());
            } else if (e instanceof LockAcquisitionException) {
                throw new SOSHibernateLockAcquisitionException((LockAcquisitionException) e, ex.getStatement());
            } else if (e instanceof SQLException) {
                if (getFactory().getDbms().equals(SOSHibernateFactory.Dbms.MYSQL)) {
                    // MySQL with the mariadb driver not throws a specific exception - check error code
                    SQLException se = (SQLException) e;
                    if (se.getErrorCode() == 1205 || se.getErrorCode() == 1213) {// Lock wait timeout, Deadlock
                        throw new SOSHibernateLockAcquisitionException(se, ex.getStatement());
                    }
                }
            }
            e = e.getCause();
        }
        throw ex;
    }
}