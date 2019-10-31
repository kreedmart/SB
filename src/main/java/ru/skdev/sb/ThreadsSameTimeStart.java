package ru.skdev.sb;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergekos
 */
public class ThreadsSameTimeStart {

    private static Logger LOGGER = Logger.getLogger(ThreadsSameTimeStart.class.getName());

    private static class DummyTask implements Runnable {

        private final CyclicBarrier barrier;

        public DummyTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                LOGGER.log(
                        Level.INFO,
                        "Thread {0} is waiting for barrier",
                        new Object[]{Thread.currentThread().getName()});
                barrier.await();
                
                LOGGER.log(        
                        Level.INFO,
                        "Thread {0} is working",
                        new Object[]{Thread.currentThread().getName()});
                Thread.sleep(5000);

                LOGGER.log(
                        Level.INFO,
                        "Thread {0} is done",
                        new Object[]{Thread.currentThread().getName()});

            } catch (InterruptedException | BrokenBarrierException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier barrier = new CyclicBarrier(4);
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        threadPool.submit(new DummyTask(barrier));
        threadPool.submit(new DummyTask(barrier));
        threadPool.submit(new DummyTask(barrier));

        barrier.await();        
    }
}
