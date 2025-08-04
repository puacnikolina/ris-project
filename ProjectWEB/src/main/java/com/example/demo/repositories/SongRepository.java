package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Song;

public interface SongRepository extends JpaRepository<Song, Integer>{

}
