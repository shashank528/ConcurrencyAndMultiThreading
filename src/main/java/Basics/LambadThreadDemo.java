package Basics;

import java.sql.SQLOutput;

public class LambadThreadDemo {
    public static void main(String[] args) {
        Runnable runnable =()->{
            System.out.println(Thread.currentThread().getName()+ " this is new lambda");
        };
        Thread t = new Thread(runnable,"shashank");
        t.start();
    }
}
