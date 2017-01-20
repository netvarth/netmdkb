package com.nv.netmdkb.analytic.bl.report;

import com.nv.netmdkb.rs.dto.Inference;




public interface InformationExplorer {
	
public String getName();

public int getId();

public Inference getInference( String fmonth,String  fyear,String toMonth, String toYear); 	

public Inference getInference( String fmonth,String  fyear,String toMonth, String toYear,Integer[] hospitalList);

public Inference getInferenceForGraphCoordinates(String fMonth, String fYear,
		String toMonth, String toYear, Integer[] hospitalList);

public Inference getInferenceForGraphCoordinates(String fMonth, String fYear,
		String toMonth, String toYear);


}
