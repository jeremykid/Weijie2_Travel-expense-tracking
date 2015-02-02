package ca.ualberta.cs.travel;

import java.io.Serializable;



public class expenseItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2360354095004578844L;

	protected String Type;
	protected String Expense;
	protected String Name;
	protected String date;
	protected String time;
	private String unit;
	private String category;
	private String description;
	
	public expenseItem(String name) {
		this.Name = name;
	}

	public String getName() {
		return this.Name;
	}

	public String toString(){
		
		return Name+" spends "+Expense+unit;
	}
	
	public void setName(String name) {
		this.Name = name;
	}

	public String getExpense() {
		return this.Expense;
	}

	public void setExpense(String string) {
		this.Expense = string;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String d) {
		date = d;
	}
	
	public String getTime(){
		return time;
		
	}
	
	public void setTime(String t){
		
		time=t;
	}
	
	public void setunit(String u){
		
		unit= u;
	}
	
	public String getunit(){
		
		return unit;
	}
	
	public String getcategory(){
		
		return category;
	}
	
	public String getdescription(){
		
		return description;
	}
	
	public void setcategory(String c){
		
		category = c;
	}
	
	public void setdescription(String d){
		
		description = d;
	}
}
