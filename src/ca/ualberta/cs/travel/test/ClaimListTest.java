package ca.ualberta.cs.travel.test;

import java.util.Collection;

import ca.ualberta.cs.travel.Claim;
import ca.ualberta.cs.travel.ClaimList;
import ca.ualberta.cs.travel.EmptyClaimListException;

import junit.framework.TestCase;

public class ClaimListTest extends TestCase {
	public void testEmptyClaimList(){
		ClaimList claimList = new ClaimList();
		//Collection<Claim> claims = claimList.getClaims();
		assertTrue("Empty Claim List", claimList.size() == 0);
		
	}
	
	public void testAddClaimList(){
		ClaimList claimList = new ClaimList();
		String claimName = "A Claim";
		Claim testClaim = new Claim(claimName);
		claimList.addClaim(testClaim);
		Collection<Claim> claims = claimList.getClaims();		
		assertTrue("Claim List Size", claimList.size() == 1);
		assertTrue("Test Claim Not Contained",claimList.contains(testClaim));
		
	}
	
	public void testGetClaimList(){
		ClaimList claimList = new ClaimList();
		String claimName = "A Claim";
		Claim testClaim = new Claim(claimName);
		claimList.addClaim(testClaim);
		Collection<Claim> claims = claimList.getClaims();		
		assertTrue("Claim List Size", claims.size() == 1);
		assertTrue("Test Claim Not Contained",claims.contains(testClaim));
		
	}
	
	public void testRemoveClaim(){
		ClaimList claimList = new ClaimList();
		String claimName = "A Claim";
		Claim testClaim = new Claim(claimName);
		claimList.addClaim(testClaim);
				
		assertTrue("Claim List Size Isn't Big Enough", claimList.size() == 1);
		assertTrue("",claimList.contains(testClaim));
		claimList.removeClaim(testClaim);

		assertTrue("Claim List Size Isn't Big Enough", claimList.size() == 0);
		assertFalse("Test Claim still contained",claimList.contains(testClaim));
	}

	public void testChooseClaimList(){
		try{
		ClaimList claimList = new ClaimList();
		String claimName = "A Claim";
		Claim testClaim = new Claim(claimName);
		claimList.addClaim(testClaim);
		// testing for 1 student
		for (int i = 0; i < 10; i++){
			Claim claim = claimList.chooseClaim();
			assertTrue("Claim is not null", claim != null);
			assertTrue("Didn't choose the right claim",claim.equals(testClaim));
		}
		String claimNameB = "Another Claim";
		Claim testClaimB = new Claim(claimNameB);
		claimList.addClaim(testClaimB);
		Claim [] claimArray = {testClaim,testClaimB};
		for (int i = 0; i<claimArray.length;i++){
			Claim targetclaim = claimArray[i];
			int maxcount = 1000;
			while(maxcount >0 && !targetclaim.equals(claimList.chooseClaim())){
				// do nothing
				maxcount --;
			}
			assertTrue("Too Many iterations", maxcount > 0);
		}
		}catch (EmptyClaimListException e){
			assertTrue("EmptyStudentListException was thrown"+e,false);
		}
		
	}

	public void testChooseEmptyClaimList(){
		ClaimList claimList = new ClaimList();
		try{
			Claim s = claimList.chooseClaim();
			assertFalse("we should reach here", s==null);
			assertTrue("we should reach here",false);
		}catch (EmptyClaimListException e){
			assertTrue("we should reach here",true);
			
		}
	}
}
