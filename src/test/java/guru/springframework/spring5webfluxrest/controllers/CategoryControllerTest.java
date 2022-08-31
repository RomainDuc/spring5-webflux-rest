package guru.springframework.spring5webfluxrest.controllers;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static guru.springframework.spring5webfluxrest.controllers.CategoryController.CATEGORY_URL;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {

    WebTestClient webTestClient;
    CategoryRepository categoryRepository;
    CategoryController categoryController;



    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController= new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void getAllCategories() {

        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(Category.builder().description("cat1").build() ,
                        Category.builder().description("cat2").build()));
        webTestClient.get()
                .uri(CATEGORY_URL)
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void findById() {
        BDDMockito.given(categoryRepository.findById("id hehe"))
                .willReturn(Mono.just(Category.builder().description("cat1").build()));

        webTestClient.get().uri(CATEGORY_URL+"/id hehe")
                .exchange()
                .expectBody(Category.class);

    }
}