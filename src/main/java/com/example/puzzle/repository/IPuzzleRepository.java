package com.example.puzzle.repository;

import com.example.puzzle.domain.Puzzle;
import org.springframework.data.repository.CrudRepository;

public interface IPuzzleRepository extends CrudRepository<Puzzle, Long>{

}
