class makeMiniBankAccount
{
	public static void main(String[] args)
	{
		if( args.length != 4 )
		{
			System.out.println("format: makeMiniBankAccount \"include MiniBankAccount\" \"include MiniBankAccountSync\" \"include MiniBankAccountSemaphore\" \"include MiniBankAccountLock\"");
			System.exit(0);
		}
		
		boolean includeMiniBankAccount = false;
		boolean includeMiniBankAccountSync = false;
		boolean includeMiniBankAccountSemaphore = false;
		boolean includeMiniBankAccountLock = false;
		
		// figure out which classes to include in the output
		if( args[0].toLowerCase().startsWith("t") || args[0].toLowerCase().startsWith("y") )
		{
			includeMiniBankAccount = true;
		}
		
		if( args[1].toLowerCase().startsWith("t") || args[1].toLowerCase().startsWith("y") )
		{
			includeMiniBankAccountSync = true;
		}
		
		if( args[2].toLowerCase().startsWith("t") || args[2].toLowerCase().startsWith("y") )
		{
			includeMiniBankAccountSemaphore = true;
		}
		
		if( args[3].toLowerCase().startsWith("t") || args[3].toLowerCase().startsWith("y") )
		{
			includeMiniBankAccountLock = true;
		}
		
		// create the one semaphore
		java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(1, true);
		
		// create the reentrantlock
		java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock(true);
		
		// create the basic account
		MiniBankAccount mba = new MiniBankAccount("Smith", "Jane");
		
		// create the basic account with synchronized methods for shared access
		MiniBankAccountSync mbaSync = new MiniBankAccountSync("Smith", "Jane Sync");
		
		// create the basic account with semaphore for shared access
		MiniBankAccountSemaphore mbaSemaphore = new MiniBankAccountSemaphore("Smith", "Jane Semaphore", semaphore);
		
		// create the basic account with reentrantlock for shared access
		MiniBankAccountLock mbaLock = new MiniBankAccountLock("Smith", "Jane Lock", lock);
		
		// how many threads will access each class
		int numberOfThreadsOfEachAccountType = 3;
		
		MiniBankAccountProcessor[] miniBankAccountProcessor = new MiniBankAccountProcessor[numberOfThreadsOfEachAccountType];
		MiniBankAccountProcessor[] miniBankAccountProcessorSync = new MiniBankAccountProcessor[numberOfThreadsOfEachAccountType];
		MiniBankAccountProcessor[] miniBankAccountProcessorSemaphore = new MiniBankAccountProcessor[numberOfThreadsOfEachAccountType];
		MiniBankAccountProcessor[] miniBankAccountProcessorLock = new MiniBankAccountProcessor[numberOfThreadsOfEachAccountType];
		
		// create the list of transactions that each account will process
		java.util.ArrayList<Transaction> transactionsToPerform = new java.util.ArrayList<>();
		transactionsToPerform.add(new Transaction("depositChecking", null, 20));
		transactionsToPerform.add(new Transaction("depositSavings", null, 30));
		transactionsToPerform.add(new Transaction("withdrawChecking", null, 10));
		transactionsToPerform.add(new Transaction("withdrawSavings", null, 20));
		transactionsToPerform.add(new Transaction("transferToSavings", null, 5));
		transactionsToPerform.add(new Transaction("transferToChecking", null, 7));
		
		// create the threads for each account 
		for( int i = 0; i < miniBankAccountProcessor.length; i++ )
		{
			miniBankAccountProcessor[i] = new MiniBankAccountProcessor(mba, transactionsToPerform);
			miniBankAccountProcessorSync[i] = new MiniBankAccountProcessor(mbaSync, transactionsToPerform);
			miniBankAccountProcessorSemaphore[i] = new MiniBankAccountProcessor(mbaSemaphore, transactionsToPerform);
			miniBankAccountProcessorLock[i] = new MiniBankAccountProcessor(mbaLock, transactionsToPerform);
		}
		
		// start the threads running for the account types that we want output for
		for( int i = 0; i < miniBankAccountProcessor.length; i++ )
		{
			if( includeMiniBankAccount )
			{
				miniBankAccountProcessor[i].start();
			}
			
			if( includeMiniBankAccountSync )
			{
				miniBankAccountProcessorSync[i].start();
			}
			
			if( includeMiniBankAccountSemaphore )
			{
				miniBankAccountProcessorSemaphore[i].start();
			}
			
			if( includeMiniBankAccountLock )
			{
				miniBankAccountProcessorLock[i].start();
			}
		}
		
		// wait for the threads to finish running for those that we are getting output for 
		for( int i = 0; i < miniBankAccountProcessor.length; i++ )
		{
			try
			{
				if( includeMiniBankAccount )
				{
					miniBankAccountProcessor[i].join();
				}
				
				if( includeMiniBankAccountSync )
				{
					miniBankAccountProcessorSync[i].join();
				}
				
				if( includeMiniBankAccountSemaphore )
				{
					miniBankAccountProcessorSemaphore[i].join();
				}
				
				if( includeMiniBankAccountLock )
				{
					miniBankAccountProcessorLock[i].join();
				}
			}
			catch(Exception e)
			{
			}
		}
		
		// output the account information for the accounts that we had threads running for
		if( includeMiniBankAccount )
		{
			System.out.println("mba.toString()\n" + mba.toString());
		}
		
		if( includeMiniBankAccountSync )
		{
			System.out.println("mbaSync.toString()\n" + mbaSync.toString());
		}
		
		if( includeMiniBankAccountSemaphore )
		{
			System.out.println("mbaSemaphore.toString()\n" + mbaSemaphore.toString());
		}
		
		if( includeMiniBankAccountLock )
		{
			System.out.println("mbaLock.toString()\n" + mbaLock.toString());
		}
	}
}
