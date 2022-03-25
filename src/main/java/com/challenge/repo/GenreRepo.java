package com.challenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.domain.Genre;

public interface GenreRepo extends JpaRepository<Genre, Long>{
	Genre findByName(String name);
}
