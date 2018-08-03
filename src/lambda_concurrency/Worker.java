package lambda_concurrency;

public class Worker implements Runnable{

	@Override
	public void run() {
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
	} // end of run()
} // end of class
