package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class Exposition implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("login");
        System.out.format("Exposition l:%s\n", login);
        if( login == null) {
            return "/login.jsp";
//            return "/WEB-INF/user/exposition.jsp";
        }
        if(CommandUtil.checkUserIsLogged(request, login)){
            return "/WEB-INF/user/exposition.jsp";
        }
        return "/login.jsp";

    }
}
