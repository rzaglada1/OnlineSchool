package repositories;

public interface InterfaceRepository<E> {
    int size ();
    boolean isEmpty();
    E get(int index);
    void add(E element);
    void remove(int index);
}
