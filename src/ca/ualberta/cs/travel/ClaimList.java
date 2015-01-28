package ca.ualberta.cs.travel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

//import ca.ualberta.cs.travel.test.Claim;

public class ClaimList implements Serializable{

	/**
	 * ClaimList serialization ID
	 */
	private static final long serialVersionUID = -315406992188409147L;
	protected ArrayList<Claim> claimList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	
	public ClaimList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
		

	
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	
	public Collection<Claim> getClaims() {
		// TODO Auto-generated method stub
		return claimList;
	}

	public void addClaim(Claim testClaim) {
		// TODO Auto-generated method stub
		
		claimList.add(testClaim);
		notifyListeners();
	}

	private void notifyListeners() {
		// TODO Auto-generated method stub
		for (Listener listener  : getListeners()) {
			listener.update();
		}
	}

	public void removeClaim(Claim testClaim) {
		
		claimList.remove(testClaim);
		notifyListeners();
	}

	public Claim chooseClaim() throws EmptyClaimListException{
		int size =claimList.size();
		if (size <= 0){
			throw new EmptyClaimListException();
			
		}
		int index = (int) (claimList.size() * Math.random());
		return claimList.get(index);
	}

	public int size() {
		// TODO Auto-generated method stub
		return claimList.size();
	}

	public boolean contains(Claim testClaim) {
		
		return claimList.contains(testClaim);
	}

	public void addListener(Listener l) {
		getListeners().add(l);
			
	}

	
	
	public void removeListener(Listener l) {
		// TODO Auto-generated method stub
		getListeners().remove(l);
	}



	public Claim getPosition(int position) {
		// TODO Auto-generated method stub
		return  claimList.get(position);
	}

	
}
