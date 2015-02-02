package ca.ualberta.cs.travel;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ClipData.Item;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddItemActivity extends Activity implements OnClickListener {
	private TextView itemname;
	private Button addedititem;
	private TextView expense;
	private TextView itemdate;
	private TextView itemtime;
	//reference from http://www.mkyong.com/android/android-date-picker-example/ all about datepicker
	private Button btnDate, btnTime;
	private Calendar calendar;
	private int day, year, month,hour,minute;
	
	private Spinner currencytype;
	private ArrayAdapter<String> CurrencyAdapter;
	
	private String currency;
	
	private TextView description;
	private TextView category;
	private TextView unit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.additem);
		Bundle b = getIntent().getExtras();
		itemname = (TextView) findViewById(R.id.edititemname);
		addedititem = (Button) findViewById(R.id.addedititem);
		expense = (TextView) findViewById(R.id.editexpenseamount);
		ClaimListManager.initManager(this.getApplicationContext());
		
		itemdate = (TextView) findViewById(R.id.itemdate);
		itemtime=(TextView) findViewById(R.id.itemtime);
		
		btnDate = (Button) findViewById(R.id.setitemdate);
		btnTime = (Button) findViewById(R.id.setitemtime);
		btnDate.setOnClickListener(this);
		btnTime.setOnClickListener(this);
		calendar = Calendar.getInstance();
		
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		
		showDate(year, month + 1, day);
		showTime(hour,minute);
		
		description = (TextView) findViewById(R.id.descriptiontext);
		category = (TextView) findViewById(R.id.categorytext);
		unit = (TextView) findViewById(R.id.textcurrency);
		
		final int temp = b.getInt("id");
		if (b.size() == 2) {
			final int itemid = b.getInt("itemname");
			expenseItem openeditem = ClaimListController.getClaimList().getPosition(temp)
					.getPosition(itemid);
			String iname = openeditem.getName();
			String amount = openeditem.getExpense();
			Toast.makeText(this, iname, Toast.LENGTH_SHORT).show();
			itemname.setText(iname);
			expense.setText(amount);
			
			String itemtimet;
			itemtimet = openeditem.getTime();
			itemtime.setText(itemtimet);
			
			String itemdatet;
			itemdatet = openeditem.getDate();
			itemdate.setText(itemdatet);
			
			String descriptiont;
			descriptiont = openeditem.getdescription();
			description.setText(descriptiont);
			
			String categoryt;
			categoryt = openeditem.getcategory();
			category.setText(categoryt);
			
			String unitt;
			unitt = openeditem.getunit();
			unit.setText(unitt);
			
			addedititem.setOnClickListener(new editItemAction(openeditem));
		}//with id and itemname
		else if (b.size() == 1) {
			addedititem.setOnClickListener(new addItemAction());
		}//with id
		
		// oldClaim = (ListView)findViewById(R.id.evenListView);
		//taken from http://stackoverflow.com/questions/26119284/android-spinner-onnothingselected
		currencytype = (Spinner) findViewById(R.id.currencytype);
		CurrencyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, getDate());
		CurrencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		currencytype.setAdapter(CurrencyAdapter);
		currencytype.setOnItemSelectedListener(new OnItemSelectedListener(){
			  @Override
			             public void onItemSelected(AdapterView<?> parent, View view,
			                      int position, long id) {

			           	currency = parent.getItemAtPosition(position).toString();
			               
			           	

			            	   
			             }
			 
		            @Override
			            public void onNothingSelected(AdapterView<?> parent) {

			             }
		       });
			
		



		
	
	}

	public class editItemAction implements OnClickListener {
		private expenseItem item;

		public editItemAction(expenseItem item) {
			// TODO Auto-generated constructor stub
			this.item = item;
		}
		
		
		public void onClick(View v) {
			itemname = (EditText) findViewById(R.id.edititemname);
			expense= (EditText) findViewById(R.id.editexpenseamount);
			Bundle extras = getIntent().getExtras();
			int temp = extras.getInt("id");
			int itemid = extras.getInt("itemname");
			ClaimListController.getClaimList().getPosition(temp)
					.getPosition(itemid).setName(itemname.getText().toString());
			
			this.getItem().setExpense(expense.getText().toString());
			
			TextView itemtime = (TextView) findViewById(R.id.itemtime);
			TextView itemdate = (TextView) findViewById(R.id.itemdate);
			this.getItem().setTime(itemtime.getText().toString());
			this.getItem().setDate(itemdate.getText().toString());
			
			this.getItem().setunit(currency);
			
			TextView des = (TextView) findViewById(R.id.descriptiontext);
			this.getItem().setdescription(des.getText().toString());
			
			TextView cat = (TextView) findViewById(R.id.categorytext);
			this.getItem().setcategory(cat.getText().toString());
			
			TextView unit = (TextView) findViewById(R.id.textcurrency);
			this.getItem().setunit(unit.getText().toString());
			
			Intent intent = new Intent(AddItemActivity.this,
					ExpenseItemActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("id", temp);
			intent.putExtra("itemname", itemid);
			ClaimListController.saveClaimList();
			startActivity(intent);
		}
		
		private expenseItem getItem() {
			// TODO Auto-generated method stub
			return item;
		}
	}

	public class addItemAction implements OnClickListener {

		
		public void onClick(View v) {
			itemname = (EditText) findViewById(R.id.edititemname);
			Bundle b = getIntent().getExtras();
			int temp = b.getInt("id");
			expenseItem newitem = new expenseItem(itemname.getText().toString());
			ClaimListController.getClaimList().getPosition(temp)
					.addItem(newitem);
			newitem.setExpense(expense.getText().toString());
			
			TextView itemtime = (TextView) findViewById(R.id.itemtime);
			newitem.setTime(itemtime.getText().toString());
		
			TextView itemdate= (TextView) findViewById(R.id.itemdate);
			newitem.setDate(itemdate.getText().toString());
			
			newitem.setunit(currency);
			
			
			Claim storeclaim = ClaimListController.getClaimList().getPosition(
					temp);

//			if (currency == "USD"){
//		
//				storeclaim.setUSD(expense.getText().toString());
//				Toast.makeText(AddItemActivity.this,
//						  storeclaim.getUSD(),
//	                        Toast.LENGTH_SHORT).show();
//
//			}
//			else if (currency == "CAD"){
//				storeclaim.setCAD(expense.getText().toString());
//				
//				
//			}
//			
			TextView des = (TextView) findViewById(R.id.descriptiontext);
			newitem.setdescription(des.getText().toString());
			
			TextView cat = (TextView) findViewById(R.id.categorytext);
			newitem.setcategory(cat.getText().toString());
			
			Intent intent = new Intent(AddItemActivity.this,
					ExpenseItemActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("id", temp);
			ClaimListController.saveClaimList();
			startActivity(intent);
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_item, menu);
        return true;
    }
	
    public void gotomainaction(MenuItem menu){
    	Toast.makeText(this, "Edit Claims", Toast.LENGTH_SHORT).show();
    	Intent intent= new Intent(AddItemActivity.this, MainActivity.class);
    	startActivity(intent);
    }

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub

			showDate(arg1, arg2 + 1, arg3);
		}
	};
    
	private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
		
			showTime(arg1, arg2 + 1);
		}
	};
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.setitemdate:
			DatePickerDialog datePicker = new DatePickerDialog(
					AddItemActivity.this, new OnDateSetListener() {

						@Override
						public void onDateSet(DatePicker view, int currentyear,
								int monthOfYear, int dayOfMonth) {
							// TODO Auto-generated method stub
							Toast.makeText(
									AddItemActivity.this,
									year + "year " + (monthOfYear + 1)
											+ "month " + dayOfMonth + "day",
									Toast.LENGTH_SHORT).show();
							itemdate.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-")
									.append(year).append(" "));
							year = currentyear;
							month = monthOfYear;
							day = dayOfMonth;
							
						}
					}, 2013, 7, 20);
			datePicker.show();
			break;

		case R.id.setitemtime:
			TimePickerDialog time = new TimePickerDialog(
					AddItemActivity.this, new OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int currentminute) {
							// TODO Auto-generated method stub
							Toast.makeText(AddItemActivity.this,
									hourOfDay + "hourOfDay " + minute + "currentminute",
									Toast.LENGTH_SHORT).show();
							itemtime.setText(new StringBuilder().append(hourOfDay).append("-").append(currentminute).append(" "));
							hour = hourOfDay;
							minute = currentminute;
						}
					}, 18, 25, true);
			time.show();
			break;

		}
	}

	private void showDate(int year, int month, int day) {
		itemtime.setText(new StringBuilder().append(hour).append("/")
				.append(minute));
	}
	
	private void showTime(int hour, int minute) {
		itemdate.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
	}
	
	private List<String> getDate() {
		// TODO Auto-generated method stub
		List<String> dataList = new ArrayList<String>();
		 dataList.add("USD");
		 dataList.add("CAD");
		 dataList.add("EUR");
		 dataList.add("GBP");
		 
		return dataList;
	}
}

