package com.sos.hibernate.classes;

import org.eclipse.swt.graphics.Color;

import com.sos.hibernate.interfaces.ISOSTableItem;

/**
* \class SosSortTableItem 
* 
* \brief SosSortTableItem - 
* 
* \details
* 
* \section SosSortTableItem.java_intro_sec Introduction
*
* \section SosSortTableItem.java_samples Some Samples
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
* \author Uwe Risse
* \version 20.12.2011
* \see reference
*
* Created on 20.12.2011 13:43:21
 */

public class SosSortTableItem {

	@SuppressWarnings("unused")
	private final String	conClassName	= "SosSortTableItem";
	public String[] textBuffer;
 	public DbItem data;
    public Color background;
    public Color foreground;
    public Color[] backgroundColumn;
    public Color[] foregroundColumn;
 		


 	public SosSortTableItem() {
 
	}
 	

 	public SosSortTableItem(ISOSTableItem tableItem) {
		this.setTextBuffer(tableItem.getTextBuffer());
        this.setData((DbItem)tableItem.getData());
        this.setBackground(tableItem.getBackground());
        this.setForeground(tableItem.getForeground());
        
        this.setBackgroundColumn(tableItem.getBackgroundColumn());
        this.setForegroundColumn(tableItem.getForegroundColumn());
	}

	public String[] getTextBuffer() {
		return textBuffer;
	}

	public void setTextBuffer(String[] textBuffer) {
		this.textBuffer = textBuffer;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public DbItem getData() {
		return data;
	}

	public void setData(DbItem data) {
		this.data = data;
	}

	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}


    public Color[] getBackgroundColumn() {
        return backgroundColumn;
    }


    public void setBackgroundColumn(Color[] backgroundColumn) {
        this.backgroundColumn = backgroundColumn;
    }


    public Color[] getForegroundColumn() {
        return foregroundColumn;
    }


    public void setForegroundColumn(Color[] foregroundColumn) {
        this.foregroundColumn = foregroundColumn;
    }
}
