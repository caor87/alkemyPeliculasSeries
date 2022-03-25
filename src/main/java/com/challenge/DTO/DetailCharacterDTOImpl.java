package com.challenge.DTO;

import java.util.List;

import com.challenge.domain.Character;
import com.challenge.domain.MovieSerie;
import com.challenge.service.CharacterMovieSeriesgenreService;

import lombok.Data;

@Data
public class DetailCharacterDTOImpl {
	
	private int id;
	
	public DetailCharacterDTO detailCharacterDTOImplment(CharacterMovieSeriesgenreService characterMovieSeriesgenreService,
			Long id, Character charExits) {
		DetailCharacterDTO detailCharacterDTO = new DetailCharacterDTO();
		List<MovieSerie> movies = characterMovieSeriesgenreService.getMovieSeries();
		movies.forEach(mov ->{
			List<Character> Charactesrs = mov.getCharacter();
			Charactesrs.forEach(charMove->{
				if(charMove.getId() == id) {
					detailCharacterDTO.setAgeCharacter(charMove.getAge());;
					detailCharacterDTO.setCalificationMovie(mov.getCalification());
					detailCharacterDTO.setCreationdateMovie(mov.getCreationdate());
					detailCharacterDTO.setHistoryCharacter(charMove.getHistory());
					detailCharacterDTO.setIdCharacter(charMove.getId());
					detailCharacterDTO.setIdGenre(mov.getGenre().getId());
					detailCharacterDTO.setIdMovie(mov.getId());
					detailCharacterDTO.setImageCharacter(charMove.getImage());
					detailCharacterDTO.setImageGenre(mov.getGenre().getImage());
					detailCharacterDTO.setImageMovie(mov.getImage());
					detailCharacterDTO.setNameCharacter(charMove.getName());
					detailCharacterDTO.setNameGenre(mov.getGenre().getImage());
					detailCharacterDTO.setPesoCharacter(charMove.getPeso());
					detailCharacterDTO.setTitleMovie(mov.getTitle());
				}
			});
		});
		
		if(detailCharacterDTO.getIdCharacter() == null) {
			detailCharacterDTO.setAgeCharacter(charExits.getAge());
			detailCharacterDTO.setHistoryCharacter(charExits.getHistory());
			detailCharacterDTO.setIdCharacter(charExits.getId());
			detailCharacterDTO.setImageCharacter(charExits.getImage());
			detailCharacterDTO.setNameCharacter(charExits.getName());
			detailCharacterDTO.setPesoCharacter(charExits.getPeso());
		}
		return detailCharacterDTO;
	}

}
