package ua.expo.controller;

import ua.expo.controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        //todo commands init
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/api/" , "");
        Command command = commands.getOrDefault(path , (r)->"/index.jsp)");
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
