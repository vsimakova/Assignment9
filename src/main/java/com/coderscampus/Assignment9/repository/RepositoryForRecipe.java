package com.coderscampus.Assignment9.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coderscampus.Assignment9.recipe.RecipeObject;

@Repository
public class RepositoryForRecipe {
	private List<RecipeObject> listOfRecipes = new ArrayList<>();
	
	public List<RecipeObject> getRecipes() { return listOfRecipes; }
}
