package br.com.multitenancy.tenancy.controle;

import br.com.multitenancy.tenancy.modelo.tenancy.User;
import br.com.multitenancy.tenancy.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.userRepository.findAll(), HttpStatus.ACCEPTED);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        return new ResponseEntity<>(this.userRepository.save(user),HttpStatus.CREATED);
    }
    

}
