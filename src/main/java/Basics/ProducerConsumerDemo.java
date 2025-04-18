package Basics;
class SharedResource
{
    private int data;
    private boolean hasData;
    public  synchronized void produce(int data)
    {
        while(hasData)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.data=data;
        System.out.println("Prodcue data "+data);
        hasData=true;
        notify();
    }
    public synchronized int consume()
    {
        while(!hasData)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hasData=false;
        notify();
        System.out.println("consumer data "+data);
        return data;
    }
}
class Producer implements Runnable
{
    SharedResource sharedResource;
    int i=0;

    public Producer(SharedResource sharedResource) {
        this.sharedResource=sharedResource;
    }

    @Override
    public void run() {
       while(true)
       {
           sharedResource.produce(i++);
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
class Consumer implements Runnable
{
    SharedResource sharedResource;

    public Consumer(SharedResource sharedResource) {
        this.sharedResource=sharedResource;
    }

    @Override
    public void run() {
        while(true)
        {
            sharedResource.consume();
        }
    }
}
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Producer p = new Producer(sharedResource);
        Consumer c = new Consumer(sharedResource);
        Thread t1= new Thread(p,"producer-thread");
        Thread t2 = new Thread(c,"consumer-threead");
        t1.start();
        t2.start();
    }
}
