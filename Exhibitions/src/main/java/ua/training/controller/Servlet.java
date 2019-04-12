package ua.training.controller;

import ua.training.controller.command.*;
import ua.training.controller.command.Exception;
import ua.training.model.service.ExpositionService;
import ua.training.model.service.HallsService;
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
    private UserService  userService;
    private ExpositionService expoService ;
    private HallsService hallsService ;
    private Map<String, Command> commands;

    public void init(ServletConfig config){
        System.out.println("In init method");
        config.getServletContext().setAttribute("loggedUsers",
                new HashSet<String>());
        userService = new UserService();
        expoService = new ExpositionService();
        hallsService = new HallsService();
        commands = new HashMap<>();
        commands.put("logout", new LogOut());
        commands.put("login", new LogIn(userService));
        commands.put("registration", new Registration(userService));
        commands.put("exception" , new Exception());
        commands.put("r/exposition" , new Exposition(expoService));
        commands.put("r/admin/stat",new AdminPage());
        commands.put("r/admin/halls", new AdminHalls(hallsService));
        commands.put("r/admin/expositions", new AdminExpo(expoService));
        commands.put("r/admin/main", new AdminMain());
        commands.put("r/user/buy", new Buy(expoService));
        commands.put("r/user/buy/payment/conf", new PaymentConfirm(userService));
        commands.put("error", e -> "/WEB-INF/error.jsp");
        commands.put("r/forbidden", e -> "/WEB-INF/admin/forbidden.jsp");
        commands.put("r/user/buy/payment", e -> "/WEB-INF/user/payment.jsp");
        commands.put("r/user/tickets", new UserTickets(userService));
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

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");
        Command command = commands.getOrDefault(path ,
                (r)->"/index.jsp");
        String page = command.execute(request);
        if(page.contains("redirect")){
        response.sendRedirect(page.replace("redirect:", ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}