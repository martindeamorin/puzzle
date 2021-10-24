package com.example.puzzle.controllers;

import com.example.puzzle.domain.Image;
import com.example.puzzle.domain.Puzzle;
import com.example.puzzle.service.ImageService;
import com.example.puzzle.service.PuzzleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    
    @Autowired
    PuzzleService puzzleService;
    @Autowired
    ImageService imageService;
    
    @GetMapping("/")
    public String viewHome(){
        return "home";
    }
    
    @GetMapping("/create-puzzle")
    public String viewAddPuzzle(){
        return "addPuzzle";
    }
    
    @GetMapping("/play/{id}")
    public String viewPlay(@PathVariable("id") String id, Model model) throws Exception{
        Puzzle puzzle = this.puzzleService.getPuzzleById(Long.parseLong(id));
        List<Image> images = puzzle.getImages();
        this.imageService.shuffleList(images);
        model.addAttribute("images", images);
        model.addAttribute("name", puzzle.getName());
        return "play";
    }
}
