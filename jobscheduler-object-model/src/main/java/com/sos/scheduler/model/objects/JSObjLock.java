package com.sos.scheduler.model.objects;


import java.math.BigInteger;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;

import com.sos.VirtualFileSystem.Interfaces.ISOSVirtualFile;
import com.sos.scheduler.model.SchedulerObjectFactory;

/**
* \class JSObjLock 
* 
* \brief JSObjLock - 
* 
* \details
*
* \section JSObjLock.java_intro_sec Introduction
*
* \section JSObjLock.java_samples Some Samples
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
* Created on 09.02.2011 14:47:38
 */

/**
 * @author oh
 *
 */
public class JSObjLock extends Lock {

	private final String		conClassName	= "JSObjLock";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(JSObjLock.class);
	
	public final static String fileNameExtension = ".lock.xml";

	public JSObjLock (SchedulerObjectFactory schedulerObjectFactory) {
		objFactory = schedulerObjectFactory;
	}
	
	public JSObjLock (SchedulerObjectFactory schedulerObjectFactory, Lock origOrder) {
		objFactory = schedulerObjectFactory;
		setObjectFieldsFrom(origOrder);
	}
	
	
	@SuppressWarnings("unchecked")
	public JSObjLock(SchedulerObjectFactory schedulerObjectFactory, ISOSVirtualFile pobjVirtualFile) {
		objFactory = schedulerObjectFactory;
		this.objJAXBElement = (JAXBElement<JSObjBase>) unMarshal(pobjVirtualFile);
		setObjectFieldsFrom((Lock) this.objJAXBElement.getValue());
		setHotFolderSrc(pobjVirtualFile);	
	}
	
	   public void  setMaxNonExclusiveIfNotEmpty(String value) {
	         if (!isEmpty(value)) {
	             super.setMaxNonExclusive(new BigInteger(value)); 
	         }
	      }   
	   
	   public void  setNameIfNotEmpty(String value) {
	          if (!isEmpty(value)) {
	              super.setName(value);
	          }
	       }

	
	
	public void setMaxNonExclusive(int pMaxNonExclusive) {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::setMaxNonExclusive";
		setMaxNonExclusive(BigInteger.valueOf(pMaxNonExclusive));
	} // public void setMaxNonExclusive
}
