package controller;

import service.StudentService;

import util.Render;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginController {

    private StudentService studentService = null;
    private Render render;

    public LoginController(){
        initLoginController();
    }

    private void initLoginController(){
        this.studentService = new StudentService();
        render = new Render();
        get("/index", (request, response) -> render.renderContent("index.html"));
        post("/login", (request, response) -> studentService.studentLogin(request.queryParams("username"), request.queryParams("password")));
    }
}
