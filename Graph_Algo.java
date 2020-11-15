package ex1;

import java.util.*;

//import ex1.WGraph_DS.node;

public class WGraph_Algo implements weighted_graph_algorithms {
	
	
	weighted_graph graph = null;
	
	private HashMap<Integer, Double> dist;
	private HashMap<Integer, Integer> prev;
	
	/**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
	@Override
	public void init(weighted_graph g) {
		this.graph = g;
		this.dist = new HashMap<>();
		this.prev = new HashMap<>();
	}

    /**
     * Return the underlying graph of which this class works.
     * @return
     */
	@Override
	public weighted_graph getGraph() {
		return this.graph;
	}

    /**
     * Compute a deep copy of this weighted graph.
     * @return
     */
	@Override
	public weighted_graph copy() {

		if (this.graph != null) {
		
			weighted_graph new_graph = new WGraph_DS();
			
			for (node_info node : this.graph.getV()) {
				
				new_graph.addNode(node.getKey()); //deep copy of nodes
				
				node_info n = new_graph.getNode(node.getKey());
				n.setInfo(node.getInfo());
				n.setTag(node.getTag());
			}
			
			for (node_info node : this.graph.getV()) {
				
				for (node_info ni : this.graph.getV(node.getKey())) {
					
					new_graph.connect(node.getKey(), ni.getKey(), this.graph.getEdge(node.getKey(), ni.getKey()));
				}
				
			}
			
			return new_graph;
		
		}
		
		return null;
	}

	
//	PriorityQueue<Integer> q = new PriorityQueue<>();
//	
//	q.add(15);
//	q.add(20):
//	q.add(10);
//	
//	q.peek() // 10
//	q.poll() // 10
//	q.peek() // 15
	
	
	
	public void djikstra(int source) {
		//google "djikstra psuedo code" (wikipedia)
		//https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
		
		if (this.graph != null) {
			
			PriorityQueue<node_info> q = new PriorityQueue<>();
			//HashMap<Integer, node_info> q = new HashMap<>();
			
			for (node_info node : this.graph.getV()) {
				
				if (node.getKey() == source) {
					
					this.dist.put(source, 0.0);
					node.setTag(0);
				}
				else {
					
					node.setTag(Double.MAX_VALUE);
					this.dist.put(node.getKey(), Double.MAX_VALUE);
					this.prev.put(node.getKey(), null);
				}
				
				q.add(node);
				//add to map
				
			}
			
			while (!q.isEmpty()){
				
				node_info u = q.poll(); // min_tag(q)
				//find_min_in_map();
				
				for (node_info ni : this.graph.getV(u.getKey())) {
					
					Double alt = this.dist.get(u.getKey()) + this.graph.getEdge(u.getKey(), ni.getKey());
					
					if (alt < this.dist.get(ni.getKey())) {
						
						this.dist.put(ni.getKey(), alt);
						this.prev.put(ni.getKey(), u.getKey());
						
						//q.decrease_key();
						
						ni.setTag(alt);
						
						q.add(ni);
						
//						if(u1 > u2) -> u2
						
					}
					
				}
				
			}
			
		}
		
	}
	
	
    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     * @return
     */
	@Override
	public boolean isConnected() {
//		djikstra(source);
		
		if (this.graph.nodeSize() == 0 || this.graph.nodeSize() == 1)
			return true; //0 or 1 nodes in graph = connected
		
		List<node_info> list = new ArrayList<>(this.graph.getV());
		djikstra(list.get(0).getKey());
		
		for (Double dist : this.dist.values()) {
			if (dist == Double.MAX_VALUE)
				return false;
		}
		
		return true;
	}

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
	@Override
	public double shortestPathDist(int src, int dest) {
		djikstra(src);
		return this.dist.get(dest);
	}

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
	@Override
	public List<node_info> shortestPath(int src, int dest) {
		djikstra(src);
		
		List<node_info> result = new ArrayList<>();
		
		int key = dest;
		
		result.add(this.graph.getNode(key)); //add dest to result
		
		while (true) {
			
			result.add(this.graph.getNode(this.prev.get(key)));
			key = this.prev.get(key);
			
			if (key == src)
				break;
			
		}
		
		List<node_info> back_res = new ArrayList<>();
		
		for (int i = result.size() - 1; i >= 0; i--) {
			back_res.add(result.get(i));
		}
		
		return back_res;
	}

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
	@Override
	public boolean save(String file) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
	@Override
	public boolean load(String file) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
