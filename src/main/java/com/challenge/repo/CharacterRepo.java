package com.challenge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.challenge.domain.Character;

public interface CharacterRepo extends JpaRepository<com.challenge.domain.Character, Long>{
	List<Character> findByName(String name);
	List<Character> findByAge(String age);
	List<Character> findByPeso(String peso);
	
	@Query("select c from Character c where c.name=:name or c.peso=:name or c.age=:name")
	List<Character> search(@Param("name") String name);
}
