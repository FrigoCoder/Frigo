
package frigo.dojo;

import java.util.Collection;
import java.util.Iterator;

public class StringJoiner {

    public static String join (Collection<String> strings, char delimiter) {
        if( strings == null ){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = strings.iterator();
        if( iterator.hasNext() ){
            builder.append(iterator.next());
        }
        while( iterator.hasNext() ){
            builder.append(delimiter);
            builder.append(iterator.next());
        }
        return builder.toString();
    }

}
