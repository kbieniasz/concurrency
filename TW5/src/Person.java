public class Person extends Thread{
    public boolean isWriter;
    public ReadingRoom readingRoom;


    public Person(boolean isWriter,  ReadingRoom readingRoom)
    {
        this.isWriter = isWriter;
        this.readingRoom = readingRoom;

    }

    @Override
    public void run() {
        if(isWriter)
        {

        }
        else
        {

        }
    }
}
