package com.example.puzzle.service;

import com.example.puzzle.domain.Puzzle;
import com.example.puzzle.repository.IPuzzleRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuzzleService {
    
    @Autowired
    private IPuzzleRepository IPuzzleRepository;
    
    @Transactional
    public Puzzle getPuzzleById(Long id) throws Exception{
        try{
            return this.IPuzzleRepository.findById(id).orElse(null);            
        }catch(Exception e){
            throw new Exception();
        }

    }
    
    @Transactional
    public void createPuzzle(Puzzle puzzle) throws Exception{
        try{
            this.IPuzzleRepository.save(puzzle);
        }catch(Exception e){
            throw new Exception();
        }
        
    }
}
