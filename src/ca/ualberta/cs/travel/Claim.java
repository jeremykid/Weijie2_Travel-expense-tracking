package ca.ualberta.cs.travel;

import java.io.Serializable;

public class Claim implements Serializable{
	/**
	 * Claim Serializable ID
	 */
	private static final long serialVersionUID = 1852502152473055665L;
	
	protected String claimName;
	public String getName(){
		
		return this.claimName;
	};

	public Claim(String claimName) {
		this.claimName = claimName;
	}
	
	public String toString(){
		return getName();
	}
	
	public boolean equals(Object compareClaim) {
		if (compareClaim != null &&
				compareClaim.getClass()==this.getClass()) {
			return this.equals((Claim)compareClaim);
		} else {
			return false;
		}
	}

	
	public boolean equals(Claim compareClaim){
		if(compareClaim == null){
			
			return false;
		}
		return getName().equals(compareClaim.getName());
		
	}
	
	
	public int hashCode() {
		return ("Claim:"+getName()).hashCode();
	}
}
