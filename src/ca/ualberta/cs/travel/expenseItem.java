package ca.ualberta.cs.travel;

import java.util.ArrayList;

public class expenseItem {
	
	protected String Type ;
	protected int Expense ;
	protected String Name;
	protected String date;


	
	public expenseItem(String name) {
		this.Name = name;
		
	}

	public String getName(){
		
		return this.Name;
	}
	
	public void setName(String name){
		
		this.Name=name;
	}
	
	public int getExpense(){
		
		return this.Expense;
	}
	
	public void setExpense(int expense){
		
		this.Expense=expense;
	}
	
	
	public String getDate(){
		
		return date;
	}
	
	public void setDate(String d){
		date = d;
		
	}
	
}
