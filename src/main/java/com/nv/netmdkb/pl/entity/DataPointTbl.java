package com.nv.netmdkb.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the data_point_tbl database table.
 * 
 */
@Entity
@Table(name="data_point_tbl")
@NamedQuery(name="DataPointTbl.findAll", query="SELECT d FROM DataPointTbl d")
public class DataPointTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="cluster_xml")
	private String clusterXml;

	private String name;

	private String uid;
	
	private int order;



	//bi-directional many-to-one association to AnswerStatTbl
	@OneToMany(mappedBy="dataPointTbl")
	private List<AnswerStatTbl> answerStatTbls;

	//bi-directional many-to-one association to QuestionnaireTbl
	@ManyToOne
	@JoinColumn(name="questionnaire")
	private QuestionnaireTbl questionnaireTbl;

	public DataPointTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClusterXml() {
		return this.clusterXml;
	}

	public void setClusterXml(String clusterXml) {
		this.clusterXml = clusterXml;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<AnswerStatTbl> getAnswerStatTbls() {
		return this.answerStatTbls;
	}

	public void setAnswerStatTbls(List<AnswerStatTbl> answerStatTbls) {
		this.answerStatTbls = answerStatTbls;
	}

	public AnswerStatTbl addAnswerStatTbl(AnswerStatTbl answerStatTbl) {
		getAnswerStatTbls().add(answerStatTbl);
		answerStatTbl.setDataPointTbl(this);

		return answerStatTbl;
	}

	public AnswerStatTbl removeAnswerStatTbl(AnswerStatTbl answerStatTbl) {
		getAnswerStatTbls().remove(answerStatTbl);
		answerStatTbl.setDataPointTbl(null);

		return answerStatTbl;
	}

	public QuestionnaireTbl getQuestionnaireTbl() {
		return this.questionnaireTbl;
	}

	
	public void setQuestionnaireTbl(QuestionnaireTbl questionnaireTbl) {
		this.questionnaireTbl = questionnaireTbl;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}