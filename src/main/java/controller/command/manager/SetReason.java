package controller.command.manager;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class SetReason implements Command {
    @Override
    public String execute(HttpServletRequest request) {
//        try {
//            request.setCharacterEncoding("cp1251");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        String reason = request.getParameter(Const.REASON);
//        try {
//            String str = new String(request.getParameter(Const.REASON).getBytes("cp1251"), StandardCharsets.UTF_8);
//            System.out.println(str);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        System.out.println(reason);
        int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
        new OrderService().setReason(orderId, reason);
        return "/managerOrders";
    }
}
