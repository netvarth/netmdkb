package com.nv.netmdkb.pl.dao.xml.impl;

import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.nv.netmdkb.analytic.bl.dataCluster.Cluster;
import com.nv.netmdkb.analytic.bl.dataCluster.Conjunction;
import com.nv.netmdkb.analytic.bl.dataCluster.Expression;
import com.nv.netmdkb.analytic.bl.dataCluster.Predicate;
import com.nv.netmdkb.analytic.bl.dataCluster.Rule;
import com.nv.netmdkb.analytic.bl.dataCluster.Unit;
import com.nv.netmdkb.questionnaire.Question;
import com.nv.netmdkb.questionnaire.Questionnaire;


public class ClusterParserHandler extends DefaultHandler {

	
	private List<Cluster> clusters = new ArrayList<Cluster>();

	private boolean inClusterSet;
	private boolean inCluster;
	private boolean inRuleSet;
	private boolean inRule;
	private boolean inExpression;
	private Cluster currentClusterSet;
	private Cluster currentCluster;
	private Rule currentRuleSet;
	private Rule currentRule;
	private Predicate predicate;
	private Conjunction conjunction;
	private Unit unit;
	private Expression currentExpression;
	private CharArrayWriter contents = new CharArrayWriter();
	private Questionnaire questionnare;
	private Question question;
	
	

	public ClusterParserHandler() {
		
	}

	
	public void startElement(String s, String localName, String element,
			Attributes attributes) {
	      contents.reset();
		
		if (element.equals("clusterSet")){
			currentClusterSet = new Cluster();
			inClusterSet=true;
		}
		
		if (element.equals("cluster")){
			currentCluster = new Cluster();
			inCluster=true;
		}

		if (element.equals("ruleSet")){
			currentRuleSet = new Rule();
			inRuleSet=true;
		}

		if (element.equals("rule")){
			currentRule = new Rule();
			inRule=true;
		}
		
		if (element.equals("expression")){
			currentExpression = new Expression();
			inExpression=true;
		}
	}

	public void endElement(String s, String localName, String element) {
		

		if (element.equals("question") && inExpression){
			question =questionnare.getQuestion(contents.toString().trim());
	        currentExpression.setQuestion(question);
	        
	        return;
		}
		
		if(element.equals("edge") && inExpression){
			currentExpression.setEdge(contents.toString().trim());
			
			return;
		}
		
		if (element.equals("expression") && inRule){
			currentRule.setExpression(currentExpression);
			inExpression=false;
			
			return;
		}
		
		
		
		if (element.equals("predicate") && inRule){
			predicate =Predicate.valueOf(contents.toString().trim());
			currentRule.setPredicate(predicate);
			
			return;
		}
		
		if (element.equals("conjunction") && inRule){
			conjunction =Conjunction.valueOf(contents.toString().trim());
			currentRule.setConj(conjunction);
			
			return;
		}
		
		
		
		
		if (element.equals("expression")  && inRuleSet){
			currentRuleSet.setExpression(currentExpression);
			inExpression=false;
			
			return;
		}

			if (element.equals("predicate") && inRuleSet){
			predicate =Predicate.valueOf(contents.toString().trim());
			currentRuleSet.setPredicate(predicate);
			
			return;
		}
		
		if (element.equals("conjunction") && inRuleSet){
			
			conjunction =Conjunction.valueOf(contents.toString().trim());
			currentRuleSet.setConj(conjunction);
			
			return;
		}
		
		
		if (element.equals("cluster") ){
			currentClusterSet.addCluster(currentCluster);
			
			inCluster=false;
			
		return;
		}
		if (element.equals("rule")  && inCluster){
			currentRuleSet.addRule(currentRule);
			inRule=false;
		return;
		}
	    	
		if (element.equals("rule")  && inClusterSet){
			currentRuleSet.addRule(currentRule);
			inRule=false;
		return;
		}
		
		if (element.equals("ruleSet")  && inCluster) {
			currentCluster.setRule(currentRuleSet);
			inRuleSet=false;
			return;
		}
		
		if (element.equals("id") && inCluster){
			currentCluster.setId(contents.toString().trim());
			
		return;	
		}
		if (element.equals("name") && inCluster){
			currentCluster.setName(contents.toString().trim());
			
		return;	
		}
		if (element.equals("unit") && inCluster){
			unit =Unit.valueOf(contents.toString().trim());
			currentCluster.setUnit(unit);
			
		return;
		}
	

		
		
		if (element.equals("ruleSet")  && inClusterSet) {
			currentClusterSet.setRule(currentRuleSet);
			inRuleSet=false;
			return;
		}
		
		if (element.equals("id") && inClusterSet){
			currentClusterSet.setId(contents.toString().trim());
			
		return;	
		}
		if (element.equals("name") && inClusterSet){
			currentClusterSet.setName(contents.toString().trim());
			
		return;	
		}
		if (element.equals("unit") && inClusterSet){
			unit =Unit.valueOf(contents.toString().trim());
			currentClusterSet.setUnit(unit);
			
		return;
		}
		
		if (element.equals("clusterSet") ){
			clusters.add(currentClusterSet);
			
			inClusterSet=false;
			
		return;
		}
		
			
		
	}

	public void characters(char[] ac, int start, int length) throws SAXException {
	      contents.write( ac, start, length );
	}


	public void setQuestionnare(Questionnaire questionnare) {
		this.questionnare = questionnare;
	}

    public List<Cluster> getClusters(){
    return	this.clusters;
    }

     
	
	
}
