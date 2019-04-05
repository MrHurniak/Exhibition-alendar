package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminPage implements Command{
    @Override
    public String execute(HttpServletRequest request) {
//        if(!CommandUtil.isUserLogged(request)){
//            return "redirect:/app/login";
//        }
        if(!CommandUtil.isAdmin(request)) {
            return "redirect:forbidden";
        }
        return "/WEB-INF/admin/adminPage.jsp";
    }
}
