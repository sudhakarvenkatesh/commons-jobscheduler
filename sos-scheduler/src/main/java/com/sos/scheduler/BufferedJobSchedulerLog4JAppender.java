package com.sos.scheduler;

import java.util.ArrayList;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import sos.util.SOSSchedulerLogger;

/**
* \class StringBufferAppender 
* 
* \brief StringBufferAppender - Collect Messages in a StringBuffer-Object
* 
* \details
* This Class acts as a redirection for all Log4J-Messages to the JobScheduler Logger.
* 
* 
* \section StringBufferAppender.java_intro_sec Introduction
*
* \section StringBufferAppender.java_samples Some Samples
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
* \author KB
* @version $Id: BufferedJobSchedulerLog4JAppender.java 14731 2011-07-05 20:50:42Z sos $13.06.2010
* \see reference
*
* Created on 13.06.2010 12:08:56
 */

/**
 * @author SS
 *
 */
public class BufferedJobSchedulerLog4JAppender extends JobSchedulerLog4JAppender {

	private ArrayList<LoggingEvent> logEvents;

	public BufferedJobSchedulerLog4JAppender() {
		super();
	}

	public BufferedJobSchedulerLog4JAppender(Layout pobjLayout) {
		super(pobjLayout);
	}

	@Override public void setSchedulerLogger(SOSSchedulerLogger pobjSchedulerLogger) {
		super.setSchedulerLogger(pobjSchedulerLogger);
		for (int i = 0; i < logEvents.size(); i++) {
			super.subAppend(logEvents.get(i));
		}
	}

	/** 
	 * @see org.apache.log4j.FileAppender#activateOptions() */
	@Override
	public void activateOptions() {
		super.activateOptions();
		logEvents = new ArrayList<LoggingEvent>();
	}

	/** 
	 * @see org.apache.log4j.WriterAppender#subAppend(LoggingEvent) */
	@Override
	protected void subAppend(LoggingEvent event) {
		if (hasLogger())
			super.subAppend(event);
		else
			logEvents.add(event);
	}

}
