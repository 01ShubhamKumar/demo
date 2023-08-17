package com.example.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResposne {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private  int totalPages;
    private  boolean last;

}
