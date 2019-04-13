package ua.training.controller.command;

import ua.training.model.service.HallsService;

import javax.servlet.http.HttpServletRequest;

public class AdminPage implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("totalExpositions", HallsService.getInstance().getNumberOfRows());
        request.setAttribute("adminPage","/WEB-INF/admin/adminStat.jsp");
        return "/WEB-INF/admin/admin.jsp";
    }
}
