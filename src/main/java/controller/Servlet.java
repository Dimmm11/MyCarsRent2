package controller;

import controller.command.*;
import controller.command.admin.*;
import controller.command.client.*;
import controller.command.Registration;
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
//        commands.put("carSelect", new CarSelect());
        commands.put("carsByMarque", new CarsByMarque());
        commands.put("carsByClass", new CarsByClass());
        commands.put("menu", new Menu());
        commands.put("order", new MakeOrder());
        commands.put("cancelOrder", new CancelOrder());
        commands.put("profile", new Profile());


        commands.put("welcomeAdmin", new WelcomeAdmin());
//        commands.put("admincars", new AdminCars());
//        commands.put("adminclients", new AdminClients());
        commands.put("adminStaff", new AdminStaff());
        commands.put("caradd", new AdminCarAdd());
        commands.put("adminDeleteCar", new AdminDeleteCar());
//        commands.put("adminaddcar", new AddCarButton());
//        commands.put("adminOrders", new AdminOrders());


        commands.put("welcomeManager", new WelcomeManager());
        commands.put("managerCars", new ManagerCars());
        commands.put("managerClients", new ManagerClients());
        commands.put("managerOrders", new ManagerOrders());

        commands.put("deleteClient", new DeleteClient());
        commands.put("setRights", new StaffRights());
        commands.put("updatePrice", new UpdatePrice());
        commands.put("setReason", new SetReason());
        commands.put("setPenalty", new ManagerSetPenalty());
        commands.put("setOrderStatus", new SetOrderStatus());
        commands.put("returnCar", new CarReturn());
        commands.put("ban", new Ban());
        commands.put("unBan", new UnBan());

        commands.put("register", new Registration());

//        commands.put("ru", new LangRu());



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
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/cars"));
        } else {
            if (session.getAttribute("client") == null) {
                Client client = new Client();
                session.setAttribute("client", client);
                session.setAttribute("role", 0);
            }
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
