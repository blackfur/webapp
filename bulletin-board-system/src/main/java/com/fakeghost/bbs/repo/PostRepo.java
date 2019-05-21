package com.fakeghost.bbs.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fakeghost.bbs.model.Post;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "posts", path = "posts")
//public interface PostRepo extends CrudRepository<Post, Long> {
public interface PostRepo extends PagingAndSortingRepository<Post, Long> {

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

    /*
   @Override protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration conf) {
   conf.setPageParamName("page") .setLimitParamName("limit") .setSortParamName("sort");
   }
   */
}
