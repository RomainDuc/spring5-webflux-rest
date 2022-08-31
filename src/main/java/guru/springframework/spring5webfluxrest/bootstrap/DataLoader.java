package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader  implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public DataLoader(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryRepository.count().block() == 0) {
            loadCategories();
        }

        if(vendorRepository.count().block() == 0) {
            populateVendors();
        }

    }

    private void loadCategories() {
        //ca viens
        categoryRepository.save(Category.builder()
                .description("Fruits").build()).block();

        categoryRepository.save(Category.builder()
                .description("Nuts").build()).block();

        categoryRepository.save(Category.builder()
                .description("Breads").build()).block();

        categoryRepository.save(Category.builder()
                .description("Meats").build()).block();

        categoryRepository.save(Category.builder()
                .description("Eggs").build()).block();

        System.out.println("Loaded Categories: " + categoryRepository.count().block());

    }


    private void populateVendors() {
        //apparemment dès qu'on part en mode builder on peu plus utiliser le constructeur vide...

//        Vendor c1 = new Vendor();
//        c1.setFirstName("Michael");
//        c1.setLastName("Lachappele");
//
//        Vendor c2 = new Vendor();
//        c2.setFirstName("David");
//        c2.setLastName("Winter");
//
//        Vendor c3 = new Vendor();
//        c3.setFirstName("Anne");
//        c3.setLastName("Hine");
//
//        Vendor c4 = new Vendor();
//        c4.setFirstName("Alice");
//        c4.setLastName("Eastman");
//
//        Vendor c5 = new Vendor();
//        c5.setFirstName("Sam");
//        c5.setLastName("Axe");
////
//        vendorRepository.save(c1);
//        vendorRepository.save(c2);
//        vendorRepository.save(c3);
//        vendorRepository.save(c4);
//        vendorRepository.save(c5);

        vendorRepository.save(Vendor.builder()
                .firstName("Joe")
                .lastName("Buck").build()).block();

        vendorRepository.save(Vendor.builder()
                .firstName("Micheal")
                .lastName("Weston").build()).block();

        vendorRepository.save(Vendor.builder()
                .firstName("Jessie")
                .lastName("Waters").build()).block();

        vendorRepository.save(Vendor.builder()
                .firstName("Bill")
                .lastName("Nershi").build()).block();

        vendorRepository.save(Vendor.builder()
                .firstName("Jimmy")
                .lastName("Buffett").build()).block();



        System.out.println("Vendors Loaded = " + vendorRepository.count().block() );

    }


}
