package controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        /**
         * set language
         */
        String lang = "en";
        if(session.getAttribute("lang")!=null){
            lang=(String)session.getAttribute("lang");
        }
        if(req.getParameter("lang")!=null) {
           lang = req.getParameter("lang");
        }
        System.out.println("----------------------");
        System.out.println(lang);
        session.setAttribute("lang", lang);
        System.out.println("lang in session: "+ session.getAttribute("lang"));
        System.out.println("-----------------");

        /**
         * encoding
         */
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
