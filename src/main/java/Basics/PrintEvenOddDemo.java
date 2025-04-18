package Basics;
class Shared
{
    private int data;
    boolean flag=false;
    public synchronized void printOdd(int val)
    {
        while(flag)
        {
            try {
                System.out.println(Thread.currentThread().getName()+" goes to waiting ");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" "+val);
        flag=true;
        notify();
    }
    public synchronized  void printEven(int val)
    {
        while(!flag)
        {
            try {
                System.out.println(Thread.currentThread().getName()+" goes to waiting ");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" "+val);
        flag=false;
        notify();
    }
}
public class PrintEvenOddDemo {
    public static void main(String[] args) {
        Shared shared = new Shared();
        Runnable r1= ()->{

            for(int i=1;i<=20;i+=2)
            {
                shared.printOdd(i);
            }
        };
        Runnable r2= ()->{

            for(int i=2;i<=20;i+=2)
            {
                shared.printEven(i);
            }
        };
        Thread t1= new Thread(r1,"odd -thread ");
        Thread t2 = new Thread(r2,"even-thread");
        t1.start();
        t2.start();
    }
}
