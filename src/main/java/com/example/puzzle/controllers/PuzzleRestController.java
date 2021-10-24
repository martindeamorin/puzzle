package com.example.puzzle.controllers;

import com.example.puzzle.domain.Image;
import com.example.puzzle.domain.Puzzle;
import com.example.puzzle.domain.RequestPuzzleCreation;
import com.example.puzzle.service.ImageService;
import com.example.puzzle.service.PuzzleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/puzzle")
public class PuzzleRestController {
    @Autowired
    PuzzleService puzzleService;
    @Autowired
    ImageService imageService;
    
    @PostMapping
    public ResponseEntity<?> handlePuzzleCreation(@ModelAttribute RequestPuzzleCreation requestPuzzleCreation){
        try{
            Puzzle newPuzzle = new Puzzle();
            List<Image> images = this.imageService.uploadImages(requestPuzzleCreation.getFiles());
            newPuzzle.setImages(images);
            newPuzzle.setName(requestPuzzleCreation.getName());
            System.out.println(newPuzzle.getName());
            puzzleService.createPuzzle(newPuzzle);
            return ResponseEntity.ok("{\"message\" : \"Salió todo bien\"}");       
            
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/verify/{id}")
    public ResponseEntity<?> verifyPuzzleResolution(@PathVariable("id") Long id, @RequestParam("imagesIds") List<Long> imagesIds) throws Exception{
        try{
            Puzzle puzzle = puzzleService.getPuzzleById(id);
            List <Image> userImagesOrder = this.imageService.getAllImagesById(imagesIds);
            boolean equals = this.imageService.verifyEqual(userImagesOrder, puzzle.getImages());
            return ResponseEntity.ok("{\"message\": \"" + equals + "\"}");            
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    
}
