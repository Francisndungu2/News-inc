import com.google.gson.Gson;
import dao.DepartmentDao;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import exceptions.ApiException;
import models.Department;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {

    public static void main(String[] args) {

        staticFileLocation("/public");

        Sql2oDepartmentDao departmentsDao;
        Sql2oNewsDao newsDao;
        Sql2oUserDao usersDao;

        Connection conn;
        Gson gson = new Gson();
        String connectionString =  ("jdbc:postgresql://localhost:5432/");
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "fRANC220515");


        departmentsDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        usersDao = new Sql2oUserDao(sql2o);

//        conn = sql2o.open();
        get("/news", "application/json", (req, res) -> {
            System.out.println(newsDao.getAll());

            if(newsDao.getAll().size() > 0) {
                return gson.toJson(newsDao.getAll());
            }
            else{
                return "{\"this is website to sell supercar.\"}";
            }
        });
        post("/department/new", "application/json", (req, res)->{
            DepartmentDao.Department department = gson.fromJson(req.body(), (Type) Department.class);
            departmentsDao.add(department);
            res.status(201);
            return gson.toJson(department);
        });


        get("/", "application/json", (req, res) ->
                "{\"we sell suppercar eg  bughati.\"}");


        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });






        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });


        after((req, res) ->{
            res.type("application/json");
        });

    }
    }
