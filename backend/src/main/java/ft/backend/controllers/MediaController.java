package ft.backend.controllers;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.entities.*;
import ft.backend.repositories.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/assets")
public class MediaController {
    
    @Autowired
    ConteudoMediaDAO rep_users;

    @GetMapping(value = "/photo/{path}") 
    public ResponseEntity<InputStreamResource> getImagem(@PathVariable String path){
        ConteudoMedia u = rep_users.getOne(Integer.parseInt(path));
        if(u!=null){
            return ResponseEntity.ok()
                .contentLength(u.getConteudo().length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new ByteArrayInputStream(u.getConteudo())));
        }
        return null;
    }

    @GetMapping(value = "/video/{path}") 
    public ResponseEntity<InputStreamResource> getVideo(@PathVariable String path){
        ConteudoMedia u = rep_users.getOne(Integer.parseInt(path));
        if(u!=null){
            return ResponseEntity.ok()
                .contentLength(u.getConteudo().length)
                .contentType(MediaType.parseMediaType("video/mp4"))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=video%s.%s", 1, "mp4"))
                .body(new InputStreamResource(new ByteArrayInputStream(u.getConteudo())));
        }
        return null;
    }

}