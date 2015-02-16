package domain;

import domain.ProductService;
import domain.Provider;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-14T07:28:21")
@StaticMetamodel(Product.class)
public class Product_ extends CommonEntity_ {

    public static volatile SingularAttribute<Product, String> name;
    public static volatile ListAttribute<Product, ProductService> productServices;
    public static volatile ListAttribute<Product, Provider> providers;

}