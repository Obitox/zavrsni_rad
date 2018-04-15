import controller.LoginController;
import controller.PanelController;
import controller.RegistrationController;

import static spark.Spark.staticFileLocation;

public class Main {

    public static void main(String[] args) {
        staticFileLocation("/public");

        new LoginController();
        new RegistrationController();
        new PanelController();
    }
}
