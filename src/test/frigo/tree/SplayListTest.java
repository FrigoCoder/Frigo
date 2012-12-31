
package frigo.tree;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class SplayListTest {

    private IntegerItem item1;
    private IntegerItem item2;
    private IntegerItem item3;
    private SplayList<Integer, IntegerItem> list;

    @Before
    public void setUp () {
        list = new SplayList<>();
        item1 = new IntegerItem(1);
        item2 = new IntegerItem(2);
        item3 = new IntegerItem(3);
    }

    @Test
    public void testContainsNoItem () {
        assertThat(list.contains(item1.getKey()), is(false));
    }

    @Test
    public void testContainsOneItem () {
        list.insert(item1);
        assertThat(list.contains(item1.getKey()), is(true));
    }

    @Test
    public void testContainsTwoItem () {
        list.insert(item1);
        list.insert(item2);
        assertThat(list.contains(item1.getKey()), is(true));
        assertThat(list.contains(item2.getKey()), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindBadKey () {
        list.insert(item1);
        list.find(item2.getKey());
    }

    @Test
    public void testFindMovesItemToRoot () {
        list.insert(item3);
        list.insert(item2);
        list.insert(item1);
        list.find(item1.getKey());
        assertThat(item1.getRight(), is(item2));
        assertThat(item2.getRight(), is(item3));
        assertThat(item3.getRight(), nullValue());

        list.find(item2.getKey());
        assertThat(item1.getRight(), is(item3));
        assertThat(item2.getRight(), is(item1));
        assertThat(item3.getRight(), nullValue());

        list.find(item3.getKey());
        assertThat(item1.getRight(), nullValue());
        assertThat(item2.getRight(), is(item1));
        assertThat(item3.getRight(), is(item2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNoItem () {
        list.find(item1.getKey());
    }

    @Test
    public void testFindOneItem () {
        list.insert(item1);
        assertThat(list.find(item1.getKey()), is(item1));
    }

    @Test
    public void testFindTwoItems () {
        list.insert(item1);
        list.insert(item2);
        assertThat(list.find(item1.getKey()), is(item1));
        assertThat(list.find(item2.getKey()), is(item2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertDuplicateKey () {
        list.insert(item1);
        list.insert(item1);
    }

    @Test
    public void testIsEmptyOfNoItems () {
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void testIsEmptyOfOneItem () {
        list.insert(item1);
        assertThat(list.isEmpty(), is(false));
    }

    @Test
    public void testIsEmptyOfTwoItems () {
        list.insert(item1);
        list.insert(item2);
        assertThat(list.isEmpty(), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNoItems () {
        list.remove(item1.getKey());
    }

    @Test
    public void testRemoveOneItem () {
        list.insert(item1);
        list.remove(item1.getKey());
        assertThat(list.contains(item1.getKey()), is(false));
    }

    @Test
    public void testRemoveTwoItems () {
        list.insert(item1);
        list.insert(item2);
        list.remove(item1.getKey());
        list.remove(item2.getKey());
        assertThat(list.contains(item1.getKey()), is(false));
        assertThat(list.contains(item2.getKey()), is(false));
    }

    @Test
    public void testSizeOfNoItems () {
        assertThat(list.size(), is(0));
    }

    @Test
    public void testSizeOfOneItem () {
        list.insert(item1);
        assertThat(list.size(), is(1));
    }

    @Test
    public void testSizeOfTwoItems () {
        list.insert(item1);
        list.insert(item2);
        assertThat(list.size(), is(2));
    }

}
