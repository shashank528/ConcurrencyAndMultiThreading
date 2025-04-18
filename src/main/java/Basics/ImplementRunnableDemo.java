package Basics;
class MyRunnable implements Runnable
{

    @Override
    public void run() {
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
public class ImplementRunnableDemo {
    public static void main(String[] args) {
        MyRunnable runnable= new MyRunnable();
        Thread t= new Thread(runnable,"runnable thread");
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
