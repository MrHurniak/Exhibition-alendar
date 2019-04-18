package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class Registration implements Command {
    private UserService userService;

    public Registration(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
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
//        String phone = request.getParameter("phone");
//        if(CommandUtil.checkUserIsLogged(request, (String) request.getSession().getAttribute("login"))){
//            return "redirect:/index.jsp";
//        }
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        String email = request.getParameter("email");
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//        if(name == null || surname == null || email == null || login == null || password == null) {
//            return "/registration.jsp";
//        }
//        //todo check and DB work
//        request.getSession().setAttribute("login",login);
//        Object obj = request.getSession().getServletContext().getAttribute("loggedUsers");
//        if(obj instanceof HashSet){
//            HashSet<String> set = (HashSet<String>) obj;
//            set.add(name);
//            return "index.jsp";
////            if(login.equals("Admin")){
////                return "/WEB-INF/admin/adminPage.jsp";
////            } else {
////                return "/WEB-INF/user/exposition.jsp";
////            }
//        }
//        return "/registration.jsp";
        //Why?
//        return "/WEB-INF/error.jsp";
    }
}
