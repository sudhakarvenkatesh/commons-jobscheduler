package com.sos.JSHelper.Options;

import org.apache.log4j.Logger;

import com.sos.JSHelper.Exceptions.JobSchedulerException;

/**
* \class SOSOptionJadeOperation
*
* \brief SOSOptionJadeOperation -
*
* \details
*
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
* \author KB
* \version $Id$
* \see reference
*
* Created on 09.09.2011 15:25:28
 */

/**
 * @author KB
 *
 */
public class SOSOptionJadeOperation extends SOSOptionStringValueList {

	private static final long	serialVersionUID	= 1786255103960193423L;
	private final String		conClassName		= "SOSOptionJadeOperation";
	@SuppressWarnings("unused")
	private static final String	conSVNVersion		= "$Id$";
	private static final Logger	logger				= Logger.getLogger(SOSOptionJadeOperation.class);

	private enuJadeOperations	enuTT				= enuJadeOperations.undefined;

	public static enum enuJadeOperations {
		send, /* old style: from localhost to target-host */
		receive, /* old style: from remote-host to localhost */
		copy, move, delete, undefined, rename, zip, getlist // get the list of filenames only
		, sendusingdmz, receiveusingdmz, copytointernet, copyfrominternet, remove /* alias for "delete"
																					/* */;

		public String Text() {
			String strT = this.name();
			return strT;
		}
	}

	/**
	 * \brief SOSOptionJadeOperation
	 *
	 * \details
	 *
	 * @param pobjParent
	 * @param pstrKey
	 * @param pstrDescription
	 * @param pstrValue
	 * @param pstrDefaultValue
	 * @param pflgIsMandatory
	 */
	public SOSOptionJadeOperation(final JSOptionsClass pobjParent, final String pstrKey, final String pstrDescription, final String pstrValue,
			final String pstrDefaultValue, final boolean pflgIsMandatory) {
		super(pobjParent, pstrKey, pstrDescription, pstrValue, pstrDefaultValue, pflgIsMandatory);

		String strT = "";
		for (enuJadeOperations enuT : enuJadeOperations.values()) {
			strT += enuT.Text() + ";";
		}
		this.createValueList(strT.substring(0, strT.length() - 1));
	}

	public void Value(final enuJadeOperations penuOperation) {
		enuTT = penuOperation;
		if (enuTT == enuJadeOperations.remove) {
			enuTT = enuJadeOperations.delete;
		}
		super.Value(enuTT.Text());
	}

	public enuJadeOperations value() {
		for (enuJadeOperations enuT : enuJadeOperations.values()) {
			if (enuT.name().equalsIgnoreCase(strValue)) {
				enuTT = enuT;
				break;
			}
		}
		return enuTT;
	}

	@Override
	public void Value(final String pstrValue) {

		boolean flgOperationIsValid = false;
		if (pstrValue == null) {
			throw new JobSchedulerException("illegal parameter value: null");
		}

		if (pstrValue != null) {
			for (enuJadeOperations enuT : enuJadeOperations.values()) {
				if (enuT.name().equalsIgnoreCase(pstrValue)) {
					this.Value(enuT);
					flgOperationIsValid = true;
					super.Value(enuTT.Text());
					return;
				}
			}

			if (flgOperationIsValid == false) {
				throw new JobSchedulerException(String.format("unknown or invalid value for parameter '%1$s' specified: '%2$s'", this.getShortKey(), pstrValue));
			}
		}
		super.Value(pstrValue);
	}

	public boolean isOperationSend() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + ":: isOperationSend";

		enuJadeOperations enuT = this.value();

		return enuT == enuJadeOperations.send || enuT == enuJadeOperations.sendusingdmz;
	} // private boolean isOperationSend

	public boolean isOperationSendUsingDMZ() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + ":: isOperationSendUsingDMZ";

		enuJadeOperations enuT = this.value();

		return enuT == enuJadeOperations.sendusingdmz;
	} // private boolean isOperationSendUsingDMZ

	public boolean isOperationCopyFromInternet() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + ":: isOperationCopyFromInternet";

		enuJadeOperations enuT = this.value();

		return enuT == enuJadeOperations.copyfrominternet;
	} // private boolean isOperationCopyFromInternet

	public boolean isOperationCopyToInternet() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + ":: isOperationCopyToInternet";

		enuJadeOperations enuT = this.value();

		return enuT == enuJadeOperations.copytointernet;
	} // private boolean isOperationCopyToInternet

	public boolean isOperationReceiveUsingDMZ() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + ":: isOperationReceiveUsingDMZ";

		enuJadeOperations enuT = this.value();

		return enuT == enuJadeOperations.receiveusingdmz;
	} // private boolean isOperationReceiveUsingDMZ

	public boolean isOperationReceive() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + ":: isOperationReceive";

		enuJadeOperations enuT = this.value();

		return enuT == enuJadeOperations.receive || enuT == enuJadeOperations.receiveusingdmz;
	} // private boolean isOperationReceive

	public boolean isOperationCopy() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + ":: isOperationReceive";

		enuJadeOperations enuT = this.value();

		return enuT == enuJadeOperations.copy;
	} // private boolean isOperationReceive

	public boolean isOperationMove() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + ":: isOperationReceive";

		enuJadeOperations enuT = this.value();

		return enuT == enuJadeOperations.move;
	} // private boolean isOperationReceive

	public boolean isOperationGetList() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + ":: isOperationGetList";

		enuJadeOperations enuT = this.value();

		return enuT == enuJadeOperations.getlist;
	} // private boolean isOperationGetList

	public boolean isOperationDelete() {
		enuJadeOperations enuT = this.value();
		return enuT == enuJadeOperations.delete;
	}


	public boolean isOperationRename() {
		enuJadeOperations enuT = this.value();
		return enuT == enuJadeOperations.rename;
	}

}
