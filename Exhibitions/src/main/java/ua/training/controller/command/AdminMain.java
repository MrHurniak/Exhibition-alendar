package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminMain implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("adminPage","/WEB-INF/admin/adminMainPage.jsp");
        return "/WEB-INF/admin/admin.jsp";
    }
}
