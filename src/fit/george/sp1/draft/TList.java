package fit.george.sp1.draft;


import java.util.ArrayList;
import java.util.List;


public class TList {
	
	private List<SimpleCreep> list;
	public static TList list_instance;

	
	
	public TList() {
		list = new ArrayList<SimpleCreep>();
		TList.list_instance = this;
	}
	
	
	
	public void add(SimpleCreep creep) {
		synchronized(this) {
			list.add(creep);
		}
	}
	
	
	
	public SimpleCreep remove(int i) {
		SimpleCreep tmp;
		synchronized(this) {
			tmp = list.remove(i);
		}
		return tmp;
	}
	
	
	
	public int size() {
		int s = -1;
		synchronized(this) {
			s = list.size();
		}
		return s;
	}
	
	
	
	public boolean isEmpty() {
		boolean s = false;
		synchronized(this) {
			s = list.isEmpty();
		}
		return s;
	}
	
	
	
	public SimpleCreep get(int i) {
		SimpleCreep tmp;
		synchronized(this) {
			tmp = list.get(i);
		}
		return tmp;
	}
	
	

}

