package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.Catalog;
import ra.model.service.catalog.CatalogServiceIMPL;

import java.util.List;

@Controller
@RequestMapping("/catalogController")
public class CatalogController {
    CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
    @GetMapping("/catalog")
    public String catalogList(){
        return "redirect:GetAll";
    }
    @GetMapping("/GetAll")
    public String getAll(Model model) {
        List<Catalog> list = catalogServiceIMPL.findAll();
        model.addAttribute("list", list);
        return "/Catalog";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        catalogServiceIMPL.delete(id);
        return "redirect:GetAll";
    }

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("/createCatalog", "NewCatalog", new Catalog());
    }

    @PostMapping ("/create")
    public String create(@ModelAttribute("NewCatalog") Catalog catalog, Model model) {
        catalogServiceIMPL.save(catalog);
        return "redirect:GetAll";
    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam int id) {
        Catalog editCatalog = catalogServiceIMPL.findById(id);
        ModelAndView mv = new ModelAndView("/editCatalog");
        mv.addObject("catalog", editCatalog);
        return mv;
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("catalog") Catalog catalog, Model model){
        catalogServiceIMPL.update(catalog);
        return "redirect:GetAll";
    }

}
