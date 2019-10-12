package br.com.multitenancy.tenancy.modelo.tenancy_dois;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movement_stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
public class MovementStock implements Serializable {

    @Id
    @Column(name = "document", length = 5, nullable = false)
    @EqualsAndHashCode.Include
    private String document;
    
    @Column(name = "type", length = 1, nullable = false)
    private String type;
    
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "code_product", nullable = false, foreignKey = @ForeignKey(name = "movement_stockFKproduct"))
    private Product product;
    
    @Column(name="quantity",length = 5,nullable=false)
    private Integer quantity;

}
