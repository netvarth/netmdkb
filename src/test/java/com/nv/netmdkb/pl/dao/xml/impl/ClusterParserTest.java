package com.nv.netmdkb.pl.dao.xml.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nv.netmdkb.analytic.bl.dataCluster.Cluster;
import com.nv.netmdkb.facade.Parser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/testDataSource.xml","classpath:META-INF/persistence-context.xml",  "classpath:META-INF/analytics-context.xml" })




public class ClusterParserTest {

		@Autowired
		private ApplicationContext applicationContext;

		@Test
		public void robsonClassTest() {
			System.out.println("iniside questionnaireTest");
						Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
					    List<Cluster> clusters=	parser.parse("RobsonClass.xml");
					
					    for(Cluster cluster:clusters){
					    	cluster.traverse();					    
					    }
		}

		@Test
		public void apgarScoreTest() {
			System.out.println("iniside apgar");
						Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
					    List<Cluster> clusters=	parser.parse("ApgarScore.xml");
					
					    for(Cluster cluster:clusters){
					    	cluster.traverse();	
					    	
					    }
		}
	
		

	@Test
		public void babyGenderAndWeightTest() {
			System.out.println("iniside baby");
						Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
					    List<Cluster> clusters=	parser.parse("BirthWeight.xml");
					
					    for(Cluster cluster:clusters){
					    	cluster.traverse();					    
					    }
		}
	
	@Test
	public void bloodGroup() {
		System.out.println("iniside bloodGroup");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("BloodGroup.xml");
				
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void bloodLoss() {
		System.out.println("iniside bloodLoss");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("BloodLoss.xml");
				
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void caesareanSection() {
		System.out.println("iniside caesareanSection");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("Caesarean.xml");
				    
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void episiotomy() {
		System.out.println("iniside Episiotomy");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("Episiotomy.xml");
				    
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	
	@Test
	public void fetalComplications() {
		System.out.println("iniside FetalComplications");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("FetalComplications.xml");
				    
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void fourthStageDuration() {
		System.out.println("iniside FourthStageDuration");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("FourthStageDuration.xml");
				    
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void parity() {
		System.out.println("iniside Parity");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("Parity.xml");
				    
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void perinealTear() {
		System.out.println("iniside PerinealTear");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("PerinealTear.xml");
				    
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void placentalWeight() {
		System.out.println("iniside PlacentalWt");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("PlacentalWeight.xml");
				    
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void presentation() {
		System.out.println("iniside Presentation");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("Presentation.xml");
				    
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void previousCS() {
		System.out.println("iniside PreviousCS");
					Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
				    List<Cluster> clusters=	parser.parse("PreviousCS.xml");
				    
				    for(Cluster cluster:clusters){
				    	cluster.traverse();					    
				    }
	}
	@Test
	public void bodyMassIndex() {
	System.out.println("iniside bodyMassIndex");
				Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
			    List<Cluster> clusters=	parser.parse("BookedStatistics.xml");
			
			    for(Cluster cluster:clusters){
			    	cluster.traverse();					    
			    }
}

@Test
public void bookedStatistics() {
System.out.println("iniside bookedStatistics");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("BodyMassIndex.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}
@Test
public void intravenusFluid() {
System.out.println("iniside intravenusFluid");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("IntraVenusFluid.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}
@Test
public void maternalAge() {
System.out.println("iniside maternal");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("MaternalAge.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}
@Test
public void maternalComplications() {
System.out.println("iniside maternal complications");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("MaternalComplications.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}
@Test
public void maternalHeight() {
System.out.println("iniside maternal height");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("MaternalHeight.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}


@Test
public void maternalMortalityMorbidility() {
System.out.println("iniside maternal mortality and morbidity");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("MaternalMortalityMorbidity.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}

@Test
public void maternalWeight() {
System.out.println("iniside maternal weight");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("MaternalWeight.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}
@Test
public void thirdStageDuration() {
System.out.println("iniside third Stage Duration");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("ThirdStageDuration.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}
@Test
public void vaginalDelivery() {
System.out.println("vaginal delivery");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("VaginalDelivery.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}
@Test
public void oxytoxic() {
System.out.println("oxytoxic");
			Parser<Cluster> parser = (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("Oxytoxin.xml");
		
		    for(Cluster cluster:clusters){
		    	cluster.traverse();					    
		    }
}

@Test
public void induction() {
System.out.println("induction");
			Parser<Cluster> parser =  (Parser<Cluster>) applicationContext.getBean("cluster.parser");     
		    List<Cluster> clusters=	parser.parse("Induction.xml");
		   for(Cluster cluster:clusters){
			   
		  	cluster.traverse();					    
		    } 
}
}
