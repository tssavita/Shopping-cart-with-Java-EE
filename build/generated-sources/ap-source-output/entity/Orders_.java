package entity;

import entity.Customer;
import entity.ProductsOrdered;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-10T10:31:58")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Float> amount;
    public static volatile SingularAttribute<Orders, Customer> customerId;
    public static volatile SingularAttribute<Orders, Date> dateCreated;
    public static volatile CollectionAttribute<Orders, ProductsOrdered> shoppingCartCollection;
    public static volatile SingularAttribute<Orders, Integer> orderId;

}