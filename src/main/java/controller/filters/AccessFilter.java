package controller.filters;

import model.entity.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
//
//if(session.getAttribute("client")==null){
//    resp.sendRedirect("login.jsp");
//}
//
//
//        chain.doFilter(request,response);



//        if((Integer)session.getAttribute("role")<1){
//            resp.sendRedirect("http://localhost:8080/cars/index.jsp");
//            resp.sendRedirect("login.jsp");
//        }else {
//            System.out.println("client NOT NULL");
//            chain.doFilter(request,response);
//        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
