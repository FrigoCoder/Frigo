
package frigo.dojo.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountLoggerDecorator implements Account {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountLoggerDecorator.class);

    private Account account;

    public AccountLoggerDecorator(Account account) {
        this.account = account;
    }

    @Override
    public String getName() {
        return account.getName();
    }

    @Override
    public int getBalance() {
        return account.getBalance();
    }

    @Override
    public void deposit(int amount) {
        LOGGER.info("Depositing " + amount + " to account " + account.getName());
        account.deposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        LOGGER.info("Withdrawing " + amount + " from account " + account.getName());
        account.withdraw(amount);
    }

    @Override
    public void transferTo(Account destination, int amount) {
        LOGGER.info("Trying to transfer " + amount + " from account " + account.getName() + " to account " +
                    destination.getName());
        account.transferTo(destination, amount);
    }

}
