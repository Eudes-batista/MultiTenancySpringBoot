package br.com.multitenancy.tenancy.repositorio;

import br.com.multitenancy.tenancy.modelo.tenancy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    
}
