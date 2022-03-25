package com.challenge.service;

import java.util.List;

import com.challenge.domain.Character;
import com.challenge.domain.Genre;
import com.challenge.domain.MovieSerie;

public interface CharacterMovieSeriesgenreService {

	Character saveCharacter(Character character);
	List<Character> getCharacter();
	void deleteCharac(Long id);
	Character characterById(Long id);
	Character upDateCharacter(Character character);
	List<Character> CharacterByNameById();
	List<Character> findByNamePointSix(String name);
	List<Character> findByAgePointSix(String age);
	List<Character> findByPesoPointSix(String peso);
	List<Character> search(String name);
	List<MovieSerie> searchMovieCharacter(String name);
	
	MovieSerie saveMovieSerie(MovieSerie movieSerie);
	List<MovieSerie> getMovieSeries();
	MovieSerie getMoviefindByid(Long id);
	MovieSerie upDatemovie(MovieSerie movie);
	void deleteMovie(Long id);
	List<MovieSerie> searchMovie(String name, Long id);
	
	Genre saveGenre(Genre genre);
	List<Genre> getGenres();
}
