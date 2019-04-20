package com.fakeghost.bbs;

import com.fakeghost.bbs.model.User;
import com.fakeghost.bbs.model.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
 
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring.xml"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ModelMapperTest {
    final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("userMapper")
    UserMapper userMapper;

    @Test
    public void testQueryList() {
       //AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
         //ctx.register(AppConfig.class, OtherConfig.class);
         //ctx.register(AdditionalConfig.class);
         //ctx.refresh();
       assertThat(userMapper, instanceOf(UserMapper.class));

       List<User> rows = userMapper.users(32);
       if(rows != null && rows.size() >0){
         for(int i = 0; i< rows.size(); i++){
            User row = rows.get(i);
            int id = row.id;
            String nickname = row.nickname;
            log.info("index: " + i + ", id: " + id + ", nickname: " + nickname);
         }
         User one = rows.get(0);
         assertThat(one.nickname, is(equalTo("Jenkins")));
       }
    }
}

