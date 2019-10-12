package br.com.multitenancy.tenancy.modelo.tenancy_dois;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @Column(length = 50, nullable = false)
    private String name;
   
    @Column(precision = 15, scale = 2, nullable = false)
    private Double price;
  
    @Column(name = "current_inventory", length = 5, nullable = false)
    private Integer currentInventory;

}
