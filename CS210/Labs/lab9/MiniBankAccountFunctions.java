interface MiniBankAccountFunctions
{
	public void withdrawFromChecking(double amount);
	public void withdrawFromSavings(double amount);
	public void depositToChecking(double amount);
	public void depositToSavings(double amount);
	public void transferFromSavingsToChecking(double amount);
	public void transferFromCheckingToSavings(double amount);
}
