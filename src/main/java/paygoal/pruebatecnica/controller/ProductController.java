package paygoal.pruebatecnica.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paygoal.pruebatecnica.http.request.product.CreateProductRequest;
import paygoal.pruebatecnica.http.request.product.UpdateProductRequest;
import paygoal.pruebatecnica.http.response.product.ProductResponse;
import paygoal.pruebatecnica.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody
                                                         CreateProductRequest createProductRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(productService.createProduct(createProductRequest));
    }

    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody
                                                         UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updateProduct(updateProductRequest));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<ProductResponse> findProductByName(@PathVariable String productName) {
        return ResponseEntity.ok(productService.findProductByName(productName));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProductsSortedByPriceAsc() {
        return ResponseEntity.ok(productService.findAllProductsSortedByPriceAsc());
    }

}
