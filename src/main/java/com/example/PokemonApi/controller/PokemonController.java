package com.example.PokemonApi.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PokemonApi.model.Pokemon;
import com.example.PokemonApi.service.PokemonService;

@RestController 
@RequestMapping("/api/pokemones")
public class PokemonController {
    
    private final PokemonService pokemonService;

    @Autowired 
    public PokemonController(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @GetMapping 
    public List<Pokemon> obtenerTodos(){
        return pokemonService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Pokemon obtenerPorId(@PathVariable Long id){
        return pokemonService.obtenerPorId(id);
    }

    @PostMapping 
    public Pokemon crearPokemon(@Valid @RequestBody Pokemon pokemon){
        return pokemonService.crearPokemon(pokemon);
    }

    @PutMapping("/{id}")
    public Pokemon actualizarCompleto(@Valid @PathVariable Long id, @RequestBody Pokemon pokemon){
        return pokemonService.actualizarPokemonCompleto(id, pokemon);
    }

    @PatchMapping("/{id}")
    public Pokemon actualizarParcial(@PathVariable Long id, @RequestBody Pokemon pokemon){
        return pokemonService.actualizaPokemonParcial(id, pokemon);
    }

    @DeleteMapping("/{id}")
    public void eliminarPokemon(@PathVariable Long id){
        pokemonService.eliminarPokemon(id);
    }
}
