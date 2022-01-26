package br.com.api.stock.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="product", schema= "api")
@Builder
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @NotNull
    @Column(name = "product_code", nullable = false)
    private String productCode;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "product_type", nullable = false)
    private String productType;

    @NotNull
    @Column(name = "cost_price", nullable = false)
    private BigDecimal costPrice;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Product product = (Product) o;
        return productCode != null && Objects.equals(productCode, product.productCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
