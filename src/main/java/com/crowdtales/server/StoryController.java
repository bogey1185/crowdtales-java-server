package com.crowdtales.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@RestController
public class StoryController {

    @Autowired
    private StoryRepository storyRepository;

    // Get all stories //

    @GetMapping("/stories")
    public Iterable<Story> getStories() { return storyRepository.findAll(); }

    // Get specific story

    @GetMapping("/stories/{id}")
    public Optional<Story> getStoryById(@PathVariable int id) { return storyRepository.findById(id); }

    // Create story //

    @PostMapping("/stories")
    public Story createStory(@RequestBody Story story, HttpSession session) {
        String currentUsername = session.getAttribute("username").toString();
        int currentId = Integer.parseInt(session.getAttribute("id").toString());
        story.setUser_id(currentId);
        story.setUsername(currentUsername);
        story.setDate(new Date());
        return storyRepository.save(story);
    }




}
