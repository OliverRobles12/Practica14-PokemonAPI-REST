package com.example.PokemonApi.service;

import java.util.List;

import com.example.PokemonApi.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PokemonApi.model.Pokemon;
import com.example.PokemonApi.repository.PokemonRepository;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> obtenerTodos() {
        return pokemonRepository.findAll();
    }

    public Pokemon crearPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Pokemon obtenerPorId(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el Pokemon con id " + id));
    }

    public Pokemon actualizarPokemonCompleto(Long id, Pokemon pokemonActualizado) {
        return pokemonRepository.findById(id).map(pokemon -> {
            pokemon.setNombre(pokemonActualizado.getNombre());
            pokemon.setTipo(pokemonActualizado.getTipo());
            pokemon.setNivel(pokemonActualizado.getNivel());
            pokemon.setAtaque(pokemonActualizado.getAtaque());
            pokemon.setDefensa(pokemonActualizado.getDefensa());
            pokemon.setDescripcion(pokemonActualizado.getDescripcion());
            pokemon.setImagenUrl(pokemonActualizado.getImagenUrl());
            pokemon.setEmail(pokemonActualizado.getEmail());
            pokemon.setFechaCaptura(pokemonActualizado.getFechaCaptura());
            return pokemonRepository.save(pokemon);
        }).orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, no existe el Pokemon con id " + id));
    }

    public Pokemon actualizaPokemonParcial(Long id, Pokemon pokemonParcial) {
        return pokemonRepository.findById(id).map(pokemon -> {
            if (pokemonParcial.getNombre() != null) pokemon.setNombre(pokemonParcial.getNombre());
            if (pokemonParcial.getTipo() != null) pokemon.setTipo(pokemonParcial.getTipo());
            if (pokemonParcial.getNivel() != null) pokemon.setNivel(pokemonParcial.getNivel());
            if (pokemonParcial.getAtaque() != null) pokemon.setAtaque(pokemonParcial.getAtaque());
            if (pokemonParcial.getDefensa() != null) pokemon.setDefensa(pokemonParcial.getDefensa());
            if (pokemonParcial.getDescripcion() != null) pokemon.setDescripcion(pokemonParcial.getDescripcion());
            if (pokemonParcial.getImagenUrl() != null) pokemon.setImagenUrl(pokemonParcial.getImagenUrl());
            if (pokemonParcial.getEmail() != null) pokemon.setEmail(pokemonParcial.getEmail());
            if (pokemonParcial.getFechaCaptura() != null) pokemon.setFechaCaptura(pokemonParcial.getFechaCaptura());
            return pokemonRepository.save(pokemon);
        }).orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar, no existe el Pokemon con id " + id));
    }

    public void eliminarPokemon(Long id) {
        if (!pokemonRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se pudo eliminar, no existe el Pokemon con id " + id);
        }
        pokemonRepository.deleteById(id);
    }
}
