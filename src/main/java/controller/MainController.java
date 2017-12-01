package controller;

import model.Student;
import org.eclipse.jetty.server.Server;
import service.StudentService;
import spark.ModelAndView;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class MainController {
    public static void main(String[] args) {
        staticFileLocation("/public");

        ThymeleafTemplateEngine thymeleafTemplateEngine = new ThymeleafTemplateEngine();

        StudentService studentService = new StudentService();

        Map<String, List<Student>> map = new HashMap();
        map.put("lista", studentService.getAllStudents());

        get("/index", (rq, rs) -> thymeleafTemplateEngine.render(new ModelAndView(map, "index")));
        get("/create", (rq, rs) -> thymeleafTemplateEngine.render(new ModelAndView(map, "create")));
        get("/student", (rq, rs) -> {
            map.put("lista",studentService.getAllStudents());
            return thymeleafTemplateEngine.render(
                    new ModelAndView(map, "student")
            );
        });
        post("/create/createStudent", (rq, rs) ->
                {
                    String korisnicko_ime = rq.queryParams("korisnicko_ime");
                    String lozinka = rq.queryParams("lozinka");
                    String ime = rq.queryParams("ime");
                    String prezime = rq.queryParams("prezime");
                    String indeks = rq.queryParams("indeks");
                    studentService.createStudent(korisnicko_ime,lozinka, ime, prezime, indeks, "img/default.png");
                    return "Korisnik uspesno unet u bazu.";
                }
        );
        delete("/delete/deleteStudent", (rq, rs) -> {
           studentService.deleteStudent(rq.queryParams("id"));
           map.put("lista", studentService.getAllStudents());
           return thymeleafTemplateEngine.render(new ModelAndView(map, "delete"));
        });
        get("/update", (rq, rs) -> {
            map.put("lista",studentService.getAllStudents());
            return thymeleafTemplateEngine.render(
                    new ModelAndView(map, "update")
            );
        });
        put("/update", (rq, rs) -> {
            String korisnicko_ime = rq.queryParams("korisnicko_ime");
            String lozinka = rq.queryParams("lozinka");
            String ime = rq.queryParams("ime");
            String prezime = rq.queryParams("prezime");
            String indeks = rq.queryParams("indeks");
            System.out.println(korisnicko_ime + " " + lozinka);
            studentService.updateStudent(korisnicko_ime, lozinka, ime, prezime, indeks, "img/default.png");
            map.put("lista",studentService.getAllStudents());
            return thymeleafTemplateEngine.render(
                    new ModelAndView(map, "update")
            );
        });
    }
}
