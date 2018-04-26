package controller;

import service.UserService;
import util.Render;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginController {

    private final UserService userService;
    private Render render;

    public LoginController(){
        this.userService = new UserService();
        initLoginController();
    }

    private void initLoginController(){
        render = new Render();
        get("/index", (request, response) -> render.renderContent("index.html"));
        post("/login", (request, response) -> userService.userLogin(request.queryParams("username"), request.queryParams("password")));
    }
}
