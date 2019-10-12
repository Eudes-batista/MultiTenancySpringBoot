package br.com.multitenancy.tenancy.repositorio.tenancy_dois;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.multitenancy.tenancy.modelo.tenancy_dois.MovementStock;

@Repository
@Transactional
public interface MovementStockRepository extends JpaRepository<MovementStock, String>{
    
}
