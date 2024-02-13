package ru.pevnenko;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Мое решение задачи
 */
public class Example4New {

    public static CopyOnWriteArrayList<Integer> ls = new CopyOnWriteArrayList<>();

    class Producer implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                ls.add(Integer.valueOf(i));

                System.out.println("produces size = " + ls.size());
            }

        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                ls.remove(Integer.valueOf(i));
                System.out.println("consumer size = " + ls.size());
            }
            System.out.println("final size = " + ls.size());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Example4New example = new Example4New();

        Example4New.Producer produces = example.new Producer();
        Example4New.Consumer consumer = example.new Consumer();

        Thread producerThread = new Thread(produces);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();

        consumerThread.start();

    }
}