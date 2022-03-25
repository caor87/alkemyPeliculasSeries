package com.challenge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.challenge.domain.MovieSerie;

public interface MovieSerieRepo extends JpaRepository<MovieSerie, Long>{
	MovieSerie findByid(Long id);
	
	List<MovieSerie> findByTitle(String name);
	
	@Query("select m from MovieSerie m where m.title=:name and m.genre.id=:id order by m.Creationdate asc")
	List<MovieSerie> searchTitleGenre(@Param("name") String name, @Param("id") Long id);
	
}
