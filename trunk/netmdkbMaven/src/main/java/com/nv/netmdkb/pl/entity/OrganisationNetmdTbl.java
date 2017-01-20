package com.nv.netmdkb.pl.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the organisation_netmd_tbl database table.
 * 
 */
@Entity
@Table(name="organisation_netmd_tbl")
public class OrganisationNetmdTbl  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int id;

	 
	//bi-directional many-to-one association to NetmdBranchTbl
	@ManyToOne
	@JoinColumn(name="netmd_branch_id", nullable=false)
	private HealthCareOrganisationTbl netmdBranchTbl;

	//bi-directional many-to-one association to OrganisationTbl
	@ManyToOne
	@JoinColumn(name="organisation_id", nullable=false)
	private OrganisationTbl organisationTbl;

	public OrganisationNetmdTbl() {
	}





	public HealthCareOrganisationTbl getNetmdBranchTbl() {
		return netmdBranchTbl;
	}

	public void setNetmdBranchTbl(HealthCareOrganisationTbl netmdBranchTbl) {
		this.netmdBranchTbl = netmdBranchTbl;
	}

	public OrganisationTbl getOrganisationTbl() {
		return this.organisationTbl;
	}

	public void setOrganisationTbl(OrganisationTbl organisationTbl) {
		this.organisationTbl = organisationTbl;
	}

}