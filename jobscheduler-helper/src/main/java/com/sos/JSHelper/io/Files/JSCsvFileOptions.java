package com.sos.JSHelper.io.Files;

/** \class JSCsvFileOptions
 *
 * \brief JSCsvFileOptions - Optionen-Klasse f�r einen CSV-File
 *
 * \details Optionen-Klasse f�r einen CSV-File
 *
 * \section JSCsvFileOptions.intro_sec Introduction
 *
 * Diese Klasse repr�sentiert alle Optionen (auch Settings oder Parameter
 * genannt), die f�r die Bearbeitung einer CSV-Datei ben�tigt werden.
 *
 * \section JSCsvFileOptions.samples Some Samples
 *
 * \code .... code goes here ... \endcode
 *
 * <p style="text-align:center">
 * <br />
 * --------------------------------------------------------------------------- <br />
 * APL/Software GmbH - Berlin <br />
 * ##### generated by ClaviusXPress (http://www.sos-berlin.com) ######### <br />
 * Mittwoch, 22. Oktober 2008, sgx2343 (sgx2343) <br />
 * ---------------------------------------------------------------------------
 * </p>
 * \author sgx2343
 * 
 * @version $Id$0.9 \see JSCsvFile */

import java.util.HashMap;

import com.sos.JSHelper.Exceptions.JSExceptionMandatoryOptionMissing;
import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.Listener.JSListener;
import com.sos.JSHelper.Options.JSOptionsClass;

public class JSCsvFileOptions extends JSOptionsClass {

    private final String conClassName = "JSCsvFileOptions";

    /** \var String Delimiter: Feld-Trennzeichen */
    private String strDelimiter = String.valueOf((char) 254);
    private final String conDelimiterSettingsKey = conClassName + ".Delimiter";
    private final String conCSVColumnDelimiterSettingsKey = conClassName + ".CSVColumnDelimiter";

    /** \var boolean SkipFirstLine: Erste Zeile als �berschrift interpretieren
     * und �berlesen */
    private boolean flgSkipFirstLine = true;
    private final String conSkipFirstLineSettingsKey = conClassName + ".Skip_First_Line";

    /** \var boolean IgnoreValueDelimiter: Ignore Value Delimiter */
    private boolean flgIgnoreValueDelimiter = true;
    private final String conIgnoreValueDelimiterSettingsKey = conClassName + ".IgnoreValueDelimiter";

    public JSCsvFileOptions() {
        objParentClass = this.getClass();
        /** --------------------------------------------------------------------
         * ------- <constructor type="smcw" version="1.0">
         * <classname>JSCsvFileOptions</classname> <name></name> <title>Optionen
         * f�r CSV-Files</title> <description> <para> Optionen f�r CSV-Files
         * </para> </description> <params> </params> <keywords>
         * <keyword>CSV</keyword> <keyword>Options</keyword> </keywords>
         * <categories> <category>CSV</category> </categories> </constructor>
         * ----
         * ------------------------------------------------------------------
         * ------ */
    } // public JSCsvFileOptions

    public JSCsvFileOptions(final JSListener pobjListener) {
        registerMessageListener(pobjListener);
    } // public JSCsvFileOptions

    //

    /** ------------------------------------------------------------------------
     * --- <constructor type="smcw" version="1.0"> <name></name>
     * <title>JSIDocOptions</title> <description> <para> Konstruktor
     * JSIDocOptions, als Parameter eine HashMap mit den Optionen (so wie es im
     * Dataswitch Standard ist). Dieser Konstruktor mappt die Werte aus der
     * HashMap auf die Properties der Klasse. </para> </description> <params>
     * <param name="JSSettings" type="HashMap" ref="byvalue" > <para> Die
     * Parameter, wie sie im Settings der JS-Datenbank definiert sind, sind in
     * dieser HashMap enthalten und werden auf die Properties dieser Klasse
     * gemappt. </para> </param> </params> <keywords> <keyword>IDoc</keyword>
     * <keyword>Options</keyword> <keyword>Settings</keyword>
     * <keyword>JSCsvFileOptions:Class</keyword> </keywords> <categories>
     * <category>IDoc</category> <category>OptionClass</category> </categories>
     * </constructor>
     * ------------------------------------------------------------
     * ---------------- */
    public JSCsvFileOptions(final HashMap<String, String> JSSettings) throws Exception {
        setAllOptions(JSSettings);
    } // public JSCsvFileOptions (HashMap JSSettings)

    //

    @Override
    public void toOut() {
        System.out.println(getAllOptionsAsString());
    } // public void toOut ()

    //

    @Override
    public String toString() {
        return getAllOptionsAsString();
    } // public String toString ()

    //

    private String getAllOptionsAsString() {
        String strT = conClassName + "\n";

        strT += "Delimiter      Feld-Trennzeichen : " + this.Delimiter() + "\n";
        strT += "SkipFirstLine  Erste Zeile als �berschrift interpretieren und �berlesen : " + this.SkipFirstLine() + "\n";
        strT += "IgnoreValueDelimiter Ignore Value Delimiter : " + this.IgnoreValueDelimiter() + "\n";

        return strT;
    } // private String getAllOptionsAsString ()

    @Override
    public void setAllOptions(final HashMap<String, String> JSSettings) {
        try {
            objSettings = JSSettings;
            super.Settings(objSettings);

            flgSetAllOptions = true;

            final String strT = super.getItem(conDelimiterSettingsKey);
            if (isNotEmpty(strT)) {
                this.Delimiter(strT);
            } else {
                this.Delimiter(super.getItem(conCSVColumnDelimiterSettingsKey));
            }
            this.SkipFirstLine(super.getBoolItem(conSkipFirstLineSettingsKey));
            this.IgnoreValueDelimiter(super.getBoolItem(conIgnoreValueDelimiterSettingsKey));

            flgSetAllOptions = false;
        } catch (Exception e) {
            throw new JobSchedulerException(e);
        }
    } // public void setAllOptions (HashMap <String, String> JSSettings)

    @Override
    public void checkMandatory() throws Exception {

        try {
            this.Delimiter(this.Delimiter());
        } catch (final Exception e) {
            throw new JSExceptionMandatoryOptionMissing(e.toString());
        }

        // this.ReportingClient(this.ReportingClient());
    } // public void CheckMandatory ()

    //

    /*
     * --------------------------------------------------------------------------
     * - <method type="smcw" version="1.0"> <name>Delimiter</name>
     * <title>Feld-Trennzeichen</title> <description> <para> Feld-Trennzeichen
     * </para> <para> Initial-Wert (Default) ist "254" (ohne Anf�hrungszeichen).
     * </para> <mandatory>true</mandatory> </description> <params> <param
     * name="param1" type=" " ref="byref|byvalue|out" > <para> </para> </param>
     * </params> <keywords> <keyword>CSV</keyword> <keyword>Options</keyword>
     * <keyword>Delimiter</keyword> </keywords> <categories>
     * <category>CSV</category> </categories> </method>
     * --------------------------
     * --------------------------------------------------
     */
    /*
     * ! \brief Delimiter - Feld-Trennzeichen \details Getter: Feld-Trennzeichen
     * Example:
     * @return Returns the Delimiter.
     */
    public String Delimiter() {
        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::Delimiter";
        return strDelimiter;
    } // String Delimiter()

    /*
     * ! \brief Delimiter - Feld-Trennzeichen \details Das Feld-Trennzeichen
     * kann als einzelnes Character oder als numerischer Wert angegeben werden.
     * @param pstrDelimiter: The String Delimiter to set.
     */
    public JSCsvFileOptions Delimiter(final String pstrDelimiter) throws Exception {
        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::Delimiter";

        if (pstrDelimiter == null) {
            SignalError(conClassName + ":Delimiter" + conNullButMandatory);
        } else {
            if (pstrDelimiter.matches("^[0-9]+$")) {
                strDelimiter = String.valueOf((char) Integer.parseInt(pstrDelimiter));
            } else {
                // - <remark who='EQALS' when='Mittwoch, 14. Oktober 2009'
                // id='Korrektur' >
                /** \change Mittwoch, 14. Oktober 2009 EQALS Korrektur Korrektur */
                // - <oldcode>
                // strDelimiter = pstrDelimiter.substring(0, 0);
                // - </oldcode>
                // - <newcode>
                strDelimiter = pstrDelimiter.substring(0, 1);
                // - </newcode>
                // - </remark> <!-- id=<Korrektur> -->
            }
        }
        return this;
    } // public void Delimiter(String pstrDelimiter)

    /*
     * ! \brief SkipFirstLine - Erste Zeile der Dateials �berschrift
     * interpretieren und �berlesen \details Getter: Erste Zeile als �berschrift
     * interpretieren und �berlesen Example:
     * @return Returns the SkipFirstLine.
     */
    public boolean SkipFirstLine() {
        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::SkipFirstLine";
        return flgSkipFirstLine;
    } // boolean SkipFirstLine()

    /*
     * ! \brief SkipFirstLine - Erste Zeile als �berschrift interpretieren und
     * �berlesen \details Setter: Erste Zeile als �berschrift interpretieren und
     * �berlesen
     * @param pflgSkipFirstLine: The boolean SkipFirstLine to set.
     */
    public JSCsvFileOptions SkipFirstLine(final boolean pflgSkipFirstLine) throws Exception {
        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::SkipFirstLine";
        flgSkipFirstLine = pflgSkipFirstLine;
        return this;
    } // public void SkipFirstLine(boolean pflgSkipFirstLine)

    /*
     * ! \brief IgnoreValueDelimiter - Ignore Value Delimiter \details Getter:
     * Ignore Value Delimiter Example:
     * @return Returns the IgnoreValueDelimiter.
     */
    public boolean IgnoreValueDelimiter() {
        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::IgnoreValueDelimiter";
        return flgIgnoreValueDelimiter;
    } // boolean IgnoreValueDelimiter()

    /*
     * ! \brief IgnoreValueDelimiter - Ignore Value Delimiter \details Setter:
     * Ignore Value Delimiter
     * @param pflgIgnoreValueDelimiter: The boolean IgnoreValueDelimiter to set.
     */
    public JSCsvFileOptions IgnoreValueDelimiter(final boolean pflgIgnoreValueDelimiter) throws Exception {
        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::IgnoreValueDelimiter";
        flgIgnoreValueDelimiter = pflgIgnoreValueDelimiter;
        return this;
    } // public void IgnoreValueDelimiter(boolean pflgIgnoreValueDelimiter)

} // public class JSCsvFileOptions
