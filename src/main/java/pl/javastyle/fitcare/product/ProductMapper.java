package pl.javastyle.fitcare.product;

import org.springframework.stereotype.Component;
import pl.javastyle.fitcare.core.Mapper;
import pl.javastyle.fitcare.category.Category;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {

    @Override
    public Product dtoToDomain(ProductDTO productDTO) {
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCalories(productDTO.getCalories());
        product.setMacronutrients(productDTO.getMacronutrients());

        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public ProductDTO domainToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCalories(product.getCalories());
        productDTO.setMacronutrients(product.getMacronutrients());
        productDTO.setCategory(product.getCategory().getName());

        return productDTO;
    }
}