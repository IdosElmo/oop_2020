package ex0;


import java.util.*;


public class Graph_Algo implements graph_algorithms {

	
	private HashMap<Integer, Integer> parents; // {n1.key = n2.key, n5.key = n2.key, n2.key = null }
	private HashMap<Integer, Integer> distance;
	
	private graph graph_algo;
	
	
	public Graph_Algo() {

	}
	
	@Override
	public void init(graph g) {
		this.graph_algo = g;
		this.distance = new HashMap<Integer, Integer>();
		this.parents = new HashMap<Integer, Integer>();
	}

	@Override
//	public graph copy() {
//		graph copyGraph=this.graph_algo.
//		return null;
//	}

	public graph copy() 
	{
		Graph_DS new_graph = new Graph_DS();
		
		for (node_data v : this.graph_algo.getV())///getV())////over all nodes in graph
		{
			node_data new_node = new NodeData(v.getKey());
			new_graph.addNode(new_node); 
			
		}
		
		for (node_data v : this.graph_algo.getV()) { //connect all nodes in new graph based on old graph
			
			node_data n = new_graph.getNode(v.getKey());	//o(1)
			
			n.setInfo(v.getInfo());
			n.setTag(v.getTag());
			
			
			for (node_data ni : v.getNi()) {
				
				new_graph.connect(v.getKey(), ni.getKey());
			}
			
		}
		
		return new_graph;	
					
	
}
	
	
	
	
	public void bfs(int src) {
		
		Queue<node_data> queue = new LinkedList<>();
		
		for (node_data node_data : this.graph_algo.getV()) {
			
			node_data.setTag(0); // set color white
			
			if (node_data.getKey() == src)
				distance.put(node_data.getKey(), 0);
			else
				distance.put(node_data.getKey(), -1);
			
		}
		
		queue.add(this.graph_algo.getNode(src));
		
		while (!queue.isEmpty()){
			
			node_data u = queue.poll();
			
			for (node_data ni : u.getNi()) {
				
				if(ni.getTag() == 0) { // if not visited = color white
					
					ni.setTag(1); //set color grey = currently visiting
					
					parents.put(ni.getKey(), u.getKey());
					distance.put(ni.getKey(), distance.get(u.getKey()) + 1);
					
					queue.add(ni);
					
				}
				
			}
			
			u.setTag(2); //visited = black
			
		}
		
	}
	
	
	
	
	
	
	@Override
	public boolean isConnected() {// Check if graph is strongly connected or not
		
		if (this.graph_algo.nodeSize() == 1 || this.graph_algo.nodeSize() == 0) //1 or 0 nodes 
			return true;
		
		List<node_data> list = new ArrayList<>(this.graph_algo.getV());
		bfs(list.get(0).getKey());
		
		for (node_data node : this.graph_algo.getV()) {
			if(node.getTag() == 0) //still colored WHITE
				return false;
		}
		
		return true; //all vertexes visited
	}


		@Override
		public int shortestPathDist(int src, int dest) {
			bfs(src);
			return distance.get(dest);
		}

		
		@Override
		public List<node_data> shortestPath(int src, int dest) {
			
			bfs(src);
			
			List<node_data> result = new ArrayList<>();
			
			int father = dest;
			
			result.add(this.graph_algo.getNode(dest));
			
			while (true) {
				
				result.add(this.graph_algo.getNode(parents.get(father)));
				father = parents.get(father);
				
				if (father == src) break;
			}
			
			return result;
			
		}


	public String toString() {
		return "Graph_Algo [copy()=" + copy() + ", isConnected()=" + isConnected() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
