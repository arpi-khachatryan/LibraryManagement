package storage;

import model.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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

    public void createBooksExcel(String path) throws Exception {
        File directory = new File(path);
        if (directory.isFile()) {
            throw new RuntimeException("file must be directory");
        }
        File excelFile = new File(directory, "book_" + System.currentTimeMillis() + ".XLSX");
        excelFile.createNewFile();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Book");

        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue("No");

        Cell cell2 = row.createCell(1);
        cell2.setCellValue("Title");

        Cell cell3 = row.createCell(2);
        cell3.setCellValue("Author");

        Cell cell4 = row.createCell(3);
        cell4.setCellValue("Count");

        Cell cell5 = row.createCell(4);
        cell5.setCellValue("Genre");

        Cell cell6 = row.createCell(5);
        cell6.setCellValue("Price");

        Cell cell7 = row.createCell(6);
        cell7.setCellValue("Registered user");

        Cell cell8 = row.createCell(7);
        cell8.setCellValue("Registration date");

        for (int i = 0; i < size; i++) {
            Book book = booksArray[i];
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(i + 1);
            row1.createCell(1).setCellValue(book.getTitle());
            row1.createCell(2).setCellValue(book.getAuthor().getName());
            row1.createCell(3).setCellValue(book.getCount());
            row1.createCell(4).setCellValue(book.getGenre());
            row1.createCell(5).setCellValue(book.getPrice());
            row1.createCell(6).setCellValue(book.getRegisteredUser().getName() + " " + book.getRegisteredUser().getSurname());
            row1.createCell(7).setCellValue(book.getRegistrationDate().toString());
        }
        workbook.write(new FileOutputStream(excelFile));
        System.out.println("Excel file was successfully created");
    }

    public void readfromExcel(String fileLocation) throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        int cellNum = sheet.getRow(1).getLastCellNum();
        for (int i = 0; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < cellNum; j++) {
                Cell cell = row.getCell(j);
                switch (cell.getCellType()) {
                    case _NONE:
                        break;
                    case STRING:
                        System.out.println(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        System.out.println(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                }
            }
            System.out.println();
        }
    }
}


