package com.sos.JSHelper.Options;

import org.apache.log4j.Logger;

public class JSJobId extends SOSOptionInteger {

    private static final long serialVersionUID = -5005952055146956849L;
    @SuppressWarnings("unused")
    private final String conClassName = "JSJobId";
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(JSJobId.class);

    public JSJobId(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue,
            final String pPstrDefaultValue, final boolean pPflgIsMandatory) {
        super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
        // TODO Auto-generated constructor stub
    }

}
