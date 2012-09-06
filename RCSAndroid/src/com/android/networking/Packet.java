/* *******************************************
 * Copyright (c) 2011
 * HT srl,   All rights reserved.
 * Project      : RCS, AndroidService
 * File         : Packet.java
 * Created      : Apr 9, 2011
 * Author		: zeno
 * *******************************************/

package com.android.networking;

import java.util.ArrayList;

import com.android.networking.evidence.EvidenceType;
import com.android.networking.evidence.EvidenceReference;
import com.android.networking.util.Utils;

/**
 * The Class Packet.
 */
public class Packet {

	/** The type. */
	int type;

	/** The command. */
	private int command;

	/** The id. */
	private final long id;

	/** The data. */
	private byte[] data;

	/** The data. */
	private byte[] additional;

	private ArrayList<byte[]> items;

	/**
	 * Instantiates a new packet.
	 * 
	 * @param unique
	 *            the unique
	 */
	public Packet(final long unique) {
		type = EvidenceType.NONE;
		command = EvidenceReference.LOG_CREATE;
		id = unique;
		data = null;
	}

	public Packet(int evidenceType, byte[] additional, byte[] data) {
		type = evidenceType;
		id = Utils.getRandom();
		command = EvidenceReference.LOG_ATOMIC;
		this.data = data;
		this.additional = additional;
	}

	public Packet(int evidenceType, ArrayList<byte[]> items) {
		type = evidenceType;
		id = Utils.getRandom();
		command = EvidenceReference.LOG_ITEMS;
		this.items = items;
	
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the command.
	 * 
	 * @param c
	 *            the new command
	 */
	public void setCommand(final int c) {
		command = c;
	}

	/**
	 * Gets the command.
	 * 
	 * @return the command
	 */
	public int getCommand() {
		return command;
	}

	// Needed only when sending LOG_CREATE
	/**
	 * Sets the type.
	 * 
	 * @param evidenceType
	 *            the new type
	 */
	public void setType(final int evidenceType) {
		type = evidenceType;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Fill.
	 * 
	 * @param d
	 *            the d
	 */
	public void setData(final byte[] d) {
		data = d;
	}

	/**
	 * Peek.
	 * 
	 * @return the byte[]
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Gets the additional.
	 * 
	 * @return the additional
	 */
	public byte[] getAdditional() {
		return additional;
	}

	/**
	 * Sets the additional.
	 * 
	 * @param d
	 *            the new additional
	 */
	public void setAdditional(final byte[] d) {
		additional = d;
	}

	public ArrayList<byte[]> getItems() {
		
		return items;
	}
}
