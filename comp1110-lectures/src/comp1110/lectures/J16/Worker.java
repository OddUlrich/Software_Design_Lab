package comp1110.lectures.J16;

public class Worker implements Runnable {
    static final int NUM_THREADS = 4;
    static final int WORK_ITEMS = 1000;

    private int nextWorkItem = 0;

    private synchronized int getNextWorkItem() {
        return nextWorkItem++;
    }

    private void pretendToWork() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int completed = 0;
        int workItem = 0;
        while ((workItem = getNextWorkItem()) < WORK_ITEMS) {
            pretendToWork();
            completed++;
        }

        System.out.println(Thread.currentThread().getName() + " completed " + completed + " tasks!");
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(worker, "Worker " + i);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All done!");
    }
}
