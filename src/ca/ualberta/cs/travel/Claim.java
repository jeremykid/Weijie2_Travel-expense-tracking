package ca.ualberta.cs.travel;

public class Claim {
	protected String claimName;
	public String getName(){
		
		return this.claimName;
	};

	public Claim(String claimName) {
		this.claimName = claimName;
	}
	
}
