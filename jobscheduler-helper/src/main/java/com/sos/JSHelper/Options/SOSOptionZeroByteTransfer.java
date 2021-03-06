package com.sos.JSHelper.Options;

public class SOSOptionZeroByteTransfer extends SOSOptionStringValueList {

    private static final long serialVersionUID = 8210111365807808464L;
    private enuZeroByteTransfer enu = enuZeroByteTransfer.yes;

    public static enum enuZeroByteTransfer {
        yes, no, strict, relaxed;

        public static String[] getArray() {
            String[] strA = new String[4];
            int i = 0;
            for (enuZeroByteTransfer enuItem : enuZeroByteTransfer.values()) {
                strA[i++] = enuItem.name();
            }
            return strA;
        }

        public static boolean contains(String str) {
            for (enuZeroByteTransfer enuItem : enuZeroByteTransfer.values()) {
                if (enuItem.name().equalsIgnoreCase(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public SOSOptionZeroByteTransfer(final JSOptionsClass pobjParent, final String pstrKey, final String pstrDescription, final String pstrValue,
            final String pstrDefaultValue, final boolean pflgIsMandatory) {
        super(pobjParent, pstrKey, pstrDescription, pstrValue, pstrDefaultValue, pflgIsMandatory);
        super.valueList(enuZeroByteTransfer.getArray());
    }

    public void setValue(final enuZeroByteTransfer enu) {
        this.enu = enu;
        super.setValue(this.enu.name());
    }

    public void setValue(String enu) {
        enu = enu.toLowerCase();
        if ("true".equalsIgnoreCase(enu)) {
            enu = "yes";
        } else if ("false".equalsIgnoreCase(enu)) {
            enu = "no";
        }
        if (enuZeroByteTransfer.contains(enu)) {
            this.enu = enuZeroByteTransfer.valueOf(enu);
        }
        super.setValue(this.enu.name());
    }

    public enuZeroByteTransfer getEnum() {
        enu = enuZeroByteTransfer.valueOf(strValue);
        return enu;
    }

}