package com.nv.netmdkb.analytic.bl.dataCluster;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;







import com.nv.netmdkb.analytic.bl.QuestionIdentifier;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.questionnaire.Question;



public class Cluster{
	private Cluster parent=null;
	private String id;
	private String name;
	private Unit unit ;
	private Rule rule =null;
	private List <Cluster> subClusters=new LinkedList<Cluster>();


	public Set<Question>getQuestions(){
		Set<Question> questionSet=new HashSet<Question>();
		Rule localRule = this.rule;


		while (localRule!=null){

			questionSet.add(localRule.getExpression().getQuestion());
			localRule=localRule.getRule();
		}

		if  (subClusters.size()!=0){

			for(Cluster subcluster:subClusters){
				questionSet.addAll(subcluster.getQuestions());
			}

		}
		return questionSet;
	}





	public int getMaxIndex(Map<QuestionIdentifier,String> answer){
		int maxIndex=1;
		Question question;
		Rule localRule = this.rule;
		while (localRule!=null){
			question=localRule.getExpression().getQuestion();
			if(question.isIndexed()) {
				while (answer.containsKey(new QuestionIdentifier(question.getId(),maxIndex))){
					maxIndex++;
				}
				break;	 
			}
			localRule=localRule.getRule();
		}
		return maxIndex;
	}



	public List<String>getTreeNodes(String prefix){
		List<String> nodeList=new LinkedList<String>();
		if (this.rule!=null) nodeList.add(prefix+"."+this.id);
		for(Cluster cluster :this.subClusters){
			nodeList.addAll(cluster.getTreeNodes(prefix));
		}
		return nodeList;	
	}



	public Map<String,Integer>getMatches(Map<QuestionIdentifier,String> answers,String prefix) throws ServiceException{
		Map<String,Integer> matches= new HashMap<String,Integer>();
		Integer count;
		int maxIndex=getMaxIndex(answers);
		for(int index=0; index<maxIndex && this.rule !=null;index++){
			if (this.rule.evaluate(answers,index)){
				count =matches.get(prefix+"."+this.id);
				if(count ==null) count =0;
				matches.put(prefix+"."+this.id, count+1);
			}
		}

		for(Cluster cluster:subClusters){
			matches.putAll(cluster.getMatches(answers,prefix));
		}
		return matches;
	}


	public String[][] getClusterMatrix(String prefix){

		int size = 1+subClusters.size();
		String[][] rowColum = new String[2][size];
		int i=0;
		//when i=0 it is rowName

		rowColum[0][i]=this.name;
		rowColum[1][i++]=prefix+"."+this.id;


		for(Cluster cluster:subClusters){
			rowColum[0][i]=cluster.name;
			rowColum[1][i++]=prefix+"."+cluster.id;
			//System.out.println(prefix+"."+cluster.id);
		}
		return rowColum; 
	}

	public void addCluster(Cluster cluster){
		cluster.setParent(this);
		this.subClusters.add(cluster);
	}


	public void traverse(){
		//System.out.println("id: "+this.getId());
		//System.out.println("name: "+this.getName());
		Rule rule =this.getRule();
		while (rule!=null){
			//System.out.println("Rule:");	
			//System.out.print(rule.getExpression().getQuestion().getKey());
			//System.out.print(" "+rule.getPredicate().name());
			//System.out.print(" "+rule.getExpression().getEdge());
			//System.out.println(" "+rule.getConj().name());
			rule=rule.getRule();
		}

		if(this.getSubClusters().size()>0){
			for(Cluster subcluster:this.getSubClusters()){
				//System.out.println("SubCluster: ");
				//System.out.println("id: "+subcluster.getId());
				//System.out.println("name: "+subcluster.getName());
				Rule subRule =subcluster.getRule();
				while (subRule!=null){
					//System.out.println("Rule: ");	
					//System.out.print(subRule.getExpression().getQuestion().getKey());
					//System.out.print(" "+subRule.getPredicate().name());
					//System.out.print(" "+subRule.getExpression().getEdge());
					//System.out.println(" "+subRule.getConj().name());
					subRule=subRule.getRule();
				}

			}
		}

		return;
	}


	public Cluster(){

	}

	public Cluster(Cluster parent){
		this.parent=parent;
	}
	public Cluster getParent() {
		return parent;
	}
	public void setParent(Cluster parent) {
		this.parent = parent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public List<Cluster> getSubClusters() {
		return subClusters;
	}
	public void setSubClusters(List<Cluster> subClusters) {
		this.subClusters = subClusters;
	}

}
