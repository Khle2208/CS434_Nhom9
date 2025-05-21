package fa.mockproject.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
public class Author {
	private UUID authorId;
	@NotBlank
	private String authorName;
	private String introduction;
	
	public Author() {
		super();
	}

	public Author(UUID authorId, String authorName, String introduction) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.introduction = introduction;
	}

	public UUID getAuthorId() {
		return authorId;
	}

	public void setAuthorId(UUID authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
	
	

}
