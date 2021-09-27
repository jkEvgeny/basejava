package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Error! There is no such element " + uuid);
        return null;

    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Error! Unable to add a new resume " + r.getUuid() + "! Array overflow!");
        } else {
            int index = findIndex(r.getUuid());
            if (index < 0) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("The resume is already in the repository :" + r.getUuid());
            }
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("Error! There is no such element " + r.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
        } else {
            System.out.println("Error! There is no such element " + uuid);
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int findIndex(String uuid);
}
