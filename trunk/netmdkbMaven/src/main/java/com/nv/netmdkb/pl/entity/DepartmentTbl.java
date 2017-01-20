package com.nv.netmdkb.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the department_tbl database table.
 * 
 */
@Entity
@Table(name="department_tbl")
@NamedQuery(name="DepartmentTbl.findAll", query="SELECT d FROM DepartmentTbl d")
public class DepartmentTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

	private String name;

	private String status;

	private String uid;

	//bi-directional many-to-one association to CaseTbl
	//@OneToMany(mappedBy="departmentTbl")
	//private List<CaseTbl> caseTbls;

	//bi-directional many-to-one association to QuestionnaireTbl
	@OneToMany(mappedBy="departmentTbl")
	private List<QuestionnaireTbl> questionnaireTbls;

	public DepartmentTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

//	public List<CaseTbl> getCaseTbls() {
//		return this.caseTbls;
//	}

//	public void setCaseTbls(List<CaseTbl> caseTbls) {
//		this.caseTbls = caseTbls;
//	}

//	public CaseTbl addCaseTbl(CaseTbl caseTbl) {
//		getCaseTbls().add(caseTbl);
//		caseTbl.setDepartmentTbl(this);

//		return caseTbl;
//	}

//	public CaseTbl removeCaseTbl(CaseTbl caseTbl) {
//		getCaseTbls().remove(caseTbl);
//		caseTbl.setDepartmentTbl(null);

//		return caseTbl;
//	}

	public List<QuestionnaireTbl> getQuestionnaireTbls() {
		return this.questionnaireTbls;
	}

	public void setQuestionnaireTbls(List<QuestionnaireTbl> questionnaireTbls) {
		this.questionnaireTbls = questionnaireTbls;
	}

	public QuestionnaireTbl addQuestionnaireTbl(QuestionnaireTbl questionnaireTbl) {
		getQuestionnaireTbls().add(questionnaireTbl);
		questionnaireTbl.setDepartmentTbl(this);

		return questionnaireTbl;
	}

	public QuestionnaireTbl removeQuestionnaireTbl(QuestionnaireTbl questionnaireTbl) {
		getQuestionnaireTbls().remove(questionnaireTbl);
		questionnaireTbl.setDepartmentTbl(null);

		return questionnaireTbl;
	}

}