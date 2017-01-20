package com.nv.netmdkb.analytic.bl.dataPoint;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nv.netmdkb.analytic.bl.QuestionIdentifier;
import com.nv.netmdkb.analytic.bl.dataCluster.Cluster;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.questionnaire.Question;
public class DataPoint  {
	private int id;
	private int orderId;
	private String name;
	private String uid;

	private List<Cluster> clusters= new ArrayList<Cluster>();

	
	
	public Question[] getQuestions(){
		Set<Question> questionSet=new HashSet<Question>();
		for ( Cluster cluster :clusters){
			questionSet.addAll(cluster.getQuestions()); 	
			if (cluster.getSubClusters().size()!=0){ 
				for(Cluster subCluster:cluster.getSubClusters()){
					questionSet.addAll(subCluster.getQuestions());
				}
			}	
		}

		return questionSet.toArray(new Question[questionSet.size()]);
	}



	public Map<String,Integer>getMatches(Map<QuestionIdentifier,String>answer) throws ServiceException{
		int prefix = 0;
		Map<String,Integer>matches = new HashMap<String,Integer>();
		for(Cluster cluster:this.clusters){
			matches.putAll(cluster.getMatches(answer,Integer.toString(++prefix)));
		}
		return matches;	
	}



	public String getName() {
		return name;
	}
	public DataPoint(){

	}

	public DataPoint(List<Cluster> clusters,String name,int id,String uid) {


		this.clusters = clusters;
		this.name=name;
		this.id=id;
		this.uid=uid;

	}



	public Map<String,Integer> getValueMap(Map<QuestionIdentifier,String> answer) throws ServiceException{
		Integer count;
		Map<String,Integer> matches = this.getMatches(answer);
		List<String> treeNodes = this.getTreeNodes();
		Map<String,Integer> valueMap = new HashMap<String,Integer>();
		for(String node:treeNodes){
			if ((count=matches.get(node)) != null){
				valueMap.put(node,count);
			}else{ 
				valueMap.put(node,0);
			} 
		}
		return valueMap;
	}




	public List<String> getTreeNodes(){

		int prefix = 0;
		List<String> nodes= new LinkedList<String>();

		for (Cluster clustr:clusters){
			nodes.addAll(clustr.getTreeNodes(Integer.toString(++prefix)));

		}
		return nodes;
	}


	public List<String[][]> getClusterMatrix(){
		int prefix = 0;
		List<String[][]> matrix= new ArrayList<String[][]>();	
		for(Cluster cluster:clusters){
			matrix.add(cluster.getClusterMatrix(Integer.toString(++prefix)));
		}

		return matrix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public List<Cluster> getClusters() {
		return clusters;
	}
	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}
	public void setName(String name) {
		this.name = name;
	} 

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
