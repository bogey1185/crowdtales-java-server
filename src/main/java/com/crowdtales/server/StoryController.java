package com.crowdtales.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class StoryController {

    @Autowired
    private StoryRepository storyRepository;

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
