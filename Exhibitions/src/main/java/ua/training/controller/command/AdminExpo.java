package ua.training.controller.command;

import ua.training.model.service.ExpositionService;
import ua.training.model.service.HallsService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AdminExpo implements Command {
    private HallsService hallsService;
    private ExpositionService expoService;
    private Map<String, Consumer<HttpServletRequest>> commands;

    public AdminExpo(){
        this.commands = new HashMap<>();
        this.expoService = ExpositionService.getInstance();
        this.hallsService = HallsService.getInstance();
        commands.put("add",this::add);
        commands.put("delete", this::delete);
        commands.put("update", this::update);
    }
    @Override
    public String execute(HttpServletRequest request) {
        Consumer<HttpServletRequest> command = commands.get(request.getParameter("command"));
        request.setAttribute("halls", hallsService.getHalls());
        request.setAttribute("adminPage","/WEB-INF/admin/adminExpositions.jsp");
        if(command != null){
            command.accept(request);
            request.setAttribute("expositions", expoService.getExpositions());
            return "redirect:/app/r/admin/expositions";
        }
        request.setAttribute("expositions", expoService.getExpositions());
        return "/WEB-INF/admin/admin.jsp";
    }

    private void add(HttpServletRequest request){
        String theme = request.getParameter("theme");
        String shortDescription = request.getParameter("short");
        String fullDescription = request.getParameter("full");
        String price = request.getParameter("price");
        String hallId = request.getParameter("hall_id");
        String date = request.getParameter("date");
        expoService.add(theme, shortDescription, fullDescription, price,date, hallId);
    }

    private void delete(HttpServletRequest request){
        String expoId = request.getParameter("expo_id");
        expoService.delete(expoId);
    }

    private void update(HttpServletRequest request){
        String expoId = request.getParameter("expo_id");
        String theme = request.getParameter("theme");
        String shortDescription = request.getParameter("short");
        String fullDescription = request.getParameter("full");
        String price = request.getParameter("price");
        String date = request.getParameter("date");
        String hallId = request.getParameter("hall_id");
        expoService.update(expoId, theme, shortDescription, fullDescription,
                price,date, hallId);
    }
}

