package repositories;

public interface InterfaceIterator <E> {

    boolean hasNext();

    E next ();

    void remove();
}
