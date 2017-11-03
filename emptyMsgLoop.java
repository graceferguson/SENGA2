import java.lang.InterruptedException;

public class emptyMsgLoop implements Runnable
{
	private String message;
	private reactivate;
	
	public emptyMsgLoop(String message)
	{
		this.message = message;
		reactivate = false;
	}
	
	// function that reactivates the looping message. Called by coinReceptacleListening when coin receptacle is empty.
	public void reactivateMsg()
	{
		reactivate = true;
	}
	
	public void run()
	{
		// overall loops indefinitely until program stops
		while(true)
		{
			// while waiting for an interrupt, does the following
			try
			{
				while(true)
				{
					// call display here
					Thread.sleep(5000);
					// call display with blank message here
					Thread.sleep(10000);
				}
			}
			// when an interrupt is received 
			catch(InterruptedException e)
			{
				// waits for the message to get reactivated
				while(!reactivate)
				{
					Thread.sleep(100);
				}
				// sets reactive back to false and loops to the top
				reactivate = false;
			}
		}
	}
}