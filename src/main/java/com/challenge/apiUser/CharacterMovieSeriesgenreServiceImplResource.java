package com.challenge.apiUser;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.challenge.DTO.CharacterListDTO;
import com.challenge.DTO.DetailCharacterDTO;
import com.challenge.DTO.DetailCharacterDTOImpl;
import com.challenge.DTO.DetailMovieDTOImpl;
import com.challenge.DTO.ListMovieDTO;
import com.challenge.domain.Character;
import com.challenge.domain.Genre;
import com.challenge.domain.MovieSerie;
import com.challenge.service.CharacterMovieSeriesgenreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CharacterMovieSeriesgenreServiceImplResource {

	private final CharacterMovieSeriesgenreService characterMovieSeriesgenreService;

	@PostMapping("character/save")
	public ResponseEntity<Character> saveUser(@RequestBody Character character) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("character/save").toString());
		return ResponseEntity.created(uri).body(characterMovieSeriesgenreService.saveCharacter(character));
	}

	@GetMapping("character")
	public ResponseEntity<List<Character>> getCharacter() {
		return ResponseEntity.ok().body(characterMovieSeriesgenreService.getCharacter());
	}

	@DeleteMapping("character/{id}")
	public ResponseEntity<?> deleteCharacter(@PathVariable("id") Long id) {
		// URI uri =
		// URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}").toString());
		characterMovieSeriesgenreService.deleteCharac(id);
		return new ResponseEntity<String>("The user id:" + id + " is delete", HttpStatus.OK);
	}

	@PutMapping("character/{id}")
	public ResponseEntity<?> upDateCharacter(@PathVariable("id") Long id, @RequestBody Character character) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}").toString());
		Character character2 = characterMovieSeriesgenreService.characterById(id);
		if (character2 == null) {
			return new ResponseEntity<String>("The Character not exist", HttpStatus.BAD_REQUEST);
		} else {
			if (character2.getAge() != null) {
				character2.setAge(character.getAge());
			}

			if (character2.getHistory() != null) {
				character2.setHistory(character.getHistory());
			}

			if (character2.getImage() != null) {
				character2.setImage(character.getImage());
			}

			if (character2.getName() != null) {
				character2.setName(character.getName());
			}

			if (character2.getPeso() != null) {
				character2.setPeso(character.getPeso());
			}
			return ResponseEntity.created(uri).body(characterMovieSeriesgenreService.upDateCharacter(character2));
		}
	}

	@GetMapping("/characters")
	public ResponseEntity<?> characterByNameNyId() {

		List<CharacterListDTO> characterList = new ArrayList<>();
		List<Character> charac = characterMovieSeriesgenreService.getCharacter();
		charac.forEach(chara -> {
			CharacterListDTO characterListDTO = new CharacterListDTO();
			characterListDTO.setImage(chara.getImage());
			characterListDTO.setName(chara.getName());
			characterList.add(characterListDTO);
		});
		return new ResponseEntity<List<CharacterListDTO>>(characterList, HttpStatus.OK);
	}

	@GetMapping("character/detail/{id}")
	public ResponseEntity<?> DetailCharacter(@PathVariable("id") Long id) {
		DetailCharacterDTOImpl detailChar = new DetailCharacterDTOImpl();
		DetailCharacterDTO detailCharacter = null;

		List<Character> characteres = characterMovieSeriesgenreService.getCharacter();
		Character charExits = new Character();
		if (characteres != null) {
			characteres.forEach(chara -> {
				if (chara.getId() == id) {
					charExits.setAge(chara.getAge());
					charExits.setHistory(chara.getHistory());
					charExits.setId(chara.getId());
					charExits.setImage(chara.getImage());
					charExits.setName(chara.getPeso());
				}
			});
			if (charExits != null) {
				detailCharacter = detailChar.detailCharacterDTOImplment(characterMovieSeriesgenreService, id,
						charExits);
			}
			return new ResponseEntity<DetailCharacterDTO>(detailCharacter, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("character/{name}")
	public ResponseEntity<List<?>> searchCharacter(@PathVariable("name") String name) {
		List<Character> chars = characterMovieSeriesgenreService.search(name);
		if (!chars.isEmpty()) {
			return ResponseEntity.ok().body(characterMovieSeriesgenreService.search(name));
		} else {
			List<MovieSerie> chrasMovie = characterMovieSeriesgenreService.searchMovieCharacter(name);
			List<Character> character = new ArrayList<Character>();
			chrasMovie.forEach(charsMov -> {
				charsMov.getCharacter().forEach(charMovInd -> {
					character.add(charMovInd);
				});
			});

			return ResponseEntity.ok().body(character);
		}

	}

	@PostMapping("movieserie/save")
	public ResponseEntity<MovieSerie> saveMovieSerie(@RequestBody MovieSerie movieSerie) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("movieserie/save").toString());
		return ResponseEntity.created(uri).body(characterMovieSeriesgenreService.saveMovieSerie(movieSerie));
	}

	@GetMapping("movieserie")
	public ResponseEntity<List<MovieSerie>> getMovieSerie() {
		return ResponseEntity.ok().body(characterMovieSeriesgenreService.getMovieSeries());
	}

	@PostMapping("genre/save")
	public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("genre/save").toString());
		return ResponseEntity.created(uri).body(characterMovieSeriesgenreService.saveGenre(genre));
	}

	@GetMapping("genre")
	public ResponseEntity<List<Genre>> getGenre() {
		return ResponseEntity.ok().body(characterMovieSeriesgenreService.getGenres());
	}

	@GetMapping("movies")
	public ResponseEntity<List<ListMovieDTO>> getListMovieSerieDetail() {
		List<MovieSerie> movieSeries = characterMovieSeriesgenreService.getMovieSeries();
		List<ListMovieDTO> LisMovies = new ArrayList<ListMovieDTO>();
		movieSeries.forEach(action -> {
			ListMovieDTO moviesDTO = new ListMovieDTO();
			moviesDTO.setCreationdate(action.getCreationdate());
			moviesDTO.setImage(action.getImage());
			moviesDTO.setTitle(action.getTitle());
			LisMovies.add(moviesDTO);
		});
		return ResponseEntity.ok().body(LisMovies);
	}

	@GetMapping("movies/detail")
	public ResponseEntity<List<DetailCharacterDTO>> getDetailMoviez() {
		DetailMovieDTOImpl detailMovieDTOImpl = new DetailMovieDTOImpl();
		List<DetailCharacterDTO> detailCharacter = detailMovieDTOImpl
				.detailCharacterDTOImplment(characterMovieSeriesgenreService);
		return ResponseEntity.ok().body(detailCharacter);
	}

	@PutMapping("movies/{id}")
	public ResponseEntity<?> upDateMovie(@PathVariable("id") Long id, @RequestBody MovieSerie movie) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("movies/{id}").toString());
		MovieSerie movie2 = characterMovieSeriesgenreService.getMoviefindByid(id);
		if (movie2 == null) {
			return new ResponseEntity<String>("The Character not exist", HttpStatus.BAD_REQUEST);
		} else {
			if (movie2.getCalification() != 0) {
				movie2.setCalification(movie.getCalification());
			}

			if (movie2.getCharacter() != null) {
				movie2.setCharacter(movie.getCharacter());
			}

			if (movie2.getCreationdate() != null) {
				movie2.setCreationdate(movie.getCreationdate());
			}

			if (movie2.getGenre() != null) {
				movie2.setGenre(movie.getGenre());
			}

			if (movie2.getImage() != null) {
				movie2.setImage(movie.getImage());
				;
			}

			if (movie2.getTitle() != null) {
				movie2.setTitle(movie.getTitle());
				;
			}
			return ResponseEntity.created(uri).body(characterMovieSeriesgenreService.upDatemovie(movie2));
		}
	}

	@DeleteMapping("movies/delete/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id) {
		// URI uri =
		// URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}").toString());
		characterMovieSeriesgenreService.deleteMovie(id);
		;
		return new ResponseEntity<String>("The movie id:" + id + " is delete", HttpStatus.OK);
	}

	@GetMapping("movies/{name}/{id}")
	public ResponseEntity<List<?>> searchMovie(@PathVariable("name") String name, @PathVariable("id") Long id) {
		return ResponseEntity.ok().body(characterMovieSeriesgenreService.searchMovie(name, id));

	}

}
