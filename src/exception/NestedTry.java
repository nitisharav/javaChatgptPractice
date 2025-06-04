package exception;

public class NestedTry {
    public static void main(String[] args) {
	try {
		int[] arr= new int[5];
		arr[10]=5; // outer ArrayIndexOutOfBoundException
		try {
			int a=5/0; //inner Arithmetic exception
		}
		catch(Exception e) {
		 System.out.println("Inner exception: "+e);	
		}
	}
	catch (Exception e){
		System.out.println("outer block exception: " + e);
	}
}
}
