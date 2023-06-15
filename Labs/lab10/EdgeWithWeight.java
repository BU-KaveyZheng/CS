class EdgeWithWeight implements EdgeWithWeightFunctions
{
	private int fromVertex, toVertex;
	private double weight;

	EdgeWithWeight(int from, int to, double weight) {
		fromVertex = from;
		toVertex = to;
		this.weight = weight;
	}

	public int getFromVertex() { return fromVertex; }
	public int getToVertex() { return toVertex; }
	public double getWeight() { return weight; }
	public String toString() { return "(" + fromVertex + "," + toVertex + "," + weight + ")"; }
}