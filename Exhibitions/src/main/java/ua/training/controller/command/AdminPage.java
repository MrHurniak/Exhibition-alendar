package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminPage implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        if(!CommandUtil.checkUserIsLogged(request)){
            return "redirect:/app/login";
        }
        if(CommandUtil.isAdmin(request)) {
            return "/WEB-INF/admin/adminPage.jsp";
        } else {
            return "/WEB-INF/admin/forbidden.jsp";
        }
    }
}
