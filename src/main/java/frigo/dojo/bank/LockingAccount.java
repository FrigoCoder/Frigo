
package frigo.dojo.bank;

public class LockingAccount implements Account {

    private final String name;
    private int balance;

    public LockingAccount (String name) {
        this.name = name;
    }

    @Override
    public String getName () {
        return name;
    }

    @Override
    public synchronized int getBalance () {
        return balance;
    }

    @Override
    public synchronized void deposit (int amount) {
        balance += amount;
    }

    @Override
    public synchronized void withdraw (int amount) {
        balance -= amount;
    }

    @Override
    public synchronized void transferTo (Account destination, int amount) {
        withdraw(amount);
        destination.deposit(amount);
    }

}
