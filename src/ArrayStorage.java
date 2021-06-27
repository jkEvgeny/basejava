import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int index = 0;

    void clear() {
        Arrays.fill(storage, 0, index, null);
        index = 0;
    }

    void save(Resume r) {
        storage[index] = r;
        index++;
    }

    Resume get(String uuid) {
        for (int k = 0; k < index; k++) {
            if (storage[k].toString().equals(uuid)) {
                return storage[k];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int iUuid = 0;
        for (int k = 0; k < index; k++) {
            if (storage[k].toString().equals(uuid)) {
                iUuid = k;
            }
        }

        for (int i = 0; i < storage.length; i++) {
            if (i == iUuid) {
                // shifting elements
                if (storage.length - 1 - i >= 0) System.arraycopy(storage, i + 1, storage, i, storage.length - 1 - i);
                break;
            }
        }
        index--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, index);
    }

    int size() {
        return index;
    }
}
