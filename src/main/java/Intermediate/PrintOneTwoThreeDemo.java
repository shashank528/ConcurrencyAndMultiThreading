package Intermediate;

import java.sql.SQLOutput;

class PrintNumber
{
    private int limit;
    private int currentNumber=1;
    private int turn=1;

    public PrintNumber(int limit) {
        this.limit=limit;
    }

    public synchronized void print(int threadId)
    {
        while(currentNumber<=limit)
        {
            if(turn==threadId)
            {
                System.out.println(Thread.currentThread().getName()+" "+currentNumber);
                currentNumber++;
                turn=turn%3+1;
                notifyAll();
            }
            else
            {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
public class PrintOneTwoThreeDemo {
    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber(30);
        Runnable r1 = ()->{
            printNumber.print(1);
        };
        Runnable r2 = ()->{
            printNumber.print(2);
        };
        Runnable r3 = ()->{
            printNumber.print(3);
        };
        Thread t1 = new Thread(r1,"Thread-1");
        Thread t2 = new Thread(r2,"Thread-2");
        Thread t3 = new Thread(r3,"Thread-3");
        t1.start();
        t2.start();
        t3.start();

    }

}
