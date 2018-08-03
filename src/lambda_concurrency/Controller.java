package lambda_concurrency;

public class Controller {
	
	
	/**
	 * This class shows the most rudimentary way of running two threads at the 'same' time.
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * Method versionOne() shows the conventional, verbose way of doing things.
	 * A separate class called Worker implements the Runnable interface. In the main tread
	 * this Worker class will be instantiated and passed to the Constructor of the Tread class.
	 * On the Thread object the start()-method is called to start the separate Thread as defined in
	 * the run()-method of the Worker class. The call to Thread.start() returns immediately and the main Thread (Controller)
	 * is being continued.
	 * ++++++++++++++++++
	 * Method versionTwo shows the use of Lambda expressions.
	 * If you look at the java API you'll see that the Runnable interface is annotated
	 * @FunctionalInterface that means you can safely use Lambda expression.
	 * In this method everything is reduced. First there is only an anonymous Thread instance.
	 * The constructor of Thread requires a Runnable as an argument. This requirement can be met
	 * by passing a Lambda expression, as this will be interpreted by the compiler in the appropriate was. 
	 * Since the interface's method's signature looks like this - no parameter, no return value
	 * public void run(); the Lambda would look like this () -> {any code}. 
	 * So the entire line to start off a separate thread using Lambda would be:
	 * new Tread(()->{any code}).start(); 
	 * This example clearly shows the difference between passing on data versus passing on behavior.
	 * @param args Whatever you put on the command line.
	 */
	public static void main(String[] args) {
		new Controller();
	} // end of main()
	
	public Controller() {
		starter();
	}
	
	private void starter() {
		try {
		versionOne();
		versionTwo();
		} 
		catch (Exception e) {
			System.out.println("How could that happen: " + e);
		}
	} // end of starter()
	
	private void versionOne() throws InterruptedException {
		int b = 0;
		Worker w = new Worker();
		Thread t = new Thread(w);
		t.start();
		while(b < 10) {
			System.out.println("Nachricht #" + b);
			Thread.sleep(500);
			b++;
		}
		
	} // end of versionOne()
	
	private void versionTwo() throws InterruptedException {
		int c = 0;
		new Thread(() -> {
			int a = 0;
			while (a < 10) {
				System.out.println("Message #" + a);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // end of try/catch
				a++;
			} // end of while()
		}).start();
		while(c < 10) {
			System.out.println("Information #" + c);
			Thread.sleep(500);
			c++;
		}
		
		} // end of versionTwo()
} // end of class
