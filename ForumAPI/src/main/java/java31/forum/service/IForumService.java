package java31.forum.service;

import java.time.LocalDate;
import java.util.List;

import java31.forum.domein.Post;
import java31.forum.dto.MessageDto;
import java31.forum.dto.PostDto;
import java31.forum.dto.RequestPostDto;

public interface IForumService {
PostDto addPost (String author, RequestPostDto postDto);
PostDto findPostById(String id);
PostDto deletePost(String id);
PostDto updatePost (String id, RequestPostDto updatePostDto);
boolean addLikeToPost(String id);
PostDto addCommentToPost(String id, String author, MessageDto messageDto);
List<Post> findPostByAutor(String autor);
Iterable<PostDto> findByTags(List<String> tags);

Iterable<PostDto> findPostsCreatedBetweenDates(LocalDate from, LocalDate to);
}
