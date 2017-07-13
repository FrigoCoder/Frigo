
package frigo.dojo.bank;

public class SimulationLocking {

    public static void main (String[] args) {
        AccountFactory accountFactory = name -> new AccountLocking(name);
        Bank bank = new Bank(accountFactory, 10);
        bank.run();
    }

}
