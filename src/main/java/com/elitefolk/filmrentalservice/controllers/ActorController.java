package com.elitefolk.filmrentalservice.controllers;

import com.elitefolk.filmrentalservice.dtos.BasicActorDto;
import com.elitefolk.filmrentalservice.dtos.CompleteActorDto;
import com.elitefolk.filmrentalservice.models.Actor;
import com.elitefolk.filmrentalservice.services.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<BasicActorDto> getActors() {
        List<Actor> actors = actorService.getAllActors();
        return (List<BasicActorDto>) BasicActorDto.fromActors(actors);
    }

    @GetMapping("/{id}")
    public CompleteActorDto getActorById(@PathVariable Short id) {
        Actor actor = actorService.getActorById(id);
        return CompleteActorDto.fromActor(actor);
    }

    @PostMapping
    public CompleteActorDto saveActor(@RequestBody BasicActorDto basicActorDto) {
        Actor actor = basicActorDto.toActor();
        return CompleteActorDto.fromActor(actorService.saveActor(actor));
    }

    @PutMapping("/{id}")
    public CompleteActorDto replaceActor(@PathVariable Short id, @RequestBody BasicActorDto basicActorDto) {
        Actor actor = basicActorDto.toActor();
        return CompleteActorDto.fromActor(actorService.replaceActor(id, actor));
    }

    @PatchMapping("/{id}")
    public CompleteActorDto updateActor(@PathVariable Short id, @RequestBody BasicActorDto basicActorDto) {
        Actor actor = basicActorDto.toActor();
        return CompleteActorDto.fromActor(actorService.patchActor(id, actor));
    }
}
