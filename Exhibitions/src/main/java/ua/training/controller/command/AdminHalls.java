package ua.training.controller.command;

import ua.training.model.service.HallsService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AdminHalls implements Command {
    private Map<String, Consumer<HttpServletRequest>> commands;
    private HallsService hallsService;

    public AdminHalls(HallsService hallsService){
        this.hallsService = hallsService;
        commands = new HashMap<>();
        commands.put("add", this::add);
        commands.put("update", this::update);
        commands.put("delete", this::delete);
    }
    @Override
    public String execute(HttpServletRequest request) {
        //todo log
        Consumer<HttpServletRequest> command = commands.get(request.getParameter("command"));
        request.setAttribute("adminPage","/WEB-INF/admin/adminHalls.jsp");
        if(command != null){
            command.accept(request);
            request.setAttribute("halls", hallsService.getHalls());
            return "redirect:/app/r/admin/halls";
        }
        request.setAttribute("halls", hallsService.getHalls());

        return "/WEB-INF/admin/admin.jsp";
    }

    private void add(HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (java.lang.Exception e){}
        String name = request.getParameter("name");
        String info = request.getParameter("information");
        System.out.println(name + " " + info);
        hallsService.add(name, info);
    }

    private void delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        hallsService.delete(idStr);
    }

    private void update(HttpServletRequest request){
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String info = request.getParameter("information");
        hallsService.update(idStr, name, info);
    }
}
