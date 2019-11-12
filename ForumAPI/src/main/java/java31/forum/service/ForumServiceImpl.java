package java31.forum.service;

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

@Service
public class ForumServiceImpl implements IForumService{
	@Autowired
	IPostsRepository iPostsRepository;
	static int tempId = 1;

	@Override
	public Post addPost(String author, PostDto postDto) {

		Post tempPost = new Post(tempId + "", postDto.getTitle(),
				postDto.getContent(), author, postDto.getTags());
		tempId ++;
		iPostsRepository.save(tempPost);
		return tempPost;
	}

	@Override
	public Post findPostById(String id) {
		
		return iPostsRepository.findById(id).orElseThrow(()-> new PostNotFoundThrow(id));
	}

	@Override
	public Post deletePost(String id) {
		Post post = iPostsRepository.findById(id).orElseThrow(()-> new PostNotFoundThrow(id));
		iPostsRepository.delete(post);
		return post;
	}

	@Override
	public Post updatePost(String id, PostDto updatePostDto) {
		Post post = iPostsRepository.findById(id).orElseThrow(()-> new PostNotFoundThrow(id));
		post.setContent(updatePostDto.getContent());
		post.setTitle(updatePostDto.getTitle());
		iPostsRepository.save(post);
		return post;
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
	public Post addCommentToPost(String idPost, String author, MessageDto messageDto) {
		Post post = iPostsRepository.findById(idPost).orElseThrow(()-> new PostNotFoundThrow(idPost));
		post.addComment(new Comment(author, messageDto.getMessage()));
		iPostsRepository.save(post);
		return post;
	}

	@Override
	public List<Post> findPostByAutor(String autor) {
		
		return iPostsRepository.findPostByAuthor(autor).map(p-> p).collect(Collectors.toList());
	}

}
