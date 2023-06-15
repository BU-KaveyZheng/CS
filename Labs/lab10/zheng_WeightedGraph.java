import java.util.*;

class zheng_WeightedGraph implements WeightedGraphFunctions
{
	private final ArrayList<Integer> vertices;
	private final ArrayList<EdgeWithWeight> edges;

	zheng_WeightedGraph()
	{
		vertices = new ArrayList<Integer>();
		edges = new ArrayList<EdgeWithWeight>();
	}

	public boolean hasPath(int fromVertex, int toVertex) { return (Boolean) getPath(fromVertex, toVertex, WeightedGraphReturnType.HAS_PATH); }
	public double getMinimumWeight(int fromVertex, int toVertex) { return (Double) getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_MINIMUM_WEIGHT); }
	public EdgeWithWeight[] getPath(int fromVertex, int toVertex) { return (EdgeWithWeight[]) getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_PATH); }

	private Object getPath(int fromVertex, int toVertex, WeightedGraphReturnType typeOfInfo)
	{
		PriorityQueue<VertexWithWeight> minPriorityQueueByWeight= new PriorityQueue<>(vertices.size(), new VertexWithWeightWeightComparator());
		VertexWithWeight[] vertexCost = new VertexWithWeight[vertices.size()];
		int[] parent = new int[vertices.size()];

		for (int i = 0; i < vertices.size(); i++) 
		{
			parent[i] = -1;
			vertexCost[i] = new VertexWithWeight(vertices.get(i), Double.POSITIVE_INFINITY);
		}
		int fromVertexIndex = vertices.indexOf(fromVertex);
		parent[fromVertexIndex] = fromVertex;
		vertexCost[fromVertexIndex] = new VertexWithWeight(vertices.get(fromVertexIndex), 0.0);

		for (VertexWithWeight v : vertexCost) { minPriorityQueueByWeight.add(v); }

		while (minPriorityQueueByWeight.size() > 0) {
			VertexWithWeight v = minPriorityQueueByWeight.poll();
			int vertex = v.getVertex();

			if (parent[getIndex(vertex)] == -1) break;
			if (vertex == toVertex) break; 

			for (EdgeWithWeight e : edges) 
			{
				if (e.getFromVertex() == vertex) 
				{
					int u = e.getToVertex();
					if (vertexCost[getIndex(vertex)].getWeight() + e.getWeight() < vertexCost[getIndex(u)].getWeight())
					{
						minPriorityQueueByWeight.remove(vertexCost[getIndex(u)]);
						vertexCost[getIndex(u)].setWeight(vertexCost[getIndex(vertex)].getWeight() + e.getWeight());
						minPriorityQueueByWeight.add(vertexCost[getIndex(u)]);
						parent[getIndex(u)] = vertex;
					}
				}
			}
		}

		if(parent[getIndex(toVertex)] != -1)
		{
			if (typeOfInfo == WeightedGraphReturnType.HAS_PATH)  {  return parent[toVertex] != -1; }
			if (typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT)
			{
				int toVertexIndex = vertices.indexOf(toVertex);
				return vertexCost[toVertexIndex].getWeight();
			}
			if (typeOfInfo == WeightedGraphReturnType.GET_PATH)
			{
				ArrayList<Integer> reversePath = new ArrayList<>();
				ArrayList<Integer> forwardPath = new ArrayList<>();

				int p = toVertex;
				reversePath.add(p);
				while (p != fromVertex)
				{
					p = parent[getIndex(p)];
					reversePath.add(p);
				}

				for (int i = reversePath.size() - 1; i >= 0; i--) {  forwardPath.add(reversePath.get(i));  }

				EdgeWithWeight[] ewwArr = new EdgeWithWeight[forwardPath.size() - 1];
				double edgeWeight = 0;
				for (int i = 0; i < forwardPath.size() - 1; i++)
				{
					for (EdgeWithWeight e : edges)
					{
						if (forwardPath.get(i) == e.getFromVertex() && forwardPath.get(i + 1) == e.getToVertex()) edgeWeight = e.getWeight();
					}
					ewwArr[i] = new EdgeWithWeight(forwardPath.get(i), forwardPath.get(i + 1), edgeWeight);
				}
				return ewwArr;
			}
		}

		switch (typeOfInfo)
		{
			case HAS_PATH:
				return Boolean.valueOf(false);
			case GET_MINIMUM_WEIGHT:
				return Double.NaN;
			case GET_PATH:
				return new EdgeWithWeight[0];
			default:
				return null;
		}
	}

	private int getIndex(int i) { return vertices.indexOf(i);}
	
	public boolean addVertex(int v)
	{
		if (!vertices.contains(v)) 
		{ 
			vertices.add(v);
			return true; 
		}
		return false;
	}
	public boolean addWeightedEdge(int from, int to, double weight)
	{
		if (vertices.contains(from) && vertices.contains(to))
		{
			for (EdgeWithWeight e : edges) 
			{
				if (e.getFromVertex() == from && e.getToVertex() == to) { return false; }
			}
			edges.add(new EdgeWithWeight(from, to, weight));
			return true;
		}
		return false;
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
			EdgeWithWeight e = edges.get(i);
			sb.append(e.toString());
			if (i != edges.size() - 1) { sb.append(","); }
		}
		sb.append("}");
		return sb.toString();
	}
}
