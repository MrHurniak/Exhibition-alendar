package ua.training.controller;

import ua.training.controller.command.*;
import ua.training.controller.command.Exception;
import ua.training.model.service.ExpositionService;
import ua.training.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private UserService  userService = new UserService();
    private ExpositionService expoService = new ExpositionService();
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig config){
        config.getServletContext().setAttribute("loggedUsers",
                new HashSet<String>());
        commands.put("logout", new LogOut());
        commands.put("login", new LogIn(userService));
        commands.put("registration", new Registration(userService));
        commands.put("exception" , new Exception());
        commands.put("r/exposition" , new Exposition(expoService));
        commands.put("r/adminPage",new AdminPage());
        commands.put("error", e -> "/WEB-INF/error.jsp");
        commands.put("forbidden", e -> "/WEB-INF/admin/forbidden.jsp");
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }
    //todo path filter
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("In servlet");
        printAllCurrentUsers(request);
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");
        Command command = commands.getOrDefault(path ,
                (r)->"/index.jsp");
        String page = command.execute(request);
        System.out.println("--"+page);
        if(page.contains("redirect")){
            response.sendRedirect(page.replace("redirect:", ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    private void printAllCurrentUsers(HttpServletRequest request) {
        Object obj = request.getSession().getServletContext().getAttribute("loggedUsers");
        HashSet set = (HashSet) obj;
        System.out.println("All current users:");
        set.stream().forEach(System.out::println);
    }
}