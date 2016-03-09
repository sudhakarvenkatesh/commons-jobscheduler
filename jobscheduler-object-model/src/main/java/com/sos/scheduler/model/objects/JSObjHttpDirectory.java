package com.sos.scheduler.model.objects;

import org.apache.log4j.Logger;

import com.sos.scheduler.model.SchedulerObjectFactory;

/** \class JSObjHttpDirectory
 * 
 * \brief JSObjHttpDirectory -
 * 
 * \details
 *
 * \section JSObjHttpDirectory.java_intro_sec Introduction
 *
 * \section JSObjHttpDirectory.java_samples Some Samples
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
 *          Created on 09.02.2011 14:32:21 */

/** @author oh */
public class JSObjHttpDirectory extends HttpDirectory {

    @SuppressWarnings("unused")
    private final String conClassName = "JSObjHttpDirectory";
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(JSObjHttpDirectory.class);

    public JSObjHttpDirectory(SchedulerObjectFactory schedulerObjectFactory) {
        super();
        objFactory = schedulerObjectFactory;
    }
}
