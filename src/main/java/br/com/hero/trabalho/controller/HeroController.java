package br.com.hero.trabalho.controller;


import br.com.hero.trabalho.model.Hero;
import br.com.hero.trabalho.model.Power;
import br.com.hero.trabalho.service.HeroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.Id;
import java.util.List;

@Api(value = "This service manipulates the Person resource", tags = {"person, api, service,"})
@RestController
@RequestMapping("/hero/v1")
public class HeroController {

    private void buildEntityLink(Hero category) throws Exception {
        category.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                HeroController.class).findById(category.getId())
                ).withSelfRel());
    }

    /*
    private void buildCollectionLink (CollectionModel<Hero> heros) throws Exception {
        heros.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        HeroController.class).findAll()
        ).withRel(IanaLinkRelations.COLLECTION));
    }
    */

        private void buildEntityLink (Power power) throws Exception {
            //..add a self link
            power.add(WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(
                            PowerController.class).findById(power.getId())
                ).withSelfRel());//.. add the link of relatioships
            if(!power.getHero().hasLinks()) {
                Link powerLink= WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                PowerController.class).findById(power.getHero().getId())).withSelfRel();
                power.getHero().add(powerLink);
            }
        }

        @Autowired
        private HeroService service;

        @ApiOperation(value = "Get all registered heroes.")
        @GetMapping(value = "/", produces = {"application/json", "application/xml", "application/x-yaml"})
        public ResponseEntity<PagedModel<Hero>> findAll (@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                                        @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                        PagedResourcesAssembler<Hero> assembler) throws Exception {

            var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC: Sort.Direction.ASC;

            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "hero_name"));

            Page<Hero> heroes = service.findAll(pageable);
            for (Hero hero:heroes) {
                buildEntityLink(hero);
            }
            return new ResponseEntity(assembler.toModel(heroes), HttpStatus.OK);
        }

        @ApiOperation(value = "Find a hero by id.", response = Hero.class)
        @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
        public Hero findById ( @PathVariable("id") long Id) throws Exception {
            Hero hero = service.findById(Id);
            buildEntityLink(hero);
            return hero;
        }

        @ApiOperation(value = "Store a newly Hero", consumes = "application/json, application/xml", produces = "application/json, application/xml")
        @PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, produces = {"application/json", "application/xml", "application/x-yaml"})
        public Hero save (@RequestBody Hero hero){
            return service.save(hero);
        }

        @ApiOperation(value = "Update a hero by ID")
        @PutMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, produces = {"application/json", "application/xml", "application/x-yaml"})
        public Hero update (@RequestBody Hero hero) throws Exception {
            return service.update(hero);
        }

        @ApiOperation(value = "Delete a hero by ID")
        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete ( @PathVariable("id") long Id) throws Exception {
            service.delete(Id);
            return ResponseEntity.ok().build();
        }
    }

