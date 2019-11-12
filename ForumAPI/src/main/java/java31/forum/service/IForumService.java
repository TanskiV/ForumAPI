package java31.forum.service;

import java.util.List;

import java31.forum.domein.Post;
import java31.forum.dto.MessageDto;
import java31.forum.dto.PostDto;

public interface IForumService {
Post addPost (String author, PostDto postDto);
Post findPostById(String id);
Post deletePost(String id);
Post updatePost (String id, PostDto updatePostDto);
boolean addLikeToPost(String id);
Post addCommentToPost(String id, String author, MessageDto messageDto);
List<Post> findPostByAutor(String autor);
}
