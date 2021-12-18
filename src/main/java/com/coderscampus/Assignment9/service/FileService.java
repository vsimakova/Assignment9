package com.coderscampus.Assignment9.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderscampus.Assignment9.recipe.RecipeObject;
import com.coderscampus.Assignment9.repository.RepositoryForRecipe;

@Service
public class FileService {
	
	@Autowired
	private RepositoryForRecipe recipeRepository;
	
	private List<RecipeObject> readFile() {
		CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',').withEscape('\\')
		 .withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", "Preparation Minutes", "Price Per Serving", "Ready In Minutes", "Servings", "Spoonacular Score", "Title", "Vegan", "Vegetarian")
		 .withSkipHeaderRecord()
		 .withIgnoreSurroundingSpaces();
		
		try (Reader in = new FileReader("recipe.txt"))
		{
			Iterable<CSVRecord> records = csvFormat.parse(in);
			for (CSVRecord record : records) {
				Integer cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));
				Boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));
				Boolean glutenFree = Boolean.parseBoolean(record.get("Gluten Free"));
				String instructions = record.get("Instructions");
				Double prepMin = Double.parseDouble(record.get("Preparation Minutes"));
				Double price = Double.parseDouble(record.get("Price Per Serving"));
				Integer readyInMin = Integer.parseInt(record.get("Ready In Minutes"));
				Integer servings = Integer.parseInt(record.get("Servings"));
				Double score = Double.parseDouble(record.get("Spoonacular Score"));
				String title = record.get("Title");
				Boolean vegan = Boolean.parseBoolean(record.get("Vegan"));
				Boolean vegetarian = Boolean.parseBoolean(record.get("Vegetarian"));
				
				RecipeObject recipe = new RecipeObject();
				recipe.setCookingMinutes(cookingMinutes);
				recipe.setDairyFree(dairyFree);
				recipe.setGlutenFree(glutenFree);
				recipe.setInstructions(instructions);
				recipe.setPreparationMinutes(prepMin);
				recipe.setPricePerServing(price);
				recipe.setReadyInMinutes(readyInMin);
				recipe.setServings(servings);
				recipe.setSpoonacularScore(score);
				recipe.setTitle(title);
				recipe.setVegan(vegan);
				recipe.setVegetarian(vegetarian);
				
				recipeRepository.getRecipes().add(recipe);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return recipeRepository.getRecipes();
	}
	
	public List<RecipeObject> getRecipes() {
		if (recipeRepository.getRecipes().size() == 0) { return readFile(); }
		return recipeRepository.getRecipes();
	}
}
