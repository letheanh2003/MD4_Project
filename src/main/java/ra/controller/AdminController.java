package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminController")
public class AdminController {
    @GetMapping("/admin")
    public String getAdmin(){
        return "/admin";
    }
}
