package Adavanced;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWrite
{
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock read  =  lock.readLock();
    private Lock write = lock.writeLock();
    int count=1;
    public int getCount()
    {
        read.lock();
        try
        {
            System.out.println("count ="+count);
            return count;
        }
        finally {
            read.unlock();
        }
    }
    public void incrementCount()
    {
        write.lock();
        try
        {
            count++;
            System.out.println("incremnetd count "+count);
        }
        finally {
            write.unlock();
        }
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReadWrite readWrite = new ReadWrite();
        new Thread(()->{
            while(true) {
                readWrite.getCount();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->
        {
            while(true) {
                readWrite.incrementCount();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
