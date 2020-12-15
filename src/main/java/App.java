import DAO.Sql2oDepartment;
import DAO.Sql2oDepartmentNews;
import DAO.Sql2oGeneralNews;
import DAO.Sql2oUser;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        System.out.println("Getting ready!");

        String connectionString = "jdbc:postgresql://localhost:5432/news";


        Sql2o sql2o = new Sql2o(connectionString, "moringa", "fRANC220515");
        Sql2oGeneralNews sql2oGeneralNews = new Sql2oGeneralNews(sql2o);
        Sql2oDepartment sql2oDepartment = new Sql2oDepartment(sql2o);
        Sql2oDepartmentNews sql2oDepartmentNews = new Sql2oDepartmentNews(sql2o);
        Sql2oUser sql2oUser = new Sql2oUser(sql2o);

        Map<String, Object> model = new HashMap<>();
    }
}