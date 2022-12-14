package guru.springframework.spring5webfluxrest.controllers;


import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



import static guru.springframework.spring5webfluxrest.controllers.CategoryController.CATEGORY_URL;

@RestController
@RequestMapping(CATEGORY_URL)
public class CategoryController {

    public final static String CATEGORY_URL= "/api/v1/categories";

    private final CategoryRepository categoryRepository;


    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Category> findById(@PathVariable String id) {
        return categoryRepository.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createCategory(@RequestBody Publisher<Category> categoriesStream) {
        return categoryRepository.saveAll(categoriesStream).then();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Category> updateCategory(@PathVariable  String id,@RequestBody Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }
}
