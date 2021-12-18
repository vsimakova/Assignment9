package com.coderscampus.Assignment9.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.Assignment9.recipe.RecipeObject;
import com.coderscampus.Assignment9.service.FileService;

@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/gluten-free")
	public List<RecipeObject> getGlutenFree () {
		return fileService.getRecipes()
						  .stream()
						  .filter(RecipeObject::getGlutenFree)
						  .collect(Collectors.toList());
	}
	
	@GetMapping("/vegan")
	public List<RecipeObject> getVegan () {
		return fileService.getRecipes()
						  .stream()
						  .filter(RecipeObject::getVegan)
						  .collect(Collectors.toList());
	}
	@GetMapping("/vegan-and-gluten-free")
	public List<RecipeObject> getVeganAndGlutenFree () {
		return fileService.getRecipes()
						  .stream()
						  .filter(recipe -> recipe.getVegan() && recipe.getGlutenFree())
						  .collect(Collectors.toList());
	}

	@GetMapping("/vegetarian")
	public List<RecipeObject> getVegetarian () {
		return fileService.getRecipes()
				  .stream()
				  .filter(RecipeObject::getVegetarian)
				  .collect(Collectors.toList());
	}

	@GetMapping("/all-recipes")
	public List<RecipeObject> getAllRecipes () {
		return fileService.getRecipes();
	}
}
