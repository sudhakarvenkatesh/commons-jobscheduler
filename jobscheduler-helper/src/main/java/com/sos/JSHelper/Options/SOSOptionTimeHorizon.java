package com.sos.JSHelper.Options;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sos.JSHelper.Exceptions.JobSchedulerException;

/** \class SOSOptionTimeHorizon
 * 
 * \brief SOSOptionTimeHorizon -
 * 
 * \details
 *
 * \section SOSOptionTimeHorizon.java_intro_sec Introduction
 *
 * \section SOSOptionTimeHorizon.java_samples Some Samples
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
 *          Created on 09.03.2011 01:23:07 */
/** @author oh */
public class SOSOptionTimeHorizon extends SOSOptionString {

    /**
	 * 
	 */
    private static final long serialVersionUID = -5511394144386870461L;
    /**
	 * 
	 */
    @SuppressWarnings("unused")
    private final String conClassName = "SOSOptionTimeHorizon";

    private final static String isoPattern = "(\\d{4})-(\\d{1,2})-(\\d{1,2})(?:[ T](\\d{1,2}):(\\d{1,2})(?::(\\d{1,2}))?)?";
    private final static String periodPattern = "([+-]?\\d+)(?::(\\d{1,2}):(\\d{1,2})(?::(\\d{1,2}))?)?";

    /** \brief SOSOptionTimeHorizon
     *
     * \details
     *
     * @param pPobjParent
     * @param pPstrKey
     * @param pPstrDescription
     * @param pPstrValue
     * @param pPstrDefaultValue
     * @param pPflgIsMandatory */
    public SOSOptionTimeHorizon(JSOptionsClass pPobjParent, String pPstrKey, String pPstrDescription, String pPstrValue, String pPstrDefaultValue,
            boolean pPflgIsMandatory) {
        super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
    }

    public Date getDateObject() {
        Calendar objCal = Calendar.getInstance();
        Matcher objIsoDateMatcher = Pattern.compile(isoPattern).matcher(strValue);
        Matcher objPeriodMatcher = Pattern.compile(periodPattern).matcher(strValue);
        int seconds = 0;
        if (objIsoDateMatcher.find()) {
            objCal.set(Integer.parseInt(objIsoDateMatcher.group(1)), Integer.parseInt(objIsoDateMatcher.group(2)) - 1, Integer.parseInt(objIsoDateMatcher.group(3)));
            if (isNotEmpty(objIsoDateMatcher.group(4))) {
                objCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(objIsoDateMatcher.group(4)));
                objCal.set(Calendar.MINUTE, Integer.parseInt(objIsoDateMatcher.group(5)));
                if (isNotEmpty(objIsoDateMatcher.group(6))) {
                    seconds = Integer.parseInt(objIsoDateMatcher.group(6));
                }
                objCal.set(Calendar.SECOND, seconds);
                objCal.set(Calendar.MILLISECOND, 0);
            }
        } else if (objPeriodMatcher.find()) {
            objCal.setTimeInMillis(System.currentTimeMillis() + (Long.parseLong(objPeriodMatcher.group(1).replace("+", "")) * 24 * 60 * 60 * 1000));
            if (isNotEmpty(objPeriodMatcher.group(2))) {
                objCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(objPeriodMatcher.group(2)));
                objCal.set(Calendar.MINUTE, Integer.parseInt(objPeriodMatcher.group(3)));
                if (isNotEmpty(objPeriodMatcher.group(4))) {
                    seconds = Integer.parseInt(objPeriodMatcher.group(4));
                }
                objCal.set(Calendar.SECOND, seconds);
                objCal.set(Calendar.MILLISECOND, 0);
            }
        } else {
            throw new JobSchedulerException(String.format("%1$s must be in the format yyyy-MM-dd[ HH:mm[:ss]] or <+/-number of days from now>[:HH:mm[:ss]]", strValue));
        }
        return objCal.getTime();
    } // public Date getDate

    public Date getEndFromNow() {
        Calendar result = Calendar.getInstance();
        Matcher objPeriodMatcher = Pattern.compile(periodPattern).matcher(strValue);
        int seconds = 0;
        if (objPeriodMatcher.find()) {
            if (isNotEmpty(objPeriodMatcher.group(1)))
                result.add(Calendar.DAY_OF_YEAR, Integer.parseInt(objPeriodMatcher.group(1).replace("+", "")));
            if (isNotEmpty(objPeriodMatcher.group(2))) {
                result.add(Calendar.HOUR, Integer.parseInt(objPeriodMatcher.group(2)));
                result.add(Calendar.MINUTE, Integer.parseInt(objPeriodMatcher.group(3)));
                if (isNotEmpty(objPeriodMatcher.group(4))) {
                    seconds = Integer.parseInt(objPeriodMatcher.group(4));
                }
                result.add(Calendar.SECOND, seconds);
            }
        } else {
            throw new JobSchedulerException(String.format("%1$s must be in the format <+/-number of days from now>[:HH:mm[:ss]]", strValue));
        }
        return result.getTime();
    }

}
