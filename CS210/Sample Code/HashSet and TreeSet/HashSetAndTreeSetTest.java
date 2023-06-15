class HashSetAndTreeSetTest
{
	// program to show
	// 1) HashSet does not guarantee order
	// 2) TreeSet has guaranteed order
	// 3) Example of enhanced for loop for HashSet & TreeSet
	// 4) Example of Iterator for HashSet & TreeSet
	// 5) Example to toArray for HashSet & TreeSet
	public static void main(String[] args)
	{
		if( args.length != 2 )
		{
			System.out.println("format: HashSetAndTreeSetTest \"number of values\" \"max value\"");
			System.exit(0);
		}
		
		java.util.HashSet<Integer> hashSet = new java.util.HashSet<>();
		java.util.TreeSet<Integer> treeSet = new java.util.TreeSet<>();
		java.util.Random rand = new java.util.Random();
		int numberOfValues = Integer.parseInt(args[0]);
		int maxValue = Integer.parseInt(args[1]);
		
		// fill the HashSet and TreeSet with pseudo random values
		for( int i = 0; i < numberOfValues; i++ )
		{
			Integer v = Integer.valueOf(rand.nextInt(maxValue));
			hashSet.add(v);
			treeSet.add(v);
		}
		
		// enhanced loop over HashSet
		System.out.println("enhanced for loop over hashSet values (not necessarily sorted in integer value)");
		for( Integer currentIntegerValue : hashSet )
		{
			System.out.println(currentIntegerValue);
		}
		System.out.println();
		
		// enhanced loop over TreeSet
		System.out.println("enhanced for loop over treeSet values (sorted by integer value)");
		for( Integer currentIntegerValue : treeSet )
		{
			System.out.println(currentIntegerValue);
		}
		System.out.println();
		
		// iterator loop over HashSet
		java.util.Iterator<Integer> hashSetIterator = hashSet.iterator();
		System.out.println("iterator over hashSet values (not necessarily sorted in integer value)");
		while( hashSetIterator.hasNext() ) 
		{
			System.out.println(hashSetIterator.next());
		}
		System.out.println();
		
		// iterator loop over TreeSet
		java.util.Iterator<Integer> treeSetIterator = treeSet.iterator();
		System.out.println("iterator over treeSet values (sorted in integer value)");
		while( treeSetIterator.hasNext() ) 
		{
			System.out.println(treeSetIterator.next());
		}
		System.out.println();
		
		// toArray for HashSet
		Integer[] hashSetArray = hashSet.toArray(new Integer[0]);
		System.out.println("toArray output over hashSet values (not necessarily sorted in integer value)");
		for( int i = 0; i < hashSetArray.length; i++ )
		{
			System.out.println(hashSetArray[i]);
		}
		
		// toArray for TreeSet
		Integer[] treeSetArray = treeSet.toArray(new Integer[0]);
		System.out.println("toArray output over treeSet values (sorted in integer value)");
		for( int i = 0; i < treeSetArray.length; i++ )
		{
			System.out.println(treeSetArray[i]);
		}
	}
}
