import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class WeightedListTest {

    private WeightedList<String> getList() {
        return new WeightedList<>();
    }

    private WeightedList<String> filledList() {
        WeightedList<String> list = getList();
        list.add("3", 3);
        list.add("1", 1);
        list.add("2", 2);
        list.add("5", 5);
        list.add("4", 4);

        return list;
    }

    @Test
    void size() {
        assertEquals(5, filledList().size());
        assertEquals(0, getList().size());
    }

    @Test
    void isEmpty() {
        assertFalse(filledList().isEmpty());
        assertTrue(getList().isEmpty());
    }

    @Test
    void contains() {
        assertTrue(filledList().contains("1"));
        assertFalse(filledList().contains("7"));
        assertFalse(getList().contains("7"));
    }

    @Test
    void iterator() {
        Iterator<String> iterator = filledList().iterator();
        iterator.next();
        String a = iterator.next();
        assertEquals("4", a);
    }

    @Test
    void toArray() {
        assertArrayEquals(new String[]{"5", "4", "3", "2", "1"}, filledList().toArray());
        assertArrayEquals(new String[]{}, getList().toArray());
    }

    @Test
    void toArray1() {
        String[] array = filledList().toArray(new String[filledList().size()]);
        assertArrayEquals(new String[]{"5", "4", "3", "2", "1"}, array);
    }

    @Test
    void add() {
        WeightedList<String> array = filledList();
        assertTrue(array.add("0"));
        assertFalse(array.add("0"));
        assertArrayEquals(new String[]{"5", "4", "3", "2", "1", "0"}, array.toArray());
    }

    @Test
    void add1() {
        WeightedList<String> array = filledList();
        array.add(1, "0");
        array.add(4, "0");
        assertArrayEquals(new String[]{"5", "4", "3", "2", "1", "0"}, array.toArray());
    }

    @Test
    void add2() {
        WeightedList<String> array = filledList();
        assertTrue(array.add("10", 10));
        assertFalse(array.add("10", 2));
        array.add("a", 5);
        array.add("b", 1);
        array.add("c", 1);
        assertArrayEquals(new String[]{"10", "5", "a", "4", "3", "2", "1", "b", "c"}, array.toArray());
    }

    @Test
    void increment() {
        WeightedList<String> array = filledList();
        assertFalse(array.increment("10"));
        assertTrue(array.increment("1"));
        assertEquals(2, (int) array.getWeight("1"));
        assertArrayEquals(new String[]{"5", "4", "3", "2", "1"}, array.toArray());
        array.increment("1");
        assertArrayEquals(new String[]{"5", "4", "3", "1", "2"}, array.toArray());
        array.increment("5");
        assertArrayEquals(new String[]{"5", "4", "3", "1", "2"}, array.toArray());
        array.increment("2");
        assertArrayEquals(new String[]{"5", "4", "3", "1", "2"}, array.toArray());
        array.increment("2");
        assertArrayEquals(new String[]{"5", "4", "2", "3", "1"}, array.toArray());
    }

    @Test
    void decrement() {
        WeightedList<String> array = filledList();
        assertFalse(array.decrement("10"));
        assertTrue(array.decrement("5"));
        assertEquals(4, (int) array.getWeight("5"));
        assertArrayEquals(new String[]{"5", "4", "3", "2", "1"}, array.toArray());
        array.decrement("5");
        assertEquals(3, (int) array.getWeight("5"));
        assertArrayEquals(new String[]{"4", "5", "3", "2", "1"}, array.toArray());
        array.decrement("5");
        assertArrayEquals(new String[]{"4", "3", "5", "2", "1"}, array.toArray());
        array.decrement("5");
        assertArrayEquals(new String[]{"4", "3", "2", "5", "1"}, array.toArray());
        array.decrement("5");
        assertArrayEquals(new String[]{"4", "3", "2", "1", "5"}, array.toArray());
        array.decrement("5");
        assertArrayEquals(new String[]{"4", "3", "2", "1", "5"}, array.toArray());
        array.decrement("1");
        assertArrayEquals(new String[]{"4", "3", "2", "1", "5"}, array.toArray());
        array.decrement("1");
        assertArrayEquals(new String[]{"4", "3", "2", "1", "5"}, array.toArray());
        array.decrement("1");
        assertArrayEquals(new String[]{"4", "3", "2", "5", "1"}, array.toArray());
        array.decrement("3");
        array.decrement("4");
        array.decrement("4");
        assertArrayEquals(new String[]{"4", "3", "2", "5", "1"}, array.toArray());
        array.decrement("4");
        assertArrayEquals(new String[]{"3", "2", "4", "5", "1"}, array.toArray());

    }

    @Test
    void getWeight() {
        WeightedList<String> array = filledList();
        assertEquals(5, (int) array.getWeight("5"));
    }

    @Test
    void getWeight1() {
        WeightedList<String> array = filledList();
        assertEquals(5, (int) array.getWeight(0));
    }

    @Test
    void setWeight() {
        WeightedList<String> array = filledList();
        assertTrue(array.setWeight("1", 5));
        assertEquals(5, (int) array.getWeight("1"));
        assertArrayEquals(new String[]{"5", "1", "4", "3", "2"}, array.toArray());
    }

    @Test
    void setWeight1() {
        WeightedList<String> array = filledList();
        assertTrue(array.setWeight(4, 5));
        assertEquals(5, (int) array.getWeight("1"));
        assertArrayEquals(new String[]{"5", "1", "4", "3", "2"}, array.toArray());
    }

    @Test
    void remove() {
        WeightedList<String> array = filledList();
        array.remove("4");
        assertArrayEquals(new String[]{"5", "3", "2", "1"}, array.toArray());
    }

    @Test
    void containsAll() {
        WeightedList<String> array   = filledList();
        List<String>         compare = new ArrayList<>();
        compare.add("1");
        compare.add("2");
        assertTrue(array.containsAll(compare));
        compare.add("20");
        assertFalse(array.containsAll(compare));
    }

    @Test
    void addAll() {
        WeightedList<String> array = filledList();
        List<String>         add   = new ArrayList<>();
        add.add("10");
        add.add("20");
        array.addAll(add);
        assertArrayEquals(new String[]{"5", "4", "3", "2", "1", "10", "20"}, array.toArray());
    }

    @Test
    void addAll1() {
        WeightedList<String> array = filledList();
        List<String>         add   = new ArrayList<>();
        add.add("10");
        add.add("20");
        array.addAll(3, add);
        assertArrayEquals(new String[]{"5", "4", "3", "2", "1", "10", "20"}, array.toArray());
    }

    @Test
    void removeAll() {
        WeightedList<String> array  = filledList();
        List<String>         remove = new ArrayList<>();
        remove.add("4");
        remove.add("2");
        remove.add("20");
        array.removeAll(remove);
        assertArrayEquals(new String[]{"5", "3", "1"}, array.toArray());
        assertNull(array.getWeight("2"));
    }

    @Test
    void retainAll() {
        WeightedList<String> array  = filledList();
        List<String>         retain = new ArrayList<>();
        retain.add("4");
        retain.add("2");
        retain.add("20");
        array.retainAll(retain);
        assertArrayEquals(new String[]{"4", "2"}, array.toArray());
        assertNull(array.getWeight("5"));
    }

    @Test
    void clear() {
        WeightedList<String> array = filledList();
        array.clear();
        assertTrue(array.isEmpty());
        assertNull(array.getWeight("5"));
    }

    @Test
    void get() {
        WeightedList<String> array = filledList();
        assertEquals("4", array.get(1));
    }

    @Test
    void set() {
        WeightedList<String> array = filledList();
        assertEquals("5", array.set(0, "10"));
        assertArrayEquals(new String[]{"4", "3", "2", "1", "10"}, array.toArray());
    }

    @Test
    void remove1() {
        WeightedList<String> array = filledList();
        assertEquals("4", array.remove(1));
        assertArrayEquals(new String[]{"5", "3", "2", "1"}, array.toArray());
    }

    @Test
    void indexOf() {
        WeightedList<String> array = filledList();
        assertEquals(1, array.indexOf("4"));
    }

    @Test
    void lastIndexOf() {
        WeightedList<String> array = filledList();
        array.add("3");
        assertEquals(2, array.indexOf("3"));
    }

    @Test
    void listIterator() {
        WeightedList<String> array    = filledList();
        ListIterator<String> iterator = array.listIterator();
        iterator.next();
        assertEquals("4", iterator.next());
        iterator.previous();
        assertEquals("5", iterator.previous());
    }

    @Test
    void listIterator1() {
        WeightedList<String> array    = filledList();
        ListIterator<String> iterator = array.listIterator(2);
        iterator.next();
        assertEquals("2", iterator.next());
        iterator.previous();
        assertEquals("3", iterator.previous());
    }

    @Test
    void subList() {
        WeightedList<String> array = filledList();
        assertArrayEquals(new String[]{"4", "3"}, array.subList(1, 3).toArray());
    }
}