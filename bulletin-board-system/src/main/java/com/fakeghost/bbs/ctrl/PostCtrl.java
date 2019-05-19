package com.fakeghost.bbs.ctrl;

import com.fakeghost.bbs.model.Post;
import com.fakeghost.bbs.repo.PostRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/posts")
public class PostCtrl{
    final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    PostRepo repo;

    @ResponseBody
    @RequestMapping
    public ResponseEntity posts(
          @RequestParam int offset,
          @RequestParam int limit
          ) {

       Map<String, Object> resp = new HashMap<String, Object>();

        //List<com.fakeghost.bbs.model.Post> rows = repo.findAll(offset, limit);
        Iterable<com.fakeghost.bbs.model.Post> rows = repo.findAll();
        if(null !=rows&& rows.iterator().hasNext()){
           resp.put("payload", rows.toString());
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
    public ResponseEntity submit(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String createTime,
            @RequestParam String category,
            @RequestParam String content
            ){
        Post p = new Post();
        p.setAuthor(author);
        p.setCategory(category);
        p.setTitle(title);
        p.setCreateTime(createTime);
        p.setContent(content);

        repo.save(p);
        Map<String, Object> resp = new HashMap<>();
        resp.put("msg", "Success.");
        return ResponseEntity.ok().body(resp);
        // Method failure
        //return ResponseEntity.status(420).body(null);
    }
}
