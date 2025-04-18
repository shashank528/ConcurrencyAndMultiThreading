package Basics;
class newRunnable implements  Runnable
{

    @Override
    public void run() {
        printNumber();
    }
    public void printNumber()
    {
        for(int i=1;i<=10;i++)
        {
            if(i%2==0)
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
}
public class OddEvenThreadDemo {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            for(int i=1;i<=10;i++)
            {
                if(i%2!=0)
                {
                    System.out.println(Thread.currentThread().getName()+" "+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        newRunnable runnable1 = new newRunnable();
        Thread t1= new Thread(runnable,"odd ");
        Thread t2 = new Thread(runnable1,"even ");
        t1.start();
        t2.start();

    }
}
