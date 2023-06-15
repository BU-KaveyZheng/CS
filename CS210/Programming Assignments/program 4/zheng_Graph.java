import java.util.*;

public class zheng_Graph implements ConnectedGraphFunctions
{
	private final ArrayList<Integer> vertices;
	private final ArrayList<Edge> edges;
	private final boolean isDirected;
	
	public zheng_Graph() { this(false);}
	public zheng_Graph(boolean isDirected) 
	{
		vertices = new ArrayList<Integer>();
		edges = new ArrayList<Edge>();
		this.isDirected = isDirected;
	}
	
	public int getNumberOfVertices() { return vertices.size(); }
	
	public int getNumberOfEdges() { return edges.size(); }
	
	public boolean isDirected() { return isDirected; }
	
	public void addVertex(int v) throws GraphException
	{
		if (!vertices.contains(v)) { vertices.add(v); }
		else { throw new GraphException("Duplicate Vertex"); }
	}
	
	public void addEdge(int from, int to) throws GraphException
	{
		if (vertices.contains(from) && vertices.contains(to))
		{
			for (Edge e : edges) 
			{
				if ((e.fromVertex() == from && e.toVertex() == to) || 
					(!isDirected && (e.fromVertex() == to && e.toVertex() == from)))
				{ throw new GraphException("Duplicate Edge"); }
			}
			edges.add(new Edge(from, to));
		}
		else { throw new GraphException("Invalid Edge"); }
	}
	
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("G = (V, E)\n");
		sb.append("V = {");
		for (int i = 0; i < vertices.size(); i++) {
			sb.append((vertices.get(i)).toString());
			if (i != vertices.size() - 1) { sb.append(","); }
		}
		sb.append("}\n");
		sb.append("E = {");
		for (int i = 0; i < edges.size(); i++) {
			Edge e = edges.get(i);
			sb.append("(" + (e.fromVertex()).toString() + "," + e.toVertex().toString() + ")");
			if (i != edges.size() - 1) { sb.append(","); }
		}
		sb.append("}");
		return sb.toString();
	}
	
	private boolean isConnected(ArrayList<Edge> edges) 
	{
		HashSet<Integer> connectedSubset = new HashSet<Integer>();
		ArrayDeque<Integer> newlyAddedVertices = new ArrayDeque<Integer>();
		connectedSubset.add(vertices.get(0));
		newlyAddedVertices.add(vertices.get(0));
		
		while(!newlyAddedVertices.isEmpty())
		{
			int currentVertex = newlyAddedVertices.pollFirst();
			for (Edge e : edges)
			{
				if (e.fromVertex() == currentVertex && !connectedSubset.contains(e.toVertex()))
				{
					connectedSubset.add(e.toVertex());
					newlyAddedVertices.add(e.toVertex());	
				}
				if (!isDirected)
				{
					if(e.toVertex() == currentVertex && !connectedSubset.contains(e.fromVertex()))
					{
						connectedSubset.add(e.fromVertex());
						newlyAddedVertices.add(e.fromVertex());
					}
				}
			}
		}
		return connectedSubset.size() == vertices.size();
	}
	
	public boolean isConnected() 
	{
		if (isConnected(edges))
		{
			if(!isDirected) { return true; }
			else {
				ArrayList<Edge> reversedEdges = new ArrayList<>();
				for(Edge e : edges) { reversedEdges.add(e); }
				if(isConnected(reversedEdges)) { return true; }
			}
		}
		return false;
	}
}
