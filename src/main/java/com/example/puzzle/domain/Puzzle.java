package com.example.puzzle.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity()
public class Puzzle {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "puzzleimage", 
        joinColumns = @JoinColumn(name = "idPuzzle"), 
        inverseJoinColumns = @JoinColumn(name = "idImage"))
    private List<Image> images = new ArrayList();
    

    public Puzzle(String name){
        this.name = name;
    }
    
}
