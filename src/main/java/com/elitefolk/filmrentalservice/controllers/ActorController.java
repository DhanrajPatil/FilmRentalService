package com.elitefolk.filmrentalservice.controllers;

import com.elitefolk.filmrentalservice.dtos.ActorDto;
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
    public List<ActorDto> getActors() {
        List<Actor> actors = actorService.getAllActors();
        return ActorDto.fromActors(actors);
    }

    @PostMapping
    public ActorDto saveActor(@RequestBody ActorDto actorDto) {
        Actor actor = actorDto.toActor();
        return ActorDto.fromActor(actorService.saveActor(actor));
    }

    @PutMapping("/{id}")
    public ActorDto replaceActor(@PathVariable Short id, @RequestBody ActorDto actorDto) {
        Actor actor = actorDto.toActor();
        return ActorDto.fromActor(actorService.replaceActor(id, actor));
    }

    @PatchMapping("/{id}")
    public ActorDto updateActor(@PathVariable Short id, @RequestBody ActorDto actorDto) {
        Actor actor = actorDto.toActor();
        return ActorDto.fromActor(actorService.updateActor(id, actor));
    }
}
