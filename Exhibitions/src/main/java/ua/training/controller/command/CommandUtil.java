package ua.training.controller.command;

import ua.training.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtil {

    public static boolean hasRole(HttpServletRequest request, Role role) {
        Object roleObj = request.getSession().getAttribute("role");
        return role == (roleObj != null ? Role.valueOf(roleObj.toString()) : Role.UNKNOWN);
    }

    @SuppressWarnings("unchecked")
    public static boolean isUserLogged(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            return false;
        }
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");
        loggedUsers.forEach(System.out::println);
        return  loggedUsers.contains(login);
    }

    @SuppressWarnings("unchecked")
    static boolean containsInLogged(HttpServletRequest request, String login){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");
        return loggedUsers.stream().anyMatch(login::equals);
    }

    @SuppressWarnings("unchecked")
    static void logOutUser(HttpServletRequest request, String login) {
        HashSet<String> loggedUsers = (HashSet<String>)
                request.getSession().getServletContext().getAttribute("loggedUsers");
        loggedUsers.remove(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        HttpSession session = request.getSession();
        session.setAttribute("login", null);
        session.setAttribute("role", null);
    }

    @SuppressWarnings("unchecked")
    static void logInUser(HttpServletRequest request, String login, Role role) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");
        loggedUsers.add(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        HttpSession session = request.getSession();
        session.setAttribute("login", login);
        session.setAttribute("role", role);
    }
}
