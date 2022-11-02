package com.example.euphoriagifts2.service.impl;


import com.example.euphoriagifts2.model.entity.CommentEntity;
import com.example.euphoriagifts2.model.entity.GiftEntity;
import com.example.euphoriagifts2.model.entity.UserEntity;
import com.example.euphoriagifts2.model.service.CommentServiceModel;
import com.example.euphoriagifts2.model.view.CommentViewModel;
import com.example.euphoriagifts2.repository.CommentRepository;
import com.example.euphoriagifts2.service.CommentService;
import com.example.euphoriagifts2.service.GiftService;
import com.example.euphoriagifts2.service.UserService;
import com.example.euphoriagifts2.service.exceptions.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final UserService userService;
    private final GiftService giftService;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserService userService, GiftService giftService, CommentRepository commentRepository) {
        this.userService = userService;
        this.giftService = giftService;
        this.commentRepository = commentRepository;
    }

    @Transactional
    @Override
    public List<CommentViewModel> getAllCommentsByGiftId(Long giftId) {

        GiftEntity giftById = this.giftService.findGiftById(giftId);
        return giftById.getComments().stream()
                .map(this::mapComment)
                .collect(Collectors.toList());
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {

        Objects.requireNonNull(commentServiceModel.getCreator());
        UserEntity userByUsername = userService.findByUsername(commentServiceModel.getCreator());

        if (userByUsername == null) {
            throw new UsernameNotFoundException("User with username " + commentServiceModel.getCreator() + " does not exist!z");
        }

        GiftEntity giftById = giftService.findGiftById(commentServiceModel.getGiftId());

        if (giftById == null) {
            throw new ObjectNotFoundException("Gift with id: " + commentServiceModel.getGiftId() + " was not found!");
        }

        CommentEntity newComment = new CommentEntity();
        newComment.setMessage(commentServiceModel.getMessage());
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        newComment.setCreatedOn(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        newComment.setIsApproved(true);
        newComment.setUser(userByUsername);
        newComment.setGiftEntity(giftById);

        commentRepository.save(newComment);

        return mapComment(newComment);

    }

    private CommentViewModel mapComment(CommentEntity commentEntity) {

        CommentViewModel commentViewModel = new CommentViewModel();
        commentViewModel.setId(commentEntity.getId());
        commentViewModel.setApprove(true);
        commentViewModel.setCreatedOn(commentEntity.getCreatedOn());
        commentViewModel.setUser(String.format("%s %s", commentEntity.getUser().getFirstName(),
                commentEntity.getUser().getLastName()));
        commentViewModel.setMessage(commentEntity.getMessage());

        return commentViewModel;
    }
}
