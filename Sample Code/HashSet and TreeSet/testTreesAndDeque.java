import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayDeque;

// program to show difference between HashSet, TreeSet, ArrayDeque as queue, ArrayDeque as stack
class TestTreesAndDeque
{
	public static void main(String[] args)
	{
		if( args.length == 0 )
		{
			System.out.println("format is: TestTreesAndDeque \"int sample values\"");
			System.exit(0);
		}
		
		HashSet<Integer> hashSet = new HashSet<>();
		TreeSet<Integer> treeSet = new TreeSet<>();
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		for( int i = 0; i < args.length; i++ )
		{
			Integer v = Integer.valueOf(args[i]);
			hashSet.add(v);
			treeSet.add(v);
			queue.addLast(v);
			stack.push(v);
		}
		
		// output the HashSet
		System.out.println("HashSet");
		for( Integer v : hashSet )
		{
			System.out.println(v);
		}
		System.out.println();
		
		// output the TreeSet
		System.out.println("TreeSet");
		for( Integer v : treeSet )
		{
			System.out.println(v);
		}
		System.out.println();
		
		// output the ArrayDeque as a queue
		System.out.println("ArrayDeque as queue");
		while( queue.size() > 0 )
		{
			Integer v = queue.pollFirst();
			System.out.println(v);
		}
		System.out.println();
		
		// output the ArrayDeque as a stack
		System.out.println("ArrayDeque as stack");
		while( stack.size() > 0 )
		{
			Integer v = stack.pop();
			System.out.println(v);
		}
		System.out.println();
		
		
	}
}
