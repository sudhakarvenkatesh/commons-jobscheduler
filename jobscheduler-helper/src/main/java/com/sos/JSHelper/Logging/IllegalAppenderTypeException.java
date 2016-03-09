package com.sos.JSHelper.Logging;

import org.apache.log4j.Logger;

import com.sos.JSHelper.Exceptions.JobSchedulerException;

/** \file IllegalAppenderTypeException.java \brief Appender ist vom falschen Typ
 * 
 * \class IllegalAppenderTypeException \brief Appender ist vom falschen Typ
 * \detail Ein erwarteter Log4J-Appender ist in der Log4J-Konfigurationsdatei
 * vorhanden, aber vom falschen Typ.
 *
 * \section IllegalAppenderTypeException.java_intro_sec Introduction
 *
 * \section IllegalAppenderTypeException.java_samples Some Samples
 *
 * \author EQCPN
 * 
 * @version $Id$Dienstag, 5. Mai 2009 \see reference
 *
 *          <div class="sos_branding">
 *          <p>
 *          � 2009 APL/Software GmbH - Berlin - generated by ClaviusXPress (<a
 *          style="color:silver" href="http://www.sos-berlin.com"
 *          target="_blank">http://www.sos-berlin.com</a>)
 *          </p>
 *          </div> */
public class IllegalAppenderTypeException extends JobSchedulerException {

    @SuppressWarnings("unused")
    private static final String conClassName = "IllegalAppenderTypeException";
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getRootLogger();

    /*
     * !
     * @brief Construtor with message.
     * @param msg the message-text of the exception
     */
    public IllegalAppenderTypeException(String pstrMessage) {
        super(pstrMessage);
        this.Message(pstrMessage);
        this.Status(JobSchedulerException.PENDING);
        logger.error(this.ExceptionText());
        // this.Category(CategoryOptions);
        // this.Typ(TypeOptionMissing);
    }

    public IllegalAppenderTypeException() {
        this("exception 'IllegalAppenderTypeException' raised ...");
        logger.error(this.ExceptionText());
    } // public IllegalAppenderTypeException

    public String ExceptionText() {
        String strT = null;
        strT = super.ExceptionText();
        return strT;
    }
} // public class IllegalAppenderTypeException
