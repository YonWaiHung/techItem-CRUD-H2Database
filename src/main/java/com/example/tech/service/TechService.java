package com.example.tech.service;

import com.example.tech.model.Tech;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechService {

    static List<Tech> techItems = new ArrayList<Tech>();
    static long id = 0;

    public List<Tech> findAll() {
        return techItems;
    }

    public List<Tech> findByTechTypeContaining(String techType) {
        return techItems.stream().filter(tech -> tech.getTechType().contains(techType)).toList();
    }

    public Tech findById(long id) {
        return techItems.stream().filter(tech -> id == tech.getId()).findAny().orElse(null);
    }

    public Tech save(Tech tech) {
        // update Tech
        if (tech.getId() != 0) {
            long _id = tech.getId();

            for (int idx = 0; idx < techItems.size(); idx++)
                if (_id == techItems.get(idx).getId()) {
                    techItems.set(idx, tech);
                    break;
                }

            return tech;
        }

        // create new Tech
        tech.setId(++id);
        techItems.add(tech);
        return tech;
    }

    public void deleteById(long id) {
        techItems.removeIf(tech -> id == tech.getId());
    }

    public void deleteAll() {
        techItems.removeAll(techItems);
    }

}
