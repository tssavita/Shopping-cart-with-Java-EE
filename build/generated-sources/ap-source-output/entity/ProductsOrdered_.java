package entity;

import entity.Orders;
import entity.Product;
import entity.ProductsOrderedPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-10T10:31:58")
@StaticMetamodel(ProductsOrdered.class)
public class ProductsOrdered_ { 

    public static volatile SingularAttribute<ProductsOrdered, Product> product;
    public static volatile SingularAttribute<ProductsOrdered, ProductsOrderedPK> shoppingCartPK;
    public static volatile SingularAttribute<ProductsOrdered, Integer> productQty;
    public static volatile SingularAttribute<ProductsOrdered, Orders> orders;

}