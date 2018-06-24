package controller;

import service.AdminService;
import spark.Redirect;
import util.Render;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.redirect;

public class LoginController {

    private final AdminService adminService;
    private Render render;
    private final String SESSION_NAME;

    public LoginController(){
        this.adminService = new AdminService();
        this.SESSION_NAME = "user_id";
        initLoginController();
    }

    private void initLoginController(){
        render = new Render();
        get("/index", (request, response) -> render.renderContent("index.html"));
        post("/login", (request, response) -> {
            if(adminService.adminLogin(request.queryParams("username"), request.queryParams("password")).equals("Success")){
                request.session().attribute(SESSION_NAME);
                request.session().attribute(SESSION_NAME, adminService.getAdminId(request.queryParams("username")));
                return "Success";
            }
            return "Failed";
        });
    }
}
