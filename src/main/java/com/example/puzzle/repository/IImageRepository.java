package com.example.puzzle.repository;

import com.example.puzzle.domain.Image;
import org.springframework.data.repository.CrudRepository;

public interface IImageRepository extends CrudRepository<Image, Long>{

}
