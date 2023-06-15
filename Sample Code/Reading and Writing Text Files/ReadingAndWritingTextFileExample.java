import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

class ReadingAndWritingTextFileExample
{
	public static void main(String[] args)
	{
		if( args.length != 2 )
		{
			System.out.println("format is: ReadingAndWritingTextFileExample \"input file name\" \"output file name\"");
			System.exit(0);
		}
		
		try
		{
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
			
			String inn;
			while( (inn = input.readLine()) != null )
			{
				output.println(inn);
			}
			input.close();
			output.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}

