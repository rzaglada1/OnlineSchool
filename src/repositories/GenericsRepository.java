package repositories;

import exceptions.EntityNotFoundException;
import models.Model;

import java.util.Arrays;

public class GenericsRepository<E extends Model> implements  InterfaceRepository<E> {

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
        if (arrayGenericRepository.length != 0 && arrayGenericRepository.length > index) {
            E tmp = arrayGenericRepository[index];

            if (index != 0) {
                arrayGenericRepository[index] = arrayGenericRepository[arrayGenericRepository.length - 1];
                arrayGenericRepository[arrayGenericRepository.length - 1] = tmp;
                arrayGenericRepository = Arrays.copyOfRange(arrayGenericRepository, 0, arrayGenericRepository.length - 1);
            } else {
                arrayGenericRepository = Arrays.copyOfRange(arrayGenericRepository, 0, 0);
            }

        } else {

            System.out.println("Index out of bounds");
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

    public E getById (int id) throws EntityNotFoundException {
        for (E element: arrayGenericRepository) {
            if (element.getID() == id) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");
    }
}

