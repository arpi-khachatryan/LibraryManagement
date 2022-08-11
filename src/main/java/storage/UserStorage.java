package storage;

import model.User;

public class UserStorage {
    private User[] array = new User[10];
    private int size = 0;

    public void add(User user) {
        if (size == array.length) {
            increaseArray();
        }
        array[size++] = user;
    }

    private void increaseArray() {
        User[] userArray = new User[array.length * 2];
        System.arraycopy(array, 0, userArray, 0, array.length);
        array = userArray;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }

    public int getSize() {
        return size;
    }

    public User getUserByEmailAndPassword(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }
}