
package frigo.dojo.bank;

import java.util.Random;

public class Owner extends Thread {

    private Bank bank;
    private Account account;
    private Random random;

    public Owner (Bank bank, Account account) {
        super("Account owner " + account.getName());
        this.bank = bank;
        this.account = account;
        random = new Random();
    }

    @Override
    public void run () {
        while( true ){
            account.transferTo(getRandomDestinationAccount(), random.nextInt(100));
        }
    }

    private Account getRandomDestinationAccount () {
        while( true ){
            Account destination = getRandomAccount();
            if( destination != account ){
                return destination;
            }
        }
    }

    private Account getRandomAccount () {
        Account[] accounts = bank.getAccounts();
        return accounts[random.nextInt(accounts.length)];

    }

}
