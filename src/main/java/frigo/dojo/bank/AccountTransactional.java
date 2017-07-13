
package frigo.dojo.bank;

import static org.multiverse.api.StmUtils.atomic;

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
        return atomic( () -> balance.get());
    }

    @Override
    public void deposit (final int amount) {
        atomic( () -> balance.increment(amount));
    }

    @Override
    public void withdraw (final int amount) {
        atomic( () -> balance.decrement(amount));
    }

    @Override
    public void transferTo (final Account destination, final int amount) {
        atomic( () -> {
            withdraw(amount);
            destination.deposit(amount);
        });
    }

}
