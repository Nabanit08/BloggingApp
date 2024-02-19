package com.masai.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostResponse {
private List<PostDTO> content;
private int pageNumber;
private int pageSize;
private long totalElements;
private int totalPages;
private boolean lastPage;
}
