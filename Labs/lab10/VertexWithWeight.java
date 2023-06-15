class VertexWithWeight implements VertexWithWeightFunctions
{
	private final int vertex;
	private double weight;

	VertexWithWeight(int v, double w) 
	{
		vertex = v;
		weight = w;
	}

	public int getVertex() { return vertex; }
	public double getWeight() { return weight; }
	public void setWeight(double w) { weight = w; }
	public String toString() { return "(" + vertex + "," + weight + ")"; }
	public boolean equals(Object o) {
		if (o == null) { return false; } 
		if (this == o) { return true; } 
		if (getClass() == o.getClass()) {
			if (vertex == ((VertexWithWeight) o).getVertex()) { return true; }
		}
		return false;
	}
}