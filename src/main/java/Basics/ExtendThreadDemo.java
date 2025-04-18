package Basics;
class MyThread extends Thread
{
    public MyThread(String thread1) {
        super(thread1);
    }

    public void run()
    {
        printNumber();
    }
    public void printNumber()
    {
        for(int i=5;i<=10;i++)
        {
            System.out.println(Thread.currentThread().getName()+" "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ExtendThreadDemo {
    public static void main(String[] args) {
        MyThread t= new MyThread("thread1");
        t.start();
        for(int i=1;i<=4;i++)
        {
            System.out.println(Thread.currentThread().getName()+" "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
