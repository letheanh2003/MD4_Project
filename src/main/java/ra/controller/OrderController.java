package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.CartItem;
import ra.model.entity.Order;
import ra.model.entity.UserLogin;
import ra.model.service.cart.CartServiceIMPL;
import ra.model.service.order.OrderServiceIMPL;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/orderController")
public class OrderController {
    OrderServiceIMPL orderServiceIMPL = new OrderServiceIMPL();
    CartServiceIMPL cartServiceIMPL = new CartServiceIMPL();
    @PostMapping("/create")
    public String create(@RequestParam("phone") String phone, @RequestParam("address") String address, HttpSession session, Model model) {
        UserLogin userLogin = (UserLogin) session.getAttribute("usersLogin");
        List<CartItem> list = cartServiceIMPL.FindAllByCartId(userLogin.getCartId());
        double totalAmount = 0.0;
        for (CartItem c: list) {
            totalAmount += c.getPrice()*c.getQuantity();
        }
        int newCartId = orderServiceIMPL.createOrderNew(new Order(userLogin.getCartId(),phone,address, totalAmount));
        userLogin.setCartId(newCartId);
        session.setAttribute("userLogin",userLogin);
        return "redirect:/cartController";
    }
}
