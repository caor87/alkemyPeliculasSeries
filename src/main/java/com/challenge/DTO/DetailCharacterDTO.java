package com.challenge.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class DetailCharacterDTO {

	private Long idCharacter;
	private String imageCharacter;
	private String nameCharacter;
	private String ageCharacter;
	private String pesoCharacter;
	private String historyCharacter;
	private Long idMovie;
	private String imageMovie;
	private String titleMovie;
	private Date CreationdateMovie;
	private int calificationMovie;
	private Long idGenre;
	private String nameGenre;
	private String imageGenre;
	
}
