package multithreading;

public class MyThread extends Thread {
	public void run() {
		System.out.println("Thread running via Thread Class");
	}
	public static void main(String[] args) {
		BankAccount account=new BankAccount();
		account.withdraw("test", 1000);
		account.withdraw("test", 5000);
		
		Thread t1=new Thread(new WithdrawalTask(account, 1000));
		Thread t2=new Thread(new WithdrawalTask(account, 5000));
		t1.start();
		t2.start();
	}

}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Thread running from runnable interface");
		
	}
	
}
//3. run() vs start()
/*
Calling run() executes on the current thread.
Calling start() creates a new thread and executes run().
*/
//4. Thread Life Cycle
/*
NEW -> RUNNABLE -> RUNNING -> BLOCKED/WAITING -> TERMINATED
*/
//5. Print numbers from 1 to 10 using a thread

class PrintNumber extends MyThread {
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println(i);
		}
	}
}
//6. Demonstrate thread synchronization with a shared resource (like a bank account).

class BankAccount1{
	private int balance=10000;
	public synchronized void  withdraw(int amount) {
		if(balance>=amount) {
			balance-=amount;
			System.out.println("balance withdrawn successfully and current balamce: "+balance);
		}
		else {
			System.out.println("insufficient balance");
		}
	}
}

//6. explanation with full example
class BankAccount{
	private int balance=10000;
	public synchronized void withdraw(String threadName, int amount) {
		if(balance>=amount) {
			balance-=amount;
			System.out.println("Thread: "+threadName+" ,balance withdrawn successfully and current balamce: "+balance);
		}
		else {
			System.out.println("insufficient balance");
		}
	}
}

class WithdrawalTask implements Runnable{
	BankAccount account;
	int amount;
	
public WithdrawalTask(BankAccount account, int amount ) {
	this.account= account;
	this .amount=amount;
}
 public void run() {
	 account.withdraw(Thread.currentThread().getName(), amount);
 }
}

//7. Write a program to simulate a producer-consumer problem using wait() and notify().

class SharedQueue {
	int item;
	boolean available=false; //item is available
	
	public synchronized void produce(int val) throws InterruptedException{
		while(available) wait();
		item=val;
		available=true;
		System.out.println("Produced: "+val);
		notify();
	}
	
	public synchronized int consume() throws InterruptedException {
		while(!available) wait();
		available=false;
		System.out.println("Consumed: "+ item);
		notify();
		return item;
		
	}
}
//8. synchronized method vs block
class Printer{
	public synchronized void printVal(int val) {
		System.out.println("Whole method is synchronized");
	}
	
	public void blockprint(int val) {
		synchronized(this) {
			System.out.println("only this block is synchronized");
		}
		System.out.println("this part is not synchronized");
	} 
}
//9. What is the use of volatile keyword in Java?
/*
 volatile is a keyword used to mark a variable as stored in main memory. It tells the JVM:

“Hey! Don’t cache this variable in a thread’s local memory. Always read/write it directly from/to the main memory.”
In Java, each thread can cache variables locally, and might not see the latest value updated by other threads. This leads to visibility issues.

volatile solves this by forcing threads to always use the most up-to-date value.
 */

class VolatileExample extends Thread{
	private volatile boolean running= true;
	public void run() {
		while(running) {
			System.out.println("Thread running");
		}
	}
	public void stopThread() {
		running= false;
	}
}
// 10. Write a program to create two threads, one prints even numbers and another prints odd numbers up to 100


class EvenOdd{
	int num=1;
	synchronized void printEven() throws InterruptedException {
		while(num<=100){
			if(num%2==0) {
				System.out.println("Even number: "+num);
				notify();
			}
			else wait();
		}
	}
	
	synchronized void printOdd() throws InterruptedException {
		while (num<=100) {
			if(num%2!=0) {
				System.out.println("Odd number: "+ num);
				notify();
			} else wait();
		}
	}
}

//20. Executor vs ExecutorService vs ScheduledExecutorService
/*
Executor: Interface with execute() method.
ExecutorService: Extends Executor, adds shutdown, submit(), invokeAll(), etc.
ScheduledExecutorService: Schedules tasks to run after delay or periodically.
*/