package com.example.apiproject.dto;

import com.example.apiproject.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Long idPost;
    private String title;
    private String summary;
    private String content;
    private Long sportsFacilityId;

    public PostDTO(Post post) {
        this.idPost = post.getIdPost();
        this.title = post.getTitle();
        this.summary = post.getSummary();
        this.content = post.getContent();
        this.sportsFacilityId = post.getSportsFacility().getSportsFacilityId();
    }
}
