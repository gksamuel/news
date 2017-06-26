package com.gachanja.mavenproject3.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-26T14:27:21")
@StaticMetamodel(Pageviews.class)
public class Pageviews_ { 

    public static volatile SingularAttribute<Pageviews, String> ipaddress;
    public static volatile SingularAttribute<Pageviews, Integer> articleid;
    public static volatile SingularAttribute<Pageviews, Date> datecreated;
    public static volatile SingularAttribute<Pageviews, Integer> pageviewid;

}