package repositories;

import models.Model;


public class SimpleIterator<E extends Model> implements InterfaceIterator<E> {


    private int cursor = -1;
    private final E[] arrayE;

    SimpleIterator(E[] arrayE) {
        this.arrayE = arrayE;
    }


    @Override
    public boolean hasNext() {
        if (arrayE.length > cursor && arrayE.length != 0) {
            return true;
        }
        return false;
    }

    @Override
    public E next() throws ArrayIndexOutOfBoundsException {
        cursor++;
        return arrayE[cursor];
    }

    @Override
    public void remove() throws ArrayIndexOutOfBoundsException {

        arrayE[cursor] = null;
    }
}
