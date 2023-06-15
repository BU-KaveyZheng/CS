import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.StringTokenizer;

class zheng_lab1
{
	public static void main(String[] args)
	{
		// check if there are any commandline arguements, and if none output a message and exit
		if( args.length < 1 )
		{
			System.out.println("format is: ReadTextFileAndParseTokensExample \"input file name\"");
			System.exit(0);
		}

		// create the DecimalFormat 
		java.text.DecimalFormat df = new java.text.DecimalFormat("###,###,###,###,###");
		
		// try/catch block to catch any exceptions related to the BufferedReader
		try
		{
			// open the BufferedReader
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			
			int lineCount = 0;
			int tokenCount = 0;
			int intCount = 0;
			long intSum = 0;
			int[] stringCount = new int[args.length];

			// loop to read the input file
			String inn;
			while((inn = input.readLine()) != null)
			{
				// create the StringTokenizer
				StringTokenizer st = new StringTokenizer(inn);
				tokenCount += st.countTokens();
				lineCount++;

				// loop over the tokens of the StringTokenizer
				while(st.hasMoreTokens())
				{
					String s = st.nextToken();

					// try/catch block for checking if the current token is an integer
					try
					{
						// convert the current token into an int
						// increment int counter and int sum
						intSum += Long.parseLong(s);
						intCount++;
					}
					catch(Exception e)
					{
						// you shouldn't need anything here
						// if the current token is not an int, the Integer.parseInt() statement will throw an exception and come here
					}
					// loop over the commandline arguments with indices 1, 2, 3, ... , args.length-1
					// and compare each to the current token using s1.equalsIgnoreCase(s2) as mentioned in the lab description
					for (int i = 1; i < args.length; i++)
					{
						if (s.equalsIgnoreCase(args[i])) stringCount[i]++;
					}
				}
			}
			// close the BufferedReader
			input.close();
			// output the results
			System.out.println("lineCount = " + df.format(lineCount));
			System.out.println("tokenCount = " + df.format(tokenCount));
			System.out.println("intCount = " + df.format(intCount));
			System.out.println("intSum = " + df.format(intSum));

			for (int i = 1; i < args.length; i++) {
				System.out.println(args[i] + " count = " + stringCount[i]);
			}
		}
		catch(Exception e)
		{
			// output the exception if there is one and exit
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}

