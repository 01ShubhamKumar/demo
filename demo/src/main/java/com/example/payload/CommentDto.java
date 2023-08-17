package com.example.payload;

import com.example.entity.Post;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
   private  long id;
   @NotEmpty
   @Size(min=4,message = "Name should be at least 2 character")
   private  String name;
   @NotEmpty
   @Email
   private  String email;
   @NotEmpty
   @Size(min=5,message = "Name should be at least 5 characters")
   private  String body;

}
