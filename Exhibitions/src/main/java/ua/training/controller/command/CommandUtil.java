package ua.training.controller.command;

import ua.training.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtil {

    public static boolean isRole(HttpServletRequest request, Role role) {
        Object roleObj = request.getSession().getAttribute("role");
        return role == (roleObj != null ? Role.valueOf(roleObj.toString()) : Role.UNKNOWN);
    }

    public static boolean isUserLogged(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            return false;
        }
        return findOrAdd(request, login);
    }

    static boolean isAbsentInLogged(HttpServletRequest request, String login, Role role) {
        if (findOrAdd(request, login)) {
            return true;
        }
        HttpSession session = request.getSession();
        session.setAttribute("login", login);
        session.setAttribute("role", role);
        return false;
    }

    @SuppressWarnings("unchecked")
    private static boolean findOrAdd(HttpServletRequest request, String login) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");
        if (loggedUsers.stream().anyMatch(login::equals)) {
            return true;
        }
        loggedUsers.add(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
