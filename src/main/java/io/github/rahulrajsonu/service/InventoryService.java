package io.github.rahulrajsonu.service;

import io.github.rahulrajsonu.domain.Inventory;
import io.github.rahulrajsonu.domain.ProductOption;

import java.util.concurrent.CompletableFuture;

import static io.github.rahulrajsonu.util.CommonUtil.delay;


public class InventoryService {
    public Inventory addInventory(ProductOption productOption) {
        delay(500);
        return Inventory.builder()
                .count(2).build();

    }

    public CompletableFuture<Inventory> addInventory_CF(ProductOption productOption) {

        return CompletableFuture.supplyAsync(() -> {
            delay(500);
            return Inventory.builder()
                    .count(2).build();
        });

    }
}
