package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserTickets implements Command {
    private UserService userService;

    public UserTickets(){
        this.userService = UserService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = userService.getByLogin(
                (String)request.getSession().getAttribute("login")).get();
        request.setAttribute("tickets", userService.getUserTickets(user));
        request.setAttribute("user", user);
        return "/WEB-INF/user/tickets.jsp";
    }
}
