class SimpleSharedClassLock implements SimpleSharedClassFunctions
{
	private int delayInMilliSeconds;
	private int delayInNanoSeconds;
	private int value;
	private final java.util.concurrent.locks.ReentrantLock lock;
	
	SimpleSharedClassLock(int delayInMilliSeconds, int delayInNanoSeconds, java.util.concurrent.locks.ReentrantLock lock)
	{
		value = 0;
		this.delayInMilliSeconds = delayInMilliSeconds;
		this.delayInNanoSeconds = delayInNanoSeconds;
		this.lock = lock;
	}
	
	public void decreaseValue(int v)
	{
		lock.lock();
		
		try
		{
			int x = value;
			pause(delayInMilliSeconds, delayInNanoSeconds);
			value = x - v;
		}
		finally
		{
			lock.unlock();
		}
	}
	
	public void increaseValue(int v)
	{
		lock.lock();
		
		try
		{
			int x = value;
			pause(delayInMilliSeconds, delayInNanoSeconds);
			value = x + v;
		}
		finally
		{
			lock.unlock();
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
