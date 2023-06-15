class MiniBankAccountLock implements MiniBankAccountFunctions
{
	private static final int delayTime = 1;
	private final String ownerLastName;
	private final String ownerFirstName;
	private double checkingBalance;
	private double savingsBalance;
	
	private final java.util.concurrent.locks.ReentrantLock lock;

	MiniBankAccountLock(String ownerLastName, String ownerFirstName, java.util.concurrent.locks.ReentrantLock lock)
	{
		this.ownerLastName = ownerLastName;
		this.ownerFirstName = ownerFirstName;
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;

		this.lock = lock;
	}
	
	public void withdrawFromChecking(double amount)
	{
		lock.lock();
		try 
		{
			System.out.println("start withdrawChecking " + amount + "\t" + ownerFirstName);
			double x = checkingBalance;
			pause(delayTime);
			checkingBalance = x - amount;
			System.out.println("end withdrawChecking " + amount + "\t" + ownerFirstName);
		}
		finally { lock.unlock(); }
	}
	
	public void withdrawFromSavings(double amount)
	{
		lock.lock();
		try
		{
			System.out.println("start withdrawSavings " + amount + "\t" + ownerFirstName);
			double x = savingsBalance;
			pause(delayTime);
			savingsBalance = x - amount;
			System.out.println("end withdrawSavings " + amount + "\t" + ownerFirstName);
		}
		finally { lock.unlock(); }
	}
	
	public void depositToSavings(double amount)
	{
		lock.lock();
		try
		{
			System.out.println("start depositSavings " + amount + "\t" + ownerFirstName);
			double x = savingsBalance;
			pause(delayTime);
			savingsBalance = x + amount;
			System.out.println("end depositSavings " + amount + "\t" + ownerFirstName);
		}
		finally { lock.unlock(); }
	}
	
	public void depositToChecking(double amount)
	{
		lock.lock();
		try
		{
			System.out.println("start depositChecking " + amount + "\t" + ownerFirstName);
			double x = checkingBalance;
			pause(delayTime);
			checkingBalance = x + amount;
			System.out.println("end depositChecking " + amount + "\t" + ownerFirstName);
		}
		finally { lock.unlock(); }
	}
	
	public void transferFromSavingsToChecking(double amount)
	{
		lock.lock();
		try
		{
			System.out.println("start transferToChecking " + amount + "\t" + ownerFirstName);
			double x = checkingBalance;
			double y = savingsBalance;
			if( amount > y )
			{
				amount = 0;
			}
			pause(delayTime);
			checkingBalance = x + amount;
			savingsBalance = y - amount;
			System.out.println("end transferToChecking " + amount + "\t" + ownerFirstName);
		}
		finally { lock.unlock(); }
	}
	
	public void transferFromCheckingToSavings(double amount)
	{
		lock.lock();
		try
		{
			System.out.println("start transferToSavings " + amount + "\t" + ownerFirstName);
			double x = checkingBalance;
			double y = savingsBalance;
			if( amount > x )
			{
				amount = 0;
			}
			pause(delayTime);
			checkingBalance = x - amount;
			savingsBalance = y + amount;
			System.out.println("end transferToSavings " + amount + "\t" + ownerFirstName);
		}
		finally { lock.unlock(); }
	}
		
	public String toString()
	{
		lock.lock();
		String result = ownerLastName + ", " + ownerFirstName + "\n";
		try 
		{
			result = result + checkingBalance + "\n";
			result = result + savingsBalance;
		}
		finally { 
			lock.unlock(); 
			return result;
		}
	}
	
	public String getOwnerLastName()
	{
		return ownerLastName;
	}
	
	public String getOwnerFirstName()
	{
		return ownerFirstName;
	}
		
	private void pause(int delay)
	{
		try
		{
			java.lang.Thread.sleep(delay);
		}
		catch(Exception e)
		{
		}
	}
}
