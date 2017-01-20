package com.nv.netmdkb.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the organisation_tbl database table.
 * 
 */
@Entity
@Table(name="organisation_tbl")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")	
@NamedQuery(name="OrganisationTbl.findAll", query="SELECT o FROM OrganisationTbl o")
public class OrganisationTbl extends HealthCareOrganisationTbl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date_time")
	private Date createDateTime;

	@Column(name="department_type")
	private String departmentType;

	@Column(name="enable_sync")
	private Boolean enableSync;

	@Column(name="head_office_address")
	private String headOfficeAddress;

	@Column(name="head_office_email")
	private String headOfficeEmail;

	@Column(name="head_office_mobile")
	private String headOfficeMobile;

	@Column(name="head_office_name")
	private String headOfficeName;

	@Column(name="head_office_phone")
	private String headOfficePhone;

	//private String name;

	@Column(name="owner_address")
	private String ownerAddress;

	@Column(name="owner_email")
	private String ownerEmail;

	@Column(name="owner_first_name")
	private String ownerFirstName;

	@Column(name="owner_last_name")
	private String ownerLastName;

	@Column(name="owner_mobile")
	private String ownerMobile;

	@Column(name="owner_phone")
	private String ownerPhone;

	//private String status;

	@Column(name="sync_freq_type")
	private String syncFreqType;

	@Column(name="sync_time")
	private Integer syncTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date_time")
	private Date updateDateTime;

	//bi-directional many-to-one association to OrganisationNetmdTbl
	@OneToMany(mappedBy="organisationTbl")
	private List<OrganisationNetmdTbl> organisationNetmdTbls;

	//bi-directional one-to-one association to HealthCareOrganisationTbl
	@OneToOne
	@JoinColumn(name="id")
	private HealthCareOrganisationTbl healthCareOrganisationTbl;

	//bi-directional many-to-one association to OrganisationLoginTbl
	@ManyToOne
	@JoinColumn(name="login_id")
	private OrganisationLoginTbl organisationLoginTbl;

	//bi-directional many-to-one association to QuestionnaireTbl
	@OneToMany(mappedBy="organisationTbl")
	private List<QuestionnaireTbl> questionnaireTbls;

	public OrganisationTbl() {
	}

//	public int getId() {
//		return this.id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getDepartmentType() {
		return this.departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public Boolean getEnableSync() {
		return this.enableSync;
	}

	public void setEnableSync(Boolean enableSync) {
		this.enableSync = enableSync;
	}

	public String getHeadOfficeAddress() {
		return this.headOfficeAddress;
	}

	public void setHeadOfficeAddress(String headOfficeAddress) {
		this.headOfficeAddress = headOfficeAddress;
	}

	public String getHeadOfficeEmail() {
		return this.headOfficeEmail;
	}

	public void setHeadOfficeEmail(String headOfficeEmail) {
		this.headOfficeEmail = headOfficeEmail;
	}

	public String getHeadOfficeMobile() {
		return this.headOfficeMobile;
	}

	public void setHeadOfficeMobile(String headOfficeMobile) {
		this.headOfficeMobile = headOfficeMobile;
	}

	public String getHeadOfficeName() {
		return this.headOfficeName;
	}

	public void setHeadOfficeName(String headOfficeName) {
		this.headOfficeName = headOfficeName;
	}

	public String getHeadOfficePhone() {
		return this.headOfficePhone;
	}

	public void setHeadOfficePhone(String headOfficePhone) {
		this.headOfficePhone = headOfficePhone;
	}

//	public String getName() {
//		return this.name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public String getOwnerAddress() {
		return this.ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getOwnerEmail() {
		return this.ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerFirstName() {
		return this.ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return this.ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerMobile() {
		return this.ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public String getOwnerPhone() {
		return this.ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

//	public String getStatus() {
//		return this.status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

	public String getSyncFreqType() {
		return this.syncFreqType;
	}

	public void setSyncFreqType(String syncFreqType) {
		this.syncFreqType = syncFreqType;
	}

	

	public Date getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public List<OrganisationNetmdTbl> getOrganisationNetmdTbls() {
		return this.organisationNetmdTbls;
	}

	public void setOrganisationNetmdTbls(List<OrganisationNetmdTbl> organisationNetmdTbls) {
		this.organisationNetmdTbls = organisationNetmdTbls;
	}

	public OrganisationNetmdTbl addOrganisationNetmdTbl(OrganisationNetmdTbl organisationNetmdTbl) {
		getOrganisationNetmdTbls().add(organisationNetmdTbl);
		organisationNetmdTbl.setOrganisationTbl(this);

		return organisationNetmdTbl;
	}

	public OrganisationNetmdTbl removeOrganisationNetmdTbl(OrganisationNetmdTbl organisationNetmdTbl) {
		getOrganisationNetmdTbls().remove(organisationNetmdTbl);
		organisationNetmdTbl.setOrganisationTbl(null);

		return organisationNetmdTbl;
	}

	public HealthCareOrganisationTbl getHealthCareOrganisationTbl() {
		return this.healthCareOrganisationTbl;
	}

	public void setHealthCareOrganisationTbl(HealthCareOrganisationTbl healthCareOrganisationTbl) {
		this.healthCareOrganisationTbl = healthCareOrganisationTbl;
	}

	public OrganisationLoginTbl getOrganisationLoginTbl() {
		return this.organisationLoginTbl;
	}

	public void setOrganisationLoginTbl(OrganisationLoginTbl organisationLoginTbl) {
		this.organisationLoginTbl = organisationLoginTbl;
	}

	public List<QuestionnaireTbl> getQuestionnaireTbls() {
		return this.questionnaireTbls;
	}

	

	public void setQuestionnaireTbls(List<QuestionnaireTbl> questionnaireTbls) {
		this.questionnaireTbls = questionnaireTbls;
	}

	public Integer getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Integer syncTime) {
		this.syncTime = syncTime;
	}

	public QuestionnaireTbl addQuestionnaireTbl(QuestionnaireTbl questionnaireTbl) {
		getQuestionnaireTbls().add(questionnaireTbl);
		questionnaireTbl.setOrganisationTbl(this);

		return questionnaireTbl;
	}

	public QuestionnaireTbl removeQuestionnaireTbl(QuestionnaireTbl questionnaireTbl) {
		getQuestionnaireTbls().remove(questionnaireTbl);
		questionnaireTbl.setOrganisationTbl(null);

		return questionnaireTbl;
	}

}