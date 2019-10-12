package br.com.multitenancy.tenancy.modelo.tenancy;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="tenancy_id",length = 4, nullable = false)
    private String tenancyId;
    
    @Column(length = 50, nullable = false)
    private String nome;
    
    @Column( length = 10, nullable = false)
    private String senha;
    
    @Column(length = 20, nullable = false)
    private String banco;

}
