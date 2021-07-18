package io.github.rahulrajsonu.service;


import io.github.rahulrajsonu.domain.ProductInfo;
import io.github.rahulrajsonu.domain.ProductOption;

import java.util.List;

import static io.github.rahulrajsonu.util.CommonUtil.delay;


public class ProductInfoService {

    public ProductInfo retrieveProductInfo(String productId) {
        delay(1000);
        List<ProductOption> productOptions = List.of(new ProductOption(1, "64GB", "Black", 699.99),
                new ProductOption(2, "128GB", "Black", 749.99));
        return ProductInfo.builder().productId(productId)
                .productOptions(productOptions)
                .build();
    }
}
