package domain;

import domain.Policy;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-14T07:28:21")
@StaticMetamodel(Claim.class)
public class Claim_ extends CommonEntity_ {

    public static volatile SingularAttribute<Claim, Date> reportDate;
    public static volatile SingularAttribute<Claim, Date> incidentDate;
    public static volatile SingularAttribute<Claim, Policy> policy;

}