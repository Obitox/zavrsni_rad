package controller;

import com.google.gson.Gson;
import service.StudentService;
import util.Render;

import static spark.Spark.*;

public class PanelController {
    private StudentService studentService;
    private Render render;

    public PanelController(){
        initPanelController();
    }

    private void initPanelController(){
        studentService = new StudentService();
        render = new Render();
        get("/panel", (request,response) -> render.renderContent("admin-panel.html"));
        get("/panel/data", (request,response) -> new Gson().toJson(studentService.retrieveStudents()));
        put("/panel/data/update", (request,response) -> studentService.updateStudent( Integer.parseInt(request.queryParams("student_id")),
                                                                                 request.queryParams("username"),
                                                                                 request.queryParams("password"),
                                                                                 request.queryParams("email"),
                                                                                 request.queryParams("jmbg"),
                                                                                 request.queryParams("fullname"),
                                                                                 request.queryParams("index")));
        delete("/panel/data/delete/:student_id", (request,response) -> studentService.deleteStudent(Integer.parseInt(request.params(":student_id"))));
        post("/panel/data/create", (request, response) -> studentService.studentRegistration(request.queryParams("username"),
                                                                                 request.queryParams("password"),
                                                                                 request.queryParams("email"),
                                                                                 request.queryParams("jmbg"),
                                                                                 request.queryParams("fullname"),
                                                                                 request.queryParams("index")));
        options("/panel/data/options", (request, response) -> response.raw());
    }
}
