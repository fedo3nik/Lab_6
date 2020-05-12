package Skybase;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Client extends Thread implements IClient {
    private int _clientNumber;
    private Base _callBase;
    private Semaphore _semaphore;
    private int _priority;
    private int _age;

    public Client(int clientNumber, int age, Base base, Semaphore semaphore)
    {
        this._clientNumber = clientNumber;
        this._age = age;
        this._callBase = base;
        this._semaphore = semaphore;

        if(this._age < 55)
        {
            this._priority =Thread.MIN_PRIORITY;
        }
        else
        {
            this._priority = Thread.MAX_PRIORITY;
        }
    }

    @Override
    public String GetClient() {return "Number: " + this._clientNumber + " , age: " + this._age; }

    public void run() {
        Random rand = new Random();
        int waitingTime = rand.nextInt(50000) + 100;
        System.out.println("Client " + this.GetClient() + " coming..");

        try {
            if (_semaphore.tryAcquire(waitingTime, TimeUnit.MICROSECONDS)) {
                System.out.println("Client " + this.GetClient() + " have a dialog");
                _callBase.connect(this);
                _callBase.showLines();
                Thread.sleep(rand.nextInt(1000) + 500);
                _callBase.disconnect(this);
                _semaphore.release();
                System.out.println("Client " + this.GetClient() + " end dialog");
            } else {
                System.out.println("Client " + this.GetClient() + " left");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
