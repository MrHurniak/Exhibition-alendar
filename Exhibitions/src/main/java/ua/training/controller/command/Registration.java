package ua.training.controller.command;

import ua.training.model.exceptions.NotUniqLoginException;
import ua.training.model.service.UserService;
import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    private UserService userService;

    public Registration(){
        this.userService = UserService.getInstance();
    }
    @Override
    public String execute(HttpServletRequest request) {
        if(CommandUtil.isUserLogged(request)){
            return "/WEB-INF/error.jsp";
        }
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        if(name == null || surname == null || login == null || email == null){
            return "/registration.jsp";
        }
        try {
            userService.createUser(name, surname, email, login, password);
        } catch (NotUniqLoginException e){
            request.setAttribute("message", e.getMessage());
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            return "/registration.jsp";
        }
        return "redirect:/app/r/exposition";
    }
}
