package ca.ualberta.cs.travel;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddItemActivity extends Activity {
	protected EditText itemName;
	protected Spinner currencytype;
	protected EditText currency;
	
	protected Button addedititem;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.additem);
		
		addedititem = (Button) findViewById(R.id.setfromdate);
		itemName = (EditText) findViewById(R.id.edititemname);
		ClaimListManager.initManager(this.getApplicationContext());
		Bundle b= getIntent().getExtras();
		
		final int name = b.getInt("name");
		if (b.size()== 2){
			//addedititem.setText("Edit An Item");
        	
			final int set = b.getInt("itemid");
			Toast.makeText(this, "Expense Item"+ set, Toast.LENGTH_SHORT).show();
			expenseItem item = ClaimListController.getClaimList().getPosition(name).getPosition(set);
        	String iname = item.getName();
        	itemName.setText(iname);
			
			
			addedititem.setOnClickListener(new EditItemAction());
			
		}
		if (b.size() == 1){
			addedititem.setOnClickListener(new addItemAction());
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item, menu);
		return true;
	}

	//shenmegui



	private class addItemAction implements OnClickListener{
		public void onClick(View v) {

			itemName = (EditText) findViewById(R.id.edititemname);
			Bundle extras = getIntent().getExtras();
			int temp = extras.getInt("name");
			expenseItem newitem = new expenseItem(itemName.getText().toString());
			//ClaimListController.getClaimList().getPosition(temp).getItemList().add(newitem);
			Intent intent = new Intent(AddItemActivity.this,
					ExpenseItemActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("name", temp);
			ClaimListController.saveClaimList();
			startActivity(intent);

		}
		
		
	}

	public class EditItemAction implements OnClickListener {

	
		public void onClick(View v) {
			itemName = (EditText) findViewById(R.id.edititemname);
			Bundle extras = getIntent().getExtras();
			int temp = extras.getInt("name");
			int pos = extras.getInt("itemname");
			ClaimListController.getClaimList().getPosition(temp)
					.getPosition(pos).setName(itemName.getText().toString());
			Intent intent = new Intent(AddItemActivity.this,
					ExpenseItemActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("name", temp);
			ClaimListController.saveClaimList();
			startActivity(intent);

		}

	}

	public void saveItem(View v) {
		Toast.makeText(this, "addItemClaim", Toast.LENGTH_SHORT).show();
		itemName = (EditText) findViewById(R.id.edititemname);
		Bundle extras = getIntent().getExtras();
		int temp = extras.getInt("name");
		expenseItem newitem = new expenseItem(itemName.getText().toString());
		ClaimListController.getClaimList().getPosition(temp).getItemList().add(newitem);
		Intent intent = new Intent(AddItemActivity.this,
				ExpenseItemActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("name", temp);
		startActivity(intent);
	}
	
}

