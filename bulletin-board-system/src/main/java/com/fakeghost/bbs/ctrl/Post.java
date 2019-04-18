package com.fakeghost.bbs.ctrl;

import com.fakeghost.bbs.model.PostMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.cache.annotation.Cacheable;

@Controller
@RequestMapping(value="/posts")
public class Post{
    Logger log = Logger.getLogger(getClass().getSimpleName());

    @Autowired
    @Qualifier("postMapper")
      PostMapper postMapper;

    @ResponseBody
    @RequestMapping(value="")
    //Current Spring cache implementation uses all method parameters as the cache key if not specified otherwise.
    //@Cacheable(value="sampleCache", key="#offset")
    @Cacheable(value="sampleCache")
    public ResponseEntity posts(
          // get Post content
          // @RequestBody String txt
          // URL query param
          @RequestParam int offset,
          @RequestParam int limit
          ) {

       Map<String, Object> resp = new HashMap<String, Object>();

        List<com.fakeghost.bbs.model.Post> rows = postMapper.posts(offset, limit);
        if(null !=rows&& rows.size()> 0){
           resp.put("payload", rows);
           resp.put("msg", "Success.");
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
        Map<String, Object> resp = new HashMap<String, Object>();
        postMapper.submit(title, author, createTime, category, content);
        resp.put("msg", "Success.");
        return ResponseEntity.ok().body(resp);
        // Method failure
        //return ResponseEntity.status(420).body(null);
    }
}
