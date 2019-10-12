package br.com.multitenancy.tenancy.controle;

import br.com.multitenancy.tenancy.modelo.tenancy_dois.MovementStock;
import br.com.multitenancy.tenancy.repositorio.tenancy_dois.MovementStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movementStock")
public class MovementStockController {
 
   @Autowired private MovementStockRepository movementStockRepository;
   
    @GetMapping
    public ResponseEntity<?> getMovementStocks() {
        return new ResponseEntity<>(this.movementStockRepository.findAll(),HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody MovementStock movementStock) {
        return new ResponseEntity<>(this.movementStockRepository.save(movementStock),HttpStatus.CREATED);
    }
      
   
}
