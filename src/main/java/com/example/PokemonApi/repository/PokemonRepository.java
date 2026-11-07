package com.example.PokemonApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PokemonApi.model.Pokemon;

@Repository 
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
