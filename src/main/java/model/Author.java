package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static util.DateUtil.dateToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private String name;
    private String surname;
    private String email;
    private Gender gender;
    private Date registrationDate;
}
