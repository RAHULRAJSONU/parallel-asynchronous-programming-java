package io.github.rahulrajsonu.executor;

import io.github.rahulrajsonu.domain.Product;
import io.github.rahulrajsonu.domain.ProductInfo;
import io.github.rahulrajsonu.domain.Review;
import io.github.rahulrajsonu.service.ProductInfoService;
import io.github.rahulrajsonu.service.ReviewService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static io.github.rahulrajsonu.util.CommonUtil.stopWatch;
import static io.github.rahulrajsonu.util.LoggerUtil.log;

public class ProductService {
    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductService(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        Future<ProductInfo> productInfoFuture = executorService.submit(()->productInfoService.retrieveProductInfo(productId));
        Future<Review> reviewFuture = executorService.submit(()->reviewService.retrieveReviews(productId));

        Review review = null;
        ProductInfo productInfo=null;
        try {
            productInfo = productInfoFuture.get();
            review = reviewFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductService productService = new ProductService(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);
        executorService.shutdown();
    }
}
