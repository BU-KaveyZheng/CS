class ReadAndWriteBinaryFilesOneByteAtATimeWithBuffering
{
	// sample program using BufferedInputStream & BufferedOutputStream to read & write binary files one byte at a time
	public static void main(String[] args)
	{
		if( args.length < 2 )
		{
			System.out.println("format: ReadAndWriteBinaryFilesOneByteAtATimeWithBuffering \"input file\" \"size of buffer\" \"<output file>\"");
			System.exit(0);
		}
		
		try
		{
			int bufferSize = Integer.parseInt(args[1]);
			java.io.BufferedInputStream input;
			if( bufferSize == 0 )
			{
				input = new java.io.BufferedInputStream(new java.io.FileInputStream(args[0]));
			}
			else
			{
				input = new java.io.BufferedInputStream(new java.io.FileInputStream(args[0]), bufferSize);
			}
			java.io.BufferedOutputStream output = null;
			boolean copyFile = false;
			if( args.length > 2 )
			{
				if( bufferSize == 0 )
				{
					output = new java.io.BufferedOutputStream(new java.io.FileOutputStream(args[2]));
				}
				else
				{
					output = new java.io.BufferedOutputStream(new java.io.FileOutputStream(args[2]), bufferSize);
				}
				copyFile = true;
			}
			
			long t0 = System.currentTimeMillis();
			int currentByte;
			int byteCount = 0;
			while( (currentByte = input.read()) >= 0 )
			{
				byteCount = byteCount+1;
				
				// using the if versus not doing a test did not have much of an 
				// effect the time to copy the file 
				if( copyFile )
				{
					output.write(currentByte);
				}
			}
			System.out.println("byteCount = " + byteCount);
			
			input.close();
			if( args.length > 2 )
			{
				output.close();
			}
			
			long t1 = System.currentTimeMillis();
			System.out.println("total time " + ((t1-t0)/1000.0) + " seconds");
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}
