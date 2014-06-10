package entity;

import entity.Feedback;
import entity.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-10T10:31:58")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> lastName;
    public static volatile SingularAttribute<Customer, String> emailId;
    public static volatile SingularAttribute<Customer, String> customerId;
    public static volatile SingularAttribute<Customer, String> streetNo;
    public static volatile CollectionAttribute<Customer, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Customer, String> town;
    public static volatile SingularAttribute<Customer, String> firstName;
    public static volatile CollectionAttribute<Customer, Orders> ordersCollection;
    public static volatile SingularAttribute<Customer, String> country;

}