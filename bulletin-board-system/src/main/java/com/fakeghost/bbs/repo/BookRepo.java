package com.fakeghost.bbs.repo;
import com.fakeghost.bbs.doc.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

@SuppressWarnings("unused")
public interface BookRepo extends ElasticsearchRepository<Book, String> {

   Page<Book> findByAuthor(String author, Pageable pageable);

   List<Book> findByTitle(String title);

}
