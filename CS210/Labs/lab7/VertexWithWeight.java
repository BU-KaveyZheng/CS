class VertexWithWeight 
{
	private final Integer vertex;
	private Double weight;

	VertexWithWeight(int v, double w) {
		vertex = v;
		weight = w;
	}

	public Integer getVertex() { return vertex; }
	public Double getWeight() { return weight; }
	public void setWeight(double w) { weight = w;}
	public String toString() { return "(" + vertex + "," + weight + ")"; }
	public boolean equals(Object o) {
		if (o == null) { return false; } 
		if (this == o) { return true; } 
		if (getClass() == o.getClass()) {
			if (vertex.intValue() == ((VertexWithWeight) o).getVertex().intValue()) { return true; }
		}
		return false;
	}
}