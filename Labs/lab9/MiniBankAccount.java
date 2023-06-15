class MiniBankAccount implements MiniBankAccountFunctions
{
	private static final int delayTime = 1;
	private final String ownerLastName;
	private final String ownerFirstName;
	private double checkingBalance;
	private double savingsBalance;
	
	MiniBankAccount(String ownerLastName, String ownerFirstName)
	{
		this.ownerLastName = ownerLastName;
		this.ownerFirstName = ownerFirstName;
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
	}
	
	public void withdrawFromChecking(double amount)
	{
		System.out.println("start withdrawChecking " + amount + "\t" + ownerFirstName);
		double x = checkingBalance;
		pause(delayTime);
		checkingBalance = x - amount;
		System.out.println("end withdrawChecking " + amount + "\t" + ownerFirstName);
	}
	
	public void withdrawFromSavings(double amount)
	{
		System.out.println("start withdrawSavings " + amount + "\t" + ownerFirstName);
		double x = savingsBalance;
		pause(delayTime);
		savingsBalance = x - amount;
		System.out.println("end withdrawSavings " + amount + "\t" + ownerFirstName);
	}
	
	public void depositToSavings(double amount)
	{
		System.out.println("start depositSavings " + amount + "\t" + ownerFirstName);
		double x = savingsBalance;
		pause(delayTime);
		savingsBalance = x + amount;
		System.out.println("end depositSavings " + amount + "\t" + ownerFirstName);
	}
	
	public void depositToChecking(double amount)
	{
		System.out.println("start depositChecking " + amount + "\t" + ownerFirstName);
		double x = checkingBalance;
		pause(delayTime);
		checkingBalance = x + amount;
		System.out.println("end depositChecking " + amount + "\t" + ownerFirstName);
	}
	
	public void transferFromSavingsToChecking(double amount)
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
	
	public void transferFromCheckingToSavings(double amount)
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
		
	public String toString()
	{
		String result = ownerLastName + ", " + ownerFirstName + "\n";
		result = result + checkingBalance + "\n";
		result = result + savingsBalance;
		return result;
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
