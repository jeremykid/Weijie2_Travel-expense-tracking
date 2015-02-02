package ca.ualberta.cs.travel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.R.integer;


//some taken from https://github.com/abramhindle/student-picker

public class Claim implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9163912537171359690L;

	// protected int fromday;
	// protected int frommonth;
	// protected int fromyear;
	// protected int today;
	// protected int tomonth;
	// protected int toyear;
	protected String fromdate;
	protected String todate;
	protected String descripition;
	protected String claimName;
	protected ArrayList<expenseItem> itemList ;
	protected transient ArrayList<Listener> listeners = null;
	protected String status = "progress";
	
	//protected Map<String,Integer> currencymap;


	protected String startdate = "0";
	
	protected String cad = "0";
	protected String usd = "0";
	protected String eur = "0";
	protected String gbp = "0";
	
	protected String totalcurrency;
	
	
	// protected ArrayList<Item> item;
	public Claim(String claimname) {
		this.claimName = claimname;
		itemList = new ArrayList<expenseItem>();
		listeners = new ArrayList<Listener>();
	
		
	}

	public ArrayList<expenseItem> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<expenseItem> list) {
		itemList = list;
	}

	public void setName(String claimname) {
		claimName = claimname;
	}

	public String getName() {
		return claimName;
	};

	public String toString() {
		return getName()+" status is "+status;
	}

	public boolean equals(Object compareClaim) {
		if (compareClaim != null && compareClaim.getClass() == this.getClass()) {
			return this.equals((Claim) compareClaim);
		} else {
			return false;
		}
	}

	public boolean equals(Claim compareClaim) {
		if (compareClaim == null) {
			return false;
		}
		return getName().equals(compareClaim.getName());
	}

	public int hashCode() {
		return ("Claim:" + getName()).hashCode();
	}

	public String getToDate() {
		return todate;
	}

	public void setToDate(String date) {
		todate = date;
	}

	public String getFromDate() {
		return fromdate;
	}

	public void setFromDate(String date) {
		fromdate = date;
	}

	public String getdescripition() {
		return descripition;
	}

	public void setdescripition(String des) {
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
		for (Listener listener : getListener()) {
			listener.update();
		}
	}

	private ArrayList<Listener> getListener() {
		if (listeners == null) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}

	public void removeItem(expenseItem item) {
		itemList.remove(item);
		notifyListener();
	}



	public void removeListener(Listener l) {
		// TODO Auto-generated method stub
		getListener().remove(l);
	}
	
	public void addListener(Listener l) {
		getListener().add(l);
	}
	
	public String totalcurrency(){
		String cad ="0";
		String usd ="0";
		String eur ="0";
		String gbp ="0";
		
		for (int i =0; i<itemList.size();i++){
			if (itemList.get(i).getunit().equals("USD")){
				String s=itemList.get(i).getExpense();
				try {
				    int myset = Integer.parseInt(s);
				    int myusd = Integer.parseInt(usd);
				    myusd += myset;
				    usd = Integer.toString(myusd);
				} catch(NumberFormatException nfe) {
				  // Handle parse error.
			}
			}
			else if (itemList.get(i).getunit().equals("CAD")){
					String s=itemList.get(i).getExpense();
					try {
					    int myset = Integer.parseInt(s);
					    int myusd = Integer.parseInt(cad);
					    myusd += myset;
					    cad = Integer.toString(myusd);
					} catch(NumberFormatException nfe) {
					  // Handle parse error.
				}
				
				
			}		
			else if (itemList.get(i).getunit().equals("EUR")){
				String s=itemList.get(i).getExpense();
				try {
				    int myset = Integer.parseInt(s);
				    int myusd = Integer.parseInt(eur);
				    myusd += myset;
				    eur = Integer.toString(myusd);
				} catch(NumberFormatException nfe) {
				  // Handle parse error.
				}
			
			
			}
			else if (itemList.get(i).getunit().equals("GBP")){
				String s=itemList.get(i).getExpense();
				try {
				    int myset = Integer.parseInt(s);
				    int myusd = Integer.parseInt(gbp);
				    myusd += myset;
				    gbp = Integer.toString(myusd);
				} catch(NumberFormatException nfe) {
				  // Handle parse error.
			}
			
			
		}	
		}
		totalcurrency = "USD ="+usd+"\nCAD ="+cad+"\nEUR ="+eur+"\nGBP ="+gbp;
		return ("USD ="+usd+"\nCAD ="+cad+"\nEUR ="+eur+"\nGBP ="+gbp);
		}

	public String getStartDate(){
		
		return startdate;
		
	}
	
	public void setStartDate(String date){
		
		startdate = date;
	}
	
	public String getStatus(){
		
		return status;
	}
	
	public void setStatus(String s){
		status = s;
	}

	public String gettotalcurrency(){
		
		return totalcurrency;
	}
	
}
		
	
//	public void setCAD(String set){
//		int mycad;
//		int myset;
//		try {
//		    myset = Integer.parseInt(set);
//		    mycad = Integer.parseInt(cad);
//		    mycad += myset;
//		    cad = Integer.toString(mycad);
//		} catch(NumberFormatException nfe) {
//		  // Handle parse error.
//		}
//		
//		
//	}
//	
//	public String getCAD(){
//		
//		return cad;
//	}
//	
//	public void setUSD(String set){
//		int myusd ;
//		int myset ;
//		try {
//		    myset = Integer.parseInt(set);
//		    myusd = Integer.parseInt(usd);
//		    myusd += myset;
//		    usd = Integer.toString(myusd);
//		} catch(NumberFormatException nfe) {
//		  // Handle parse error.
//		}
//		
//	}
//	
//	public String getUSD(){
//		
//		return usd;
//	}
//	
//	public void setEUR(String set){
//		
//		eur +=set;
//	}
//	
//	public String getEUR(){
//		
//		return eur;
//	}
//	
//	public void setGBP(String set){
//		
//		gbp +=set;
//	}
//	
//	public String getGBP(){
//		
//		return gbp;
//	}
