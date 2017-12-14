package repository.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import repository.entities.Serviceuse;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-27T16:17:51")
@StaticMetamodel(Userinfo.class)
public class Userinfo_ { 

    public static volatile SingularAttribute<Userinfo, String> firstname;
    public static volatile SingularAttribute<Userinfo, String> password;
    public static volatile SingularAttribute<Userinfo, String> address;
    public static volatile SingularAttribute<Userinfo, Integer> phonenumber;
    public static volatile SingularAttribute<Userinfo, String> usertype;
    public static volatile SingularAttribute<Userinfo, Integer> id;
    public static volatile CollectionAttribute<Userinfo, Serviceuse> serviceuseCollection1;
    public static volatile SingularAttribute<Userinfo, String> email;
    public static volatile CollectionAttribute<Userinfo, Serviceuse> serviceuseCollection;
    public static volatile SingularAttribute<Userinfo, String> lastname;

}