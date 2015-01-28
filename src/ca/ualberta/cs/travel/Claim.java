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
	
	protected Date fromdate;
	protected Date todate;
	protected String descripition;
	
	protected String claimName;
	
	protected ArrayList<Item> itemList;
	
	//protected ArrayList<Item> item;
	
	public void setName(String claimname){
		
		this.claimName= claimname;
	}
	
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
	
	public Date getFromDate(){
		
		return fromdate;
	}
	
	public Date getToDate(){
		
		return todate;
	}
	
	public void setFromDate(Date fromdate){
		
		this.fromdate=fromdate;
	}
	
	public void setToDate(Date todate){
		
		this.todate=todate;
	}
	
	public String getdescription(){
		
		return descripition;
	}
	
	public void setdescripion(String descripition){
		
		this.descripition=descripition;
	}
}
