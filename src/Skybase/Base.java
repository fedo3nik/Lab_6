package Skybase;

public class Base implements IBase{
    private int _amountOfOperators;
    private Client[] _operators;

    public Base(int amountOfOperators) {
        this._amountOfOperators = amountOfOperators;
        this._operators = new Client[amountOfOperators];
    }


    @Override
    public void showLines() {
        System.out.println("Line:{");
        for (int i = 0; i < _amountOfOperators; i++) {
            if (_operators[i] != null) {
                System.out.println(i+1 + ") " + _operators[i].GetClient());
            } else {
                System.out.println(i+1 + ") empty");
            }
        }
        System.out.println("}");
    }

    @Override
    public int FreeOperator() {
        for (int i = 0; i < this._amountOfOperators; i++) {
            if (_operators[i] == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void connect(Client client) {
        _operators[this.FreeOperator()] = client;
    }

    @Override
    public void disconnect(Client client) {
        for (int i = 0; i < this._amountOfOperators; i++) {
            if (_operators[i] == client) {
                _operators[i] = null;
            }
        }
    }


}
