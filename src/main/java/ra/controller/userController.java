package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.service.user.IUserService;
import ra.model.service.user.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/userController")
public class userController {
    private IUserService userService = new UserServiceIMPL();
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
    @GetMapping("/getAll")
    public String getAll(Model model){
      List<User> users = userServiceIMPL.findAll();
      model.addAttribute("listUser", users);
      return "/User/userView";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request) {
        // validate input
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("login1", "Please enter a username");
            return "/Login";
        }
        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("login", "Please enter a password");
            return "/Login";
        }

        // validate user credentials
        UserLogin user = userService.login(new User(username, password));
        if (user == null) {
            model.addAttribute("login", "Username or password incorrect");
            return "/Login";
        } else if (!user.isStatus()) {
            model.addAttribute("loginStatus", "You Account has been locked!!!");
            return "/Login";
        } else {
            request.getSession().setAttribute("usersLogin", user);
            if (user.getRole() == 1) {
                // day la admin
                return "/admin";
//                return "redirect:/catalogController/GetAll";
            } else if (user.getRole() == 0) {
                //day la nguoi dung
                return "redirect:/";
            } else {
                return "/index";
            }
        }
    }


    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("rePassword") String rePassword,
                           Model model) {

        if (username.trim().equals("")) {
            model.addAttribute("usernameError", "Username is required");
            return "/Register";
        }

        if (userService.checkExistsUsername(username)) {
            model.addAttribute("usernameError", "Username already exists");
            return "/Register";
        }

        if (email.trim().equals("")) {
            model.addAttribute("emailError", "Email is required");
            return "/Register";
        }

        if (!isValidEmail(email)) {
            model.addAttribute("emailError", "Invalid email format");
            return "/Register";
        }

        if (password.trim().equals("")) {
            model.addAttribute("passwordError", "Password is required");
            return "/Register";
        }

        if (!isValidPassword(password)) {
            model.addAttribute("passwordError", "Password does not meet requirements");
            return "/Register";
        }

        if (!password.equals(rePassword)) {
            model.addAttribute("rePasswordError", "Passwords do not match");
            return "/Register";
        }
        userService.save(new User(username, email, password, rePassword));
        return "/Login";
    }


    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }

    private boolean isValidPassword(String password) {
        String regex = "^(\\w){6,}$";
        return password.matches(regex);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("userLogin");
        return "/index";
    }
    @PostMapping("/update-status/{userId}")
    public String updateStatus(@PathVariable("userId") Integer userId,Integer status){
        boolean success = userService.updateStatusUser(userId, status);
        if (success) {
            return "redirect:/userController/getAll";
        }else {
            return "redirect:/userController/getAll";
        }
    }
}
