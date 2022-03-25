package com.challenge.DTO;

import java.util.ArrayList;
import java.util.List;

import com.challenge.domain.Character;
import com.challenge.domain.MovieSerie;
import com.challenge.service.CharacterMovieSeriesgenreService;

import lombok.Data;

@Data
public class DetailMovieDTOImpl {
	
	private Long id;

	public List<DetailCharacterDTO> detailCharacterDTOImplment(CharacterMovieSeriesgenreService characterMovieSeriesgenreService) {
		
		List<MovieSerie> movies = characterMovieSeriesgenreService.getMovieSeries();
		List<DetailCharacterDTO> detailMovie = new ArrayList<DetailCharacterDTO>();
		movies.forEach(mov ->{
			List<Character> Charactesrs = mov.getCharacter();
			Charactesrs.forEach(charMove->{
					DetailCharacterDTO detailCharacterDTO = new DetailCharacterDTO();
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
					detailMovie.add(detailCharacterDTO);
			});
		});
		
		return detailMovie;
	}
	
}
