package com.gachanja.hivisasanews.models;

import com.gachanja.hivisasanews.models.Articles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-26T19:19:01")
@StaticMetamodel(Pageviews.class)
public class Pageviews_ { 

    public static volatile SingularAttribute<Pageviews, Articles> articleid;
    public static volatile SingularAttribute<Pageviews, Date> datecreated;
    public static volatile SingularAttribute<Pageviews, Integer> pageviewid;
    public static volatile SingularAttribute<Pageviews, String> ipaddress;

}