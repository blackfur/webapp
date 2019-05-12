package com.fakeghost.bookshelf;

import com.fakeghost.bookshelf.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface TodoRepo extends JpaRepository<Todo, Long> {}
