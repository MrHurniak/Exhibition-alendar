package ua.training.controller.command;

import org.apache.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class LogIn implements Command {

    private static final Logger LOGGER = Logger.getLogger(LogIn.class);
    private UserService userService;

    public LogIn() {
        this.userService = UserService.getInstance();
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
        //todo don`t forgot to uncomment and rewrite passwords fo users
        if (user.isPresent() /*&& userService.checkPassword(user.get(), password)*/) {
            if (CommandUtil.containsInLogged(request, login)) {
                LOGGER.warn("Logged user try to login. User login="
                        + request.getSession().getAttribute("login"));
                return "/WEB-INF/error.jsp";
            }
            CommandUtil.logInUser(request, user.get().getLogin(), user.get().getRole());
            LOGGER.info("User log in system. User login=" + user.get().getLogin());
            return "redirect:/app/r/exposition";
        }
        LOGGER.info("User entered wrong login or password");
        request.setAttribute("message", "wrong.login.password");
        return "/login.jsp";
    }

}