
package frigo;

import static com.google.common.base.Preconditions.checkArgument;

public class Box {

    public final int left;
    public final int right;

    public Box (int left, int right) {
        checkArgument(left <= right);
        this.left = left;
        this.right = right;
    }

    public int getCount () {
        return right - left + 1;
    }

}
