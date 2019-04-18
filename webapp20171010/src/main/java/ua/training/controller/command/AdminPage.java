package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminPage implements Command{
    @Override
    public String execute(HttpServletRequest request) {
//        if("Admin".equals(request.getSession().getAttribute("login")) &&
//            CommandUtil.checkUserIsLogged(request, "Admin")){
//            return "/WEB-INF/admin/adminPage.jsp";
//        }
//        return "/login.jsp";
        return "/WEB-INF/admin/adminPage.jsp";
    }
}
