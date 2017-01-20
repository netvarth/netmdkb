package com.nv.netmdkb.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the answer_set_tbl database table.
 * 
 */
@Entity
@Table(name="answer_set_tbl")
public class AnswerSetTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="local_answer_set")
	private int localAnswerSet;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="s_date")
	private Date sDate;

	//bi-directional many-to-one association to HealthCareOrganisationTbl
	@ManyToOne
	@JoinColumn(name="branch_id")
	private HealthCareOrganisationTbl healthCareOrganisationTbl;
//
//	//bi-directional many-to-one association to OrganisationTbl
//	@ManyToOne
//	@JoinColumn(name="branch_id")
//	private OrganisationTbl organisationTbl;

	//bi-directional many-to-one association to QuestionnaireTbl
	@ManyToOne
	@JoinColumn(name="questionnaire_id")
	private QuestionnaireTbl questionnaireTbl;

	//bi-directional many-to-one association to AnswerTbl
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="answerSetTbl")
	private List<AnswerTbl> answerTbls;

	public AnswerSetTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLocalAnswerSet() {
		return this.localAnswerSet;
	}

	public void setLocalAnswerSet(int localAnswerSet) {
		this.localAnswerSet = localAnswerSet;
	}

	public Date getSDate() {
		return this.sDate;
	}

	public void setSDate(Date sDate) {
		this.sDate = sDate;
	}

	public HealthCareOrganisationTbl getHealthCareOrganisationTbl() {
		return this.healthCareOrganisationTbl;
	}

	public void setHealthCareOrganisationTbl(HealthCareOrganisationTbl healthCareOrganisationTbl) {
		this.healthCareOrganisationTbl = healthCareOrganisationTbl;
	}

//	public OrganisationTbl getOrganisationTbl() {
//		return this.organisationTbl;
//	}
//
//	public void setOrganisationTbl(OrganisationTbl organisationTbl) {
//		this.organisationTbl = organisationTbl;
//	}

	public QuestionnaireTbl getQuestionnaireTbl() {
		return this.questionnaireTbl;
	}

	public void setQuestionnaireTbl(QuestionnaireTbl questionnaireTbl) {
		this.questionnaireTbl = questionnaireTbl;
	}

	public List<AnswerTbl> getAnswerTbls() {
		return this.answerTbls;
	}

	public void setAnswerTbls(List<AnswerTbl> answerTbls) {
		this.answerTbls = answerTbls;
	}

	public AnswerTbl addAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().add(answerTbl);
		answerTbl.setAnswerSetTbl(this);

		return answerTbl;
	}

	public AnswerTbl removeAnswerTbl(AnswerTbl answerTbl) {
		getAnswerTbls().remove(answerTbl);
		answerTbl.setAnswerSetTbl(null);

		return answerTbl;
	}

}