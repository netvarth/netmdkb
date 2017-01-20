package com.nv.netmdkb.analytic.bl;





public class QuestionIdentifier {
	



	private int id;
	private int index;
	/**
	 * @param id
	 */
	public QuestionIdentifier(int id) {
		super();
		this.id = id;
	}

	/**
	 * @param id
	 * @param index
	 */
	public QuestionIdentifier(int id, int index) {
		super();
		this.id = id;
		this.index = index;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	




	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}


	public int hashCode(){
		
        int hashcode = 0;
        hashcode = id*index;
        return hashcode;
    }
     
   
	public boolean equals(Object obj){

        if (obj instanceof QuestionIdentifier) {
        	QuestionIdentifier pp = (QuestionIdentifier) obj;
            return (pp.id==this.id) && (pp.index == this.index);
        } else {
            return false;
        }
	}
	
	

}
