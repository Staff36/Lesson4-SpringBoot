package ru.tronin.lesson4springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tronin.lesson4springboot.exceptions.NoEntityException;
import ru.tronin.lesson4springboot.model.Product;
import ru.tronin.lesson4springboot.services.ProductDAOImpl;

import javax.validation.Valid;


@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductDAOImpl productsDAO;

    @Autowired
    public ProductsController(ProductDAOImpl productsDAO) {
        this.productsDAO = productsDAO;
    }

    @GetMapping()
    public String index(Model model){
            model.addAttribute("prods", productsDAO.getAll());
        return "products/products";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
    model.addAttribute("product", productsDAO.getEntityById(id));
    return "products/show";
}

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("prod", new Product());
        return "products/new";
     }

    @PostMapping()
    public String createProduct(@ModelAttribute("prod") @Valid Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "products/new";
        }
        productsDAO.create(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(Model model, @PathVariable Integer id){
        try {
            model.addAttribute("prod", productsDAO.getEntityById(id));
        } catch (NoEntityException ignored){
            return "products/notFound";
        }
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String updateProduct(@ModelAttribute("prod") @Valid Product product,
                                BindingResult bindingResult,
                                @PathVariable Integer id){
        if (bindingResult.hasErrors()){
            return "products/edit";
        }
        productsDAO.update(product, id);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productsDAO.deleteById(id);
        return "redirect:/products";
    }

}
