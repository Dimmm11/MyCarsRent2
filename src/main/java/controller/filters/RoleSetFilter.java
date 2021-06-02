package controller.filters;

import controller.constants.Const;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class RoleSetFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(RoleSetFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("RoleSetFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        int role = 0;
        Optional<Object> roleOpt = Optional.ofNullable(session.getAttribute(Const.ROLE));
        if (roleOpt.isPresent()) {
            role = (int) roleOpt.get();
        }
        logger.info("role: 0");
        session.setAttribute(Const.ROLE, role);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
