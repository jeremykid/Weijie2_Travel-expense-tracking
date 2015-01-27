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

public class AddTravelClaim extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.travelclaim);
		//StudentListManager.initManager(this.getApplicationContext());
		
		
		//very important
    	ClaimListManager.initManager(this.getApplicationContext());


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
		Intent intent = new Intent(AddTravelClaim.this, MainActivity.class);
    	startActivity(intent);
	}

	
	public void expenseItemsAction(View v){
		Toast.makeText(this, "Expense Item", Toast.LENGTH_SHORT).show();
		//ClaimListController st = new ClaimListController();
		Intent intent = new Intent(AddTravelClaim.this, ExpenseItemActivity.class);
    	startActivity(intent);
	}
	
	
	
}
