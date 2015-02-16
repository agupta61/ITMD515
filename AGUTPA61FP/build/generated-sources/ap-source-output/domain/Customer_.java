package domain;

import domain.Account;
import domain.Address;
import domain.Policy;
import domain.security.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-14T07:28:21")
@StaticMetamodel(Customer.class)
public class Customer_ extends CommonEntity_ {

    public static volatile SingularAttribute<Customer, Address> address;
    public static volatile SingularAttribute<Customer, Date> dob;
    public static volatile SingularAttribute<Customer, String> name;
    public static volatile ListAttribute<Customer, Account> accounts;
    public static volatile SingularAttribute<Customer, User> user;
    public static volatile SingularAttribute<Customer, String> phoneno;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile ListAttribute<Customer, Policy> policy;

}