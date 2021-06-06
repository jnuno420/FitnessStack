package ft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ft.backend.entities.Treino;
import java.util.*;

public interface TreinoDAO extends JpaRepository<Treino,Integer>{

    @Query(value = "SELECT * FROM Treino",nativeQuery = true)
    public List<Treino> findAllTreinos();

    @Query(value = "SELECT * FROM Treino u WHERE u.id = :id",nativeQuery = true)
    public Treino findbyId(@Param("id") int id);

    @Query(value = "SELECT * FROM Treino u WHERE u.codigo = :codigo",nativeQuery = true)
    public Treino findbyCodigo(@Param("codigo") String codigo);

    @Query(value = "SELECT u.codigo FROM Treino u",nativeQuery = true)
    public List<String> getCodigos();

    @Query(value = "SELECT u.nome FROM Treino u where u.codigo = :codigo",nativeQuery = true)
    public String getTreinoNome(@Param("codigo") String codigo);
}
