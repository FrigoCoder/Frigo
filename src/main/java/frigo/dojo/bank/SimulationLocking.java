
package frigo.dojo.bank;

public class SimulationLocking {

    public static void main (String[] args) {
        AccountFactory accountFactory = new AccountFactory() {

            @Override
            public Account createAccount (String name) {
                return new AccountLocking(name);
            }
        };
        Bank bank = new Bank(accountFactory, 10);
        bank.run();
    }

}
