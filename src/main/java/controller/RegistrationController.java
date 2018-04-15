package controller;

import service.StudentService;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import util.Render;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;


public class RegistrationController {
    private StudentService studentService = null;
    private Render render;

    public RegistrationController (){
        initRegistrationController();
    }

    private void initRegistrationController(){
        studentService = new StudentService();
        render = new Render();
        get("/registration", (rq, rs) -> render.renderContent("registration.html"));
        post("/registration/signup", "application/json", (rq,rs) -> {
            String username = rq.queryParams("username");
            String password = rq.queryParams("password");
            String email = rq.queryParams("email");
            String jmbg = rq.queryParams("jmbg");
            String fullname = rq.queryParams("fullname");
            String index = rq.queryParams("index");
            studentService.studentRegistration(username, password, email, jmbg, fullname, index);
            return "Your registration completed successfully!";
        });

    }
}
