package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    private UserService userService;

    public Registration(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        if(CommandUtil.checkUserIsLogged(request)){
            return "/WEB-INF/error.jsp";
        }
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String role = Role.USER.name();/*request.getParameter("role");*/
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        if(name == null || surname == null || login == null || email == null){
            return "/registration.jsp";
        }


        User user = new User();
        user.setRole(Role.valueOf(role));
        user.setName(name);
        user.setEmail(email);
        user.setSurname(surname);
        //todo encrypt password
        user.setPassword(password);

        if(userService.validateData(user)){
            return "/registration.jsp";
        }
        return "/registration.jsp";
    }
}
