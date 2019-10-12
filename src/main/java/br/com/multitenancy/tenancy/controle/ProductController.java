package br.com.multitenancy.tenancy.controle;

import br.com.multitenancy.tenancy.modelo.tenancy_dois.Product;
import br.com.multitenancy.tenancy.repositorio.tenancy_dois.ProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        return new ResponseEntity<>(this.productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProducts(@PathVariable("id") Integer codigo) {
        Optional<Product> optionalProduct = this.productRepository.findById(codigo);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        return new ResponseEntity<>(this.productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@RequestBody Product product) {
        this.productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") Integer codigo) {
        this.productRepository.deleteById(codigo);
    }

}
