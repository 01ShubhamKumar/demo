package com.example.controller;

import com.example.payload.CommentDto;
import com.example.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")

    public ResponseEntity<Object>createComment(@Valid  @PathVariable("postId") long postId , @RequestBody CommentDto commentDto, BindingResult result){
if(result.hasErrors()){
    return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
}
        CommentDto dto=commentService.createComment(postId, commentDto);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable("postId") long postId){
         return  commentService.getCommentsByPostId(postId);
    }
    //http://localhost:8080/api/posts/1/comments/1
    @GetMapping("/posts/{postId}/comments/{id}")
    public  ResponseEntity<CommentDto> getCommentById(@PathVariable("postId")long postId,@PathVariable("id")long commentid){
        CommentDto dto=commentService.getCommentById(postId ,commentid);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId")long postId,@PathVariable("id") long id,
                                                    @RequestBody CommentDto commentDto){
        CommentDto dto=commentService.updateComment(postId,id,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String>deleteComment(@PathVariable("postId") long postId,@PathVariable("id")long id){
        commentService.deleteComment(postId,id);
        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }


}
