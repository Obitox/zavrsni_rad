package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int student_id;
    private String username;
    private String password;
    private String email;
    private String jmbg;
    private String fullname;
    private String index;
}
