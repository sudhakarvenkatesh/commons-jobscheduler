package com.sos.dialog.components;

import static com.sos.dialog.Globals.MsgHandler;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.sos.JSHelper.Options.SOSOptionElement;
import com.sos.dialog.Globals;
import com.sos.dialog.classes.SOSCheckBox;
import com.sos.dialog.classes.SOSGroup;
import com.sos.dialog.classes.SOSTextBox;
import com.sos.dialog.layouts.Gridlayout;

/** @author KB */
public class ControlCreator {

    private Composite objParentComposite = null;

    public ControlCreator(final Composite pobjParentComposite) {
        objParentComposite = pobjParentComposite;
    }

    public SOSGroup getGroup(final String pstrText) {
        SOSGroup group_source = new SOSGroup(objParentComposite, SWT.NONE);
        Gridlayout.set4ColumnGroupLayout(group_source);
        group_source.setLayout(Gridlayout.get4ColumnLayout());
        MsgHandler.newMsg(pstrText).control(group_source);
        group_source.setBackground(Globals.getCompositeBackground());
        return group_source;
    }

    public Control getControl(final SOSOptionElement pobjOption) {
        return getControl(pobjOption, 1);
    }

    public SOSCheckBox getCheckBox(final SOSOptionElement pobjOption) {
        return (SOSCheckBox) getControl(pobjOption, 1);
    }

    public Text getText(final SOSOptionElement pobjOption) {
        return (Text) getControl(pobjOption, 1);
    }

    public Control getLabel() {
        return getLabel(1);
    }

    public Control getLabel(final int intNoOfLabels) {
        Label label = null;
        for (int i = 0; i < intNoOfLabels; i++) {
            label = new Label(objParentComposite, SWT.NONE);
            label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        }
        return label;
    }

    public Control getSeparator() {
        Label label = new Label(objParentComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1));
        label.setBackground(Globals.getCompositeBackground());
        return label;
    }

    public Control getSeparator(final String pstrI18NKey) {
        CLabel label = new CLabel(objParentComposite, SWT.CENTER);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1));
        label.setBackground(new Color[] { Globals.getSystemColor(SWT.COLOR_WHITE), Globals.getSystemColor(SWT.COLOR_GRAY),
                Globals.getFieldHasFocusBackground(), Globals.getSystemColor(SWT.COLOR_GRAY), Globals.getSystemColor(SWT.COLOR_WHITE) }, new int[] {
                25, 50, 75, 100 }, false);
        label.setText(MsgHandler.newMsg(pstrI18NKey).label());
        label.setFont(Globals.stFontRegistry.get("text"));
        label.setToolTipText(MsgHandler.newMsg(pstrI18NKey).tooltip());
        return label;
    }

    public Control getInvisibleSeparator() {
        Control label = getSeparator();
        label.setVisible(false);
        return label;
    }

    public Control getControl(final SOSOptionElement pobjOption, final int pintHorizontalSpan) {
        Control objT = null;
        Label lblNewLabel = new Label(objParentComposite, SWT.NONE);
        GridData lblGridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        lblNewLabel.setLayoutData(lblGridData);
        MsgHandler.newMsg(pobjOption.getShortKey()).control(lblNewLabel);
        String strControlType = pobjOption.getControlType();
        if ("text".equalsIgnoreCase(strControlType)) {
            SOSTextBox tbxText = new SOSTextBox(objParentComposite, Globals.gTextBoxStyle);
            if (pobjOption.isHideValue()) {
                tbxText.setEchoChar('*');
            }
            objT = tbxText;
            tbxText.setAutoCompletehandler(pobjOption);
        } else if ("combo".equalsIgnoreCase(strControlType)) {
            CCombo cbxCCombo1 = new CCombo(objParentComposite, SWT.NONE | Globals.gTextBoxStyle);
            objT = cbxCCombo1;
        } else if ("checkbox".equalsIgnoreCase(strControlType)) {
            SOSCheckBox btnO = new SOSCheckBox(objParentComposite, SWT.CHECK | SWT.FLAT);
            objT = btnO;
        } else if ("file".equalsIgnoreCase(strControlType)) {
            SOSFileNameSelector objFS = new SOSFileNameSelector(objParentComposite, Globals.gTextBoxStyle);
            objFS.setPreferenceStoreKey(pobjOption.getShortKey());
            objT = objFS;
            objFS.setAutoCompletehandler(pobjOption);
        } else if ("folder".equalsIgnoreCase(strControlType)) {
            SOSFileNameSelector objFS = new SOSFileNameSelector(objParentComposite, Globals.gTextBoxStyle);
            objFS.setPreferenceStoreKey(pobjOption.getShortKey());
            objT = objFS;
            objFS.setAutoCompletehandler(pobjOption);
        }
        if (objT != null) {
            objT.setData("option", pobjOption);
        }
        new ControlHelper(lblNewLabel, objT, pobjOption);
        if (objT != null) {
            objT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, pintHorizontalSpan, 1));
        }
        return objT;
    }

}
