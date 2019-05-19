package com.fakeghost.bbs.repo;

import com.fakeghost.bbs.App;
import com.fakeghost.bbs.doc.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class PostTest {
    final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    PostRepo repo;
    @Test
    public void posts_ShouldReturnAllPosts(){
        Iterable<Post> all = repo.findAll();
        Iterator<Post> transvers = all.iterator();
        assertTrue(transvers.hasNext());
        Post one = transvers .next();
        log.info(one.toString());
    }
}
