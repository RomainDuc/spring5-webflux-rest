package guru.springframework.spring5webfluxrest.controllers;


import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static guru.springframework.spring5webfluxrest.controllers.VendorController.URL_VENDOR;


@RestController
@RequestMapping(URL_VENDOR)
public class VendorController {

    public static final String URL_VENDOR = "api/v1/vendors";

    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Vendor> getVendorById(@PathVariable String id) {
        return vendorRepository.findById(id);
    }
}
