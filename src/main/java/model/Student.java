package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int student_id;
    private String korisnicko_ime;
    private String lozinka;
    private String ime;
    private String prezime;
    private String indeks;
    private String korisnik_slika;
}
