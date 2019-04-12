package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.exceptions.NotUniqLoginException;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    private UserService userService;

    public Registration(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        if(CommandUtil.isUserLogged(request)){
            return "/WEB-INF/error.jsp";
        }
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
//        String role = Role.USER.name();/*request.getParameter("role");*/
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
//
//        if(userService.validateData(user)){
//            userService.createUser(user);
//            return "redirect:/app/r/exposition";
//        }
//        return "/registration.jsp";
    }
}
