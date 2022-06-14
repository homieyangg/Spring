package product.service;

import java.util.List;

import product.model.Productbean;

public interface ProductService {

	Object createProduct(Productbean productbean);

	void deleteProduct(Integer pd_id);

	void updateProduct(Productbean productbean);

	Productbean findProduct(Integer pd_id);

	 List<Productbean> showProductByTable();

	void close();
}
