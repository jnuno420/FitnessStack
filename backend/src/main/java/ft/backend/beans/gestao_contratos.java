package ft.backend.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ft.backend.entities.Contrato;
import ft.backend.entities.Treinador;
import ft.backend.entities.Utilizador;
import ft.backend.repositories.ContratoDAO;
import ft.backend.repositories.TreinadorDAO;
import ft.backend.repositories.UtilizadorDAO;

@Service
public class gestao_contratos {
    
    @Autowired
    ContratoDAO contratoDAO;
    @Autowired
    UtilizadorDAO utilizadorDAO;
    @Autowired
    TreinadorDAO treinadorDAO;


    public Contrato getContratoUser(String username){
        Contrato ret =null;
        Utilizador u = utilizadorDAO.findUtilizador_Username(username);
        if (u!=null){
            ret = contratoDAO.findContratoIdUser(u.getID());
           
        }

        return ret;
    }

    public List<Contrato> getContratoTreinador(String username){
        List<Contrato> ret =null;
        Treinador u = treinadorDAO.findTreinador_Username(username);
        if (u!=null){
            ret = contratoDAO.findContratoIdTreinador(u.getID());
           
        }
        
        return ret;
    }
    public boolean acceptContrato(String username,String treinador){
        Contrato ret =null;
        Utilizador u = utilizadorDAO.findUtilizador_Username(username);
        if (u!=null ){
            ret = contratoDAO.findContratoIdUser(u.getID());
            
            if (ret.isEstado()==false && ret.getTreinador_responsavel().getUsername().equals(treinador)){
                ret.setEstado(true);
                contratoDAO.save(ret);
                return true;
            }
        }

        return false;
    }
    public boolean removeContrato(String username,String treinador){
        Contrato ret =null;
        Utilizador u = utilizadorDAO.findUtilizador_Username(username);
        if (u!=null ){
            ret = contratoDAO.findContratoIdUser(u.getID());
            contratoDAO.delete(ret);
            return true;
            
        }

        return false;
    }
    
    public boolean removeContratoUser(String username){
        
        Utilizador u = utilizadorDAO.findUtilizador_Username(username);
        if (u!=null ){

            Contrato ret = contratoDAO.findContratoIdUser(u.getID());
            if(ret!=null){
                contratoDAO.delete(ret);
                return true;
            }
            
        }

        return false;
    }
    
    public boolean criarContrato(String username,String treinador,String comentario){
        
        Utilizador u= utilizadorDAO.findUtilizador_Username(username);
        Treinador t = treinadorDAO.findTreinador_Username(treinador);
        Contrato c = contratoDAO.findContratoIdUser(u.getID());


        if ( u !=null && t!=null && c==null){
           
            Contrato ret=new Contrato();
            ret.setComentario(comentario);
            ret.setEstado(false);
            ret.setTreinador_responsavel(t);
            ret.setUtilizador(u);

            contratoDAO.save(ret);
            return true;
            
        }

        return false;
    }
}
