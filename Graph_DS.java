package ex0;

import java.util.*;

public class Graph_DS implements  graph 
{
	private HashMap<Integer, node_data> nodes;
	private int edgeSize;
	private int getMC;

	
	public Graph_DS() {
		this.nodes = new HashMap<>();
		this.edgeSize = 0;
		this.getMC = 0;
	}

	@Override
	public node_data getNode(int key) {
		/// Check what happened the node id its not in graph /.need to retuen null 
		
		if(nodes.containsKey(key))
			return nodes.get(key);

		return null;

	}


	@Override
	public boolean hasEdge(int node1, int node2) {
		
		if (node1 == node2) //if im the same vertex - i have an edge to myself
			return true;
		else {
			
			if (nodes.get(node1).hasNi(node2) && nodes.get(node2).hasNi(node1))
				return true;
			else
				return false;
		
		}
//		return this.nodes.get(node1).getNi().contains(this.nodes.get(node2)) ;
		//return this.neighbors.get(node1).containsKey(node2);
	}

	@Override
	public void addNode(node_data n) {
		if(!nodes.containsKey(n.getKey()))
				{
					nodes.put(n.getKey(), n);
					getMC++;
				}
		else {
			System.out.println("This key is taken");

			try {
				throw new  Exception(n.toString()+"already exist");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	//////////////////////help function/////////////////
//	private boolean ifconnect(int node1, int node2) {
//		
//		return false;
//	}
	////////////////////////////////////////////////////////////
	@Override
	public void connect(int node1, int node2) {
		/////////// need to check what to do in case the node/'s I get not in graph 
		////////// in sure i need insert "if .... contaniskey.....
		if (node1 != node2)
		{
			if (nodes.containsKey(node1) && nodes.containsKey(node2)){
				
				node_data n1 = nodes.get(node1);
				node_data n2 = nodes.get(node2);
				
				if(n1 != null && n2 != null) {
					
						if(!hasEdge(node1, node2) && !hasEdge(node2, node1)) {
							
							nodes.get(node1).addNi(n2);
							nodes.get(node2).addNi(n1);///Always.  just because  it unidirectional graph
							getMC++;
							edgeSize++;
					}
				}
			}
//		neighbors.get(node1).put(node2, nodes.get(node2));
//		neighbors.get(node2).put(node2, nodes.get(node2));
		}
	}

	

	@Override
	public Collection<node_data> getV() {
		//Iterator<node_data> iteratorNodes = nodes.values().iterator();
		
		List<node_data> l = new ArrayList<node_data>(nodes.values());
		return l;
//		return this.nodes.values();
	}

	@Override
	public Collection<node_data> getV(int node_id) {
		
		if(nodes.containsKey(node_id))
			return this.nodes.get(node_id).getNi();
		
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		if(nodes.containsKey(key)){
			
			if(nodes.get(key) == null) return null;
			

			for (node_data node : nodes.get(key).getNi()) {
				node.getNi().remove(key);
				getMC++;
				edgeSize--;
			}
			
			node_data nodeWeRemove = this.nodes.remove(key);
			getMC++;
			return nodeWeRemove;

		}
		
		return null;
		
	}


	@Override
	public void removeEdge(int node1, int node2) {
		
		if (node1 != node2) {
			
			if (nodes.containsKey(node1) && nodes.containsKey(node2)) {
				
				if (hasEdge(node1, node2) && hasEdge(node2, node1))	{
					nodes.get(node1).getNi().remove(nodes.get(node2));
					nodes.get(node2).getNi().remove(nodes.get(node1));
					edgeSize--;
					getMC++;
				}
			}
		}
		
		
	}

	@Override
	public int nodeSize() {/// need ask about that
		// TODO Auto-generated method stub
		return getV().size();
	}

	@Override
	public int edgeSize() {/// need ask about that
		
		return this.edgeSize;
	}

	@Override
	public int getMC() {/// need ask about that
		// TODO Auto-generated method stub
		return getMC;
	}

}
