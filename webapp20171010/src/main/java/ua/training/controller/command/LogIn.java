package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
//import java.util.HashSet;

import java.util.Map;
import java.util.Optional;

public class LogIn implements Command {
    private UserService userService;
    private Map<String, String> pages = new HashMap<>();

    public LogIn(UserService userService) {
        this.userService = userService;
        pages.put("login", "/login.jsp");
        //todo ????
        pages.put("ADMIN", "redirect:admin");
        pages.put("USER", "redirect:user");
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || login.equals("")
                || password == null || password.equals("")) {
            return "/login.jsp";
        }
        Optional<User> user = userService.getByLogin(login);

        if (user.isPresent() && /*password.equals(user.get().getPassword())*/ true) {

            if (CommandUtil.isUserAbsentInLogged(request, user.get().getEmail(), user.get().getRole())) {
                return "/WEB-INF/error.jsp";
            }

            return pages.getOrDefault(user.get().getRole() + "", pages.get("login"));
        }
        return "/login.jsp";
    }

}
//        if(CommandUtil.checkUserIsLogged(request, (String) request.getSession().getAttribute("login"))){
//            return "redirect:/index.jsp";
//        }
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
////        System.out.println(login + " " + password);
//        System.out.format("LogIn: l:%s - p:%s", login, password);
//        if( login == null || login.equals("") || password == null || password.equals("")  ){
//            return "/login.jsp";
//        }
//
////        System.out.println(login + " " + password);
//        //System.out.println("Yes!");
//        //todo: check login with DB
//
////        if(CommandUtil.checkUserIsLogged(request, login)){
////            return "/WEB-INF/error.jsp";
////        }
//        //todo if is in DB
//        if(login.equals("Admin") || login.equals("User")){
//            //todo smth bad
//            ((HashSet)request.getSession().getServletContext().getAttribute("loggedUsers")).add(login);
//            request.getSession().setAttribute("login", login);
////            return "/WEB-INF/user/exposition.jsp";
//            return "/index.jsp";
//        }
//        //if no such in DB
//        //todo add some attribute that repeatable login
//        return "/login.jsp";
//    }
//}
