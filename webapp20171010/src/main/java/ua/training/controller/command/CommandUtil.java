package ua.training.controller.command;

import ua.training.model.entity.enums.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtil {
    static void setUserRole(HttpServletRequest request, int role, String login){
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        context.setAttribute("login", login);
        session.setAttribute("role", role);
    }
    static boolean checkUserIsLogged(HttpServletRequest request, String userName){
        if(request == null || userName == null){
            return false;
        }
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
        if(loggedUsers.stream().anyMatch(userName::equals)){
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
    static boolean isUserAbsentInLogged(HttpServletRequest request, String login, Role role){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(login::equals)){
            return true;
        }

        loggedUsers.add(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        HttpSession session = request.getSession();

        session.setAttribute("login", login);
        session.setAttribute("role", role);

        return false;
    }
}
