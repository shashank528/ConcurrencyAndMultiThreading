package Basics;
class SharedResources
{
    private boolean flag=true;
    public synchronized void doWait()
    {
        while(flag)
        {
            System.out.println(Thread.currentThread().getName()+" "+"executing");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" resume execution");
    }
    public synchronized void doNotify()
    {
        while(flag)
        {
            flag=false;
            notify();
        }
    }
    public synchronized void doNotifyAll()
    {
        while(flag)
        {
            flag=false;
            notifyAll();
        }
    }
}
class Waiter implements Runnable
{
    SharedResources sharedResource;

    public Waiter(SharedResources sharedResource) {
        this.sharedResource=sharedResource;
    }

    @Override
    public void run() {
        sharedResource.doWait();
    }
}
class Notifier implements Runnable
{
    SharedResources sharedResource;
    public Notifier(SharedResources sharedResource)
    {
        this.sharedResource=sharedResource;
    }
    @Override
    public void run() {
        sharedResource.doNotify();
    }
}
class NotifierAll implements Runnable
{
    SharedResources sharedResource;
    public NotifierAll(SharedResources sharedResource)
    {
        this.sharedResource=sharedResource;
    }
    @Override
    public void run() {
        sharedResource.doNotifyAll();
    }
}
public class WaiterNotifierDemo {
    public static void main(String[] args) {
        SharedResources sharedResource = new SharedResources();
        Waiter waiter1 = new Waiter(sharedResource);
        Waiter waiter2 = new Waiter(sharedResource);
        Waiter waiter3 = new Waiter(sharedResource);
        Notifier notifier1 = new Notifier(sharedResource);
        NotifierAll notifierAll = new NotifierAll(sharedResource);
        Thread t1= new Thread(waiter1,"waiter-1");
        Thread t2 = new Thread(waiter2,"waiter-2");
        Thread t3 = new Thread(waiter2,"waiter-3");

        Thread t4 = new Thread(notifier1,"notifierAll-1");
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t4.start();

    }
}
