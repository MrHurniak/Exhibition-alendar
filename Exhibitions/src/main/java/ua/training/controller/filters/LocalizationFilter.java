package ua.training.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(LocalizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String lang = servletRequest.getParameter("lang");
        if (lang != null) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            LOGGER.debug("Trying to change locale to lang=" + lang);
            if (lang.equals("ua")) {
                LOGGER.debug("Set ukrainian locale");
                Config.set(request.getSession(), Config.FMT_LOCALE, new Locale("uk", "UA"));
            }
            if (lang.equals("en")) {
                LOGGER.debug("Set english locale");
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
