package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int check = checkingForAvailability(r.getUuid());
        if (check != -1) {
            storage[size] = r;
        }else{
            System.out.println("Error! There is no such element");
        }
    }

    public void save(Resume r) {
        if (size == 10000) {
            System.out.println("Error! Unable to add a new resume!");
        } else {
            if (size == 0) {
                storage[size] = r;
                size++;
            } else {
                int check = checkingForAvailability(r.getUuid());
                if (check == -1) {
                    storage[size] = r;
                    size++;
                }
            }
        }
    }

    public Resume get(String uuid) {
        int check = checkingForAvailability(uuid);
        if (check != -1) {
            return storage[check];
        } else {
            System.out.println("Error! There is no such element");
            return null;
        }
    }

    public void delete(String uuid) {
        int check = checkingForAvailability(uuid);
        if (check != -1) {
            System.arraycopy(storage, check + 1, storage, check, size - 1);
            size--;
        }else {
            System.out.println("Error! There is no such element");
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

    private int checkingForAvailability(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            } else {
                return -1;
            }
        }
        return -1;
    }
}
