import java.lang.InterruptedException;

/**
* Class that handles the looping message that occurs when the number of credits in the machine is 0.
* In the form of a thread so that it can run while the machine itself is waiting for coin insertion/button presses
*/
public class emptyMsgLoop implements Runnable
{
	private String message;
	private Boolean reactivate;
	private vendCommunicator communicator;
	
	public emptyMsgLoop(String message, vendCommunicator communicator)
	{
		this.message = message;
		reactivate = false;
		this.communicator = communicator;
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
					communicator.displayMsg("Hi there!")
					Thread.sleep(5000);
					communicator.displayMsg("")
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