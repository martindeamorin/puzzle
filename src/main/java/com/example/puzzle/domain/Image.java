package com.example.puzzle.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity()
public class Image {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    @ToString.Exclude
    @ManyToMany
    @JoinTable(
        name = "puzzleimage", 
        joinColumns = @JoinColumn(name = "idImage"), 
        inverseJoinColumns = @JoinColumn(name = "idPuzzle"))
    private List<Puzzle> puzzles = new ArrayList();
        
    public Image(String nombre, String url){
        this.name = nombre;
        this.url = url;
    }
    
}
