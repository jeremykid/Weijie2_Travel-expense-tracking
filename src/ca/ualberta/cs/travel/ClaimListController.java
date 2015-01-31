package ca.ualberta.cs.travel;

import java.io.IOException;

public class ClaimListController {
	// Lazy Singleton
	private static ClaimList claimList = null;

	static public ClaimList getClaimList() {
		if (claimList == null) {
			try {
				claimList = ClaimListManager.getManager().loadClaimList();
				claimList.addListener(new Listener() {
					@Override
					public void update() {
						saveClaimList();
					}
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(
						"Could not deserialize ClaimList from ClaimListManager");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(
						"Could not deserialize ClaimList from ClaimListManager");
			}
		}
		return claimList;
	}

	public Claim chooseClaim() throws EmptyClaimListException {
		return getClaimList().chooseClaim();
	}

	public void addClaim(Claim claim) {
		// TODO Auto-generated method stub
		getClaimList().addClaim(claim);
	}

	static public void saveClaimList() {
		try {
			ClaimListManager.getManager().saveClaimList(getClaimList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(
					"Could not deserialize StudentList from StudentListManager");
		}
	}
}