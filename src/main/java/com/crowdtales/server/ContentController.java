package com.crowdtales.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

@RestController
public class ContentController {

    @Autowired
    private ContentRepository contentRepository;

    // get all content //

    @GetMapping("/content")
    public HashMap<String, Iterable<Content>> getAllContent() {
        HashMap<String, Iterable<Content>> content = new HashMap<String, Iterable<Content>>();
        Iterable<Content> allContent = contentRepository.findAll();
        content.put("content": allContent);
        return content;
    }

    // get all content for specific story

    // create new content //

    @PostMapping("/content")
    public Content createContent(@RequestBody Content content, HttpSession session) {
        content.setUserid(Integer.parseInt(session.getAttribute("id").toString()));
        content.setUsername(session.getAttribute("username").toString());
        content.setDate(new Date());
        return contentRepository.save(content);
    }



}
