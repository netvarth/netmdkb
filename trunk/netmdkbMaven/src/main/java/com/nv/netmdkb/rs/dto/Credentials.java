/**
 * HeaderDTO.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmdkb.rs.dto;

public class Credentials {
	private int headOfficeId;
	private String passPhrase;
	private String macId;
	private int branchId;


	/**
	 * 
	 */
	public Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @return the passPhrase
	 */
	public String getPassPhrase() {
		return passPhrase;
	}

	/**
	 * @param passPhrase
	 *            the passPhrase to set
	 */
	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	/**
	 * @return the macId
	 */
	public String getMacId() {
		return macId;
	}

	/**
	 * @param macId the macId to set
	 */
	public void setMacId(String macId) {
		this.macId = macId;
	}



	/**
	 * @return the headOfficeId
	 */
	public int getHeadOfficeId() {
		return headOfficeId;
	}



	/**
	 * @param headOfficeId the headOfficeId to set
	 */
	public void setHeadOfficeId(int headOfficeId) {
		this.headOfficeId = headOfficeId;
	}



	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}



	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the netMdBranchId
	 */
	

}
