package br.com.hero.trabalho.repository;
import br.com.hero.trabalho.model.Hero;
import br.com.hero.trabalho.model.Power;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerRepository extends JpaRepository<Power, Long> {

}
