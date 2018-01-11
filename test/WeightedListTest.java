import org.junit.jupiter.api.Test;

import java.util.Iterator;

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
//        list.increment("4");
//        for(String a:list){
//            System.out.println(a);
//        }
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
        assertArrayEquals(new String[]{"10", "a", "5", "4", "3", "2", "c", "b", "1"}, array.toArray());
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
        array.setWeight("1", 5);
        assertEquals(5, (int) array.getWeight("1"));
        assertArrayEquals(new String[]{"5", "1", "4", "3", "2"}, array.toArray());
    }

    @Test
    void setWeight1() {
        WeightedList<String> array = filledList();
        array.setWeight(4, 5);
        assertEquals(5, (int) array.getWeight("1"));
        assertArrayEquals(new String[]{"5", "1", "4", "3", "2"}, array.toArray());
    }

    @Test
    void remove() {
    }

    @Test
    void containsAll() {
    }

    @Test
    void addAll() {
    }

    @Test
    void addAll1() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void retainAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void remove1() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void listIterator() {
    }

    @Test
    void listIterator1() {
    }

    @Test
    void subList() {
    }

}