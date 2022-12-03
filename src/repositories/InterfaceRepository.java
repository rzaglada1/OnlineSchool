package repositories;

public interface InterfaceRepository<E> {
    int size ();
    boolean isEmpty();
    E get(int index) throws Exception;
    void add(E element);
    void remove(int index);
}
