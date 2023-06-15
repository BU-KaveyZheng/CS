import java.util.PriorityQueue;
import java.util.Iterator;

class zheng_lab7
{
	public static void main(String[] args)
	{
		PriorityQueue<VertexWithWeight> p = new PriorityQueue<>(new VertexWithWeightComparator());

		VertexWithWeight[] vww = new VertexWithWeight[10];
		vww[0] = new VertexWithWeight(0, 0.0);
		vww[1] = new VertexWithWeight(1, 1.0);
		vww[2] = new VertexWithWeight(2, 2.0);
		vww[3] = new VertexWithWeight(3, 3.0);
		vww[4] = new VertexWithWeight(4, 4.0);
		vww[5] = new VertexWithWeight(5, 0.0);
		vww[6] = new VertexWithWeight(6, 1.0);
		vww[7] = new VertexWithWeight(7, 2.0);
		vww[8] = new VertexWithWeight(8, 3.0);
		vww[9] = new VertexWithWeight(9, 4.0);

		// round 1
		for (VertexWithWeight v : vww) { p.add(v); }

		System.out.println("iterator after initial addition");
		Iterator<VertexWithWeight> iter = p.iterator();
		while (iter.hasNext()) { System.out.println("\t" + iter.next()); }

		System.out.println("toArray after initial addition");
		VertexWithWeight[] priorityArr = p.toArray(new VertexWithWeight[0]);
		for (VertexWithWeight v : priorityArr) {
			System.out.println("\t" + v);
		}

		System.out.println("polling after initial addition");
		while (!p.isEmpty()) { System.out.println("\t" + p.poll()); }


		System.out.print("\n\n\n");
		// round 2
		for (VertexWithWeight v : vww) { p.add(v); }
		vww[0].setWeight(10);

		System.out.println("iterator after modification");
		iter = p.iterator();
		while (iter.hasNext()) { System.out.println("\t" + iter.next()); }

		System.out.println("toArray after modification");
		priorityArr = p.toArray(new VertexWithWeight[0]);
		for (VertexWithWeight v : priorityArr) {
			System.out.println("\t" + v);
		}

		System.out.println("polling after modification");	
		while (!p.isEmpty()) { System.out.println("\t" + p.poll()); }
	
	
		System.out.print("\n\n\n");
		// round 3
		vww[0].setWeight(0);
		for (VertexWithWeight v : vww) { p.add(v); }
		p.remove(vww[0]);
		vww[0].setWeight(10);
		p.add(vww[0]);

		System.out.println("polling after deletion, modification, and addition");
		while (!p.isEmpty()) { System.out.println("\t" + p.poll()); }
	}
}