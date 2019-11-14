package java31.forum.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java31.forum.dao.IPostsRepository;
import java31.forum.dao.PostNotFoundThrow;
import java31.forum.domein.Comment;
import java31.forum.domein.Post;
import java31.forum.dto.MessageDto;
import java31.forum.dto.PostDto;
import java31.forum.dto.RequestPostDto;

@Service
public class ForumServiceImpl implements IForumService{
	@Autowired
	IPostsRepository iPostsRepository;
	//static int tempId = 1;

	@Override
	public PostDto addPost(String author, RequestPostDto postDto) {

		Post tempPost = new Post( postDto.getTitle(),
				postDto.getContent(), author, postDto.getTags());

		iPostsRepository.save(tempPost);
		return postToPostDto(tempPost);
	}



	@Override
	public PostDto findPostById(String id) {
		
		Post post =  iPostsRepository.findById(id).orElseThrow(()-> new PostNotFoundThrow(id));
		return postToPostDto(post);
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = iPostsRepository.findById(id).orElseThrow(()-> new PostNotFoundThrow(id));
		iPostsRepository.delete(post);
		return postToPostDto(post);
	}

	@Override
	public PostDto updatePost(String id, RequestPostDto updatePostDto) {
		Post post = iPostsRepository.findById(id).orElseThrow(()-> new PostNotFoundThrow(id));
		post.setContent(updatePostDto.getContent());
		post.setTitle(updatePostDto.getTitle());
		iPostsRepository.save(post);
		return postToPostDto(post);
	}

	@Override
	public boolean addLikeToPost(String id) {
		Post post =  iPostsRepository.findById(id).orElseThrow(() -> new PostNotFoundThrow(id));
		int postLike = post.getLikes();
		post.addLike();
		if (postLike == post.getLikes() -1) {
			iPostsRepository.save(post);
			return true;
		}else return false;
	}

	@Override
	public PostDto addCommentToPost(String idPost, String author, MessageDto messageDto) {
		Post post = iPostsRepository.findById(idPost).orElseThrow(()-> new PostNotFoundThrow(idPost));
		post.addComment(new Comment(author, messageDto.getMessage()));
		iPostsRepository.save(post);
		return postToPostDto(post);
	}

	@Override
	public List<Post> findPostByAutor(String autor) {
		
		return iPostsRepository.findPostByAuthor(autor).map(p-> p).collect(Collectors.toList());
	}
	
	private PostDto postToPostDto(Post tempPost) {
		PostDto postDto = new PostDto(tempPost.getId(),tempPost.getTitle(), tempPost.getContent(),
				tempPost.getAuthor(), tempPost.getDateCreated(), tempPost.getTags(),tempPost.getLikes(),
				tempPost.getComments());

		return postDto;
	}


	@Override
	public Iterable<PostDto> findPostsByTags(List<String> tags) {
		
		return iPostsRepository.findPostsByTags(tags);
	}



	@Override
	public Iterable<PostDto> findPostsCreatedBetweenDates(LocalDate from, LocalDate to) {
		
		return iPostsRepository.findPostsByDateCreated(from, to);
	}

}
