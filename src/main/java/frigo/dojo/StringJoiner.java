
package frigo.dojo;

import java.util.Collection;
import java.util.Iterator;

public class StringJoiner {

    public static String join (Collection<String> strings, char delimiter) {
        if( strings == null ){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for( Iterator<String> iterator = strings.iterator(); iterator.hasNext(); ){
            String string = iterator.next();
            builder.append(string);
            if( iterator.hasNext() ){
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
}
