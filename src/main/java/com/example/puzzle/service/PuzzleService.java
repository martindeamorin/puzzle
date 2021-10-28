package com.example.puzzle.service;

import com.example.puzzle.domain.Image;
import com.example.puzzle.domain.Puzzle;
import com.example.puzzle.domain.RequestPuzzleCreation;
import com.example.puzzle.repository.IPuzzleRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PuzzleService {
    
    @Autowired
    private IPuzzleRepository IPuzzleRepository;
    @Autowired 
    private ImageService imageService;
    
    @Transactional
    public Puzzle getPuzzleById(Long id) throws Exception{
        try{
            return this.IPuzzleRepository.findById(id).orElse(null);            
        }catch(Exception e){
            throw new Exception();
        }

    }
    
    @Transactional
    public ResponseEntity<String> createPuzzle(RequestPuzzleCreation requestPuzzleCreation) throws Exception{
        try{
            Puzzle newPuzzle = new Puzzle();
            List<Image> images = this.imageService.uploadImages(requestPuzzleCreation.getFiles());
            newPuzzle.setImages(images);
            newPuzzle.setName(requestPuzzleCreation.getName());
            System.out.println(newPuzzle.getName());
            this.IPuzzleRepository.save(newPuzzle);
            return ResponseEntity.ok("{\"message\" : \"Salió todo bien\"}");       
            
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }
    
    public ResponseEntity<String> verifyPuzzle(Long id, List<Long> imagesIds) throws Exception{
        try{
            Puzzle puzzle = this.getPuzzleById(id);
            List <Image> userImagesOrder = this.imageService.getAllImagesById(imagesIds);
            boolean equals = this.imageService.verifyEqual(userImagesOrder, puzzle.getImages());
            return ResponseEntity.ok("{\"message\": \"" + equals + "\"}");            
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }
}
