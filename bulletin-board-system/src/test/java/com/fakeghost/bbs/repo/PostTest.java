package com.fakeghost.bbs.repo;

import org.hibernate.SessionFactory;
import com.fakeghost.bbs.App;
import com.fakeghost.bbs.conf.AppConf;
import com.fakeghost.bbs.model.Post;
import org.hibernate.criterion.Projections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Iterator;
import javax.transaction.Transactional;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class}, webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
//@Transactional
public class PostTest {
    final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired PostRepo repo;
    @Autowired SessionFactory sessionf;
   //@PersistenceContext EntityManager entitymr;

    @Test
    public void posts_ShouldReturnAllPosts(){
		 repo.save(new Post("CN", "CN Sucks.", System.currentTimeMillis()));
        Iterable<Post> all = repo.findAll();
        Iterator<Post> transvers = all.iterator();
        assertTrue(transvers.hasNext());
        Post one = transvers .next();
        log.info(one.toString());
    }
    @Test
    public void ShouldReturnNumber(){
        Integer all= ((Long)sessionf.getCurrentSession().createQuery("select count(1) from Post").uniqueResult()).intValue();
        //Long all = (Long)sessionf.getCurrentSession().createCriteria("Post") .setProjection(Projections.rowCount()) .uniqueResult();
        // Integer all = sessionf.getCurrentSession().createQuery("select count(1) from Post").getFirstResult();
        assertTrue( null !=all && all >= 0);
        log.info(all.toString());
    }
    @Test
    public void ShouldRetrieveSavedOne(){
       // Save
        Post p = new Post();
        p.setTimestamp(System.currentTimeMillis());
        p.setTitle("Title");
        p.setContent("txt");
        log.info("##############################");
        p = repo.save(p);
        log.info("Saved ID: " + p.getId());
        //entitymr.persist(p);
        // Retrieve
         //p = repo.findOne(p.getId());
         //assertTrue(null != p);
         log.info(p.toString());
        log.info("##############################");
    }

}
