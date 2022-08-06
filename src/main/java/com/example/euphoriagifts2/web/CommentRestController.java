package com.example.euphoriagifts2.web;

import com.example.euphoriagifts2.model.binding.CreateCommentBidingModel;
import com.example.euphoriagifts2.model.service.CommentServiceModel;
import com.example.euphoriagifts2.model.service.validation.ApiError;
import com.example.euphoriagifts2.model.view.CommentViewModel;
import com.example.euphoriagifts2.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import static org.springframework.http.HttpStatus.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
public class CommentRestController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/{giftId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(
            @PathVariable Long giftId, Principal principal, Model model){

        List<CommentViewModel> allCommentsByGiftId = commentService
                .getAllCommentsByGiftId(giftId);
        model.addAttribute("comments", allCommentsByGiftId);
        return ResponseEntity.ok(allCommentsByGiftId);

    }
    @PostMapping("/api/{giftId}/comments")
    public ResponseEntity<CommentViewModel> createComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long giftId,
            @RequestBody @Valid CreateCommentBidingModel createCommentBidingModel
    ) {

        CommentServiceModel serviceModel =
                modelMapper.map(createCommentBidingModel, CommentServiceModel.class);
        serviceModel.setGiftId(giftId);
        serviceModel.setCreator(principal.getUsername());

        CommentViewModel newComment =
                commentService.createComment(serviceModel);

        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s", giftId, newComment.getId()));

        return ResponseEntity.
                created(locationOfNewComment).
                body(newComment);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        List<FieldError> fieldErrors = exc.getFieldErrors();
        fieldErrors.forEach(fe ->
                apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }
}
