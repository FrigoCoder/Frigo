
package frigo.dojo.bank;

public interface Account {

    String getName();

    int getBalance();

    void deposit(int amount);

    void withdraw(int amount);

    void transferTo(Account destination, int amount);

}
