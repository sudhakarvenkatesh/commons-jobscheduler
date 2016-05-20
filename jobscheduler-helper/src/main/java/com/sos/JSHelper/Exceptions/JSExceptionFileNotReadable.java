package com.sos.JSHelper.Exceptions;

/** \class JSExceptionFileNotReadable
 * 
 * \brief JSExceptionFileNotReadable -
 * 
 * \details
 *
 * \section JSExceptionFileNotReadable.java_intro_sec Introduction
 *
 * \section JSExceptionFileNotReadable.java_samples Some Samples
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
 * \author EQALS
 * 
 * @version $Id$20.01.2009 \see reference
 *
 *          Created on 20.01.2009 12:16:45 */

public class JSExceptionFileNotReadable extends JobSchedulerException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3028342712536869075L;

    @SuppressWarnings("unused")
    private final String conClassName = "JSExceptionFileNotReadable";

    /** \brief JSExceptionFileNotReadable
     *
     * \details Construtor with message.
     *
     * @param pstrMessage */
    public JSExceptionFileNotReadable(String pstrMessage) {
        super(pstrMessage);
        this.setStatus(JobSchedulerException.PENDING);
    }

    /** \brief JSExceptionFileNotReadable
     *
     * \details Construtor without message. */
    public JSExceptionFileNotReadable() {
        super("File is not readable");
        this.setStatus(JobSchedulerException.PENDING);
    }

}
