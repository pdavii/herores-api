package br.com.hero.trabalho.repository;
import br.com.hero.trabalho.model.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    @Query(value = "SELECT * FROM Hero ORDER BY id", nativeQuery = true)
    Page<Hero> findAll(Pageable pageable);
}
