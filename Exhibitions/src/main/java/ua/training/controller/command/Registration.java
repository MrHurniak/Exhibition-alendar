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
        request.setAttribute("name_min",2);
        request.setAttribute("name_max",20);
        request.setAttribute("surname_min",2);
        request.setAttribute("surname_max",20);
        request.setAttribute("login_min", 6);
        request.setAttribute("login_max", 20);
        request.setAttribute("password_min", 8);
        request.setAttribute("password_max",30);
        request.setAttribute("login_ptrn", "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
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
        } catch (IllegalArgumentException e){
            request.setAttribute("message", e.getMessage());
            request.setAttribute("message", e.getMessage());
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            request.setAttribute("login", login);
        }
        return "redirect:/app/r/exposition";
    }
}
