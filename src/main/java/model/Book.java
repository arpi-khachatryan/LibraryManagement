package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static util.DateUtil.dateToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private Author author;
    private double count;
    private String genre;
    private double price;
    private User registeredUser;
    private Date registrationDate;
}