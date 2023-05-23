package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.entity.Product;
import ra.model.service.product.ProductServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    ProductServiceIMPL productServiceIMPL = new ProductServiceIMPL();
    @GetMapping("/")
    public String showHome() {
        return "/index";
    }

    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("usersLogin");
        return "redirect:/";
    }

    @GetMapping("/menu")
    public String showMenu(Model model) {
        List<Product> listProduct = productServiceIMPL.findAll();
        model.addAttribute("listProduct", listProduct);
        return "/menu";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "/about";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "/Login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "/Register";
    }





}
