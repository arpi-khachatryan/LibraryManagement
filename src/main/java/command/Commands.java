package command;

public interface Commands {
    int LOGOUT = 0;
    int PRINT_ALL_BOOKS = 1;
    int PRINT_BOOKS_BY_AUTHOR_NAME = 2;
    int PRINT_BOOKS_BY_GENRE = 3;
    int PRINT_BOOKS_BY_PRICE_RANGE = 4;
    int PRINT_AUTHOR_BY_GENDER = 5;
    int PRINT_AUTHOR_BY_EMAIL = 6;
    int PRINT_ALL_AUTHORS = 7;
    int ADD_BOOK = 8;
    int ADD_AUTHOR = 9;
    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;

    static void printInitialCommands() {
        System.out.println("Please, input " + EXIT + " for exit");
        System.out.println("Please, input " + LOGIN + " for login");
        System.out.println("Please, input " + REGISTER + " for register");
    }

    static void printUserCommands() {
        System.out.println("Please, input " + LOGOUT + " for logout");
        System.out.println("Please, input " + PRINT_ALL_BOOKS + " to print all the books");
        System.out.println("Please, input " + PRINT_BOOKS_BY_AUTHOR_NAME + " to show all the books by your favorite author");
        System.out.println("Please, input " + PRINT_BOOKS_BY_GENRE + " to show all the books of your favorite genre");
        System.out.println("Please, input " + PRINT_BOOKS_BY_PRICE_RANGE + " to show all the books at a specified price");
        System.out.println("Please, input " + PRINT_AUTHOR_BY_GENDER + " to show the author by gender");
        System.out.println("Please, input " + PRINT_AUTHOR_BY_EMAIL + " to show the author by email");
        System.out.println("Please, input " + PRINT_ALL_AUTHORS + " to show all the authors");
    }

    static void printAdminCommands() {
        System.out.println("Please, input " + LOGOUT + " for logout");
        System.out.println("Please, input " + PRINT_ALL_BOOKS + " to print all the books");
        System.out.println("Please, input " + PRINT_BOOKS_BY_AUTHOR_NAME + " to show all the books by your favorite author");
        System.out.println("Please, input " + PRINT_BOOKS_BY_GENRE + " to show all the books of your favorite genre");
        System.out.println("Please, input " + PRINT_BOOKS_BY_PRICE_RANGE + " to show all the books at a specified price");
        System.out.println("Please, input " + PRINT_AUTHOR_BY_GENDER + " to show the author by gender");
        System.out.println("Please, input " + PRINT_AUTHOR_BY_EMAIL + " to show the author by email");
        System.out.println("Please, input " + PRINT_ALL_AUTHORS + " to show all the authors");
        System.out.println("Please, input " + ADD_BOOK + " to add a book");
        System.out.println("Please, input " + ADD_AUTHOR + " to add the author");
    }
}
