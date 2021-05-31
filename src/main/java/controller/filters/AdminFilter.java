package controller.filters;

import model.entity.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute("client");
        System.out.println("client in AdminFilter");
        if(session.getAttribute("role")==null){ // ??????????
            resp.sendRedirect("redirect:/login.jsp");
        }
        if((int)session.getAttribute("role")==3){
            System.out.println("role from AdminFIlter = "+(int)session.getAttribute("role"));
            chain.doFilter(request,response);
        }else {
            System.out.println("role from AdminFIlter = "+(int)session.getAttribute("role"));
            System.out.println("YOU ARE NOT ADMIN FROM FILTER)");
//            req.getRequestDispatcher("/WEB-INF/client/login.jsp").forward(req,resp);
            resp.sendRedirect("redirect:/login.jsp");

        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
