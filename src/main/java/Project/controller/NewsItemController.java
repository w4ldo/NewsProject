package Project.controller;

import Project.domain.Category;
import Project.domain.FileObject;
import Project.domain.NewsItem;
import Project.repository.CategoryRepository;
import Project.repository.FileObjectRepository;
import Project.repository.NewsItemRepository;
import java.io.IOException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NewsItemController {

    @Autowired
    private NewsItemRepository newsItemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FileObjectRepository fileObjectRepository;

    @GetMapping("/newsItems")
    public String list(Model model) {
        model.addAttribute("newsItems", newsItemRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "newsItems";
    }

    @PostMapping("/newsItems")
    public String create(@RequestParam String headline, @RequestParam String lead, @RequestParam String text,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate posted,
            @RequestParam("file") MultipartFile file) throws IOException {

        NewsItem newsItem = new NewsItem();
        newsItem.setHeadline(headline);
        newsItem.setLead(lead);
        newsItem.setText(text);
        newsItem.setPosted(posted);
        newsItemRepository.save(newsItem);

        FileObject fo = new FileObject();
        fo.setName(file.getOriginalFilename());
        fo.setMediaType(file.getContentType());
        fo.setSize(file.getSize());
        fo.setContent(file.getBytes());
        fo.setNewsItem(newsItem);
        fileObjectRepository.save(fo);

        newsItem.setFileObject(fo);
        newsItemRepository.save(newsItem);
        return "redirect:/newsItems";
    }

    @GetMapping(path = "/newsItems/{newsItemId}/content", produces = "image/png")
    @ResponseBody
    public byte[] get(@PathVariable Long newsItemId) {
        NewsItem news = newsItemRepository.getOne(newsItemId);

        if (news.getFileObject() != null) {
            Long foId = news.getFileObject().getId();
            return fileObjectRepository.getOne(foId).getContent();
        }
        return null;
    }

    @GetMapping("/newsItems/{newsItemId}")
    public String newsItem(Model model, @PathVariable Long newsItemId) {
        model.addAttribute("newsItem", newsItemRepository.getOne(newsItemId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "newsItem";
    }

    @PostMapping("/newsItems/{newsItemId}/categories")
    public String assignCategory(Model model, @PathVariable Long newsItemId, @RequestParam Long categoryId) {
        NewsItem news = newsItemRepository.getOne(newsItemId);
        Category category = categoryRepository.getOne(categoryId);
        news.addCategoryToNews(category);
        category.addNewsToCategory(news);
        newsItemRepository.save(news);
        categoryRepository.save(category);
        return "redirect:/newsItems/{newsItemId}";
    }

    @PostMapping("/test")
    public String test() {
        NewsItem newsItem = new NewsItem();
        newsItem.setHeadline("headline");
        newsItem.setLead("lead");
        newsItem.setText("text");
        newsItem.setPosted(LocalDate.now());
        newsItemRepository.save(newsItem);

        Category category1 = new Category();
        category1.setName("crime");
        Category category2 = new Category();
        category2.setName("politics");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        return "redirect:/newsItems";
    }

}
