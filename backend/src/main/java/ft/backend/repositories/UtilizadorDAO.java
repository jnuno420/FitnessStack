package ft.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ft.backend.entities.*;

public interface UtilizadorDAO extends JpaRepository<Utilizador,Integer> {

    @Query(value = "SELECT * FROM Utilizador u WHERE u.nome = :searchTerm",nativeQuery = true)
    public List<Utilizador> findByJavardice(@Param("searchTerm") String searchTerm);

    @Query(value = "SELECT * FROM Utilizador u WHERE u.email = :email and u.password = :pass",nativeQuery = true)
    public Utilizador findUtilizador_Email_Password(@Param("email") String email,@Param("pass") String password);

}
