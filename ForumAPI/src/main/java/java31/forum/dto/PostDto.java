package java31.forum.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	String title;
	String content;
	Set<String> tags;

}
