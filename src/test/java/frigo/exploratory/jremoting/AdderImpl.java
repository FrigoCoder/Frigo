
package frigo.exploratory.jremoting;

import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

public class AdderImpl implements Adder {

    @Override
    public int add (int x, int y) {
        return x + y;
    }

    @Override
    public String getCaller () {
        try{
            return RemoteServer.getClientHost();
        }catch( ServerNotActiveException e ){
            throw new RuntimeException(e);
        }
    }

}
