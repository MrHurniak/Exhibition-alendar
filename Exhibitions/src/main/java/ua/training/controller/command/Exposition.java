package ua.training.controller.command;

import ua.training.model.service.ExpositionService;

import javax.servlet.http.HttpServletRequest;

public class Exposition implements Command {
    private ExpositionService expoService;

    public Exposition(ExpositionService expoService){
        this.expoService = expoService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("expositionsList",expoService.getNext(0));
        return "/WEB-INF/user/exposition.jsp";
    }
}
