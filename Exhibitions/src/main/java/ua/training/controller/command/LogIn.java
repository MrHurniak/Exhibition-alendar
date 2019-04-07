package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class LogIn implements Command {
    private UserService userService;

    public LogIn(UserService userService) {
        this.userService = userService;
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
            if (CommandUtil.isUserAbsentInLogged(request, user.get().getLogin(), user.get().getRole())) {
                return "/WEB-INF/error.jsp";
            }
            request.getSession().setAttribute("message", null);
            return "redirect:/app/r";
        }
        //todo change to boolean for i18n
        request.getSession().setAttribute("message","невірний логін чи пароль");
        return "redirect:/app/login";
    }

}