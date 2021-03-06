package controller.filters;

import controller.constants.Const;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Filter to set encoding and
 * chosen language on page
 */
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        String lang = "en";
        Optional<String> sessionOpt = Optional.ofNullable((String) session.getAttribute("lang"));
        if (sessionOpt.isPresent()) {
            lang = sessionOpt.get();
        }
        Optional<String> requestOpt = Optional.ofNullable(req.getParameter(Const.LANG));
        if (requestOpt.isPresent()) {
            lang = requestOpt.get();
        }
        session.setAttribute(Const.LANG, lang);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
