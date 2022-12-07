package repositories;

import exceptions.EntityNotFoundException;
import models.Model;

import java.util.Arrays;

public class GenericsRepository<E extends Model> implements InterfaceRepository<E> {

    private E[] arrayGenericRepository;


    public GenericsRepository(E[] arrayGenericRepository) {

        this.arrayGenericRepository = arrayGenericRepository;
        remove(0);
    }

    public int size() {
        int i = 0;
        for (E element : arrayGenericRepository) {
            if (element != null) {
                i++;
            }
        }
        return i;
    }

    public boolean isEmpty() {
        int i = 0;
        for (E element : arrayGenericRepository) {
            if (element != null) {
                i++;
            }
        }
        if (i == 0) {
            return true;
        } else {
            return false;
        }
    }

    public E get(int index) throws ArrayIndexOutOfBoundsException {
        return arrayGenericRepository[index];
    }

    public void add(E element) {
        arrayGenericRepository = Arrays.copyOf(arrayGenericRepository, arrayGenericRepository.length + 1);
        arrayGenericRepository[arrayGenericRepository.length - 1] = element;

    }


    public void add(E element, int index) {
        if (arrayGenericRepository.length != 0 && arrayGenericRepository.length > index) {
            arrayGenericRepository[index] = element;
        } else {
            System.out.println("Index out of bounds");
        }
    }

    public void remove(int index) {
        if (index >= arrayGenericRepository.length && arrayGenericRepository.length != 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            for (int i = index; i < arrayGenericRepository.length - 1; i++) {
                arrayGenericRepository[i] = arrayGenericRepository[i + 1];
                arrayGenericRepository[i + 1] = null;
            }
            arrayGenericRepository = Arrays
                    .copyOfRange(arrayGenericRepository, 0, arrayGenericRepository.length - 1);
        }

    }

    public E[] getArrayGenericRepository() {
        return arrayGenericRepository;
    }

    public void setArrayGenericRepository(E[] arrayGenericRepository) {
        this.arrayGenericRepository = arrayGenericRepository;
    }

    public int objectsTotal() {
        int i = 0;
        for (E element : arrayGenericRepository) {
            if (element != null) {
                i++;
            }
        }
        return i;
    }

    @Override
    public String toString() {
        if (arrayGenericRepository.length != 0) {
            return "GenericsRepository{" +
                    "arrayGenericRepository=" + Arrays.toString(arrayGenericRepository) +
                    '}';
        }
        return "GenericsRepository is null";
    }

    public E getById(int id) throws EntityNotFoundException {
        for (E element : arrayGenericRepository) {
            if (element.getID() == id) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");
    }


    // ==================== homework 16 ================
    public SimpleIterator<E> simpleIterator() {
        return new SimpleIterator<E>(arrayGenericRepository) {

            public int cursor = -1;

            @Override
            public boolean hasNext() {
                if (arrayGenericRepository.length - 1 > cursor && arrayGenericRepository.length != 0) {
                    return true;
                }
                return false;
            }

            @Override
            public E next() throws ArrayIndexOutOfBoundsException {
                cursor++;
                return arrayGenericRepository[cursor];
            }

            @Override
            public void remove() throws ArrayIndexOutOfBoundsException {
                if (cursor > arrayGenericRepository.length || cursor == -1) {
                    throw new ArrayIndexOutOfBoundsException();
                } else {
                    for (int i = cursor; i < arrayGenericRepository.length - 1; i++) {
                        arrayGenericRepository[i] = arrayGenericRepository[i + 1];
                        arrayGenericRepository[i + 1] = null;
                    }
                    arrayGenericRepository = Arrays
                            .copyOfRange(arrayGenericRepository, 0, arrayGenericRepository.length - 1);
                }
            }
        };
    }


    public void findAll() throws ArrayIndexOutOfBoundsException {
        SimpleIterator<E> iteratorFindAll = simpleIterator();
        while (iteratorFindAll.hasNext()) {
            System.out.println(iteratorFindAll.next());
        }
    }


}

