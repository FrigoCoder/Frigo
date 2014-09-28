
package frigo.dojo.bank;

import java.util.List;
import java.util.Random;

public class AccountOwner extends Thread {

    private Account account;
    private List<Account> accounts;
    private Random random;

    public AccountOwner(Account account, List<Account> accounts) {
        super("Account owner " + account.getName());
        this.account = account;
        this.accounts = accounts;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            step();
        }
    }

    private void step() {
        switch (random.nextInt(3)) {
            case 0:
                account.deposit(random.nextInt(100));
                break;
            case 1:
                account.withdraw(random.nextInt(100));
                break;
            case 2:
                account.transferTo(getRandomDestinationAccount(), random.nextInt(100));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Account getRandomDestinationAccount() {
        while (true) {
            int index = random.nextInt(accounts.size());
            if (accounts.get(index) != account) {
                return accounts.get(index);
            }
        }
    }

}
