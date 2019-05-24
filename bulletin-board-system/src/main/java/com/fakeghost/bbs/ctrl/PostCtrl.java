package com.fakeghost.bbs.ctrl;

import com.fakeghost.bbs.model.Post;
import com.fakeghost.bbs.repo.PostRepo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.internal.ast.util.SessionFactoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

@Controller
@RequestMapping(value="/posts")
@Transactional
public class PostCtrl{
    final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    PostRepo repo;
    @Autowired SessionFactory sessionf;
    //@Autowired EntityManagerFactory entitymf;

    @ResponseBody
    @RequestMapping
    public ResponseEntity posts(
          @RequestParam int offset,
          @RequestParam int limit
          ) {

       Map<String, Object> resp = new HashMap<String, Object>();

        //List<com.fakeghost.bbs.model.Post> rows = repo.findAll(offset, limit);
         PageRequest argv = new PageRequest(offset, limit);
        Page<Post> page = repo.findAll(argv);
        if(null !=page && page.getSize() > 0){
           resp.put("payload", page.getContent());
           resp.put("msg", "Successfully");
           log.info("Query posts Successfully.");
            return ResponseEntity.ok(resp);
        }
        resp.put("msg", "No posts Found.");
        // No Content
         return ResponseEntity.status(204).body(resp);
    }
    @ResponseBody
    @RequestMapping(value="/submit")
    @Transactional
    public ResponseEntity submit(
            @RequestParam String title,
            @RequestParam String author,
            //@RequestParam String createTime,
            @RequestParam String category,
            @RequestParam String content
            ){
        Post p = new Post();
        p.setTimestamp(System.currentTimeMillis());
        p.setId(System.currentTimeMillis());
        p.setAuthor(author);
        p.setCategory(category);
        p.setTitle(title);
        //p.setCreateTime(createTime);
        p.setContent(content);

        //repo.save(p);
         Query q = sessionf.getCurrentSession()
            .createSQLQuery("insert into Post (id, title, content, timestamp) values (:id, :title, :content, :timestamp)");
         q.setParameter("id", p.getId());
         q.setParameter("title", p.getTitle());
         q.setParameter("content", p.getContent());
         q.setParameter("timestamp", p.getTimestamp());
         q.executeUpdate();
        //
        Map<String, Object> resp = new HashMap<>();
		  resp.put("timestamp", p.getTimestamp());
        resp.put("msg", "Success.");
        return ResponseEntity.ok().body(resp);
        // Method failure
        //return ResponseEntity.status(420).body(null);
    }
    @RequestMapping("/{id}")
    public ResponseEntity findById( @PathVariable Long id) {

        Map<String, Object> resp = new HashMap<String, Object>();
        Post p= repo.findOne(id);
        if(null !=p){
           log.info("Query posts Successfully.");
           return ResponseEntity.ok(p.hashmap());
        }
        resp.put("msg", "Not Found.");
        // No Content
         return ResponseEntity.status(204).body(resp);
    }
    // total
    @RequestMapping("/total")
    @ResponseBody
    public Long total(
            @RequestParam long size
    ){
        //long all = sessionf.getCurrentSession().createQuery("select count(1) from post").getFirstResult();
         //Session ss= entitymf.createEntityManager().unwrap(Session.class);
        //long all = ss.createQuery("select count(1) from post").getFirstResult();
        return  repo.count();
    }
    @RequestMapping("/timestamp/{timestamp}")
    public ResponseEntity findByTimestamp( @PathVariable Long timestamp) {

        Map<String, Object> resp = new HashMap<String, Object>();
        Post p = (Post) sessionf.getCurrentSession()
        .createCriteria(Post.class)
        //createQuery("select id, title, content, timestamp from Post where timestamp = :timestamp")
        //.setLong("timestamp", timestamp)
        .add(Restrictions.eq("timestamp", timestamp))
        .uniqueResult();
        //Post p= repo.findOne(id);
        if(null !=p){
           log.info("Query posts Successfully.");
           return ResponseEntity.ok(p.hashmap());
        }
        resp.put("msg", "Not Found.");
        // No Content
         return ResponseEntity.status(204).body(resp);
    }
}
