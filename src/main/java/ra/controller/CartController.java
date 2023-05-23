package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.*;
import ra.model.service.cart.CartServiceIMPL;
import ra.model.service.product.ProductServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cartController")
public class CartController {
    CartServiceIMPL cartServiceIMPL = new CartServiceIMPL();
    ProductServiceIMPL productServiceIMPL = new ProductServiceIMPL();

    @RequestMapping("")
    public String cart(HttpServletRequest request, Model model) {
        UserLogin userLogin = (UserLogin) request.getSession().getAttribute("usersLogin");
        if (userLogin == null) {
            return "/Login";
        } else {
//            TODO: Lay ra cartId cua user login --> find cart by user id;
//                Cart cart = cartServiceIMPL.findCartByUserId(userLogin.getId());
//            TODO: find all cart items by cartId
            List<CartItem> list = cartServiceIMPL.FindAllByCartId(userLogin.getCartId());
            model.addAttribute("listCart", list);
            double total = 0.0;
            for (CartItem c: list) {
                total += c.getPrice()*c.getQuantity();
            }
            model.addAttribute("total",total);
            return "/Cart";
        }
    }

    @GetMapping("/add-to-cart/{proId}")
    public String addToCart(@PathVariable("proId") String proId, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("usersLogin");
        Product p = productServiceIMPL.findById(Integer.parseInt(proId));
        CartItem cartItem = cartServiceIMPL.findCartItemByID(userLogin.getCartId(), Integer.parseInt(proId));
        if (cartItem == null) {
            cartServiceIMPL.save(new CartItem(0, userLogin.getCartId(), p.getId(), p.getPrice(), 1));
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartServiceIMPL.update(cartItem);
        }
        return "redirect:/cartController";
    }

    @GetMapping("/delete/{cartItemId}")
    public String delete(@PathVariable("cartItemId") String id) {
        
        cartServiceIMPL.delete(Integer.valueOf(id));
        return "redirect:/cartController";
    }

    @GetMapping("/update")
    public String update(@RequestParam("cdId") String cdId, @RequestParam("quantity") String quantity) {
        cartServiceIMPL.update(new CartItem(Integer.valueOf(cdId),Integer.valueOf(quantity)));
        return "redirect:/cartController";
    }
}
