package ca.ualberta.cs.travel;

import java.util.ArrayList;
import java.util.Collection;

import android.content.ClipData.Item;

public class ItemList {
	protected ArrayList<Item> itemList;
	protected ArrayList<Listener> listeners;
	
	
	public ItemList(){
		itemList = new ArrayList<Item>();
		listeners = new ArrayList<Listener>();
	}
		
	public Collection<Item> getItems() {
		// TODO Auto-generated method stub
		return itemList;
	}

	public void addItem(Item testItem) {
		// TODO Auto-generated method stub
		
		itemList.add(testItem);
		notifyListeners();
	}

	private void notifyListeners() {
		// TODO Auto-generated method stub
		for (Listener listener  : listeners) {
			listener.update();
		}
	}

	public void removeItem(Item testItem) {
		
		itemList.remove(testItem);
		notifyListeners();
	}

	public Item chooseItem() throws EmptyItemListException{
		int size =itemList.size();
		if (size <= 0){
			throw new EmptyItemListException();
			
		}
		int index = (int) (itemList.size() * Math.random());
		return itemList.get(index);
	}

	public int size() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	public boolean contains(Item testItem) {
		
		return itemList.contains(testItem);
	}

	public void addListener(Listener l) {
		listeners.add(l);
			
	}

	public void removeListener(Listener l) {
		// TODO Auto-generated method stub
		listeners.remove(l);
	}

	
}


