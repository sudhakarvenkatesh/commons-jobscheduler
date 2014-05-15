package com.sos.dialog.components;
import java.io.File;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;

import com.sos.JSHelper.Exceptions.JobSchedulerException;

/**
* \class FileNameSelector
*
* \brief FileNameSelector -
*
* \details
*
* \code
*   .... code goes here ...
* \endcode
*
* <p style="text-align:center">
* <br />---------------------------------------------------------------------------
* <br /> APL/Software GmbH - Berlin
* <br />##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
* <br />---------------------------------------------------------------------------
* </p>
* \see reference
*
 */
public class SOSFileNameSelector extends SOSPreferenceStoreText {
	@SuppressWarnings("unused")
	private final String		conClassName			= this.getClass().getSimpleName();
	@SuppressWarnings("unused")
	private static final String	conSVNVersion			= "$Id$";
	@SuppressWarnings("unused")
	private final Logger		logger					= Logger.getLogger(this.getClass());
	//	private JobListener		objDataProvider			= null;
	private String				strFileName				= "";
	private final String				strPreferenceStoreKey	= "";
	// TODO make class variable
	private final Preferences	prefs					= Preferences.userNodeForPackage(this.getClass());

	public String getFileName() {
		return strFileName;
	}

	//	public void setDataProvider(final JobListener pobjDataProvider) {
	//		objDataProvider = pobjDataProvider;
	//		refreshContent();
	//
	//		Menu objContextMenu = getMenu();
	//		if (objContextMenu == null) {
	//			objContextMenu = new Menu(getShell(), SWT.POP_UP);
	//		}
	//
	//		MenuItem item = new MenuItem(objContextMenu, SWT.PUSH);
	//		item.addListener(SWT.Selection, getSaveAsListener());
	//		item.setText("Save as ...");
	//	}
	//
	private Listener getSaveAsListener() {
		return new Listener() {
			@Override
			public void handleEvent(final Event e) {
			}
		};
	}

	private FocusAdapter getFocusAdapter() {
		return new FocusAdapter() {
			@Override
			public void focusGained(final FocusEvent e) {
				selectAll();
			}

			@Override
			public void focusLost(final FocusEvent e) {
			}
		};
	}

	private MouseListener getMouseListener() {
		return new MouseListener() {
			@Override
			public void mouseUp(final MouseEvent arg0) {
			}

			@Override
			public void mouseDown(final MouseEvent arg0) {
			}

			@Override
			public void mouseDoubleClick(final MouseEvent arg0) {
				String strT = "";
				strFileName = getText();
				String strKey = strPreferenceStoreKey + "/" + "lastFileName";
				if (strFileName.trim().length() <= 0) {
					strFileName = prefs.get(strPreferenceStoreKey, "");
				}
				strT = openDirectoryFile("*.*", strFileName);
				if (strT.trim().length() > 0) {
					File objFile = new File(strT);
					if (objFile.canRead()) {
						setText(objFile.getAbsoluteFile().toString());
						prefs.put(strPreferenceStoreKey, strT);
						strFileName = strT;
						setText(objFile.getName());
					}
					else {
						//							MainWindow.ErrMsg(String.format("File '%1$s' not found or is not readable", strT));
						throw new JobSchedulerException(String.format("File '%1$s' not found or is not readable", strT));
					}
				}
			}
		};
	}

	public String openDirectoryFile(final String mask, final String pstrDirectoryName) {
		String filename = "";
		FileDialog fdialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
		fdialog.setFilterPath(pstrDirectoryName);
		String filterMask = mask.replaceAll("\\\\", "");
		filterMask = filterMask.replaceAll("\\^.", "");
		filterMask = filterMask.replaceAll("\\$", "");
		fdialog.setFilterExtensions(new String[] { filterMask });
		filename = fdialog.open();
		if (filename == null || filename.trim().length() == 0) {
			filename = "";
		}
		else {
			filename = filename.replaceAll("\\\\", "/");
		}
		return filename;
	}

	public void refreshContent() {
	}

	public SOSFileNameSelector(final Composite pobjComposite, final int arg1) {
		super(pobjComposite, arg1);
		addFocusListener(getFocusAdapter());
		setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		addMouseListener(getMouseListener());
	}

}
