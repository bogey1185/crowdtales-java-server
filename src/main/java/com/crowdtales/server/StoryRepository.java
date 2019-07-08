package com.crowdtales.server;

import org.springframework.data.repository.CrudRepository;

public interface StoryRepository extends CrudRepository<Story, Integer> {
}
