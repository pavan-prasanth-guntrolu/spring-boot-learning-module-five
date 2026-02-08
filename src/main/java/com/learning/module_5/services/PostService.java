package com.learning.module_5.services;

import com.learning.module_5.dto.PostDTO;
import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
