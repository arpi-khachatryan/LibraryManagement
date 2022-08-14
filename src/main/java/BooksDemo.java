import command.Commands;
import exception.AuthorNotFoundException;
import model.*;
import storage.AuthorStorage;
import storage.BookStorage;
import storage.UserStorage;

import java.io.File;
import java.sql.SQLOutput;
import java.util.Scanner;

import static util.DateUtil.stringToDate;

public class BooksDemo implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static BookStorage bookStorage = new BookStorage();
    private static AuthorStorage authorStorage = new AuthorStorage();
    private static UserStorage userStorage = new UserStorage();
    private static User currentUser = null;

    public static void main(String[] args) {
        initData();
        boolean run = true;
        while (run) {
            Commands.printInitialCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Please, try again");
            }
        }
    }

    private static void initData() {
        User admin = new User("admin", "admin", "admin@gmail.com", "admin", Role.ADMIN);
        userStorage.add(admin);
        Author nicholas = new Author("Nicholas", " Sparks", "nicholassparks.com", Gender.MALE, stringToDate("02.09.2022"));
        authorStorage.add(nicholas);
        bookStorage.add(new Book("MessageInABottle", nicholas, 4, "novel", 2000, admin, stringToDate("01.01.2022")));
    }

    private static void login() {
        System.out.println("Please input email,password");
        String data = scanner.nextLine();
        String[] userData = data.split(",");
        User user = userStorage.getUserByEmailAndPassword(userData[0]);
        if (user == null) {
            System.out.println("User does not exist");
        } else {
            if (!user.getPassword().equals(userData[1])) {
                System.out.println("Password is incorrect");
            } else {
                currentUser = user;
                if (user.getRole() == Role.ADMIN) {
                    adminLogin();
                } else if (user.getRole() == Role.USER) {
                    userLogin();
                }
            }
        }
    }

    private static void register() {
        System.out.println("Please input name,surname,email,password");
        String data = scanner.nextLine();
        String[] userData = data.split(",");
        if (userData.length < 4) {
            System.out.println("Please input correct user data");
        } else {
            if (userStorage.getUserByEmailAndPassword(userData[2]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setRole(Role.USER);
                userStorage.add(user);
                System.out.println("You are registered");
            } else {
                System.out.println("User with " + userData[2] + " already exists");
            }
        }
    }

    private static void adminLogin() {
        System.out.println("Welcome " + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printAdminCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser = null;
                    run = false;
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    bookByAuthor();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    bookByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    bookByPriceRange();
                    break;
                case PRINT_AUTHOR_BY_GENDER:
                    printAuthorsByGender();
                    break;
                case PRINT_AUTHOR_BY_EMAIL:
                    printAuthorsByEmail();
                    break;
                case PRINT_ALL_AUTHORS:
                    authorStorage.print();
                    break;
                case DOWNLOAD_BOOKS_EXCEL:
                    downloadBooksExcel();
                    break;
                case ADD_BOOK:
                    addBooks();
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                default:
                    System.out.println("Please, try again");
            }
        }
    }

    private static void userLogin() {
        System.out.println("Welcome " + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser = null;
                    run = false;
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    bookByAuthor();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    bookByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    bookByPriceRange();
                    break;
                case PRINT_AUTHOR_BY_GENDER:
                    printAuthorsByGender();
                    break;
                case PRINT_AUTHOR_BY_EMAIL:
                    printAuthorsByEmail();
                    break;
                case PRINT_ALL_AUTHORS:
                    authorStorage.print();
                    break;
                case DOWNLOAD_BOOKS_EXCEL:
                    downloadBooksExcel();
                    break;
                default:
                    System.out.println("Please, try again");
            }
        }
    }

    private static void downloadBooksExcel() {
        System.out.println("Please, input the location of the file");
        String dirPath = scanner.nextLine();
        try {
            File directory = new File(dirPath);
            bookStorage.createBooksExcel(dirPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addBooks() {
        if (authorStorage.getSize() == 0) {
            System.out.println("Please add the author");
            addAuthor();
            addBook();
        } else {
            authorStorage.print();
            System.out.println("Do you want to add an author, YES or NO?");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("YES")) {
                addAuthor();
                addBook();
            } else if (answer.equalsIgnoreCase("NO")) {
                addBook();
            } else {
                System.out.println("Unclear answer");
                addBooks();
            }
        }
    }

    private static void addBook() {
        authorStorage.print();
        System.out.println("Please choose the author's index");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine());
            try {
                Author author = authorStorage.getAuthorByIndex(index);
                System.out.println("Please input the title of the books");
                String title = scanner.nextLine();
                double count = trueCount();
                System.out.println("Please input the genre of the book");
                String genre = scanner.nextLine();
                double price = truePrice();
                System.out.println("Please input the date");
                String strDate = scanner.nextLine();
                Book book = new Book(title, author, count, genre, price, currentUser, stringToDate(strDate));
                bookStorage.add(book);
                System.out.println("Thank you, the book was added");
            } catch (AuthorNotFoundException e) {
                System.out.println(e.getMessage());
                addBook();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input only a number");
            addBook();
        }
    }

    private static double trueCount() {
        double count = 0;
        try {
            System.out.println("Please input the number of books");
            count = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please input only a number");
            trueCount();
        }
        return count;
    }

    private static double truePrice() {
        double price = 0;
        try {
            System.out.println("Please input the price of the book");
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please input only a number");
            truePrice();
        }
        return price;
    }

    private static void bookByAuthor() {
        System.out.println("Please input your favorite author");
        String author = scanner.nextLine();
        if (author != null && !author.trim().equals("")) {
            bookStorage.bookByAuther(author);
        } else {
            System.out.println("Unclear answer");
        }
    }

    private static void bookByGenre() {
        System.out.println("Please input your favorite genre");
        String genre = scanner.nextLine();
        if (genre != null && !genre.trim().equals("")) {
            bookStorage.bookByGenre(genre);
        } else {
            System.out.println("Unclear answer");
        }
    }

    private static void bookByPriceRange() {
        double min, max;
        try {
            System.out.println("Please input the minimum amount you prefer");
            min = Double.parseDouble(scanner.nextLine());
            System.out.println("Please input the maximum amount you prefer");
            max = Double.parseDouble(scanner.nextLine());
            bookStorage.bookByPriceRange(min, max);
        } catch (NumberFormatException e) {
            System.out.println("Please input only a number");
            bookByPriceRange();
        }
    }

    private static void addAuthor() {
        System.out.println("Please input author's name");
        String name = scanner.nextLine();
        System.out.println("Please input author's surname");
        String surname = scanner.nextLine();
        System.out.println("Please input the authors's email address");
        String email = scanner.nextLine();
        Gender gender = trueGender();
        System.out.println("Please input the date");
        String strDate = scanner.nextLine();
        Author author = new Author(name, surname, email, gender, stringToDate(strDate));
        authorStorage.add(author);
        System.out.println("Thank you, the author was added");
    }

    private static Gender trueGender() {
        Gender gender;
        try {
            System.out.println("Please input the author's gender");
            String strGender = scanner.nextLine();
            gender = Gender.valueOf(strGender.toUpperCase());
            if (gender.equals(Gender.FEMALE) || gender.equals(Gender.MALE)) ;
        } catch (IllegalArgumentException e) {
            System.err.println("Incorrect gender");
            gender = trueGender();
        }
        return gender;
    }

    private static void printAuthorsByGender() {
        System.out.println("Please input the gender");
        String gender = scanner.nextLine();
        if (gender != null && !gender.trim().equals("")) {
            authorStorage.authorByGender(gender);
        } else {
            System.out.println("Unclear answer");
        }
    }

    private static void printAuthorsByEmail() {
        System.out.println("Please input the email address");
        String email = scanner.nextLine();
        if (email != null && !email.trim().equals("")) {
            authorStorage.authorByEmail(email);
        } else {
            System.out.println("Unclear answer");
        }
    }
}
