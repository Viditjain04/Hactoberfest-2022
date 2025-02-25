public class kruskalAlgo {
	// node class is to represent node
	class Node 
	{
		 int u,v, weight;
	    
	    Node(int _u, int _v, int _w) { u = _u; v = _v; weight = _w; }
	   
	}
  
	// 
	
	class SortComparator implements Comparator<Node> { // comparator to return min object by weight
		public int compare(Node node1, Node node2) 
	    { 
	        if (node1.weight < node2.weight) 
	            return -1; 
	        if (node1.weight>node2.weight) 
	            return 1; 
	        return 0; 
	   

	    } 
	} 

	class Main
	{
		// i have used disjoint set data structure to implement kruskal algo
		// finPar fun is to find the parent 
		private int findPar(int u, int parent[]) {
			if(u==parent[u]) return u;
			return parent[u] = findPar(parent[u], parent); 
		}
		// union fun is to union of two nodes u ,v
		// rank used because to check which one has more depth 
		private void union(int u, int v, int parent[], int rank[]) {
			u = findPar(u, parent); 
			v = findPar(v, parent);
			if(rank[u] < rank[v]) {
				// if rank of v is greater that means v should be parent 
	        	parent[u] = v;
	        }
	        else if(rank[v] < rank[u]) {
				// if rank of u is greater that means u should be parent 
	        	parent[v] = u; 
	        }
	        else {
				// if rank of v and u is same that means anyone could be parent , but rank will increase
	        	parent[v] = u;
	        	rank[u]++; 
	        }
		}
		// here the algo starts
	    void KruskalAlgo(ArrayList<Node> adj, int N)
	    {
	    	// first sort the edges by weight , we are doing sorting so we have used linear ds
	        Collections.sort(adj, new SortComparator());
	        int parent[] = new int[N];  // to store parent of every node
	        int rank[] = new int[N];    // to store rank of every node

	        for(int i = 0;i<N;i++) {
	        	parent[i] = i; // initially every node would be parent of itself
	        	rank[i] = 0;  // initially every node's rank would be 0 (depth)
	        }
        
	        int costMst = 0; // total min cost to make MST
	        ArrayList<Node> mst = new ArrayList<Node>();
	        for(Node it: adj) {
	        	
	        	if(findPar(it.u, parent) != findPar(it.v, parent)) {
	        		costMst += it.weight; 
	        		mst.add(it); 
	        		union(it.u, it.v, parent, rank); 
	        	}
	        } 
	        System.out.println(costMst);
	        for(Node it: mst) {
	        	System.out.println(it.u + " " +it.v); 
	        }
	    }
	  
	}
}
