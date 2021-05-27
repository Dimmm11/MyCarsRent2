package controller;

import controller.command.*;
import controller.command.admin.*;
import controller.command.client.CarSelect;
import controller.command.Registration;
import controller.command.client.MakeOrder;
import controller.command.client.Profile;
import controller.command.manager.*;
import model.entity.Client;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
//        servletConfig.getServletContext()
//                .setAttribute("loggedUsers", new HashSet<String>());
//        commands.put("logout",
//                new LogOutCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("carSelect", new CarSelect());
        commands.put("order", new MakeOrder());
        commands.put("profile", new Profile());

        commands.put("welcomeAdmin", new WelcomeAdmin());
        commands.put("admincars", new AdminCars());
        commands.put("adminclients", new AdminClients());
        commands.put("adminstaff", new AdminStaff());
        commands.put("caradd", new AdminCarAdd());
        commands.put("adminDeleteCar", new AdminDeleteCar());
        commands.put("adminaddcar", new AddCarButton());
        commands.put("adminOrders", new AdminOrders());
        commands.put("unBan", new AdminUnBan());

        commands.put("welcomeManager", new WelcomeManager());
        commands.put("managerCars", new ManagerCars());
        commands.put("managerClients", new ManagerClients());
        commands.put("managerOrders", new ManagerOrders());
        commands.put("menu", new Menu());
        commands.put("deleteClient", new DeleteClient());
        commands.put("managers", new StaffRights());
        commands.put("updatePrice", new UpdatePrice());
        commands.put("setReason", new SetReason());
        commands.put("setPenalty", new ManagerSetPenalty());
        commands.put("setOrderStatus", new SetOrderStatus());
        commands.put("returnCar", new CarReturn());


        commands.put("register", new Registration());
        commands.put("ban", new Ban());

    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String path = request.getRequestURI();
        path = path.replaceAll(".*/cars/", "");
        Command command = commands.get(path);
        String page = command.execute(request);
        System.out.println(page);

        if (page.contains("redirect:")) {
            System.out.println("redirect");
//            session.setAttribute("role", null);
//            session.setAttribute("client", null);
//            session.invalidate();

            response.sendRedirect(page.replace("redirect:", "/cars"));
        } else {
            if (session.getAttribute("client") == null) {
                Client client = new Client();
                client.setRole_id(0);
                session.setAttribute("client", client);
                session.setAttribute("role", client.getRole_id());
            }
//            System.out.println("ROLE IN Servlet: "+(int)session.getAttribute("role"));
            /**
             * check session by Role
             */
            if ((int) session.getAttribute("role") >0) {
                System.out.println(page);
                System.out.println("Servlet: forward");
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                System.out.println("Servlet: redirect to index jsp");
//                response.sendRedirect("index.jsp");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }

        }
    }
}
