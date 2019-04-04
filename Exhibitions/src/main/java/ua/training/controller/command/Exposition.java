package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Exposition implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if(CommandUtil.checkUserIsLogged(request)){
            System.out.println(request.getParameter("page"));
            return "/WEB-INF/user/exposition.jsp";
        }
        System.out.println("Make redirect");
        return "redirect:/app/login";

    }
}
