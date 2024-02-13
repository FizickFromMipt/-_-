package ru.pevnenko;

import java.util.Collections;
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
                ls.removeAll(Collections.singleton(i));
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

/**
 * Вывод :
 *
 * produces size = 1
 * produces size = 2
 * produces size = 3
 * produces size = 4
 * produces size = 5
 * produces size = 6
 * produces size = 7
 * produces size = 8
 * produces size = 9
 * produces size = 10
 * consumer size = 9
 * consumer size = 8
 * consumer size = 7
 * consumer size = 6
 * consumer size = 5
 * consumer size = 4
 * consumer size = 3
 * consumer size = 2
 * consumer size = 1
 * consumer size = 0
 * final size = 0
 *
 * Process finished with exit code 0
 *
 */