package com.nv.netmdkb.analytic.bl.dataCluster;

import java.util.Map;

import com.nv.netmdkb.analytic.bl.QuestionIdentifier;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.questionnaire.Question;


public class Rule {

	private Predicate predicate;		
	private Expression expression  ;
	private Conjunction conj=Conjunction.end;
	private Rule rule=null;

	public Rule(Predicate predicate,Expression expression) {
		super();
		this.predicate = predicate;
		this.expression = expression;
		this.conj = Conjunction.end;
	}


	public Rule() {

	}


	public void addRule(Rule rule){
		Rule cRule=this; 	
		Rule nextRule=this.rule;

		while((nextRule) !=null) {
			cRule =nextRule;
			nextRule=cRule.rule;
		}
		cRule.setRule(rule);
	}

	public boolean evaluate(Map<QuestionIdentifier,String> answers,int indexValue) throws ServiceException{
		boolean result =false;
		Question question =this.expression.getQuestion();

		ValueType answerValueType=question.getAnswerValueType();
		String edge =this.expression.getEdge();
		Parser parser =answerValueType.getParser();
		int index =question.isIndexed() ? indexValue :0 ;
		String answer;

		answer=answers.get(new QuestionIdentifier(question.getId(),index));
		String operands = edge+","+answer; 
		result =parser.evaluate(this.predicate,operands.split("\\s*,\\s*"));
		Rule localRule=null;
		localRule=rule;
		if (localRule!=null){
			if(this.conj==Conjunction.and && result==true)
				return result && localRule.evaluate(answers,index) ;	
			if (this.conj==Conjunction.or && result==false)
				return localRule.evaluate(answers,index);
		}
		return result;
	}


	public Predicate getPredicate() {
		return predicate;
	}






	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}






	public Expression getExpression() {
		return expression;
	}






	public void setExpression(Expression expression) {
		this.expression = expression;
	}






	public Conjunction getConj() {
		return conj;
	}






	public void setConj(Conjunction conj) {
		this.conj = conj;
	}






	public Rule getRule() {
		return rule;
	}






	private void setRule(Rule rule) {
		this.rule = rule;
	}



}
