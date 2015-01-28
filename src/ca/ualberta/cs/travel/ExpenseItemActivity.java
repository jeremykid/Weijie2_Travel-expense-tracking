package ca.ualberta.cs.travel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ExpenseItemActivity extends Activity {

	private static final String FILENAME = "file.sav";//static means shared by all classes. final means cannot change
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayAdapter<Item> adapter;
	private ArrayList<Item> claim;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expenseitem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_item, menu);
		return true;
	}

	
	
	private ArrayList<Item> loadFromFile() {
		Gson gson = new Gson();
		
		ArrayList<Item> claim = new ArrayList<Item>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			// taken from
			// http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/index.html
			java.lang.reflect.Type typeOfT = new TypeToken<ArrayList<String>>() {
			}.getType();
			// TypeToken<ArrayList<String>> typeOfT = new
			// TypeToken<ArrayList<String>>() {}.getType();
			claim = gson.fromJson(in, typeOfT);
			/*
			 * String line = in.readLine(); while (line != null) {
			 * tweets.add(line); line = in.readLine(); }
			 */
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return tweets.toArray(new String[tweets.size()]);
		return claim;
	}
	
	private void saveInFile(String text, int expense) {
		// to use Gson
		Gson gson = new Gson();
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0);// opening write
																// will over
																// write
			// method of activity class
			// Context.MODE_APPEND);
			// fos.write(new String(date.toString() + " | " + text)
			// .getBytes());
			OutputStreamWriter osw = new OutputStreamWriter(fos); // pass the
																	// fos to
																	// osw then
																	// we use
																	// gson
			gson.toJson(claim, osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void addanitemaction(View v) {
		Bundle extras = getIntent().getExtras();
		//int temp = extras.getInt("id");
		Toast.makeText(this, "Add A Item", Toast.LENGTH_SHORT).show();
		
		ClaimListController st = new ClaimListController();
		Intent intent = new Intent(ExpenseItemActivity.this, AddItemActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//intent.putExtra("id", temp);
    	startActivity(intent);

	}
}
