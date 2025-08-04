package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SongRepository;

import model.Product;
import model.Song;

@Controller
@RequestMapping("/song/")
public class SongController {
	
	@Autowired
	SongRepository sr;
	
	@Autowired
	ProductRepository pr;
	
	@ModelAttribute("newSong")
	public Song getNewSong() {
		return new Song();
	}
	
	@ModelAttribute("products")
	public List<Product> getProducts(){
		return pr.findAll();
	}
	
	
	@GetMapping("getSongsOverview")
	public String getSongsOverview(Model model) {
	    List<Song> songs = sr.findAll();
	    model.addAttribute("songs", songs);
	    return "admin/songsOverview";
	}
	
	@GetMapping("getAddSongPage")
	public String getAddSongPage() {
	    return "admin/addSong";
	}
	
	@PostMapping("saveSong")
	public String saveSong(@ModelAttribute Song newSong) {
	    sr.save(newSong);
	    return "admin/addSong";
	}

	@GetMapping("getEditPage")
	public String getEditPage(@RequestParam int id, Model model) {
		Song song = sr.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		model.addAttribute("song", song);
		return "admin/editSong";
	}
	
	@PostMapping("update")
	public String updateSong(@ModelAttribute("song") Song updatedSong) {
		Song song = sr.findById(updatedSong.getIdSong()).orElseThrow(() -> new RuntimeException("Product not found"));
		
		song.setName(updatedSong.getName());
		song.setDuration(updatedSong.getDuration());
		song.setProduct(updatedSong.getProduct());
		
		sr.save(song);
		
		return"redirect:/song/getSongsOverview";
	}
	
	@PostMapping("/delete")
	public String deleteSong(@RequestParam int id) {
	    if (sr.existsById(id)) {
	        sr.deleteById(id);
	    }
	    return "redirect:/song/getSongsOverview"; 
	}
}
