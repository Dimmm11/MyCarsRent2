package controller.filters;

import model.entity.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute("client");
        if(session.getAttribute("role")==null){
            System.out.println("session.getAttribute_role in ManagerFilter "+session.getAttribute("role"));
            resp.sendRedirect("http://localhost:8080/cars/");
        }else {

        if((int)session.getAttribute("role")>=2){
            System.out.println("role from FIlter = "+(int)session.getAttribute("role"));

            /**
             * stop caching pages in browser
             */
//            resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            chain.doFilter(request,response);

        }else {
            System.out.println("role from FIlter = "+(int)session.getAttribute("role"));
            System.out.println("YOU ARE NOT MANAGER - FROM FILTER)");
//            req.getRequestDispatcher("/WEB-INF/client/login.jsp").forward(req,resp);
            resp.sendRedirect("login.jsp");

        }}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
