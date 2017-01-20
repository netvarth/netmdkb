package com.nv.netmdkb.pl.dao.xml.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import com.nv.netmdkb.analytic.bl.dataCluster.Cluster;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.facade.Parser;
import com.nv.netmdkb.pl.dao.QuestionAnswerDao;
import com.nv.netmdkb.questionnaire.Questionnaire;

public class ClusterXmlParser implements Parser<Cluster> {

	private Questionnaire questionnaire;
	private ClusterParserHandler handler;
	private QuestionAnswerDao questionAnswerDao;
	private String questionnaireId;
	Resource localResource;
	
	public List<Cluster> parse(String xmlpath){
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	
		
		List<Cluster> clusters=new ArrayList<Cluster>();
		try {
			InputStream in =this.getClass().getResourceAsStream(xmlpath);
			SAXParser parser = parserFactory.newSAXParser();
			handler= new ClusterParserHandler();
			handler.setQuestionnare(questionnaire);
			parser.parse(in, handler);
            clusters =handler.getClusters();

		
		} catch (IllegalArgumentException e) {
			System.out.println("xmlpath"+ xmlpath);
			throw new ServiceException(ErrorCodeEnum.INVALIDDATA);

		} catch (ParserConfigurationException e) {
			System.out.println("xmlpath"+ xmlpath);
			throw new ServiceException(ErrorCodeEnum.INVALIDDATA);

		} catch (SAXException e) {

			System.out.println("SAXException : xml not well formed");
			throw new ServiceException(ErrorCodeEnum.INVALIDDATA);

		} catch (IOException e) {
			System.out.println("IO error");
			throw new ServiceException(ErrorCodeEnum.INVALIDDATA);

		}
		return clusters;
	}

 
	public void init(){
		questionnaire=questionAnswerDao.getQuestionnaire(questionnaireId);
		
	}


	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}


	/**
	 * @param questionAnswerDao the questionAnswerDao to set
	 */
	public void setQuestionAnswerDao(QuestionAnswerDao questionAnswerDao) {
		this.questionAnswerDao = questionAnswerDao;
	}
	
	
		

}
