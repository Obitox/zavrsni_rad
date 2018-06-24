package controller;

import com.google.gson.Gson;
import service.AdminService;
import util.Render;

import static spark.Spark.*;

public class PanelController {
    private final AdminService adminService;
    private Render render;
    private final String SESSION_NAME;

    public PanelController(){
        adminService = new AdminService();
        this.SESSION_NAME = "user_id";
        initPanelController();
    }

    /*          username: $scope.username,
                password: $scope.password,
                fullname: $scope.fullname,
                jmbg: $scope.jmbg,
                address: $scope.address,
                email: $scope.email,
                phone: $scope.phone*/

    private void initPanelController(){
        render = new Render();
        get("/panel", (request,response) -> {
            if (request.session().attribute("user_id") == null) {
                halt(401, render.renderContent("error.html"));
            }
            return render.renderContent("admin-panel.html");
        });
        get("/panel/data", (request,response) -> new Gson().toJson(adminService.retrieveStudents()));
        put("/panel/data/update", (request,response) -> adminService.updateStudent( Integer.parseInt(request.queryParams("student_id")),
                                                                                                          request.queryParams("username"),
                                                                                                          request.queryParams("password"),
                                                                                                          request.queryParams("email"),
                                                                                                          request.queryParams("jmbg"),
                                                                                                          request.queryParams("fullname"),
                                                                                                          request.queryParams("index"),
                                                                                                          request.queryParams("address")));
        delete("/panel/data/delete/:student_id", (request,response) -> adminService.deleteStudent(Integer.parseInt(request.params(":student_id"))));
        post("/panel/data/create", (request, response) -> adminService.createStudent(request.queryParams("username"),
                                                                                          request.queryParams("password"),
                                                                                          request.queryParams("email"),
                                                                                          request.queryParams("jmbg"),
                                                                                          request.queryParams("fullname"),
                                                                                          request.queryParams("index"),
                                                                                          request.queryParams("address")));
        get("/panel/logout", (request, response) -> {
            if (request.session().attribute("user_id") == null) {
                halt(401, render.renderContent("logout.html"));
            }
            request.session().removeAttribute(this.SESSION_NAME);
            return render.renderContent("logout.html");
        });
    }
}
