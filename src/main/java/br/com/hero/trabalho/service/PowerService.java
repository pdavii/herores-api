package br.com.hero.trabalho.service;

import br.com.hero.trabalho.model.Power;
import br.com.hero.trabalho.repository.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerService {
    @Autowired
    PowerRepository repository;

    public Power findById(long Id) throws Exception{
        return repository.findById(Id).orElseThrow(() -> new Exception("Not Found"));
    }

    public List<Power> findAll(){
        return repository.findAll();
    }

    public Power save(Power power){
        return repository.save(power);
    }

    public Power update(Power power) throws Exception{
        Power h = findById(power.getId());
        h.setPowerName(power.getPowerName());
        h.setStrongPoint(power.getStrongPoint());
        h.setWeakPoint(power.getWeakPoint());
        return repository.save(h);
    }

    public void delete(long Id) throws Exception{
        Power power = findById(Id);
        repository.delete(power);
    }
}
