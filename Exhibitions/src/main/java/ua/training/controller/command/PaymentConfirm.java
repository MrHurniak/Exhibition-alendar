package ua.training.controller.command;

import com.mysql.cj.Session;
import ua.training.model.entity.Exposition;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PaymentConfirm implements Command {
    private UserService userService;

    public PaymentConfirm(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("expo");
        if(obj instanceof Exposition){
            Exposition expo = (Exposition) obj;
            userService.buyTickets(
                    userService.getByLogin(((String)request.getSession().getAttribute("login"))).get(),
                    expo,
                    (Integer) request.getSession().getAttribute("tickets_count")
            );
        }
        HttpSession session = request.getSession();
        session.setAttribute("price",null);
        session.setAttribute("expo",null);
        session.setAttribute("tickets_count",null);
        return "redirect:/app/r/exposition";
    }
}
