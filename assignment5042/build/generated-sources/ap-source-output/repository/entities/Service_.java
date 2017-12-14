package repository.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import repository.entities.Serviceuse;
import repository.entities.Subservice;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-27T16:17:51")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile SingularAttribute<Service, String> servicetype;
    public static volatile SingularAttribute<Service, String> thumbnail;
    public static volatile SingularAttribute<Service, String> description;
    public static volatile SingularAttribute<Service, String> servicename;
    public static volatile CollectionAttribute<Service, Subservice> subserviceCollection;
    public static volatile SingularAttribute<Service, Integer> serviceid;
    public static volatile CollectionAttribute<Service, Serviceuse> serviceuseCollection;

}