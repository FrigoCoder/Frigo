
package frigo.dojo.bank;

import java.lang.management.ManagementFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.SneakyThrows;

public class Bank {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bank.class);

    private Account[] accounts;
    private Owner[] owners;

    public Bank (AccountFactory factory, int n) {
        accounts = new Account[n];
        owners = new Owner[n];
        for( int i = 0; i < n; i++ ){
            accounts[i] = new AccountLogDecorator(factory.createAccount("" + i));
            owners[i] = new Owner(this, accounts[i]);
        }
    }

    public Account[] getAccounts () {
        return accounts.clone();
    }

    public void run () {
        for( Owner owner : owners ){
            owner.start();
        }
        waitUntilDeadlocks();
        LOGGER.error("Deadlocks detected. Can't stop monitor deadlocks. Have a nice day.");
    }

    @SneakyThrows
    private static void waitUntilDeadlocks () {
        while( !areThereAnyDeadlocks() ){
            Thread.sleep(1000);
        }
    }

    private static boolean areThereAnyDeadlocks () {
        return ManagementFactory.getThreadMXBean().findDeadlockedThreads() != null;
    }

}
