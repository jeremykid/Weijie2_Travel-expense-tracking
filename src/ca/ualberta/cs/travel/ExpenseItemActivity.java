package ca.ualberta.cs.travel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ExpenseItemActivity extends Activity {

	//private static final String FILENAME = "file.sav";//static means shared by all classes. final means cannot change
	//private EditText bodyText;
	protected ListView itemlist;
	protected ArrayAdapter<expenseItem> adapter;
	protected Claim claim;
	protected Button additem; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expenseitem);
		
		ClaimListManager.initManager(this.getApplicationContext());
		
		Bundle b = getIntent().getExtras();
		final int name=b.getInt("name");
		
		Collection<expenseItem> Items = ClaimListController.getClaimList()
				.getPosition(name).getItemList();
		final ArrayList<expenseItem> items = new ArrayList<expenseItem>(Items);
		final ArrayAdapter<expenseItem> itemAdapter = new ArrayAdapter<expenseItem>(this,
				android.R.layout.simple_list_item_1, items);
		itemlist = (ListView) findViewById(R.id.ItemList);
		itemlist.setAdapter(itemAdapter);
		
		
		itemlist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {

				final int pos = position;
				
				AlertDialog.Builder adb = new AlertDialog.Builder(
						ExpenseItemActivity.this);
				adb.setMessage("Item: ");
				adb.setCancelable(true);
				adb.setPositiveButton("Edit",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(ExpenseItemActivity.this,
										AddItemActivity.class);
										intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
										intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
										intent.putExtra("name", name);
										intent.putExtra("itemid",pos);
										startActivity(intent);
							}
						});
				adb.show();
				adapter.notifyDataSetChanged();

			}
		});
		 			
    	itemlist.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					 int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(ExpenseItemActivity.this);
				adb.setMessage("Edit or Delete "+claim.getItemList().get(position).toString()+"?");
				adb.setCancelable(true);
				
				final int itemid = position;
				
				//
				adb.setNeutralButton("Edit", new OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
						
						//taken from http://handsomeliuyang.iteye.com/blog/1315283
						Intent myintent = new Intent(ExpenseItemActivity.this, AddItemActivity.class);
						myintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						myintent.putExtra("name", name);
						myintent.putExtra("itemid", itemid);
						//Toast.makeText(this, "Expense Item"+ finalPosition, Toast.LENGTH_SHORT).show();
						//Intent intent = new Intent(ExpenseItemActivity.this, AddItemActivity.class);
				    	startActivity(myintent);
				}

				});
				
				
		          
    	

			adb.setPositiveButton("Delete", new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					expenseItem item = claim.getItemList().get(name);
					ClaimListController.getClaimList().getPosition(name).removeItem(item);
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
    	});	
		
		ClaimListController.getClaimList().getPosition(name).addListener(new Listener() {
					@Override
					public void update() {
						// TODO Auto-generated method stub
						items.clear();
						Collection<expenseItem> Items = ClaimListController
								.getClaimList().getPosition(name).getItemList();
						items.addAll(Items);
						itemAdapter.notifyDataSetChanged();
					}
				});
    	
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_item, menu);
		return true;
	}


	
	

	

	public void addanitemaction(View v) {
		
		Toast.makeText(this, "Add A Item", Toast.LENGTH_SHORT).show();
		Bundle b = getIntent().getExtras();
		int name = b.getInt("name");
		
		//ClaimListController st = new ClaimListController();
		Intent intent = new Intent(ExpenseItemActivity.this, AddItemActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("name", name);
    	startActivity(intent);

	}
	

}
