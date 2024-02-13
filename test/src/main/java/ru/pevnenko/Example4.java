package ru.pevnenko;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Исходный класс из тестового задания
 *
 * Какие вижу проблемы :
 * - Consumer удаляет первое вхождение элемента i (методом remove)
 * - после первой итерации потребитель не удаляет ничего, так как все елементы уже удалены
 * - проблема с многопоточостью, так как я пытаюсь тут изменяь общую коллекцию  из нескольких потоков без синхронизации
 */
public class Example4 {

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

        Example4 example = new Example4();

        Example4.Producer produces = example.new Producer();
        Example4.Consumer consumer = example.new Consumer();

        Thread producerThread = new Thread(produces);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();

        consumerThread.start();

    }
}

/**
 * Вывод:
 *
 *produces size = 1
 * consumer size = 1
 * produces size = 2
 * produces size = 2
 * consumer size = 1
 * consumer size = 2
 * consumer size = 1
 * consumer size = 1
 * consumer size = 1
 * produces size = 3
 * produces size = 2
 * produces size = 3
 * produces size = 4
 * produces size = 5
 * produces size = 6
 * consumer size = 1
 * produces size = 7
 * consumer size = 6
 * consumer size = 5
 * consumer size = 4
 * final size = 4
 *
 * Process finished with exit code 0
 */