package ca.ualberta.cs.travel;

import java.util.ArrayList;
import java.util.Collection;

//import ca.ualberta.cs.travel.test.Claim;

public class ClaimList {

	protected ArrayList<Claim> claimList;
	
	public ClaimList(){
		claimList = new ArrayList<Claim>();
	}
		
	public Collection<Claim> getClaims() {
		// TODO Auto-generated method stub
		return claimList;
	}

	public void addClaim(Claim testClaim) {
		// TODO Auto-generated method stub
		
		claimList.add(testClaim);
	}

	public void removeClaim(Claim testClaim) {
		// TODO Auto-generated method stub
		claimList.remove(testClaim);
	}

	public Claim chooseClaim() {
		// TODO Auto-generated method stub
		int index = (int) (claimList.size() * Math.random());
		return claimList.get(index);
	}

}
