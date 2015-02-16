package domain;

import domain.Account;
import domain.Claim;
import domain.Customer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-14T07:28:21")
@StaticMetamodel(Policy.class)
public class Policy_ extends CommonEntity_ {

    public static volatile SingularAttribute<Policy, Long> productId;
    public static volatile SingularAttribute<Policy, Long> providerId;
    public static volatile ListAttribute<Policy, Claim> claim;
    public static volatile SingularAttribute<Policy, Date> policyEndDate;
    public static volatile SingularAttribute<Policy, Date> policyStartDate;
    public static volatile SingularAttribute<Policy, Account> account;
    public static volatile SingularAttribute<Policy, Customer> customer;

}