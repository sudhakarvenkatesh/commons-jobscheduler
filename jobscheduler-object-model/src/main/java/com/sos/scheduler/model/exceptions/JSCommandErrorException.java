package com.sos.scheduler.model.exceptions;

import org.apache.log4j.Logger;

import com.sos.JSHelper.Exceptions.JobSchedulerException;

/** \class JSCommandErrorException
 * 
 * \brief JSCommandErrorException -
 * 
 * \details
 *
 * \section JSCommandErrorException.java_intro_sec Introduction
 *
 * \section JSCommandErrorException.java_samples Some Samples
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
 *          Created on 03.02.2011 17:50:16 */

/** @author oh */
public class JSCommandErrorException extends JobSchedulerException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6924451147571494210L;
    @SuppressWarnings("unused")
    private final String conClassName = "JSCommandErrorException";
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(JSCommandErrorException.class);

    public JSCommandErrorException() {
        //
    }

    /** \brief JSCommandErrorException
     *
     * \details
     *
     * @param pstrMessage */
    public JSCommandErrorException(String pstrMessage) {
        super(pstrMessage);
        // TODO Auto-generated constructor stub
    }

    /** \brief JSCommandErrorException
     *
     * \details
     *
     * @param pstrMessage
     * @param e */
    public JSCommandErrorException(String pstrMessage, Exception e) {
        super(pstrMessage, e);
        // TODO Auto-generated constructor stub
    }
}
