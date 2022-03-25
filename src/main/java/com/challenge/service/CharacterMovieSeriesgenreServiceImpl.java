package com.challenge.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.challenge.domain.Character;
import com.challenge.domain.Genre;
import com.challenge.domain.MovieSerie;
import com.challenge.repo.CharacterRepo;
import com.challenge.repo.GenreRepo;
import com.challenge.repo.MovieSerieRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CharacterMovieSeriesgenreServiceImpl implements CharacterMovieSeriesgenreService {

	private final CharacterRepo characterRepo;
	private final MovieSerieRepo movieSerieRepo;
	private final GenreRepo genreRepo;

	@Override
	public Character saveCharacter(Character character) {
		log.info("Saving new user {} to the date base", character.getName());
		return characterRepo.save(character);
	}

	@Override
	public List<Character> getCharacter() {
		log.info("Fetching all characters");
		return characterRepo.findAll();
	}

	@Override
	public MovieSerie saveMovieSerie(MovieSerie movieSerie) {
		log.info("Saving new user {} to the date base", movieSerie.getTitle());
		return movieSerieRepo.save(movieSerie);
	}

	@Override
	public List<MovieSerie> getMovieSeries() {
		return movieSerieRepo.findAll();
	}

	@Override
	public Genre saveGenre(Genre genre) {
		log.info("Saving new user {} to the date base", genre.getName());
		return genreRepo.save(genre);
	}

	@Override
	public List<Genre> getGenres() {
		return genreRepo.findAll();
	}

	@Override
	public void deleteCharac(Long id) {
		characterRepo.deleteById(id);
	}

	@Override
	public Character characterById(Long id) {
		return characterRepo.getById(id);
	}

	@Override
	public Character upDateCharacter(Character character) {
		return characterRepo.save(character);
	}

	@Override
	public List<Character> CharacterByNameById() {
		return characterRepo.findAll();
	}

	@Override
	public List<Character> findByNamePointSix(String name) {
		return characterRepo.findByName(name);
	}

	@Override
	public List<Character> findByAgePointSix(String age) {
		return characterRepo.findByAge(age);
	}

	@Override
	public List<Character> findByPesoPointSix(String peso) {
		return characterRepo.findByPeso(peso);
	}

	@Override
	public List<Character> search(String name) {
		return characterRepo.search(name);
	}

	@Override
	public List<MovieSerie> searchMovieCharacter(String name) {

		return movieSerieRepo.findByTitle(name);
	}

	@Override
	public MovieSerie getMoviefindByid(Long id) {
		return movieSerieRepo.findByid(id);
	}

	@Override
	public MovieSerie upDatemovie(MovieSerie movie) {
		return movieSerieRepo.save(movie);
	}

	@Override
	public void deleteMovie(Long id) {
		movieSerieRepo.deleteById(id);
	}

	@Override
	public List<MovieSerie> searchMovie(String name, Long id) {
		return movieSerieRepo.searchTitleGenre(name, id);
	}
}
