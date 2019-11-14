package java31.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import java31.forum.domein.Post;
import java31.forum.dto.PostDto;

public interface IPostsRepository extends MongoRepository<Post, String>{
Stream<Post> findPostByAuthor(String author);
Stream<Post> findByTagsIn(List<String> tags);
Iterable<PostDto> findPostsByDateCreated(LocalDate from, LocalDate to);
}
