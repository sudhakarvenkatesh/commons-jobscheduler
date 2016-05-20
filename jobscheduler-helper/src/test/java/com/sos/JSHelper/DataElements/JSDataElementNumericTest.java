package com.sos.JSHelper.DataElements;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** \class JSDataElementNumericTest
 * 
 * \brief JSDataElementNumericTest -
 * 
 * \details
 *
 * \section JSDataElementNumericTest.java_intro_sec Introduction
 *
 * \section JSDataElementNumericTest.java_samples Some Samples
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
 * @version $Id$14.08.2009 \see reference
 *
 *          Created on 14.08.2009 13:04:10 */

public class JSDataElementNumericTest {

    @SuppressWarnings("unused")
    private final String conClassName = "JSDataElementNumericTest";

    public JSDataElementNumericTest() {
        //
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSubtract() throws Exception {
        final JSDataElementNumeric objEins = new JSDataElementNumeric("1");
        final JSDataElementNumeric objDeliveryNumberIDoc = new JSDataElementNumeric("10");
        objDeliveryNumberIDoc.subtract(objEins).getValue();
        assertEquals("Eins subtrahieren => 10-1=9", "9", objDeliveryNumberIDoc.getValue());
    }

}
