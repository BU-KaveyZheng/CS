import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SubsetOfJunitTestsForLab6
{	
	// sample_directed_graph_1.txt
	String directedAndConnectedVertics = "{1,3,2,4,5,1,2}";
	String directedAndConnectedEdges = "{(1,4),(2,1),(2,3),(3,5),(4,5),(5,2)}";
	
	// sample_directed_graph_2.txt
	String directedAndNotConnectedVertics = "{1,3,2,4,5,1,2}";
	String directedAndNotConnectedEdges = "{(1,4),(2,1),(2,3),(3,5),(4,5),(0,1),(0,7),(1,7)}";
	
	// sample_undirected_graph_1.txt
	String undirectedAndConnectedVertics = "{0,1,3,2,4,5,6,7,8,9,0,2}";
	String undirectedAndConnectedEdges = "{(0,5),(1,7),(2,4),(3,6),(4,9),(5,8),(6,9),(7,9),(8,9),(5,0)}";
	
	// sample_undirected_graph_2.txt
	String undirectedAndNotConnectedVertics = "{0,1,3,2,4,5,6,7,8,9,0,2}";
	String undirectedAndNotConnectedEdges = "{(0,5),(1,7),(2,4),(4,9),(5,8),(6,9),(7,9),(8,9),(5,0)}";
	
	garrison_Graph g;
	
	@BeforeEach
	public void setUpBeforeEach() throws Exception 
	{
		g = new garrison_Graph();
	}
	
	// add vertices 0 - 100, then add edges (i,i+1) i = 0, ..., 99,
	// verify the number of edges is correct after each one is added
	@Test
	void testGetNumberOfEdges() 
	{
		int[] getNumberOfEdges = new int[101];
		int[] expectedResultGetNumberOfEdges = new int[getNumberOfEdges.length];
		getNumberOfEdges[0] = g.getNumberOfEdges();
		expectedResultGetNumberOfEdges[0] = 0;
		for( int i = 0; i < getNumberOfEdges.length; i++ )
		{
			try
			{
				g.addVertex(i);
			}
			catch(GraphException e)
			{
			}
		}
		
		for( int i = 1; i < getNumberOfEdges.length; i++ )
		{
			expectedResultGetNumberOfEdges[i] = i;
			try
			{
				g.addEdge(i-1, i);
				getNumberOfEdges[i] = g.getNumberOfEdges();
				
			}
			catch(GraphException e)
			{
			}
		}
		
		assertArrayEquals(getNumberOfEdges, expectedResultGetNumberOfEdges);
	}
	
	// test isConnected() recognizes sample_undirected_graph_1.txt is connected
	@Test
	void testIsConnectedUndirectedAndConnected() 
	{
		g = new garrison_Graph(false);
		java.util.StringTokenizer st = new java.util.StringTokenizer(undirectedAndConnectedVertics, "{},");
		while( st.hasMoreTokens() )
		{
			int newVertex = Integer.parseInt(st.nextToken());
			
			try
			{
				g.addVertex(newVertex);
			}
			catch(GraphException e)
			{
			}
		}
		
		st = new java.util.StringTokenizer(undirectedAndConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while( st.hasMoreTokens() )
		{
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			try
			{
				g.addEdge(from, to);
			}
			catch(GraphException e)
			{
			}
		}
		
		assertEquals(true, g.isConnected());
	}
	
	// test isConnected() recognizes sample_undirected_graph_2.txt is not connected
	@Test
	void testIsConnectedUndirectedAndNotConnected() 
	{
		g = new garrison_Graph(false);
		java.util.StringTokenizer st = new java.util.StringTokenizer(undirectedAndNotConnectedVertics, "{},");
		while( st.hasMoreTokens() )
		{
			int newVertex = Integer.parseInt(st.nextToken());
			
			try
			{
				g.addVertex(newVertex);
			}
			catch(GraphException e)
			{
			}
		}
		
		st = new java.util.StringTokenizer(undirectedAndNotConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while( st.hasMoreTokens() )
		{
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			try
			{
				g.addEdge(from, to);
			}
			catch(GraphException e)
			{
			}
		}
		
		assertEquals(false, g.isConnected());
	}
	
	// add 100 vertices twice and verify that the number of vertices 
	// is correct each time one is added
	@Test
	void testAddVertex() 
	{
		int[] numberOfVertices = new int[101];
		int[] expectedNumberOfVertices = new int[numberOfVertices.length];
		numberOfVertices[0] = g.getNumberOfVertices();
		expectedNumberOfVertices[0] = 0;
		for( int i = 0; i < numberOfVertices.length-1; i++ )
		{
			expectedNumberOfVertices[i+1] = i+1;
			try
			{
				g.addVertex(i);
				numberOfVertices[i+1] = g.getNumberOfVertices();
				g.addVertex(i);
			}
			catch(Exception e)
			{
			}
		}
		assertArrayEquals(numberOfVertices, expectedNumberOfVertices);
	}
	
	// try to add vertex 0 twice, verify there is an exception on the second one
	@Test
	void testGraphExceptionForDuplicateVertex()
	{
		try
		{
			g.addVertex(0);
		}
		catch(GraphException e)
		{
		}
		assertThrows(GraphException.class, () -> g.addVertex(0));
	}
}
