package domain;

import domain.Product;
import domain.Provider;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-14T07:28:21")
@StaticMetamodel(ProductService.class)
public class ProductService_ extends CommonEntity_ {

    public static volatile SingularAttribute<ProductService, Product> product;
    public static volatile SingularAttribute<ProductService, Provider> provider;
    public static volatile SingularAttribute<ProductService, String> serviceName;

}