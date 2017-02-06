package com.nv.netmdkb.pl;

public class Query {

	public static final String GET_ORGANISATION_USER_BY_PASSWORD = "from OrganisationLoginTbl as login  where login.password =:param1 and login.userName =:param2";
	public static final String GET_ORGANISATIONDETAILS_USER = "from OrganisationLoginTbl as login where login.userName=:param1";
	public static final String GET_ORGANISATION_BY_USER = "from OrganisationTbl as org where org.organisationLoginTbl.userName=:param1";
	public static final String GET_BY_ORGANISATION = "from OrganisationNetmdTbl as org where org.organisationTbl.id=:param1";
	public static final String GET_USERDETAILS = "from OrganisationTbl as orgn where orgn.organisationLoginTbl.id=:param1";
	
	public static final String GET_QUESTIONNAIRE_BY_UID = "from QuestionnaireTbl as q where q.uid=:param1";
	public static final String GET_ANSWER_BY_QSTN="from AnswerTbl as answer where answer.questionTbl.questionKey=:param1 and answer.answerSetTbl.id=:param2 and answer.indx=:param3";
	public static final String GET_DATAPOINTS_BY_QUESTIONNAIRE="from DataPointTbl as datapoint where datapoint.questionnaireTbl.id=:param1 order by datapoint.order";
	public static final String GET_QSTN_BY_KEY="from QuestionTbl as question where question.key=:param1 and question.uid=:param2";
	public static final String GET_NETMD_BRANCH_PASSPHRASE = " from NetmdPassphraseTbl as branchPassphrase where branchPassphrase.passPhrase=:param1";
	public static final String GET_QUESTIONS_BY_QUESTIONNAIREID = "from QuestionTbl as q where q.questionnaireTbl.id=:param1";
    public static final String GET_ANSWERSTAT_TBL="select new com.nv.netmdkb.rs.dto.MonthlyAnswerStat(dataPointTbl.id,year(date),month(date),stat.healthCareOrganisationTbl.name,sum(compute1),sum(compute2),sum(compute3),sum(compute4),sum(compute5),sum(compute6),sum(compute7),sum(compute8),sum(compute9),sum(compute10),sum(compute11),"
    		+ "sum(compute12),sum(compute13),sum(compute14),sum(compute15),sum(compute16),sum(compute17),sum(compute18),sum(compute19),sum(compute20),sum(compute21),sum(compute22),sum(compute23),sum(compute24),sum(compute25),sum(compute26),sum(compute27),sum(compute28),sum(compute29),sum(compute30),sum(compute31),sum(compute32),sum(compute33),sum(compute34),"
    		+ "sum(compute35),sum(compute36),sum(compute37),sum(compute38),sum(compute39),sum(compute40),sum(compute41),sum(compute42),sum(compute43),sum(compute44),sum(compute45)) "
    		+ "from AnswerStatTbl as stat where stat.dataPointTbl.id=:param1 and stat.date between :param2 and :param3  group by stat.healthCareOrganisationTbl.name, year(date), month(date),stat.date  order by stat.healthCareOrganisationTbl.name,stat.date";
    
    public static final String GET_ANSWERSTAT_TBL_BY_HOSPITAL="select new com.nv.netmdkb.rs.dto.MonthlyAnswerStat(dataPointTbl.id,year(date),month(date),stat.healthCareOrganisationTbl.name,sum(compute1),sum(compute2),sum(compute3),sum(compute4),sum(compute5),sum(compute6),sum(compute7),sum(compute8),sum(compute9),sum(compute10),sum(compute11),"
    		+ "sum(compute12),sum(compute13),sum(compute14),sum(compute15),sum(compute16),sum(compute17),sum(compute18),sum(compute19),sum(compute20),sum(compute21),sum(compute22),sum(compute23),sum(compute24),sum(compute25),sum(compute26),sum(compute27),sum(compute28),sum(compute29),sum(compute30),sum(compute31),sum(compute32),sum(compute33),sum(compute34),"
    		+ "sum(compute35),sum(compute36),sum(compute37),sum(compute38),sum(compute39),sum(compute40),sum(compute41),sum(compute42),sum(compute43),sum(compute44),sum(compute45)) "
    		+ "from AnswerStatTbl as stat where stat.dataPointTbl.id=:param1 and stat.date between :param2 and :param3 and stat.healthCareOrganisationTbl.id in :param4 group by stat.healthCareOrganisationTbl.name, year(date), month(date),stat.date  order by stat.healthCareOrganisationTbl.name,stat.date";
    
    public static final String GET_QSTN_BY_QUESTION_KEY="from QuestionTbl as question where question.questionKey=:param1";
	public static final String GET_ANS_BY_ANSSET_ID = "from AnswerTbl as answer where answer.answerSetTbl.id=:param1";
	public static final String GET_QUESTION_TBL = "from QuestionTbl";
	public static final String GET_DATAPOINT = "from AnswerStatTbl where dataPointTbl.id=:param1 and date=:param2 and healthCareOrganisationTbl.id=:param3";

	public static final String GET_ANSWER="from AnswerTbl as answer left join fetch answer.answerSetTbl where answer.answerSetTbl.id=:param1 and answer.questionTbl.id=:param2";

	public static final String GET_ANSWERSET_BY_LOCALID="from AnswerSetTbl where localAnswerSet=:param1 and healthCareOrganisationTbl.id=:param2";
	/*graph coordinate*/
	public static final String GET_ANSWERSTAT_TBL_GRAPH = "select new com.nv.netmdkb.rs.dto.MonthlyAnswerStat(dataPointTbl.id,year(date),month(date),stat.healthCareOrganisationTbl.name,sum(compute1),sum(compute2),sum(compute3),sum(compute4),sum(compute5),sum(compute6),sum(compute7),sum(compute8),sum(compute9),sum(compute10),sum(compute11),"
    		+ "sum(compute12),sum(compute13),sum(compute14),sum(compute15),sum(compute16),sum(compute17),sum(compute18),sum(compute19),sum(compute20),sum(compute21),sum(compute22),sum(compute23),sum(compute24),sum(compute25),sum(compute26),sum(compute27),sum(compute28),sum(compute29),sum(compute30),sum(compute31),sum(compute32),sum(compute33),sum(compute34),"
    		+ "sum(compute35),sum(compute36),sum(compute37),sum(compute38),sum(compute39),sum(compute40),sum(compute41),sum(compute42),sum(compute43),sum(compute44),sum(compute45)) "
    		+ "from AnswerStatTbl as stat where stat.dataPointTbl.id=:param1 and stat.date between :param2 and :param3  group by  year(date), month(date),stat.healthCareOrganisationTbl.name, stat.date  order by stat.date";
	
	public static final String GET_ANSWERSTAT_TBL_BY_HOSPITAL_FOR_GRAPH = "select new com.nv.netmdkb.rs.dto.MonthlyAnswerStat(dataPointTbl.id,year(date),month(date),stat.healthCareOrganisationTbl.name,sum(compute1),sum(compute2),sum(compute3),sum(compute4),sum(compute5),sum(compute6),sum(compute7),sum(compute8),sum(compute9),sum(compute10),sum(compute11),"
    		+ "sum(compute12),sum(compute13),sum(compute14),sum(compute15),sum(compute16),sum(compute17),sum(compute18),sum(compute19),sum(compute20),sum(compute21),sum(compute22),sum(compute23),sum(compute24),sum(compute25),sum(compute26),sum(compute27),sum(compute28),sum(compute29),sum(compute30),sum(compute30),sum(compute31),sum(compute32),sum(compute33),sum(compute34),"
    		+ "sum(compute35),sum(compute36),sum(compute37),sum(compute38),sum(compute39),sum(compute40),sum(compute41),sum(compute42),sum(compute43),sum(compute44),sum(compute45)) "
    		+ "from AnswerStatTbl as stat where stat.dataPointTbl.id=:param1 and stat.date between :param2 and :param3 and stat.healthCareOrganisationTbl.id in :param4 group by  year(date), month(date),stat.date  order by stat.date";
	
	
	
	
}                   