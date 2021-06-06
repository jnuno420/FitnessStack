package ft.backend.controllers;

import ft.backend.entities.Exercicio;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_exercicios;
import ft.backend.beans.gestao_treinadores;
import ft.backend.beans.gestao_treinos;
import ft.backend.beans.gestao_utilizadores;
import ft.backend.responses.RespostaOk;
import ft.backend.utils.Authorization;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/exercicio")
public class ExercicioController {
    
    @Autowired
    gestao_exercicios ge;

    @Autowired 
    gestao_treinadores gu;

    @PostMapping(value = "/novo")
    public ResponseEntity<String> novoExercicio(@RequestHeader String token, @RequestBody String t){
        JSONObject obj;
        String username;
        try{
            obj = new JSONObject(Authorization.extractClaims(token));
            if(obj.getBoolean("treinador_utilizador")){
                username = obj.getString("username");
                if(!gu.usernameExisteT(username)){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        obj = new JSONObject(t);

        Exercicio e = new Exercicio();
        e.setNome(obj.getString("nome"));
        e.setDescricao("descricao");
        e.setDuracao_media(obj.getFloat("duracao_media"));
        e.setMaterial_necessario(obj.getString("material_necessario"));

        ge.criaExercicio(e,gu.getTreinadorByUsername(username),obj.getJSONArray("conteudo_media"));
        
        return ResponseEntity.ok().body("");
    }

    @GetMapping(value = "/getNomesExercicios")
    public ResponseEntity<String> getNomesExercicios(@RequestHeader String token){
        return ResponseEntity.ok().body(ge.getNomeExercicios().toString());
    }

    @GetMapping(value = "/listar")
    public String getExercicios(){
        //falta verificar o token
        return ge.getExercicios().toString();
    }


}
