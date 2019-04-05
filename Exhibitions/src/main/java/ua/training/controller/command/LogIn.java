package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//import java.util.HashSet;

public class LogIn implements Command {
    private UserService userService;
    private Map<String, String> pages = new HashMap<>();

    public LogIn(UserService userService) {
        this.userService = userService;
        pages.put("login", "/login.jsp");
        //todo ????
        pages.put("ADMIN", "redirect:index.jsp");
        pages.put("USER", "redirect:index.jsp");
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
            System.out.println("user is present");
            if (CommandUtil.isUserAbsentInLogged(request, user.get().getEmail(), user.get().getRole())) {
                System.out.println("present but not log");
                return "/WEB-INF/error.jsp";
            }
//            return pages.getOrDefault(user.get().getRole() + "", pages.get("login"));
            return "redirect:r";
        }
        return "redirect:login";
    }

}