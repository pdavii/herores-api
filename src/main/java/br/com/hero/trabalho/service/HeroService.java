package br.com.hero.trabalho.service;

import br.com.hero.trabalho.model.Hero;
import br.com.hero.trabalho.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {
    @Autowired
    HeroRepository repository;

    public Hero findById(long Id) throws Exception{
        return repository.findById(Id).orElseThrow(() -> new Exception("Not Found"));
    }

    public Page<Hero> findAll(Pageable pageable){

        return repository.findAll(pageable);
    }

    public Hero save(Hero hero){
        return repository.save(hero);
    }

    public Hero update(Hero hero) throws Exception{
        Hero h = findById(hero.getId());
        h.setHeroName(hero.getHeroName());
        h.setPersonName(hero.getPersonName());
        h.setTeamName(hero.getTeamName());
        return repository.save(h);
    }

    public void delete(long Id) throws Exception{
        Hero hero = findById(Id);
        repository.delete(hero);
    }
}
