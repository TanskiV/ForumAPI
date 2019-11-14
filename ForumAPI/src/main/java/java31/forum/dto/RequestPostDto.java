package java31.forum.dto;

import java.util.Set;

import lombok.Getter;
@Getter
public class RequestPostDto {
	String title;
	String content;
	Set<String> tags;
}
