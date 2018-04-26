package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Administrator {
    private int administrator_id;
    private String username;
    private String password;
    private String full_name;
    private String address;
    private String email;
    private String phone;
}
