package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Optional<Object> login = Optional.ofNullable(request.getSession().getAttribute("login"));
        login.ifPresent(e -> logoutUser(request, e.toString()));
        return "redirect:/index.jsp";
//        String login = (String) request.getSession().getAttribute("login");
//        Object obj = request.getSession().getServletContext()
//                .getAttribute("loggedUsers");
//        if(login == null){
////            return "/index.jsp";
//            return "redirect:/index.jsp";
//        }
//        if(obj instanceof HashSet){
//            HashSet loggedUsers = (HashSet) obj;
//            loggedUsers.remove(login);
//            request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
//            request.getSession().setAttribute("login", null);
//        }
//        return "redirect:/index.jsp";
////        return "index.jsp";
    }
    private void logoutUser(HttpServletRequest request, String login){
        HashSet<String> loggedUsers = (HashSet<String>)
                request.getSession().getServletContext().getAttribute("loggedUsers");
        loggedUsers.remove(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        HttpSession session = request.getSession();

        session.setAttribute("login", null);
        session.setAttribute("role", null);
    }
}
