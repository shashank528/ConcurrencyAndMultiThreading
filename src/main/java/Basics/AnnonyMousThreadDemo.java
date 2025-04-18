package Basics;

public class AnnonyMousThreadDemo {
    public static void main(String[] args) {
        Thread t = new Thread()
        {
            public void run()
            {
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
        };
        t.start();
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
