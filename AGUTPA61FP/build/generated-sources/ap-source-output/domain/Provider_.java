package domain;

import domain.Product;
import domain.ProductService;
import domain.security.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-14T07:28:21")
@StaticMetamodel(Provider.class)
public class Provider_ extends CommonEntity_ {

    public static volatile ListAttribute<Provider, ProductService> productServices;
    public static volatile SingularAttribute<Provider, User> user;
    public static volatile SingularAttribute<Provider, String> providername;
    public static volatile ListAttribute<Provider, Product> products;

}