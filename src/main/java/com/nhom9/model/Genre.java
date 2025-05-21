package fa.mockproject.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class Genre {
	private UUID genreId;
	@NotBlank
	private String genreName;
	
	public Genre() {
		super();
	}

	public Genre(UUID genreId, String genreName) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
	}

	public UUID getGenreId() {
		return genreId;
	}

	public void setGenreId(UUID genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	
	
	
}
