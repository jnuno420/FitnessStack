package ft.backend.controllers;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ft.backend.beans.gestao_marcacoes;
import ft.backend.beans.gestao_verificacoes;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/agenda")
public class MarcacaoController {

    @Autowired
    gestao_marcacoes gm;

    @Autowired
    gestao_verificacoes verify;
    
    @GetMapping(value = "/getAgenda")
    public ResponseEntity<String> getAgenda(@RequestHeader String token, @RequestParam String username){
        String user_token = verify.verifyUser(token);
        if(user_token != null && username.equals(user_token)){
            return ResponseEntity.ok().body(gm.getAgendaUtilizador(username).toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value = "/novaMarcacao")
    public ResponseEntity<String> novaMarcacao(@RequestHeader String token, @RequestBody String marc){
        if(verify.verifyUser(token) != null || verify.verifyTreinador(token) != null){
            JSONObject obj = new JSONObject(marc);
            String cod_treino = obj.getString("treino");
            String username = obj.getString("utilizador");
            String cor = obj.getString("cor");

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date td = df.parse(obj.getString("data_hora"),new ParsePosition(0));

            if(gm.novaMarcacao(td, username, cod_treino, cor)){
                return ResponseEntity.ok().body("");
            }
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
