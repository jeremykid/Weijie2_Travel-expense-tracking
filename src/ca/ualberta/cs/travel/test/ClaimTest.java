package ca.ualberta.cs.travel.test;

import junit.framework.TestCase;
import ca.ualberta.cs.travel.Claim;

public class ClaimTest extends TestCase {
	public void testClaim(){
		String claimName = "A Claim";
		Claim claim = new Claim(claimName);
		assertTrue("Claim Name is not equal", claimName.equals(claim.getName()));
	}
}
