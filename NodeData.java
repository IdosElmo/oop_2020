package ex0;

import java.util.*;


public class NodeData implements node_data
 {
	private List<node_data> neighbors;
	
	//public HashMap<Integer,HashMap<Integer,node_data> > neighbors=new HashMap<>();
	public int key;///will see if can change in future to private
	
//	private Point3D location;
//	private double Weight;///maybe i need change to public or something
	private String info;
	private int tag; //0 = not visited, 1 = grey (currently visiting), 2 = black - visited.

	private static int countKeys = 0;

	
	public NodeData() {
		
		this.key = countKeys++;
		this.info = "";
		this.tag = 0;
		this.neighbors = new ArrayList<node_data>();
	}

	public NodeData(int key) {
		
		this.key = key;
		this.info = "";
		this.tag = 0;
		this.neighbors = new ArrayList<node_data>();
	}

//	@Override
//	public String toString() {
//		return "Node [key=" + key + "  ]";
//	}
	
	@Override
	public int getKey() {
		
		return this.key;
	}

	@Override
	public Collection<node_data> getNi() {
//		return this.myNeighbors.values(); //hashmap
		return this.neighbors;
	}

	@Override
	public boolean hasNi(int key) {
		
//		return this.neighbors.contains(key);
		
		for (node_data node_data : neighbors) {
			if (node_data.getKey() == key)
				return true;
		}
		
		return false;
		
	}

	@Override
	public void addNi(node_data t) {
//		this.myNeighbors.put(t.getKey(), t);
		
		this.neighbors.add(t);
		
	}

	@Override
	public void removeNode(node_data node) {
//		this.myNeighbors.remove(node.getKey());
		
		if (this.neighbors.contains(node))
			this.neighbors.remove(node);
		
	}

	@Override
	public String getInfo() {

		return this.info;
	}

	@Override
	public void setInfo(String s) {
		
		 this.info = s;

	}

	@Override
	public int getTag() {
		 
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		
		this.tag = t;
		
	}
	

}
