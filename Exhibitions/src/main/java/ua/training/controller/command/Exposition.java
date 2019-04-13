package ua.training.controller.command;

import ua.training.model.service.ExpositionService;
import ua.training.model.service.HallsService;
import ua.training.model.service.util.Utils;

import javax.servlet.http.HttpServletRequest;

public class Exposition implements Command {
    private ExpositionService expoService;
    private HallsService hallsService;

    public Exposition(){
        this.expoService = ExpositionService.getInstance();
        this.hallsService = HallsService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String hall = request.getParameter("hall");
        String page = request.getParameter("page");


        request.setAttribute("expositionsList",expoService.getExpoList(page, hall));
        request.setAttribute("halls", hallsService.getHalls());
        request.setAttribute("noOfPages", expoService.getNumberOfPages(hall));
        request.setAttribute("currentPage"
                , Utils.isNumber(page)? Integer.parseInt(page): 1);

        return "/WEB-INF/user/exposition.jsp";
    }
}
