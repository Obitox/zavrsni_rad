package controller;

import com.google.gson.Gson;
import service.AdminService;
import util.Render;

import static spark.Spark.*;

public class PanelController {
    private final AdminService adminService;
    private Render render;

    public PanelController(){
        adminService = new AdminService();
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
        get("/panel", (request,response) -> render.renderContent("admin-panel.html"));
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
        options("/panel/data/options", (request, response) -> request
                .headers("Access-Control-Request-Headers"));

        before((request, response) -> response.header("Access-Control-Allow-Origin", "/panel/data/options"));
    }
}
