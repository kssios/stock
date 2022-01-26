package br.com.api.stock.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import br.com.api.stock.enumeration.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name="movement", schema= "api")
@Builder
@AllArgsConstructor
public class Movement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "product_code", nullable = false)
    private String productCode;

    @NotNull
    @Column(name = "operation_type", nullable = false)
    private OperationType operationType;

    @NotNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

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
        Movement movement = (Movement) o;
        return id != null && Objects.equals(id, movement.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
