package util;

import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;

public class Render {
    public final String renderContent(String htmlFile){
        try {
            return IOUtils.toString(Spark.class.getResourceAsStream("/templates/" + htmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
