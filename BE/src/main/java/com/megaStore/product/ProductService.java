package com.megaStore.product;

import com.megaStore.product.dao.ProductDao;
import com.megaStore.product.bean.Product;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getProducts() {
        return productDao.getProducts();
    }
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    public int getNumberOfProducts() {
        return productDao.getNumberOfProducts();
    }

    public void insertNewProduct(Product product) {
        productDao.insertNewProduct(product);
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productDao.deleteProduct(id);
    }
}
