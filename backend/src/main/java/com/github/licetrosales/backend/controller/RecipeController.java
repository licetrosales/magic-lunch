package com.github.licetrosales.backend.controller;

import com.github.licetrosales.backend.model.Recipe;
import com.github.licetrosales.backend.model.RecipeDTO;
import com.github.licetrosales.backend.repo.RecipeRepo;
import com.github.licetrosales.backend.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Update.update;

@RestController
@RequestMapping("/api/users/userId")
public class RecipeController {
    private final RecipeRepo recipeRepo;
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeRepo recipeRepo) {
        this.recipeService = recipeService;
        this.recipeRepo = recipeRepo;
    }

    @GetMapping("/recipes")
    List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping("/recipes")
    Recipe addRecipe(@RequestBody RecipeDTO recipe) {

        return recipeService.addRecipe(recipe);
    }

    @DeleteMapping("/recipes/{id}")
    void deleteRecipe(@PathVariable String id) {
        recipeService.delete(id);
    }

    @PutMapping(path = "/recipes/{id}")
    Recipe updateRecipe(@PathVariable String id, @RequestBody Recipe recipeToUpdate) {
        if (!recipeToUpdate.id().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id in the url does not match the request body's id");
        }
        return recipeService.updateRecipe(recipeToUpdate);
    }
}
