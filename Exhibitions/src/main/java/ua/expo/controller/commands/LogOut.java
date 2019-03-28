package ua.expo.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        //todo check this code
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");

        return "/";
    }
}
