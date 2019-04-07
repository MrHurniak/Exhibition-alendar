package ua.training.controller.command;

import ua.training.model.entity.Exposition;
import ua.training.model.service.ExpositionService;
import ua.training.model.service.UserService;
import ua.training.model.service.util.Utils;

import javax.servlet.http.HttpServletRequest;

public class Buy implements Command {

    private ExpositionService expoService;

    public Buy(ExpositionService expoService){
        this.expoService = expoService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String expoId;
        Exposition exposition;
        String ticketsCount = request.getParameter("tickets_count");
        if(ticketsCount == null){
            expoId = request.getParameter("expo_id");
            exposition = expoService.getExposition(expoId);
            if(exposition == null){
                throw new RuntimeException("There is no exposition with such id!");
            }
            request.getSession().setAttribute("expo", exposition);
            return "/WEB-INF/user/buy.jsp";
        }

        exposition = (Exposition) request.getSession().getAttribute("expo");
        if(exposition == null){
            throw new RuntimeException("There is no exposition with such id!");
        }
        request.getSession().setAttribute("price", getTotalPrice(exposition, ticketsCount));
        return "/WEB-INF/user/payment.jsp";
    }

    private int getTotalPrice(Exposition exposition, String ticketsCount) {
        if(Utils.isNumber(ticketsCount)){
            int tickets = Integer.parseInt(ticketsCount);
            if(tickets > 0){
                return exposition.getPrice() * tickets;
            }
        }
        throw new RuntimeException("Wrong tickets count!");
    }


}
