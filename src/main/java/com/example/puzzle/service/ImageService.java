package com.example.puzzle.service;

import com.example.puzzle.domain.Image;
import com.example.puzzle.repository.IImageRepository;
import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    @Autowired
    IImageRepository IImageRepository;
    
    @Transactional
    public List<Image> uploadImages(List<MultipartFile> files) throws Exception{
        List<Image> imageArray = new ArrayList();        
        for(MultipartFile file: files){
            imageArray.add(this.uploadImage(file));
        }       
        return imageArray;
    }
    
    private String getImageUrl(String fileName){
        String url = "\\uploads\\" + fileName;
        return url;
    }
    
    private String appendUniqueIdentifier(String filename){
        String identifier = String.valueOf(System.currentTimeMillis());
        String newFileName = identifier + "-" + filename;
        return newFileName;
    }
    
    private void createImageInDirectory(MultipartFile file, String url) throws Exception{
        try {
            String currentDirectory = Paths.get("")
                .toAbsolutePath()
                .toString();
            file.transferTo(new File(currentDirectory + "\\src\\main\\resources\\static" + url));
        } catch(IOException | IllegalStateException e){
            throw new Exception();  
        }   
    }
    
    @Transactional
    private Image uploadImage(MultipartFile file) throws Exception{
        String fileName = this.appendUniqueIdentifier(this.getFileName(file));
        String url = this.getImageUrl(fileName);
        try{
            this.createImageInDirectory(file, url);   
            Image image = new Image(fileName, url);
            return this.IImageRepository.save(image);
        } catch(IOException | IllegalStateException e){
            throw new Exception();
        }
    }
    
    private String getFileName(MultipartFile file){
        return file.getOriginalFilename();
    }
    
    @Transactional
    public List<Image> getAllImagesById(List<Long> ids) throws Exception{
        try{
            List<Image> allImages = new ArrayList();
            for(Long id: ids){
                allImages.add(this.IImageRepository.findById(id).orElse(null));
            }         
            return allImages;
        }catch(Exception e){
            throw new Exception();
        }

    }
    
    public void shuffleList(List<Image> images){
        Collections.shuffle(images);
    }
    
    public boolean verifyEqual(List<Image> userList, List<Image> puzzleList){
        return Arrays.deepEquals(userList.toArray(), puzzleList.toArray());
    }
}
