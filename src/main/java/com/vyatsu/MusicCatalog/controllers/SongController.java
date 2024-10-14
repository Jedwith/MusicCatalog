package com.vyatsu.MusicCatalog.controllers;
import com.vyatsu.MusicCatalog.entities.Artist;
import com.vyatsu.MusicCatalog.entities.Song;
import com.vyatsu.MusicCatalog.entities.User;
import com.vyatsu.MusicCatalog.services.ArtistService;
import com.vyatsu.MusicCatalog.services.SongService;
import com.vyatsu.MusicCatalog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping
public class SongController {
    private final SongService songService;

    private final ArtistService artistService;
    private final UserService userService;


    @Autowired
    public SongController(SongService songService, ArtistService artistService, UserService userService) {
        this.songService = songService;
        this.artistService = artistService;
        this.userService = userService;
    }

    @GetMapping
    public String home(Model model,
                       Principal principal,
                       @RequestParam(value = "author-name", required = false) String authorName,
                       @RequestParam(value = "song-name", required = false) String songName,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable) {
        User user = userService.getUserByName(principal.getName());
        authorName = authorName == null ? "" : authorName;
        songName = songName == null ? "" : songName;

        if(!authorName.isEmpty()) model.addAttribute("author_name", authorName);
        if(!songName.isEmpty()) model.addAttribute("song_name", songName);

        Page<Song> songs = songService.findWithSort(authorName, songName, pageable);

        if (songs.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, songs.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("songs", songs);
        model.addAttribute("login", user);
        return "home";
    }

    @GetMapping("/add-music")
    public String add(Model model, Principal principal){
        User user = userService.getUserByName(principal.getName());
        List<Artist> artistsList = artistService.getAllArtists();
        model.addAttribute("artists", artistsList);
        model.addAttribute("login", user);
        return "add-music";
    }

    @GetMapping("/add-artist")
    public String addArtistPage(Model model, Principal principal){
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("login", user);
        return "add-artist";
    }


    @PostMapping("/add-music/submit")
    public String addMusic(@ModelAttribute Song newSong) {
        songService.save(newSong);
        return "redirect:/";
    }


    @PostMapping("/add-artist/submit")
    public String addArtist(@ModelAttribute Artist artist){
        artistService.save(artist);
        return "redirect:/";
    }

    @GetMapping("/edit-song/{id}")
    public String editSongPage(@PathVariable Long id,
                               Model model,
                               Principal principal){
        User user = userService.getUserByName(principal.getName());
        Song song = songService.getSongById(id);
        List<Artist> artistsList = artistService.getAllArtists();

        model.addAttribute("song", song);
        model.addAttribute("artists", artistsList);
        model.addAttribute("login", user);

        return "edit-music";
    }

    @PostMapping("/edit-song/{id}/submit")
    public String editSong(@ModelAttribute Song song){
        songService.save(song);
        return "redirect:/";

    }

    @GetMapping("/remove-song/{id}")
    public String removeSong(@PathVariable(value = "id") Long id) {
        songService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/song-info/{id}")
    public String songInfo(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        Song song = songService.getSongById(id);
        model.addAttribute("song", song);
        model.addAttribute("login", user);
        return "song-info";
    }

    @GetMapping("/artist-info/{id}")
    public String artistInfo(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        Artist artist = artistService.getArtistById(id);
        List<Song> songs = songService.getSongsByArtistId(id);  // Предполагается, что есть метод для получения песен по ID артиста
        model.addAttribute("artist", artist);
        model.addAttribute("songs", songs);
        model.addAttribute("login", user);
        return "artist-info";
    }

    @GetMapping("/remove-artist/{id}")
    public String removeArtist(@PathVariable(value = "id") Long id) {
        artistService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/user-profile")
    public String userProfile(Principal principal, Model model){
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "user-profile";
    }

}
