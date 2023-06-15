class zheng_BankAccount
{
	private final String ownerLastName;
	private final String ownerFirstName;
	private final String accountNumber;
	private double checkingBalance;
	private double savingsBalance;
	private static int bankAccountCount = 0;
	
	zheng_BankAccount(String ownerLastName, String ownerFirstName, double checkingBalance, double savingsBalance)
	{
		this.ownerLastName = ownerLastName;
		this.ownerFirstName = ownerFirstName;
		this.accountNumber = makeAccountNumber();
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
		
		bankAccountCount = bankAccountCount+1;
	}
	
	zheng_BankAccount(String ownerLastName, String ownerFirstName) { this(ownerLastName, ownerFirstName, 0.0, 0.0); }
	
	public boolean withdrawFromChecking(double amount)
	{
		if (amount <= checkingBalance) {
			checkingBalance -= amount;
			return true;
		}
		return false;
	}
	
	public boolean withdrawFromSavings(double amount)
	{
		if (amount <= savingsBalance) {
			savingsBalance -= amount;
			return true;
		}
		return false;
	}
	
	public boolean transferFromSavingsToChecking(double amount)
	{
		if (amount <= savingsBalance) {
			savingsBalance -= amount;
			checkingBalance += amount;
			return true;
		}
		return false;
	}
	
	public boolean transferFromCheckingToSavings(double amount)
	{
		if (amount <= checkingBalance) {
			checkingBalance -= amount;
			savingsBalance += amount;
			return true;
		}
		return false;
	}
	
	public void depositToSavings(double amount) { savingsBalance += amount; }
	
	public void depositToChecking(double amount) { checkingBalance += amount; }
	
	public String toString() { return ownerLastName + ", " + ownerFirstName + ", " + accountNumber + "\n" + checkingBalance + "\n" + savingsBalance; }
	
	public String getOwnerLastName() { return ownerLastName; }
	
	public String getOwnerFirstName() { return ownerFirstName; }
	
	public String getAccountNumber() { return accountNumber; }
	
	public double getCheckingBalance() { return checkingBalance; }
	
	public double getSavingsBalance() { return savingsBalance; }
	
	private String makeAccountNumber()
	{
		String aNumber = "" + bankAccountCount;
		
		while( aNumber.length() < 6 )
		{
			aNumber = "0" + aNumber;
		}
		return aNumber;
	}
	
	public static int getBankAccountCount() { return bankAccountCount; }
}
