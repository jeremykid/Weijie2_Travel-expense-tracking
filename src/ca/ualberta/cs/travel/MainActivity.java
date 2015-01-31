
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

	


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void editClaims(MenuItem menu){
    	Toast.makeText(this, "Edit Claims", Toast.LENGTH_SHORT).show();
    	Intent intent= new Intent(MainActivity.this, AddTravelClaim.class);
    	startActivity(intent);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	
    	ClaimListManager.initManager(this.getApplicationContext());
    	
		//Bundle extras = getIntent().getExtras();

    	//Button ggButton = (Button) findViewById(R.id.addtravelclaims);
		ListView listView = (ListView) findViewById(R.id.claimListView);
		Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(claimAdapter);
    	

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
				AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
				adb.setMessage("Edit or Delete "+list.get(position).toString()+"?");
				adb.setCancelable(true);
				
				final int finalPosition = position;
				
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
				}
				});
				adb.setNegativeButton("Cancel", new OnClickListener(){
					@Override
						public void onClick(DialogInterface dialog, int which) {
					}
				});
				//Toast.makeText(ListStudentsActivity.this, "Is the on click working?", Toast.LENGTH_SHORT).show();
				adb.show();
				return false;
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
				Intent intent = new Intent(MainActivity.this,
						ExpenseItemActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("id", itemPosition);
				startActivity(intent);
			}
		});

    }
    
    //
	public void goToAddClaimAction(View v) {
		//Bundle b = getIntent().getExtras();itemPosition
		Toast.makeText(this, "Add A Claim", Toast.LENGTH_SHORT).show();
		
		//ClaimListController st = new ClaimListController();
		Intent myintent = new Intent(MainActivity.this, AddTravelClaim.class);
		//myintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(myintent);

	}
}
    
