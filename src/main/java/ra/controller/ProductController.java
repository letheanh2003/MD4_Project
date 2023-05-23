package ra.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.Catalog;
import ra.model.entity.Product;
import ra.model.service.catalog.CatalogServiceIMPL;
import ra.model.service.product.ProductServiceIMPL;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/productController")
public class ProductController {
    ProductServiceIMPL productServiceIMPL = new ProductServiceIMPL();
    CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
    @GetMapping("/product")
    public String catalogList(){
        return "redirect:getAll";
    }
    @GetMapping("/getAll")
    public String getAll(Model model){
        List<Product> listProduct = productServiceIMPL.findAll();
        model.addAttribute("listProduct", listProduct);
        return "/Product/Product";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id){
        productServiceIMPL.delete(id);
        return "redirect:getAll";
    }

    @GetMapping("/add")
    public String add(Model model){
        List<Catalog> catalogList = catalogServiceIMPL.findAll();
        model.addAttribute("listCatalog", catalogList);
        return "/Product/createProduct";}

    @PostMapping("/create")
    public String create(@RequestParam("name") String name,
                      @RequestParam("title") String title,
                      @RequestParam("quantity") int quantity,
                      @RequestParam("price") float price,
                      @RequestParam("thumbnailUrl") MultipartFile images,
                      @RequestParam("id") int catalogId,
                      Model model) throws IOException {
        String uploadPath = "E:\\Code_MD4\\projectMD4\\src\\main\\webapp\\assets\\images\\";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = images.getOriginalFilename();
        FileCopyUtils.copy(images.getBytes(), new File(uploadPath + fileName));
        Catalog catalogs = catalogServiceIMPL.findById(catalogId);
        productServiceIMPL.save(new Product(name, title,quantity,price,fileName,catalogs));
        return "redirect:getAll";

    }
    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam int id, Model model) {
        Product editProduct = productServiceIMPL.findById(id);
        List<Catalog> listCatalog = catalogServiceIMPL.findAll();
        model.addAttribute("listCatalog", listCatalog);
        ModelAndView mv = new ModelAndView("/Product/editProduct");
        mv.addObject("product", editProduct);
        return mv;
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id,
                         @RequestParam("name") String name,
                         @RequestParam("title") String title,
                         @RequestParam("quantity") int quantity,
                         @RequestParam("price") float price,
                         @RequestParam("thumbnailUrl") MultipartFile images,
                         @RequestParam("catalogId") int catalogId,
                         Model model) throws IOException {
        String uploadPath = "E:\\Code_MD4\\projectMD4\\src\\main\\webapp\\assets\\images";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = images.getOriginalFilename();
        FileCopyUtils.copy(images.getBytes(), new File(uploadPath + fileName));
        Catalog catalogs = catalogServiceIMPL.findById(catalogId);
        productServiceIMPL.update(new Product(id,name, title,quantity,price,fileName,catalogs));
        return "redirect:getAll";
    }

}