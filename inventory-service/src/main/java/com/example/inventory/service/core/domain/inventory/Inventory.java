package com.example.inventory.service.core.domain.inventory;


import com.example.inventory.service.infrastructure.data.db.entities.InventoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    private Integer id;

    private String productCode;

    private Integer available;

    public void setQuantityAvailable(int orderQuantity) {
        if (orderQuantity > getAvailable())
            throw new IllegalArgumentException("Product is out of stock!");
        setAvailable(getAvailable() - orderQuantity);
    }

    public static Inventory fromDomain(InventoryEntity entity) {
        return Inventory.builder()
                .id(entity.getId())
                .productCode(entity.getProductCode())
                .available(entity.getAvailable())
                .build();
    }
}
