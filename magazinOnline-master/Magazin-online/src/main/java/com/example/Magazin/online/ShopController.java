package com.example.Magazin.online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    SecuritySession securitySession;

    @Autowired
    UserRegister userRegister;

    @Autowired
    DatabaseProductDAO databaseProductDAO;

    @Autowired
    DatabaseCategoryDAO databaseCategoryDAO;


    @Autowired
    private CartSession cartSession;


    @GetMapping("/index")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("logged", securitySession.isUserLogged());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        securitySession.userNotLogged();
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @GetMapping("/categories")
    public ModelAndView categories() {
        if (!securitySession.isUserLogged()) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView("categories");
        modelAndView.addObject("logged", securitySession.isUserLogged());
        List<Category> categoriesList = databaseCategoryDAO.findAll();
        modelAndView.addObject("categories", categoriesList);
        modelAndView.addObject("nr_product", cartSession.getProductIds().size());
        return modelAndView;
    }

    @GetMapping("/products")
    public ModelAndView showAll(@RequestParam("category_id") Integer catId) {
        if (!securitySession.isUserLogged()) {
            return new ModelAndView("redirect:/login");

        }
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("logged", securitySession.isUserLogged());
        List<Product> productList = databaseProductDAO.findAllByCatId(catId);
        modelAndView.addObject("products", productList);
        modelAndView.addObject("nr_product", cartSession.getProductIds().size());
        return modelAndView;
    }


    @GetMapping("/register-action")
    public String create(
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("address") String address

    ) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);

        userRegister.saveUser(user);

        return "redirect:/login";
    }


}