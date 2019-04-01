package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Exposition implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/user/exposition.jsp";
    }
}
