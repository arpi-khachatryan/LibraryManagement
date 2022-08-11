package storage;

import exception.AuthorNotFoundException;
import model.Author;
import model.Gender;

public class AuthorStorage {

    Author[] array = new Author[10];
    int size = 0;

    public void add(Author author) {
        if (size == array.length) {
            increaseArray();
        }
        array[size++] = author;
    }

    private void increaseArray() {
        Author[] authorArray = new Author[array.length * 2];
        for (int i = 0; i < size; i++) {
            authorArray[i] = array[i];
        }
        array = authorArray;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    public int getSize() {
        return size;
    }

    public Author getAuthorByIndex(int index) throws AuthorNotFoundException {
        if (index < -1 || index > size) {
            throw new AuthorNotFoundException("Author with " + index + " index does not exist");
        } else if (array[index] == null) {
            throw new AuthorNotFoundException("Author with " + index + " index does not exist");
        } else {
            return array[index];
        }
    }

    public void authorByGender(String genderStr) {
        try {
            int number = 0;
            for (int i = 0; i < size; i++) {
                if (array[i].getGender().equals(Gender.valueOf(genderStr.toUpperCase()))) {
                    System.out.println(array[i]);
                    ++number;
                }
            }
            if (number == 0) {
                System.out.println("There is no author with that gender");
            }
        } catch (Exception e) {
            System.out.println("Incorrect gender, please try again");
        }
    }

    public void authorByEmail(String email) {
        int number = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                System.out.println(array[i]);
                ++number;
            }
        }
        if (number == 0) {
            System.out.println("Incorrect email, please try again");
        }
    }
}