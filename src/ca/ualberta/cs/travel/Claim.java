package ca.ualberta.cs.travel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import android.content.ClipData.Item;

public class Claim implements Serializable{
	/**
	 * Claim Serializable ID
	 */
	private static final long serialVersionUID = 1852502152473055665L;
	
//	protected int fromday;
//	protected int frommonth;
//	protected int fromyear;
//	protected int today;
//	protected int tomonth;
//	protected int toyear;
	
	protected String fromdate;
	protected String todate;
	protected String descripition;
	
	protected String claimName;
	
	protected ArrayList<expenseItem> itemList = null;
	protected transient ArrayList<Listener> listeners = null;
	//protected ArrayList<Item> item;
	
	public Claim(String claimname) {
		this.claimName = claimname;
		itemList = new ArrayList<expenseItem> ();
		listeners = new ArrayList<Listener>();
	}
	
	public ArrayList<expenseItem> getItemList(){
		
		return itemList;
	}
	
	public void setItemList(ArrayList<expenseItem> list){
		
		itemList = list;
	}
	
	public void setName(String claimname){
		
		claimName= claimname;
	}
	
	public String getName(){
		
		return claimName;
	};


	
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
	

	public String getToDate(){
		
		return todate;
		
	}
	
	public void setToDate(String date){
		
		
		todate = date;
	}
	
	public String getFromDate(){
		
		
		return fromdate;
	}
	
	public void setFromDate(String date){
		
		fromdate = date;
	}
	
	
	public String getdescripition(){
		
		return descripition;
	}
	
	public void setdescripition(String des){
		
		descripition = des;
	}

	public expenseItem getPosition(int set) {
		
		return itemList.get(set);
	}

	
	public void addItem(expenseItem item) {
		itemList.add(item);
		notifyListener();
		
	}
	
	private void notifyListener() {
		// TODO Auto-generated method stub
		for (Listener listener  : getListener()) {
			listener.update();
		}
	}

	private ArrayList<Listener> getListener() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}

	public void removeItem(expenseItem item) {
		
		itemList.remove(item);
	}

	public void addListener(Listener l) {
		getListener().add(l);
			
	}

	
}
