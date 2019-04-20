package com.fakeghost.bbs.conf;

import com.fakeghost.bbs.model.PostMapper;
import com.fakeghost.bbs.model.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatis {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Bean
    public MapperFactoryBean<PostMapper> postMapper() throws Exception {
        return mapper(PostMapper.class);
    }
    @Bean
    public MapperFactoryBean<UserMapper> userMapper() throws Exception {
        return mapper(UserMapper.class);
    }
    <T> MapperFactoryBean<T> mapper(Class mapperClass) throws Exception {
        MapperFactoryBean<T> factoryBean = new MapperFactoryBean<>();
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        factoryBean.setMapperInterface(mapperClass);
        return factoryBean;
    }
}
