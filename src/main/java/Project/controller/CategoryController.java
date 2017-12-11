package Project.controller;

import Project.domain.Category;
import Project.repository.CategoryRepository;
import Project.repository.NewsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @Autowired
    private NewsItemRepository newsItemRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories/{categoryId}")
    public String list(Model model, @PathVariable Long categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        model.addAttribute("newsItems", newsItemRepository.findByCategoriesContaining(category));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("currentCategory", category);
        return "category";
    }

    @PostMapping("/categories/{newsItemId}")
    public String create(@PathVariable Long newsItemId, @RequestParam String name) {

        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);

        return "redirect:/newsItems/{newsItemId}";
    }

}
