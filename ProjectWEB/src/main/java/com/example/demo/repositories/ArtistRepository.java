package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

}
