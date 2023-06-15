class DataInputAndOutputStreamSampleCode
{
	// program to write a double and int to a DataOutputStream and then read back 
	// a double and an int from a DataInputStream
	public static void main(String[] args)
	{
		if( args.length != 3 )
		{
			System.out.println("format is: DataInputAndOutputStreamSampleCode \"filename\" \"double value\" \"int value\"");
			System.exit(0);
		}
		
		// convert the first commandline argument to a double
		double doubleValueFromCommandLine = Double.parseDouble(args[1]);
		
		// convert the second commandline argument to an int
		int intValueFromCommandLine = Integer.parseInt(args[2]);
		
		try
		{
			// open the output file as a DataOutputStream stream
			java.io.DataOutputStream output = new java.io.DataOutputStream(new java.io.FileOutputStream(args[0]));
			
			// write the double
			output.writeDouble(doubleValueFromCommandLine);
			
			// write the int
			output.writeInt(intValueFromCommandLine);
			
			// close the file so everything is written to disk and so we can open it
			output.close();
			
			// open the output file as an input file as a DataInputStream 
			// to read the two values that were just written to it
			java.io.DataInputStream input = new java.io.DataInputStream(new java.io.FileInputStream(args[0]));
			
			// read the double
			double doubleValueReadFromFile = input.readDouble();
			
			// read the int
			int intValueReadFromFile = input.readInt();
			
			// output the inputs from the commandline
			System.out.println("inputs from the commandline");
			System.out.println("doubleValueFromCommandLine = " + doubleValueFromCommandLine);
			System.out.println("intValueFromCommandLine = " + intValueFromCommandLine);
			System.out.println();
			
			// output the inputs from the file
			System.out.println("inputs from the file");
			System.out.println("doubleValueReadFromFile = " + doubleValueReadFromFile);
			System.out.println("intValueReadFromFile = " + intValueReadFromFile);
			
			System.out.println("\n" + "I can't see the screen, but I'm confident that everything matches");
			System.out.println("I guess I could have had the code check if they matched, but then what would you do");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		
	}
}
