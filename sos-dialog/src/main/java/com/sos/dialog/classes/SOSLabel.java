/**
 *
 */
package com.sos.dialog.classes;

import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.sos.dialog.Globals;

/** @author KB */
public class SOSLabel extends Label {

    @SuppressWarnings("unused")
    private final String conClassName = this.getClass().getSimpleName();
    @SuppressWarnings("unused")
    private static final String conSVNVersion = "$Id$";
    @SuppressWarnings("unused")
    private final Logger logger = Logger.getLogger(this.getClass());

    private final Vector<Control> objControlList = new Vector<Control>();

    /**
	 *
	 */
    public SOSLabel(final Composite parent, final int style) {
        super(parent, SWT.None);
        this.setBackground(Globals.getCompositeBackground());
        setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
    }

    public void addChild(final SOSControl pobjC) {
        objControlList.add(pobjC);
    }

    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

}
