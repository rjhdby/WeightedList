import java.util.*;

public class WeightedList<T> implements List<T> {
    private List<T>         elements = new ArrayList<>();
    private Map<T, Integer> values   = new HashMap<>();

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return values.containsKey(o);
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return elements.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return add(t, 0);
    }

    @Override
    public void add(int index, T element) {
        add(element, 0);
    }

    public boolean add(T t, Integer weight) {
        if (values.containsKey(t)) {
            return false;
        }
        values.put(t, weight);
        if (elements.size() == 0) {
            elements.add(t);
            return true;
        }
        Integer i = elements.size() - 1;
        while (i > 0 && getWeight(i) < weight) i--;
        elements.add(i + 1, t);
        return true;
    }

    public boolean increment(T t) {
        return increment(t, 1);
    }

    public boolean decrement(T t) {
        return decrement(t, 1);
    }

    private boolean increment(T t, Integer amount) {
        if (!values.containsKey(t)) {
            return false;
        }
        Integer weight = values.get(t) + amount;
        if (elements.indexOf(t) == 0) {
            values.put(t, weight);
            return true;
        }
        Integer index = elements.indexOf(t);
        Integer i     = index - 1;
        while (i >= 0 && values.get(elements.get(i)) < weight) i--;
        elements.remove((int) index);
        elements.add(i + 1, t);
        values.put(t, weight);
        return true;
    }

    private boolean decrement(T t, Integer amount) {
        if (!elements.contains(t)) {
            return false;
        }
        Integer weight = values.get(t) - amount;
        if (elements.indexOf(t) == elements.size() - 1) {
            values.put(t, weight);
            return true;
        }
        Integer index = elements.indexOf(t);
        Integer i     = index + 1;
        Integer last  = elements.size();
        while (i < last && values.get(elements.get(i)) > weight) i++;
        elements.add(i, t);
        elements.remove((int) index);
        values.put(t, weight);
        return true;
    }

    public Integer getWeight(T t) {
        return values.get(t);
    }

    public Integer getWeight(int index) {
        return getWeight(elements.get(index));
    }

    public boolean setWeight(T t, Integer weight) {
        if (!elements.contains(t)) return false;
        Integer current = values.get(t);
        if (current < weight) {
            return increment(t, weight - current);
        } else {
            return decrement(t, current - weight);
        }
    }

    public boolean setWeight(int index, Integer weight) {
        return setWeight(elements.get(index), weight);
    }

    @Override
    public boolean remove(Object o) {
        values.remove(o);
        return elements.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element, 0);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object element : c) {
            remove(element);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (T element : elements) {
            if (!c.contains(element)) {
                remove(element);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        elements.clear();
        values.clear();
    }

    @Override
    public T get(int index) {
        return elements.get(index);
    }

    @Override
    public T set(int index, T element) {
        T old = elements.get(index);
        elements.remove(index);
        add(element);
        return old;
    }


    @Override
    public T remove(int index) {
        T old = elements.get(index);
        elements.remove(index);
        values.remove(old);
        return old;
    }

    @Override
    public int indexOf(Object o) {
        return elements.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return elements.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return elements.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return elements.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return elements.subList(fromIndex, toIndex);
    }
}
