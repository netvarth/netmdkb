package com.nv.netmdkb.analytic.bl.dataCluster;

import com.nv.netmdkb.exception.ServiceException;

public class StringParser implements Parser{ 



	@Override
	public boolean evaluate(Predicate predicate, String... operands) throws ServiceException {
		String leftOperand = null;
		String rightOperand = null;
		boolean result;

		switch(predicate){



		case eq :
			result=true;
			for (String operand :operands){

				if (leftOperand==null){
					leftOperand = operand;
					continue;
				}
				else
					rightOperand =operand;

				result=result&&rightOperand.toUpperCase().trim().equals(leftOperand.toUpperCase().trim());
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

				result=result&&!rightOperand.toUpperCase().trim().equals(leftOperand.toUpperCase().trim());
			}
			return result;

		
		case in:
			result=false;
			for (String operand :operands){

				if (leftOperand==null){
					leftOperand = operand;
					continue;
				}
				else
					rightOperand =operand;

				result=result||rightOperand.toUpperCase().trim().equals(leftOperand.toUpperCase().trim());

			}

			return 	result;
		default:
			return false;
		}


	}

}



