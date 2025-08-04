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

import com.example.demo.repositories.ArtistRepository;
import model.Artist;

@Controller
@RequestMapping("/artist/")
public class ArtistController {

    @Autowired
    ArtistRepository ar;

    @ModelAttribute("newArtist")
    public Artist getNewArtist() {
        return new Artist();
    }

    @GetMapping("getArtistsOverview")
    public String getArtistsOverview(Model model) {
        List<Artist> artists = ar.findAll();
        model.addAttribute("artists", artists);
        return "admin/artistsOverview";
    }

    @GetMapping("getAddArtistPage")
    public String getAddArtistPage() {
        return "admin/addArtist";
    }

    @PostMapping("saveArtist")
    public String saveArtist(@ModelAttribute Artist newArtist) {
        ar.save(newArtist);
        return "admin/addArtist";
    }

    @GetMapping("getEditPage")
    public String getEditPage(@RequestParam int id, Model model) {
        Artist artist = ar.findById(id).orElseThrow(() -> new RuntimeException("Artist not found"));
        model.addAttribute("artist", artist);
        return "admin/editArtist";
    }

    @PostMapping("update")
    public String updateArtist(@ModelAttribute("artist") Artist updatedArtist) {
        Artist artist = ar.findById(updatedArtist.getIdArtist()).orElseThrow(() -> new RuntimeException("Artist not found"));

        artist.setName(updatedArtist.getName());

        ar.save(artist);

        return "redirect:/artist/getArtistsOverview";
    }

    @PostMapping("delete")
    public String deleteArtist(@RequestParam int id) {
        if (ar.existsById(id)) {
            ar.deleteById(id);
        }
        return "redirect:/artist/getArtistsOverview";
    }
}
