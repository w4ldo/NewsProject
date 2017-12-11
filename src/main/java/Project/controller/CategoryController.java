package Project.controller;

import Project.domain.Category;
import Project.repository.CategoryRepository;
import Project.repository.NewsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return "newsItems";
    }
    
}
