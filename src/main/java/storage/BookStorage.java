package storage;

import model.Book;

public class BookStorage {
    private Book[] booksArray = new Book[10];
    private int size = 0;

    public void add(Book book) {
        if (size == booksArray.length) {
            increase();
        }
        booksArray[size++] = book;
    }

    private void increase() {
        Book[] booksNewArray = new Book[booksArray.length * 2];
        for (int i = 0; i < size; i++) {
            booksNewArray[i] = booksArray[i];
        }
        booksArray = booksNewArray;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + booksArray[i]);
        }
    }

    public Book getBookByIndex(int index) {
        if (index >= 0 && index < size) {
            return booksArray[index];
        }
        return null;
    }

    public void bookByAuther(String auther) {
        int number = 0;
        for (int i = 0; i < size; i++) {
            if (booksArray[i].getAuthor().getName().equals(auther)) {
                System.out.println(booksArray[i]);
                ++number;
            }
        }
        if (number == 0) {
            System.out.println("Incorrect name, please try again");
        }
    }

    public void bookByGenre(String genre) {
        int number = 0;
        for (int i = 0; i < size; i++) {
            if (booksArray[i].getGenre().equals(genre)) {
                System.out.println(booksArray[i]);
                ++number;
            }
        }
        if (number == 0) {
            System.out.println("Sorry, the book was not found");
        }
    }

    public void bookByPriceRange(double min, double max) {
        int number = 0;
        for (int i = 0; i < size; i++) {
            if (booksArray[i].getPrice() > min - 1 && booksArray[i].getPrice() < max + 1) {
                System.out.println(booksArray[i]);
                ++number;
            }
        }
        if (number == 0) {
            System.out.println("Sorry, there is no book at that price");
        }
    }
}


