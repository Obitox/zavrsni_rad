package controller;

import service.AdminService;
import service.UserService;
import util.Render;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginController {

    private final AdminService adminService;
    private Render render;

    public LoginController(){
        this.adminService = new AdminService();
        initLoginController();
    }

    private void initLoginController(){
        render = new Render();
        get("/index", (request, response) -> render.renderContent("index.html"));
        post("/login", (request, response) -> adminService.adminLogin(request.queryParams("username"), request.queryParams("password")));
    }
}
