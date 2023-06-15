class SimpleSharedClassSemaphore implements SimpleSharedClassFunctions
{
	private int delayInMilliSeconds;
	private int delayInNanoSeconds;
	private int value;
	private final java.util.concurrent.Semaphore semaphore;
	
	SimpleSharedClassSemaphore(int delayInMilliSeconds, int delayInNanoSeconds, java.util.concurrent.Semaphore semaphore)
	{
		value = 0;
		this.delayInMilliSeconds = delayInMilliSeconds;
		this.delayInNanoSeconds = delayInNanoSeconds;
		this.semaphore = semaphore;
	}
	
	public void decreaseValue(int v)
	{
		try
		{
			semaphore.acquire();
		}
		catch(Exception e)
		{
		}
		
		int x = value;
		pause(delayInMilliSeconds, delayInNanoSeconds);
		value = x - v;
		
		try
		{
			semaphore.release();
		}
		catch(Exception e)
		{
		}
	}
	
	public void increaseValue(int v)
	{
		try
		{
			semaphore.acquire();
		}
		catch(Exception e)
		{
		}
		
		int x = value;
		pause(delayInMilliSeconds, delayInNanoSeconds);
		value = x + v;
		
		try
		{
			semaphore.release();
		}
		catch(Exception e)
		{
		}
	}
	
	public String toString()
	{
		return "value = " + value;
	}

	private void pause(int delayInMilliSeconds, int delayInNanoSeconds)
	{
		try
		{
			java.lang.Thread.sleep(delayInMilliSeconds, delayInNanoSeconds);
		}
		catch(Exception e)
		{
		}
	}
}
