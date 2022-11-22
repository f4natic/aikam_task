package com.example.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PurchaseId implements Serializable {

    @Column(name="customer_id")
    private Long customersId;

    @Column(name="product_id")
    private Long productsId;

    public PurchaseId() {
    }

    public PurchaseId(Long customersId, Long productsId) {
        this.customersId=customersId;
        this.productsId=productsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseId that = (PurchaseId) o;

        if (customersId != null ? !customersId.equals(that.customersId) : that.customersId != null) return false;
        return productsId != null ? productsId.equals(that.productsId) : that.productsId == null;
    }

    @Override
    public int hashCode() {
        int result = customersId != null ? customersId.hashCode() : 0;
        result = 31 * result + (productsId != null ? productsId.hashCode() : 0);
        return result;
    }
}