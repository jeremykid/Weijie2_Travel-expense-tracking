
/*
Travel expense tracking:  For each travel claim (for which expenses are to be reimbursed), the application should record expense items.
An expense item has a date, category (e.g., air fare, ground transport, vehicle rental, fuel, parking, registration, accommodation, meal), textual description, amount spent, and unit of currency (e.g., CAD, USD, EUR, GBP, etc.).
A travel claim has a date range and a textual description (e.g., destination and reason for travel).

Copyright (C) 2015 Weijie Sun weijie2@ualberta.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package ca.ualberta.cs.travel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private List<Claim> allclaim;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //just follow student picker to use the menu to get into the add Claim
    public void editClaims(MenuItem menu){
    	Toast.makeText(this, "Add a Claim", Toast.LENGTH_SHORT).show();
    	Intent intent= new Intent(MainActivity.this, AddTravelClaim.class);
    	startActivity(intent);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	
    	ClaimListManager.initManager(this.getApplicationContext());
    	
		//Bundle extras = getIntent().getExtras();
    	//taken from http://blog.csdn.net/janronehoo/article/details/8746447
    	//Button ggButton = (Button) findViewById(R.id.addtravelclaims);
		ListView listView = (ListView) findViewById(R.id.claimListView);
		List<Claim> claims = ClaimListController.getClaimList().getClaims();//change the collection into list
		
		Collections.sort(claims,new Comparator<Claim>() {
	        @Override  
            public int compare(Claim b1, Claim b2) {  
                return b1.getStartDate().compareTo(b2.getStartDate());  
            }  
		});
		
		
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(claimAdapter);
    	//add
		allclaim = claims;
		
    	ClaimListController.getClaimList().addListener(new Listener(){
    		@Override
    		public void update() {
    			list.clear();
    			Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
    			list.addAll(claims);
    			claimAdapter.notifyDataSetChanged();
    		}
    		
    		
    	});
    	
    	listView.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				final int finalPosition = position;
				Claim claim = list.get(finalPosition);
				AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
				adb.setMessage(claim.getName()+" total cost \n"+claim.totalcurrency()+"\n From "+claim.getFromDate()+" to "+claim.getToDate());
				adb.setCancelable(true);
				
				
				
				//
				adb.setNeutralButton("Edit", new OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
						
						//taken from http://handsomeliuyang.iteye.com/blog/1315283
						Intent myintent = new Intent(MainActivity.this, AddTravelClaim.class);
						myintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						myintent.putExtra("id", finalPosition);
		

				    	startActivity(myintent);
				}

			
				});
				
		          
    	
				adb.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Claim claim = list.get(finalPosition);
						ClaimListController.getClaimList().removeClaim(claim);
						ClaimListController.saveClaimList();
				}
				});
				adb.setNegativeButton("Cancel", new OnClickListener(){
					@Override
						public void onClick(DialogInterface dialog, int which) {
					}
				});
				//Toast.makeText(ListStudentsActivity.this, "Is the on click working?", Toast.LENGTH_SHORT).show();
				adb.show();
				return true;
			}
    		
    		}
    	
   	);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				int itemPosition = position;
				Toast.makeText(MainActivity.this,
						"open a Claim" + itemPosition, Toast.LENGTH_SHORT)
						.show();
				Intent myintent = new Intent(MainActivity.this,
						ExpenseItemActivity.class);
				myintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				myintent.putExtra("id", itemPosition);
				startActivity(myintent);
			}
		});
		}
    //
	public void goToAddClaimAction(View v) {
		//Bundle b = getIntent().getExtras();itemPosition
		Toast.makeText(this, "Add A Claim", Toast.LENGTH_SHORT).show();
		
		//ClaimListController st = newx ClaimListController();
		Intent myintent = new Intent(MainActivity.this, AddTravelClaim.class);
		//myintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(myintent);

	}
	
	
	//taken from http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
	public void emailAction(MenuItem menu){
		StringBuffer mailBody = new StringBuffer();
		for (int i = 0; i < allclaim.size(); i++){
			mailBody.append("ClaimName"+allclaim.get(i).getName() + "\n From"+allclaim.get(i).getFromDate()+
					"To"+allclaim.get(i).getToDate()+"is"
					+ allclaim.get(i).getStatus() + "\n description is"
					+ allclaim.get(i).getdescripition() + "\n");
			for (int j = 0; j <allclaim.get(i).getItemList().size();j++){
				mailBody.append(j+"ItemName"+allclaim.get(i).getItemList().get(j).getName()+"\n"+allclaim.get(i).getItemList().get(j).getDate()
						+"\n Expense"+allclaim.get(i).getItemList().get(j).getExpense()+allclaim.get(i).getItemList().get(j).getunit()
						+"\n Category"+allclaim.get(i).getItemList().get(j).getcategory()
						+"\n description"+allclaim.get(i).getItemList().get(j).getdescription());
			}
		}
		Intent myintent = new Intent(Intent.ACTION_SEND);
		myintent.setType("message/rfc822");
		myintent.putExtra(Intent.EXTRA_TEXT, mailBody.toString());
		try {
			startActivity(Intent.createChooser(myintent, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(MainActivity.this,
					"There are no email clients installed.", Toast.LENGTH_SHORT)
					.show();
		}
		
		
	}
}
    
