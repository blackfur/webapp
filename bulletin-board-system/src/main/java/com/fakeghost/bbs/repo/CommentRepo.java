package com.fakeghost.bbs.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fakeghost.bbs.model.Comment;
import javax.transaction.Transactional;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
@Transactional
public interface CommentRepo extends PagingAndSortingRepository<Comment, Long> {

    @Override
    @RestResource(exported = false)
    void delete(Comment entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}

