package ca.ualberta.cs.travel;

public class Item {
	
	protected String Type ;
	protected int Expense ;
	protected String Name;
	
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
	
	
	
	
}
