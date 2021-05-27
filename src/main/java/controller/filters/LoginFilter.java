package controller.filters;

import model.entity.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        if (client != null) {
            if (client.getRole_id() == 3) {
                System.out.println("role 3 LoginFilter");
                req.getRequestDispatcher("/WEB-INF/admin/welcomeAdmin.jsp").forward(req, resp);
            }
            if (client.getRole_id() == 2) {
                System.out.println("role 2 LoginFilter");
                req.getRequestDispatcher("/WEB-INF/manager/welcomeManager.jsp").forward(req, resp);
            }
            if(client.getRole_id()==1) {
                System.out.println("role 1 LoginFilter");
                req.getRequestDispatcher("/WEB-INF/client/menu.jsp").forward(req, resp);
            }
        } else {
            System.out.println("client == null in LoginFilter");
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
