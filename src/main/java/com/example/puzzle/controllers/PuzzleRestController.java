package com.example.puzzle.controllers;

import com.example.puzzle.domain.RequestPuzzleCreation;
import com.example.puzzle.service.ImageService;
import com.example.puzzle.service.PuzzleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> handlePuzzleCreation(@ModelAttribute RequestPuzzleCreation requestPuzzleCreation) throws Exception{
        return this.puzzleService.createPuzzle(requestPuzzleCreation);
    }
    
    @GetMapping("/verify/{id}")
    public ResponseEntity<String> verifyPuzzleResolution(@PathVariable("id") Long id, @RequestParam("imagesIds") List<Long> imagesIds) throws Exception{
        return this.puzzleService.verifyPuzzle(id, imagesIds);
    }
    
}
