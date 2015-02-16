package domain;

import domain.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-14T07:28:21")
@StaticMetamodel(Address.class)
public class Address_ extends CommonEntity_ {

    public static volatile SingularAttribute<Address, Integer> zip;
    public static volatile SingularAttribute<Address, String> country;
    public static volatile SingularAttribute<Address, String> addFirstLine;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, String> state;
    public static volatile SingularAttribute<Address, Customer> customer;

}