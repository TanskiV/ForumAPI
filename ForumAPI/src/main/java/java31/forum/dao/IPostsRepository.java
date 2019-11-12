package java31.forum.dao;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import java31.forum.domein.Post;

public interface IPostsRepository extends MongoRepository<Post, String>{
Stream<Post> findPostById(String id);
Stream<Post> findPostByAuthor(String author);
}
