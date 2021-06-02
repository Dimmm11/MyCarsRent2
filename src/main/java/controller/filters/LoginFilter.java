package controller.filters;

import model.entity.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LoginFilter.class.getName());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("LoginFilter...");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute("client");
        /**
         *  forward to basic Page if Logged in
         */
        Optional<Client> clientOptional = Optional.ofNullable((Client) session.getAttribute("client"));
        if (clientOptional.isPresent()) {
            switch (client.getRole_id()) {
                case 3:
                    logger.info("role: 3");
                    req.getRequestDispatcher("/WEB-INF/admin/welcomeAdmin.jsp").forward(req, resp);
                    break;
                case 2:
                    logger.info("role: 2");
                    req.getRequestDispatcher("/WEB-INF/manager/welcomeManager.jsp").forward(req, resp);
                    break;
                case 1:
                    logger.info("role: 1");
                    req.getRequestDispatcher("/WEB-INF/client/menu.jsp").forward(req, resp);
                    break;
                default:
                    logger.info("role: --");
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
