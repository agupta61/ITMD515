package domain;

import domain.Customer;
import domain.Policy;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-14T07:28:21")
@StaticMetamodel(Account.class)
public class Account_ extends CommonEntity_ {

    public static volatile SingularAttribute<Account, Integer> accountNo;
    public static volatile SingularAttribute<Account, String> bankName;
    public static volatile SingularAttribute<Account, Customer> customer;
    public static volatile SingularAttribute<Account, Policy> policy;

}