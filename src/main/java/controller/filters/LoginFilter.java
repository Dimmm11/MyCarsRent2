package controller.filters;

import model.entity.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
                    req.getRequestDispatcher("/WEB-INF/admin/welcomeAdmin.jsp").forward(req, resp);
                    break;
                case 2:
                    req.getRequestDispatcher("/WEB-INF/manager/welcomeManager.jsp").forward(req, resp);
                    break;
                case 1:
                    req.getRequestDispatcher("/WEB-INF/client/menu.jsp").forward(req, resp);
                    break;
                default:
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
