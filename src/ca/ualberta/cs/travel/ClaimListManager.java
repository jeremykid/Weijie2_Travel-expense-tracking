package ca.ualberta.cs.travel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ClaimListManager {
	static final String prefFile = "ClaimList";

	static final String slKey = "claimList"; 
	
	Context context;
	
	static private ClaimListManager claimListManager = null;
	
	public static void initManager(Context context) {
		if (claimListManager == null) {
			if (context==null) {
				throw new RuntimeException("missing context for ClaimListManager");
			}
			claimListManager = new ClaimListManager(context);
		}
	}
	
	public static ClaimListManager getManager() {
		if (claimListManager==null) {
			throw new RuntimeException("Did not initialize Manager");
		}
		return claimListManager;
	}
	
	
	public ClaimListManager(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public ClaimList loadClaimList() throws ClassNotFoundException, IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String claimListData = settings.getString(slKey, "");
		if (claimListData.equals("")) {
			return new ClaimList();
		} else {
			return claimListFromString(claimListData);
		}
	}
	
	static public ClaimList claimListFromString(String claimListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(claimListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ClaimList)oi.readObject();
		}

	public void saveClaimList(ClaimList sl) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(slKey, claimListToString(sl));
		editor.commit();
	}

	static public String claimListToString(ClaimList sl) throws IOException{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(sl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
}
