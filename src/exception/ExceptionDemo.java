package exception;

public class ExceptionDemo {

	public static void main(String[] args) {
		try {
			int a=5/0;
			
		}
		catch(Exception e){
			System.out.println("Catching the exception "+ e);
		}
		finally {
			System.out.println("This block will always execute");
		}
	}
}
