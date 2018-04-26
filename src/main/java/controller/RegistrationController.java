package controller;

import service.AdminService;
import util.Render;

import static spark.Spark.get;
import static spark.Spark.post;


public class RegistrationController {
    private final AdminService adminService;
    private final Render render;

    public RegistrationController (){
        adminService = new AdminService();
        render = new Render();
        initRegistrationController();
    }

    /*          username: $scope.username,
                password: $scope.password,
                fullname: $scope.fullname,
                jmbg: $scope.jmbg,
                address: $scope.address,
                email: $scope.email,
                phone: $scope.phone */

    private void initRegistrationController(){
        get("/registration", (rq, rs) -> render.renderContent("registration.html"));
        post("/registration/signup", "application/json", (rq,rs) -> {
            String username = rq.queryParams("username");
            String password = rq.queryParams("password");
            String fullname = rq.queryParams("fullname");
            String jmbg = rq.queryParams("jmbg");
            String address = rq.queryParams("address");
            String email = rq.queryParams("email");
            String phone = rq.queryParams("phone");
            adminService.administratorRegistration(username, password, fullname, jmbg, address, email, phone);
            return "Your registration completed successfully!";
        });

    }
}
