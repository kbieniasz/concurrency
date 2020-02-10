import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ListTest {
    public static void main(String[] args) throws InterruptedException {
        ListThread head1 = new ListThread(1, null );
        ReentrantLock classicListLock = new ReentrantLock();
        ListClassic head2 = new ListClassic(1, null, classicListLock);

        List<Integer> operationsTypeList = new ArrayList<>();
        List<Integer> operationsValList = new ArrayList<>();

        Random rand = new Random();
        for(int i=0; i < 1000; i++)
        {
            operationsTypeList.add(rand.nextInt(2)+1);
            operationsValList.add(rand.nextInt(126)+1);
        }

        List<Long> threadListTimes = new ArrayList<>();
        List<Long> classicListTimes = new ArrayList<>();
        long start;
        long end;

        for(int j=1; j<=10; j++)
        {
            System.out.println("lol");
            List<ListThread> listThreads = new ArrayList<>();
            head1.next = null;
            for(int i=0; i<j*100; i++)
            {
                listThreads.add(new ListThread(head1, operationsTypeList.get(i), operationsValList.get(i)));
            }
            start = System.nanoTime();
            for(int i=0; i<j*100; i++)
            {
                listThreads.get(i).start();
            }
            for(int i=0; i<j*100; i++)
            {
                listThreads.get(i).join();
            }
            end = System.nanoTime();
            threadListTimes.add(end - start);


            List<ListClassic> listClassics = new ArrayList<>();
            head2.next = null;
            for(int i=0; i<j*100; i++)
            {
                listClassics.add(new ListClassic(head2, operationsTypeList.get(i), operationsValList.get(i)));
            }
            start = System.nanoTime();
            for(int i=0; i<j*100; i++)
            {
                listClassics.get(i).start();
            }
            for(int i=0; i<j*100; i++)
            {
                listClassics.get(i).join();
            }
            end = System.nanoTime();
            classicListTimes.add(end - start);
        }

        for(int i=0; i<classicListTimes.size(); i++)
        {
            System.out.println(i + "; " + threadListTimes.get(i) + "; " + classicListTimes.get(i) );
        }

        System.out.println("lolo");

    }
}
