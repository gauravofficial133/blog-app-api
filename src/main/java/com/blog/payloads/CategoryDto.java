package com.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty(message = "category title cannot be empty")
	private String categoryTitle;
	
	@NotEmpty(message = "category description cannot be empty")
	private String categoryDescription;
}
