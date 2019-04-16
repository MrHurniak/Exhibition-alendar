package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.command.CommandUtil;
import ua.training.model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(CommandUtil.isRole(request, Role.USER)) {
            LOGGER.debug("User came to the user page");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        LOGGER.warn("Trying to get access of user pages without permissions.User login="
                + request.getSession().getAttribute("login"));
        response.sendRedirect("/app/r/forbidden");
    }

    @Override
    public void destroy() {

    }
}
