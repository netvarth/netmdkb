package com.nv.netmdkb.analytic.bl.dataCluster;


import com.nv.netmdkb.exception.ServiceException;

public class IntegerParser implements Parser {

	@Override
	public boolean evaluate(Predicate predicate, String... operands) throws ServiceException {

		String leftOperand = null;
		String rightOperand = null;
		boolean result;
		switch(predicate){

		case  gt :

			result=true;
			for (String operand :operands){

				if (leftOperand==null){
					leftOperand = operand;
				    continue;
				}
				else
					rightOperand =operand;

				try{
					
					result=result&& Integer.parseInt(rightOperand)>Integer.parseInt(leftOperand);		
				}catch(NumberFormatException e){
					return false;
				}
			}

			return 	result;
		case eq :
			result=true;
			for (String operand :operands){

				if (leftOperand==null){
					leftOperand = operand;
				    continue;
				}
				else
					rightOperand =operand;
				try{  
					result=result&&Integer.parseInt(rightOperand)==Integer.parseInt(leftOperand);
				} catch	(NumberFormatException e){

					return rightOperand.toUpperCase().trim().equals(leftOperand.toUpperCase().trim());
				}

			}

			return 	result;
		case gteq :
			result=true;
			for (String operand :operands){

				if (leftOperand==null){
					leftOperand = operand;
				    continue;
				}
				else
					rightOperand =operand;
				try{  
					result=result&&Integer.parseInt(rightOperand)>=Integer.parseInt(leftOperand);
				} catch	(NumberFormatException e){

					return false;
				}

			}

			return 	result;



		case lt :
			result=true;
			for (String operand :operands){

				if (leftOperand==null){
					leftOperand = operand;
					continue;
				}
				else
					rightOperand =operand;
				try{  
					result=result&&Integer.parseInt(rightOperand)<Integer.parseInt(leftOperand);
				} catch	(NumberFormatException e){

					return false;
				}

			}

			return 	result;


		case lteq :
			result=true;
			for (String operand :operands){

				if (leftOperand==null){
					leftOperand = operand;
					continue;
				}
				else
					rightOperand =operand;
				try{  
					result=result&&Integer.parseInt(rightOperand)<=Integer.parseInt(leftOperand);
				} catch	(NumberFormatException e){

					return false;
				}

			}

			return 	result;


		case neq :
			result=true;
			for (String operand :operands){

				if (leftOperand==null){
					leftOperand = operand;
					continue;
				}
				else
					rightOperand =operand;
				try{  
					result=result&&Integer.parseInt(rightOperand)!=Integer.parseInt(leftOperand);
				} catch	(NumberFormatException e){

					return false;
				}

			}

			return 	result;


		case in :
			result=false;
			for (String operand :operands){

				if (leftOperand==null){
					leftOperand = operand;
					continue;
				}
				else
					rightOperand =operand;
				try{  
					result=result||Integer.parseInt(rightOperand)==Integer.parseInt(leftOperand);
				} catch	(NumberFormatException e){

					return false;
				}

			}

			return 	result;


		}

		return false;
	}

}
