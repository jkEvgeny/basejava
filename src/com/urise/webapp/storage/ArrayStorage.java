package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[3];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Error! There is no such element " + r.getUuid());
        }
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Error! Unable to add a new resume " + r.getUuid() + "! Array overflow!");
        } else {
            int index = findIndex(r.getUuid());
            if (index == -1) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("The resume is already in the repository :" + r.getUuid());
            }
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Error! There is no such element " + uuid);
        return null;

    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
            size--;
        } else {
            System.out.println("Error! There is no such element " + uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
