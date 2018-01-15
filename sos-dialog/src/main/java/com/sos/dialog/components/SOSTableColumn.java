package com.sos.dialog.components;

import java.util.prefs.Preferences;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.sos.localization.Messages;

 
public class SOSTableColumn extends TableColumn {

    @SuppressWarnings("unused")
    private final String conClassName = "SOSTableColumn";
    private Preferences prefs;
    private String strI18NKey = null;
    private String strToolTip = null;
    // TODO: Besser w�re hier SOSTableDateColumn etc. ur 2012-09-18
    private ColumnType columnType = null;

    public static enum ColumnType {
        DATE, STRING;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public SOSTableColumn(Table table, String pstrI18NKey, int width, Messages objMsg, ColumnType columnType_) {
        super(table, SWT.NONE);
        prefs = Preferences.userNodeForPackage(this.getClass());
        strI18NKey = pstrI18NKey;
        String strText = objMsg.getLabel(pstrI18NKey);
        String strToolTip = objMsg.getLabel(pstrI18NKey + ".tooltip");
        this.columnType = columnType_;
        createColumn(table, SWT.NONE, strText, width);
    }

    public SOSTableColumn(Table table, String pstrI18NKey, int width, Messages objMsg) {
        super(table, SWT.NONE);
        prefs = Preferences.userNodeForPackage(this.getClass());
        strI18NKey = pstrI18NKey;
        String strText = objMsg.getLabel(pstrI18NKey);
        String strToolTip = objMsg.getLabel(pstrI18NKey + ".tooltip");
        this.columnType = ColumnType.STRING;
        createColumn(table, SWT.NONE, strText, width);
    }

    public SOSTableColumn(Table table, int style, String text, int width) {
        super(table, style);
        prefs = Preferences.userNodeForPackage(this.getClass());
        this.columnType = ColumnType.STRING;
        createColumn(table, style, text, width);
    }

    public SOSTableColumn(Table table, int style, String text, int width, ColumnType columnType_) {
        super(table, style);
        prefs = Preferences.userNodeForPackage(this.getClass());
        this.columnType = columnType_;
        createColumn(table, style, text, width);
    }

    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

    private int getIntValue(String s, int d) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException n) {
            return d;
        }
    }

    private void createColumn(final Table table, int style, final String text, int width) {
        this.setWidth(width);
        this.addControlListener(new ControlAdapter() {

            public void controlResized(final ControlEvent e) {
                int width = ((TableColumn) e.widget).getWidth();
                prefs.node(table.getClass().getName() + "/col/" + text).put("width", String.valueOf(width));
            }
        });
        if (text != null) {
            this.setText(text);
            this.setWidth(this.getIntValue(prefs.node(table.getClass().getName() + "/col/" + this.getText()).get("width", String.valueOf(width)),
                    width));
        }
        if (strToolTip != null) {
            this.setToolTipText(strToolTip);
        }
    }

}
