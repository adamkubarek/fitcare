package pl.javastyle.fitcare.product;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.core.AbstractCrudOperations;
import pl.javastyle.fitcare.category.Category;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.DbErrors;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
class ProductDAOImpl extends AbstractCrudOperations<Product> implements ProductDAO {

    public ProductDAOImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);

        if (query.getResultList().isEmpty()) {
            throw new ApplicationException(DbErrors.ITEMS_NOT_FOUND);
        }

        return query.getResultList();
    }

    @Override
    public Product save(Product product) {
        setCategoryIfAlreadyExists(product);
        try {
            return super.save(product);
        } catch (PersistenceException e) {
            throw new ApplicationException(DbErrors.DUPLICATED_PRODUCT_NAME);
        }
    }

    private void setCategoryIfAlreadyExists(Product product) {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name", Category.class);
        query.setParameter("name", product.getCategory().getName());

        if (!query.getResultList().isEmpty()) {
            product.setCategory(query.getSingleResult());
        }
    }
}