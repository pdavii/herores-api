package br.com.hero.trabalho.controller;


import br.com.hero.trabalho.model.Power;
import br.com.hero.trabalho.service.PowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "This service manipulates the Power resource", tags = {"power, api, service,"})
@RestController
@RequestMapping("/power/v1")
public class PowerController {
    @Autowired
    private PowerService service;

    @ApiOperation(value = "Get all registered power.")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<Power> findAll(){
        return service.findAll();
    }

    @ApiOperation(value = "Find a power by id.", response = Power.class)
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public Power findById(@PathVariable("id") long Id) throws Exception{
        return service.findById(Id);
    }

    @ApiOperation(value = "Store a newly Power", consumes = "application/json, application/xml", produces = "application/json, application/xml")
    @PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, produces = {"application/json", "application/xml", "application/x-yaml"})
    public Power save(@RequestBody Power power){
        return service.save(power);
    }

    @ApiOperation(value = "Update a power by ID")
    @PutMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public Power update(@RequestBody Power power) throws Exception{
        return service.update(power);
    }

    @ApiOperation(value = "Delete a power by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long Id) throws Exception{
        service.delete(Id);
        return ResponseEntity.ok().build();
    }
}
