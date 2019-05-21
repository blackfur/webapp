package com.fakeghost.bbs.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fakeghost.bbs.model.Post;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "posts", path = "posts")
public interface PostRepo extends CrudRepository<Post, Long> {

    @Override
    @RestResource(exported = false)
    void delete(Post entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    //@RestResource(exported = false) void deleteAll(Iterable<? extends Post> entities);

    //@RestResource(exported = false) void deleteById(Long aLong);

    @RestResource(path = "byTitle", rel = "customfindmethod")
    Post findByTitle(@Param("title") String title);
}
