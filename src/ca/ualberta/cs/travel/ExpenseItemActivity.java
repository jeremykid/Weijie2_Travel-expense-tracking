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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ExpenseItemActivity extends Activity {
	
		private TextView usdtext;
	
		
		private ListView listView;
		private Button btm;
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.expenseitem);
			
			ClaimListManager.initManager(this.getApplicationContext());
			
			Bundle extras = getIntent().getExtras();
			final int temp = extras.getInt("id");
			Claim storeclaim = ClaimListController.getClaimList().getPosition(
					temp);
			//btm = (Button) findViewById(R.id.backToMain);
			listView = (ListView) findViewById(R.id.ItemList);
			Collection<expenseItem> Items = ClaimListController.getClaimList().getPosition(temp).getItemList();
			final ArrayList<expenseItem> items = new ArrayList<expenseItem>(Items);
			final ArrayAdapter<expenseItem> itemAdapter = new ArrayAdapter<expenseItem>(this,
					android.R.layout.simple_list_item_1, items);
			listView.setAdapter(itemAdapter);
			
			usdtext = (TextView) findViewById(R.id.USD);
			String usd = storeclaim.totalcurrency();
			usdtext.setText(usd);
//			
//			cadtext = (TextView) findViewById(R.id.cad);
//			String cad = storeclaim.getCAD();
//			cadtext.setText("CAD="+cad);
//			
			ClaimListController.getClaimList().getPosition(temp).addListener(new Listener() {

				@Override
				public void update() {
					// TODO Auto-generated method stub
					items.clear();
					Collection<expenseItem> Items = ClaimListController
							.getClaimList().getPosition(temp).getItemList();
					items.addAll(Items);
					itemAdapter.notifyDataSetChanged();
				}
			});
			//btm.setOnClickListener(new Back_click());
			
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> adapterView, View view,
						int position, long id) {
					int pos     = position;
					Toast.makeText(ExpenseItemActivity.this, "open a item"+pos,
							Toast.LENGTH_SHORT).show();
					
					Intent intent = new Intent(ExpenseItemActivity.this,
							AddItemActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("itemname", pos);
					intent.putExtra("id", temp);
					startActivity(intent);
				}

			});
			
			
			listView.setOnItemLongClickListener(new OnItemLongClickListener() {
				public boolean onItemLongClick(AdapterView<?> adapterView,
						View view, int position, long id) {
					AlertDialog.Builder adb = new AlertDialog.Builder(
							ExpenseItemActivity.this);
					adb.setMessage("Edit/Delete " + items.get(position).toString()
							+ "?");
					adb.setCancelable(true);
					final int finalPosition = position;

					adb.setNeutralButton("Edit", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(ExpenseItemActivity.this,
									AddItemActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.putExtra("itemname", finalPosition);
							intent.putExtra("id", temp);
							startActivity(intent);
						}
					});
					adb.setPositiveButton("Delete", new OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							expenseItem item = items.get(finalPosition);
							ClaimListController.getClaimList().getPosition(temp).removeItem(item);
					}
					});
					adb.setNegativeButton("Cancel", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					adb.show();
					return false;
				}
			});
			
		}

		
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.expense_item, menu);
			return true;
		}

		
	    public void backtomainaction(MenuItem menu){
	    	Toast.makeText(this, "Edit Claims", Toast.LENGTH_SHORT).show();
	    	Intent intent= new Intent(ExpenseItemActivity.this, MainActivity.class);
	    	startActivity(intent);
	    }

		public void addanitemaction(View v){
			//Toast.makeText(this, "add new item", Toast.LENGTH_SHORT).show();
			Bundle extras = getIntent().getExtras();
			int temp = extras.getInt("id");
			//int itemname = extras.getInt("itemname");
			Intent myintent = new Intent(ExpenseItemActivity.this, AddItemActivity.class);
			myintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			myintent.putExtra("id", temp);
			//myintent.putExtra("itemname", itemname);
			startActivity(myintent);
		}
	}