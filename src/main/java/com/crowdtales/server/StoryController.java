package com.crowdtales.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class StoryController {

    @Autowired
    private StoryRepository storyRepository;

    // Get all stories //

    @GetMapping("/stories")
    public HashMap<String, Iterable<Story>> getStories() {
        HashMap<String, Iterable<Story>> storyMap = new HashMap<String, Iterable<Story>>();
        Iterable<Story> stories = storyRepository.findAll();
        storyMap.put("stories", stories);
        return storyMap;
    }

    // Get specific story

    @GetMapping("/stories/{id}")
    public Optional<Story> getStoryById(@PathVariable int id) { return storyRepository.findById(id); }

    // Get all stories with specific user id //

    @GetMapping("/userstories/{id}")
    public HashMap<String, Iterable<Story>> getUserStories(@PathVariable int id) {
        HashMap<String, Iterable<Story>> storyMap = new HashMap<String, Iterable<Story>>();
        Iterable<Story> stories = storyRepository.findAllByUserid(id);
        storyMap.put("stories", stories);
        return storyMap;
    }

    // Create story //

    @PostMapping("/stories")
    public Story createStory(@RequestBody Story story, HttpSession session) {
        String currentUsername = session.getAttribute("username").toString();
        int currentId = Integer.parseInt(session.getAttribute("id").toString());
        story.setUserid(currentId);
        story.setUsername(currentUsername);
        story.setDate(new Date());
        return storyRepository.save(story);
    }

    // edit story //

    @PutMapping("/stories/{id}")
    public Story editStory(@RequestBody Story story, @PathVariable int id) throws Exception {
        Optional<Story> foundStory = storyRepository.findById(id);
        if (foundStory.isPresent()) {
            Story storyToUpdate = foundStory.get();
            storyToUpdate.setCurrentcontrib(story.getCurrentcontrib());
            storyToUpdate.setGenre(story.getGenre());
            storyToUpdate.setStatus(story.getStatus());
            storyToUpdate.setText(story.getText());
            storyToUpdate.setTitle(story.getTitle());
            return storyRepository.save(storyToUpdate);

        } else throw new Exception("story not found");
    }

    // delete story //

    @DeleteMapping("/stories/{id}")
    public String deleteStory(@PathVariable int id) {
        storyRepository.deleteById(id);
        return "resource deleted";
    }
}
