package Intermediate;

import java.util.concurrent.Semaphore;

public class PrintZeroOddEven {
    public static final Semaphore zero = new Semaphore(1);
    public static final Semaphore odd = new Semaphore(0);
    public static final Semaphore even = new Semaphore(0);
    static boolean flag=true;
    static class PrintNumber
    {
        public void printZero() throws InterruptedException {
            for(int i=1;i<=15;i++)
            {
                zero.acquire();
                System.out.print("0");
                if(flag)
                {
                    odd.release();
                }
                else
                {
                    even.release();
                }
                flag=!flag;
            }

        }
        public void printOdd() throws InterruptedException {

            for(int i=1;i<=15;i+=2)
            {
                odd.acquire();
                System.out.print(i);
                zero.release();
            }
        }
        public void printEven() throws InterruptedException {

            for(int i=2;i<=15;i+=2)
            {
                even.acquire();
                System.out.print(i);
                zero.release();
            }
        }
    }

    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    printNumber.printZero();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    printNumber.printOdd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                try {
                    printNumber.printEven();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1= new Thread(r1,"zero-thread");
        Thread t2= new Thread(r2,"odd-thread");
        Thread t3= new Thread(r3,"even-thread");
        t1.start();
        t2.start();
        t3.start();

    }
}
