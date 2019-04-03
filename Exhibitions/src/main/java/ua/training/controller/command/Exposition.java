package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Exposition implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if(CommandUtil.checkUserIsLogged(request)){
            return "/WEB-INF/user/exposition.jsp";
        }
        System.out.println("Make redirect");
        return "redirect:/app/login";

    }
}
