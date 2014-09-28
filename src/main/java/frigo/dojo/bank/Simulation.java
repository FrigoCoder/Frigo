
package frigo.dojo.bank;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simulation {

    private static final Logger LOGGER = LoggerFactory.getLogger(Simulation.class);

    public static void main (String[] args) {
        Simulation simulation = new Simulation(10);
        simulation.run();
    }

    private List<Account> accounts;
    private List<AccountOwner> owners;

    public Simulation (int n) {
        createAccounts(n);
        createOwners();
    }

    private void createAccounts (int n) {
        accounts = new ArrayList<>(n);
        for( int i = 0; i < n; i++ ){
            accounts.add(new AccountLoggerDecorator(createAccount("" + i)));
        }
    }

    private Account createAccount (String name) {
        // return new TransactionAccount(name);
        return new LockingAccount(name);
    }

    private void createOwners () {
        owners = new LinkedList<>();
        for( Account account : accounts ){
            owners.add(new AccountOwner(account, accounts));
        }
    }

    public void run () {
        startOwners();
        waitUntilDeadlocks();
        LOGGER.error("Deadlocks detected. Can't stop monitor deadlocks. Have a nice day.");
    }

    private void startOwners () {
        for( AccountOwner owner : owners ){
            owner.start();
        }
    }

    private void waitUntilDeadlocks () {
        while( !areThereAnyDeadlocks() ){
            sleep();
        }
    }

    private boolean areThereAnyDeadlocks () {
        return ManagementFactory.getThreadMXBean().findDeadlockedThreads() != null;
    }

    private void sleep () {
        try{
            Thread.sleep(1000);
        }catch( InterruptedException e ){
            throw new RuntimeException(e);
        }
    }

}
