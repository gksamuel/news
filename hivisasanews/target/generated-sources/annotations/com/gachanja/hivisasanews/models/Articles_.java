package com.gachanja.hivisasanews.models;

import com.gachanja.hivisasanews.models.Authors;
import com.gachanja.hivisasanews.models.Categories;
import com.gachanja.hivisasanews.models.Pageviews;
import com.gachanja.hivisasanews.models.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-26T19:19:01")
@StaticMetamodel(Articles.class)
public class Articles_ { 

    public static volatile CollectionAttribute<Articles, Pageviews> pageviewsCollection;
    public static volatile SingularAttribute<Articles, String> summary;
    public static volatile SingularAttribute<Articles, Integer> articleid;
    public static volatile SingularAttribute<Articles, Integer> pagerank;
    public static volatile SingularAttribute<Articles, Status> statusid;
    public static volatile SingularAttribute<Articles, Date> datecreated;
    public static volatile SingularAttribute<Articles, Categories> categoryid;
    public static volatile SingularAttribute<Articles, String> title;
    public static volatile SingularAttribute<Articles, String> image2;
    public static volatile SingularAttribute<Articles, Integer> sticky;
    public static volatile SingularAttribute<Articles, String> image1;
    public static volatile SingularAttribute<Articles, String> article;
    public static volatile SingularAttribute<Articles, Authors> authorid;
    public static volatile SingularAttribute<Articles, Date> datemodified;

}