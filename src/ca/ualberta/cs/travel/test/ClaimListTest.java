package ca.ualberta.cs.travel.test;

import java.util.Collection;

import ca.ualberta.cs.travel.Claim;
import ca.ualberta.cs.travel.ClaimList;

import junit.framework.TestCase;

public class ClaimListTest extends TestCase {
	public void testClaimList(){
		ClaimList claimList = new ClaimList();
		Collection<Claim> claims = claimList.getClaims();
		assertTrue("Empty Claim List", claims.size() == 0);
		
	}
	
	public void testAddClaimList(){
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
		Collection<Claim> claims = claimList.getClaims();		
		assertTrue("Claim List Size Isn't Big Enough", claims.size() == 1);
		assertTrue("",claims.contains(testClaim));
		claimList.removeClaim(testClaim);
		claims = claimList.getClaims();		
		assertTrue("Claim List Size Isn't Big Enough", claims.size() == 0);
		assertFalse("Test Claim still contained",claims.contains(testClaim));
	}
}
