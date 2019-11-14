package java31.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java31.forum.domein.Post;
import java31.forum.dto.MessageDto;
import java31.forum.dto.PostDto;
import java31.forum.dto.RequestPostDto;
import java31.forum.service.IForumService;

@RestController
public class PostController {
	@Autowired
	IForumService iForumService;
	static int countId = 1;
	String tempId = "id: " + countId;
	
	@PostMapping("/forum/post/{author}")
	public PostDto addPost(@PathVariable String author, @RequestBody RequestPostDto post) {
		return iForumService.addPost(author , post);
	}
	
	@GetMapping("/forum/post/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return iForumService.findPostById(id);
	}
	@DeleteMapping("/forum/post/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return iForumService.deletePost(id);
	}
	
	@PutMapping("/forum/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody RequestPostDto postDto) {
		return iForumService.updatePost(id ,postDto);
	}
	
	@PutMapping("/forum/post/{id}/like")
	public boolean addLikeToPost(@PathVariable String id) {
		return iForumService.addLikeToPost(id);
	}
	
	@PutMapping("/forum/post/{id}/comment/{author}")
	public PostDto addCommentToPost(@PathVariable String postId, @PathVariable String author ,@RequestBody MessageDto messageDto) {
		return iForumService.addCommentToPost(postId, author, messageDto);
	}
	@GetMapping("/forum/posts/author/{author}")
	public List<Post> findPostByAuthor(@PathVariable String author) {
		return iForumService.findPostByAutor(author);
	}

}
