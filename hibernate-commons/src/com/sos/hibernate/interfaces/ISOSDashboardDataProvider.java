package com.sos.hibernate.interfaces;

import java.util.Date;
import java.util.prefs.Preferences;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Table;
import org.hibernate.Session;

import com.sos.hibernate.classes.DbItem;


 
/**
* \class DataProvider 
* 
* \brief DataProvider - 
* 
* \details
*
* \section DataProvider.java_intro_sec Introduction
*
* \section DataProvider.java_samples Some Samples
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
* \version 19.01.2012
* \see reference
*
* Created on 19.01.2012 13:05:16
 */

public interface ISOSDashboardDataProvider {
    public void getData(int limit);
    public void fillTable(Table table);
    public ISOSHibernateFilter getFilter();
    public void resetFilter();
    public void fillSchedulerIds(CCombo cbSchedulerId);
    
    public void setSchedulerId(String schedulerId);
 
    public void setFrom(Date d);
    public void setTo(Date d);
    
    public void setSearchField(String s);
    public void setShowJobs(boolean b);
    public void setShowJobChains(boolean b);
    public void setShowWithError(boolean b);
    public void setShowRunning(boolean b);
    
    public void setIgnoreList(Preferences prefs);
    public void addToIgnorelist(Preferences prefs,DbItem h);
    public void disableIgnoreList(Preferences prefs);
    public void resetIgnoreList(Preferences prefs);
    
    public void setLate(boolean b);
    public void setStatus(String statusExecuted);
    public void beginTransaction();
    public void update(DbItem h);
    public void commit();
    public Session getSession();
    
}
