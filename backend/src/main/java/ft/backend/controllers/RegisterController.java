package ft.backend.controllers;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import ft.backend.beans.gestao_verificacoes;
import ft.backend.utils.Date_Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ft.backend.beans.gestao_treinadores;
import ft.backend.beans.gestao_utilizadores;
import ft.backend.entities.ConteudoMedia;
import ft.backend.entities.Treinador;
import ft.backend.entities.Utilizador;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/register")
public class RegisterController {
    
    @Autowired
    gestao_utilizadores gestao_utilizadores;

    @Autowired
    gestao_treinadores gestao_treinadores;

    @Autowired
    gestao_verificacoes verify;

    @PostMapping(value = "/user")
    public ResponseEntity<String> register_utilizador(@RequestBody String pl){
        Utilizador u = new Utilizador();
        JSONObject obj = new JSONObject(pl);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dn = df.parse(obj.getString("data"),new ParsePosition(0));

        u.setEmail(obj.getString("email"));u.setPassword(obj.getString("password"));
        u.setNome(obj.getString("nome"));u.setAltura(obj.getFloat("altura"));
        u.setGenero(obj.getBoolean("genero"));u.setPeso(obj.getFloat("peso"));
        u.setDataNascimento(dn);u.setUsername(obj.getString("username"));

        if(obj.has("foto_perfil")){
            ConteudoMedia foto = new ConteudoMedia();
            foto.setID(Date_Utils.generateCode());
            foto.setConteudo(Base64.getDecoder().decode(obj.getString("foto_perfil")));
            foto.setExtensao(false);
            u.setFoto_perfil(foto);
        }
        
        if(!gestao_utilizadores.registerUser(u)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } 

        return ResponseEntity.ok().body("");
    }

    @PostMapping(value = "/treinador")
    public ResponseEntity<String> register_treinador(@RequestHeader String token,@RequestBody String pl){
        if(verify.verifyAdmin(token) != null){
            Treinador u = new Treinador();
            JSONObject obj = new JSONObject(pl);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dn = df.parse(obj.getString("data"),new ParsePosition(0));

            u.setEmail(obj.getString("email"));u.setPassword(obj.getString("password"));
            u.setNome(obj.getString("nome"));u.setGenero(obj.getBoolean("genero"));
            u.setDataNascimento(dn);u.setUsername(obj.getString("username"));
            u.setDescricao(obj.getString("descricao"));

            if(obj.has("foto_perfil")){
                ConteudoMedia foto = new ConteudoMedia();
                foto.setID(Date_Utils.generateCode());
                foto.setConteudo(Base64.getDecoder().decode(obj.getString("foto_perfil")));
                foto.setExtensao(false);
                u.setFoto_perfil(foto);
            }

            if(!gestao_treinadores.registerTreinador(u)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
