package com.example.euphoriagifts2.service;


import com.example.euphoriagifts2.model.service.CommentServiceModel;
import com.example.euphoriagifts2.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    List<CommentViewModel> getAllCommentsByGiftId(Long giftId);


    CommentViewModel createComment(CommentServiceModel serviceModel);
}
