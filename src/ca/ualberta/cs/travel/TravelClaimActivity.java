package ca.ualberta.cs.travel;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TravelClaimActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.travelclaim);
		ListView listView = (ListView) findViewById(R.id.claimListView);
		Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
	//ArrayAdapter<Claim> claimAdapter = new ArrayAdapter(this, android.R.id, objects); 
		ArrayList<Claim> list = new ArrayList<Claim>(claims);
		ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(claimAdapter);
}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.travel_claim, menu);
		return true;
	}
	


	public void addClaimAction(View v){
		Toast.makeText(this, "Adding A Claim", Toast.LENGTH_SHORT).show();
		ClaimListController st = new ClaimListController();
		EditText textView = (EditText) findViewById (R.id.addtravelclaimname);
		st.addClaim(new Claim(textView.getText().toString()));
		Intent intent = new Intent(TravelClaimActivity.this, MainActivity.class);
    	startActivity(intent);
	}
	
	
	public void expenseItemsAction(View v){
		Toast.makeText(this, "Expense Item", Toast.LENGTH_SHORT).show();
		//ClaimListController st = new ClaimListController();
		Intent intent = new Intent(TravelClaimActivity.this, ExpenseItemActivity.class);
    	startActivity(intent);
	}
	
	
	
}
//don't use this
