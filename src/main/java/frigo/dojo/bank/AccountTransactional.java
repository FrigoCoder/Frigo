
package frigo.dojo.bank;

import static org.multiverse.api.StmUtils.atomic;

import java.util.concurrent.Callable;

import org.multiverse.api.StmUtils;
import org.multiverse.api.references.TxnInteger;

public class AccountTransactional implements Account {

    private final String name;
    private final TxnInteger balance;

    public AccountTransactional (String name) {
        this.name = name;
        balance = StmUtils.newTxnInteger(0);
    }

    @Override
    public String getName () {
        return name;
    }

    @Override
    public int getBalance () {
        return atomic(new Callable<Integer>() {

            @Override
            public Integer call () {
                return balance.get();
            }
        });
    }

    @Override
    public void deposit (final int amount) {
        atomic(new Runnable() {

            @Override
            public void run () {
                balance.increment(amount);
            }
        });
    }

    @Override
    public void withdraw (final int amount) {
        atomic(new Runnable() {

            @Override
            public void run () {
                balance.decrement(amount);
            }
        });
    }

    @Override
    public void transferTo (final Account destination, final int amount) {
        atomic(new Runnable() {

            @Override
            public void run () {
                withdraw(amount);
                destination.deposit(amount);
            }
        });
    }

}
