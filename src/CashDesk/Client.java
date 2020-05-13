package CashDesk;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Client implements Runnable{
    private int _number;
    private final boolean[] _places;
    private final Semaphore _semaphore;

    public Client(int number, boolean[] places, Semaphore semaphore) {
        this._number = number;
        this._places = places;
        this._semaphore = semaphore;
    }

    @Override
    public void run() {
        System.out.printf("Клиент №%d стал в очередь.\n", _number);
        try {
            _semaphore.acquire();

            int parkingNumber = -1;

            //Ищем свободное место и паркуемся
            synchronized (_places) {
                for (int i = 0; i < 5; i++)
                    if (!_places[i]) {
                        _places[i] = true;
                        parkingNumber = i;
                        System.out.printf("Клиент №%d занял кассу %d.\n", _number, i);
                        break;
                    }
            }

            Thread.sleep(new Random().nextInt(3000));

            synchronized (_places) {
                _places[parkingNumber] = false;
            }

            System.out.printf("Клиент №%d покинул очередь.\n", _number);
            _semaphore.release();
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
