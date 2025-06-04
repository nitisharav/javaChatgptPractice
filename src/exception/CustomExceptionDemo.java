package exception;

class AgeTooSmallException extends Exception{
	public AgeTooSmallException(String message) {
		super(message);
	}
}

public class CustomExceptionDemo {
  static void checkAge(int age) throws AgeTooSmallException {
	  if (age < 18) throw new AgeTooSmallException("Age less than 18 not allowed");
	  else {
		  System.out.println("valid age");
	  }
  }
  
  public static void main(String[] args) {
	try {checkAge(15);}
	catch (AgeTooSmallException e) {
		//System.out.println(e.printStackTrace());
		e.printStackTrace();
	}
}
}
