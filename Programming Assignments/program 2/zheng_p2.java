import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

class zheng_p2
{
	public static void main(String[] args)
	{
		if (args.length < 3)
		{
			System.out.println("format is: \"conversion type\" \"input file name\" \"output file name\"");
			System.exit(0);
		}

		// puts some code here to check that the first commandline argument starts with "b" or "t"	
		if (args[0].startsWith("b")) { readBinarytoText(args[1], args[2]); }
		else if (args[0].startsWith("t")) { readTexttoBinary(args[1], args[2]); }
		else {
			System.out.println("format is: \"conversion type: b2t or t2b\"");
			System.exit(0);
		}
	}

	public static void readBinarytoText(String inputFilename, String outputFilename)
	{
		try
		{
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFilename));
			PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));

			byte[] buffer = new byte[20];
			ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

			input.read(buffer, 0, 4);
			int numOfBlocks = byteBuffer.getInt(0);
			int length;

			for (int i = 0; i < numOfBlocks; i++) {
				input.read(buffer, 0, 2);
				char dataType = byteBuffer.getChar(0);

				switch (dataType) {
					case 'i':
						output.print("int\t");

						input.read(buffer, 0, 4);
						output.println(byteBuffer.getInt(0));
						break;

					case 'l':
						output.print("long\t");

						input.read(buffer, 0, 8);
						output.println(byteBuffer.getLong(0));
						break;

					case 'h':
						output.print("short\t");
						
						input.read(buffer, 0, 2);
						output.println(byteBuffer.getShort(0));
						break;

					case 'f':
						output.print("float\t");

						input.read(buffer, 0, 4);
						output.println(byteBuffer.getFloat(0));
						break;

					case 'd':
						output.print("double\t");

						input.read(buffer, 0, 8);
						output.println(byteBuffer.getDouble(0));
						break;

					case 'a':
						output.print("int array\t");

						input.read(buffer, 0, 4);
						length = byteBuffer.getInt(0);
						for (int j = 0; j < length; j++) {
							input.read(buffer, 0, 4);
							
							if (j != 0) output.print(",");

							output.print(byteBuffer.getInt(0));
						}
						output.println();
						break;
					
					case 'e':
						output.print("double array\t");

						input.read(buffer, 0, 4);
						length = byteBuffer.getInt(0);
						for (int j = 0; j < length; j++) {
							input.read(buffer, 0, 8);

							if (j != 0) output.print(",");

							output.print(byteBuffer.getDouble(0));
						}
						output.println();
						break;

					default: // String
						output.print("string\t");

						input.read(buffer, 0, 4);
						length = byteBuffer.getInt(0);
						for (int j = 0; j < length; j++) {
							input.read(buffer, 0, 2);
							output.print(byteBuffer.getChar(0));
						}
						output.println();
				}
			}
			input.close();
			output.close();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}

	public static void readTexttoBinary(String inputFilename, String outputFilename)
	{
		try
		{
			// put your code to read a text file and output it as a binary file
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilename)));
			DataOutputStream output = new DataOutputStream(new FileOutputStream(outputFilename));

			ArrayList<String> inputLines = new ArrayList<>();
			String inn;
			while((inn = input.readLine()) != null ) { inputLines.add(inn); }
			output.writeInt(inputLines.size());
			//output.write(System.lineSeparator().getBytes());

			for (String s : inputLines)
			{
				StringTokenizer st = new StringTokenizer(s, "\t");
				while (st.hasMoreTokens())
				{
					String dataType = st.nextToken();
					String data = st.nextToken();
					StringTokenizer dataTokenizer = new StringTokenizer(data, ",");

					switch (dataType) {
						case "int":
							output.writeChar('i');
							output.writeInt(Integer.parseInt(data));
							break;

						case "double":
							output.writeChar('d');
							output.writeDouble(Double.parseDouble(data));
							break;

						case "float":
							output.writeChar('f');
							output.writeFloat(Float.parseFloat(data));
							break;

						case "long":
							output.writeChar('l');
							output.writeLong(Long.parseLong(data));
							break;

						case "short":
							output.writeChar('h');
							output.writeShort(Short.parseShort(data));
							break;

						case "int array":
							output.writeChar('a');

							output.writeInt(dataTokenizer.countTokens());
							while (dataTokenizer.hasMoreTokens()) {
								output.writeInt(Integer.parseInt(dataTokenizer.nextToken()));
							}
							break;

						case "double array":
							output.writeChar('e');
						
							output.writeInt(dataTokenizer.countTokens());
							while (dataTokenizer.hasMoreTokens()) {
								output.writeDouble(Double.parseDouble(dataTokenizer.nextToken()));
							}
							break;

						default: // String
							output.writeChar('s');
							output.writeInt(data.length());

							for (int i = 0; i < data.length(); i++) {
								output.writeChar(data.charAt(i));
							}
					}
				}
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