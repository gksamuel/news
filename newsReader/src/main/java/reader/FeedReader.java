/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import com.gachanja.newsReader.models.Articles;
import com.gachanja.newsReader.models.Authors;
import com.gachanja.newsReader.models.Categories;
import com.gachanja.newsReader.models.Status;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import java.net.URL;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gachanja
 */
public class FeedReader {

    public static void main(String[] args) {
        boolean ok = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.gachanja_mavenproject3_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            URL feedUrl = new URL("http://feeds.reuters.com/Reuters/PoliticsNews");
            Status status = (Status) em.createNamedQuery("Status.findByStatusid").setParameter("statusid", 1).getSingleResult();
            Authors author = (Authors) em.createNamedQuery("Authors.findByAuthorid").setParameter("authorid", 1).getSingleResult();
            Categories category = (Categories) em.createNamedQuery("Categories.findByCategoryid").setParameter("categoryid", 6).getSingleResult();
            SyndFeedInput input = new SyndFeedInput();
            SyndFeedImpl feed = (SyndFeedImpl) input.build(new XmlReader(feedUrl));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                Articles article = new Articles();
                article.setTitle(entry.getTitle());
                article.setDatecreated(entry.getPublishedDate());
                System.out.println(entry.getTitle());
                Date publishedDate = entry.getPublishedDate();
                System.out.println(publishedDate);
                SyndContent description = entry.getDescription();
                String content = String.valueOf(description);
                article.setSummary(content.substring(156, content.indexOf("<div")));
                article.setCategoryid(category);
                article.setDatemodified(new Date());
                article.setStatusid(status);
                article.setAuthorid(author);
                em.persist(article);
            }
            em.getTransaction().commit();
        } catch (IOException | IllegalArgumentException | FeedException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
