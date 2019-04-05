package ua.training.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String lang = servletRequest.getParameter("lang");
        if (lang != null) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            if (lang.equals("ua")) {
                Config.set(request.getSession(), Config.FMT_LOCALE, new Locale("ua", "UA"));
            }
            if (lang.equals("en")) {
                Config.set(request.getSession(), Config.FMT_LOCALE, new Locale("en", "US"));
            }
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }



    @Override
    public void destroy(){
    }
}
