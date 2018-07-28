////////////////////////////////////////////////////////////////////////////////
//         
// Title:            RecipeWrangler
// Files:            RecipeWrangler.java, Recipe.java
// Semester:         Fall 2015
//
////////////////////////////////////////////////////////////////////////////////

// Import Statements
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

////////////////////////////////////////////////////////////////////////////////
/**
 * Represents a recipe book described below that keeps track of your favorite
 * recipes
 * 
 * The program adds recipes from a file at the start and then allows the user to
 * add and edit recipes. The program user is able to list the available recipe
 * names and display the information about a specific recipe. When the program
 * starts, the user is asked for recipe file and the program loads all recipes
 * found in the file. When the program ends, the user is asked for a filename to
 * write (save) all recipes. The save overwrites the file if it already exists.
 *
 * Bugs: none known
 *
 * @author Akshay Mishra
 */
public class RecipeWrangler {

	/**
	 * Program execution starts here.
	 * 
	 * @param args
	 *            UNUSED
	 */
	public static void main(String[] args) {

		String userInput = null; // variables to store
		int choice = 0; // user inputs

		// Creates a new Scanner object
		Scanner scnr = new Scanner(System.in);

		// Creates an Array List to store recipe
		ArrayList<Recipe> recipeList = new ArrayList<>();

		// Displays the welcome messages to the user
		System.out.println("Recipe Wrangler 2015");
		System.out.println("Let us help you keep track of your" 
		+ " favorite recipes.");

		do {
			// Displays menu options to the user
			System.out.println("Main Menu");
			System.out.println("---------");
			System.out.println("1. Display recipe names (sorted)");
			System.out.println("2. Display/Edit/Add a recipe");
			System.out.println("3. Load recipes from a file");
			System.out.println("4. Save recipes to a file");
			System.out.println("5. Exit");

			// Asks the user to enter a choice
			System.out.print("Enter choice:");

			// sorts the array list of recipe name each time
			// the user enters a recipe
			Collections.sort(recipeList);

			// user input is stored in the variable userInput
			userInput = scnr.nextLine();

			// prints "Enter integer choice between 1-5: " if user
			// input is other than 1, 2, 3, 4 or 5
			while (!(userInput.equals("1") || userInput.equals("2") ||
					userInput.equals("3") || userInput.equals("4")
					|| userInput.equals("5"))) {
				// prints error message
				System.out.print("Enter integer choice between 1-5: ");

				// asks for new input from the user
				userInput = scnr.nextLine();
			}

			// converts user input (string) to an integer named choice
			choice = Integer.parseInt(userInput);

			if (choice == 1) {

				// calls displayName method to print names of stored
				// recipes in arrayList
				displayName(recipeList);
			}

			if (choice == 2) {

				// calls displayAddEdit method to add or edit stored
				// recipes of arrayList
				displayAddEdit(scnr, recipeList);
			}

			if (choice == 3) {

				// calls readFromFile method to read recipes stored in
				// the file entered by user
				readFromFile(scnr, recipeList);
			}

			if (choice == 4) {

				// calls saveToFile method to save recipes created by
				// user in the mentioned file
				saveToFile(scnr, recipeList);
			}

		} while (choice != 5); // loop continues until user input is 5

		if (choice == 5) {

			// prints end note
			System.out.println("Thanks for using RecipeWrangler!");

		}

	} // End of main method

////////////////////////////////////////////////////////////////////////////////

	/**
	 * Displays the content of the ArrayList of recipes
	 * 
	 * @param recipeList
	 *            the ArrayList to store recipe names
	 */
	private static void displayName(ArrayList<Recipe> recipeList) {

		// If the array list of recipes is empty, prints "No recipes"
		if (recipeList.isEmpty()) {

			System.out.println("No recipes");

		}
		// Else if the array list is not empty
		else {

			// for each element in the array list of recipes
			for (int i = 0; i < recipeList.size(); i++) {

				// print the elements
				System.out.println(recipeList.get(i).getName());
			}
		}
	} // End of displayName method

////////////////////////////////////////////////////////////////////////////////

	/**
	 * Displays the list of recipe and it's contents. New recipes can be added
	 * or existing recipes can be edited depending on user's choice
	 * 
	 * @param scnr
	 *            scanner object
	 * 
	 * @param recipeList
	 *            the array list to store recipe name
	 */

	private static void displayAddEdit(Scanner scnr,
			ArrayList<Recipe> recipeList) {

		// Prompts the user for the name of the recipe
		System.out.print("What is the name of the recipe?");

		// Takes input of the recipe name and stores it in the variable name
		String name = scnr.nextLine();

		// converts the recipe name to upperCase
		name = name.toUpperCase();

		int recipeIndex = 0; // keeps track of recipe index
		boolean RecipeInList = false;

		// for each element in the array list recipeList
		for (int i = 0; i < recipeList.size(); i++) {

			// if the recipe name entered by the user is
			// present in the arrayList
			if (recipeList.get(i).getName().equals(name)) {

				RecipeInList = true;
				
				// index of found recipe
				recipeIndex = i;
			}
		}

		// if the recipe entered by the user is in the list
		if (RecipeInList == true) {

			int input = 0;
			System.out.println("Found recipe for: " + name);

			do {
				// prints found recipe
				System.out.println(recipeList.get(recipeIndex));

				// Prompt the user to edit the recipe
				System.out.println("1. Edit ingredient list" + 
				"\n2. Edit instructions" + "\n3. Done editing"
						+ "\nEnter choice: ");

				// input validation
				if (scnr.hasNextInt()) {
					input = scnr.nextInt();
					scnr.nextLine();

					// if the user enters 1
					if (input == 1) {

						// Asks the user to enter the ingredients
						System.out.println("Enter" + " the ingredients: ");

						// stores user input for the ingredients
						// in the variable newIngredients
						String newIngredients = scnr.nextLine();

						// updates ingredient list as per user's choice
						recipeList.get(recipeIndex).
						setIngridients(newIngredients);
					}

					// else if the user enters 2
					else if (input == 2) {

						// Asks the user to enter the instructions
						System.out.println("Enter the " + "instructions: ");

						// stores user input for instructions in the
						// variable newInstructions
						String newInstructions = scnr.nextLine();

						// updates instruction list as per the user's choice
						recipeList.get(recipeIndex).
						setInstructions(newInstructions);
					}

					else { 
						// exits the loop if any other input
						break;
					}
				}

				else {
					// clear buffer
					scnr.nextLine();
					// exits the loop
					break;
				}

				
			} while (input != 3); // loop continues until user enters 3 
		}

		// else if the name entered by user in not in recipe list 
		// creates new recipe and add it to the list
		else {

			System.out.println("Adding recipe for:" + name);

			// Asks the user to enter the ingredients for the new recipe
			System.out.println("Enter the ingredients:");
			String ingredients = scnr.nextLine();

			// Asks the user to enter the instructions for the new recipe
			System.out.println("Enter the instructions:");
			String instructions = scnr.nextLine();

			// Created a new recipe as based on the information provided
			// by the user
			Recipe recipe = new Recipe(name, ingredients, instructions);

			// Adds the new recipe to the array list of recipes
			recipeList.add(recipe);

		}
	}

////////////////////////////////////////////////////////////////////////////////
	/**
	 * Loads all the recipes from the file that is entered by the user
	 * if the file name is incorrect, return "Unable to read from file:"
	 * 
	 * @param scnr scanner object
	 * 
	 * @param recipeList the array list to store recipe name
	 */
	private static void readFromFile(Scanner scnr,
			ArrayList<Recipe> recipeList) {
		
		// Asks the user for the file name
		System.out.print("Enter filename:");

		// takes user input
		String fileName = scnr.nextLine();

		// Creates a new file with the file name provided by the user
		File file = new File(fileName);

		// The number of recipes present in the Array List recipeList
		// is stored the variable a
		int a = recipeList.size();

		try {

			// creates scanner object to loop through file
			Scanner fileScanner = new Scanner(file);
			
			// converts string into integer
			int numOfRecipes = Integer.parseInt(fileScanner.nextLine());
			
			// temp holds size of arrayList of recipes
			int temp = recipeList.size();

			
			for (int i = 0; i < (numOfRecipes); i++) {

				String name = fileScanner.nextLine();
				String ingredient = fileScanner.nextLine();
				String instructions = fileScanner.nextLine();

				// creates instance of recipe
				Recipe recipe = new Recipe(name, ingredient, instructions);

				// creates variable to recipe index
				int recipeIndex = -1;

				for (int j = 0; j < a; j++) {
					
					// if element of recipe list contains recipe
					if (recipeList.get(j).getName().
							equals(recipe.getName())) {
						
						// new recipe Index
						recipeIndex = j;
					}

				}

				if (recipeIndex == -1) {
					
					// adds recipe to the list
					recipeList.add(recipe);

				}

				else {
					
					// sets recipe to the specific index
					recipeList.set(recipeIndex, recipe);
				}
			}

			for (int i = temp; i < recipeList.size(); i++) {
				
				// prints the name of recipes added to the list
				System.out.println("Added " + recipeList.get(i).getName());
			}
			
			// prints no. of recipes added from the file
			System.out.println("Added " + numOfRecipes + 
					" recipes from " + fileName);
			
			// closes scanner object;
			fileScanner.close();
		}

		// The corresponding catch block executes if an exception 
		// of a particular type occurs within the try block.
		catch (FileNotFoundException e) {
			
			// if file not found prints as follows
			System.out.println("Unable to read from file:" + fileName);
			System.out.println("Added 0 recipes from " + fileName);

		}
		
	}
////////////////////////////////////////////////////////////////////////////////

	/**
	 * saves all the recipes created by the user to the file that 
	 * is entered by the user
	 * if the file name is incorrect, return "Unable to write to file:"
	 * 
	 * @param scnr scanner object
	 * 
	 * @param recipeList the array list to store recipe name
	 */
	private static void saveToFile(Scanner scnr, 
			ArrayList<Recipe> recipeList) {
		
		// asks user to enter file name
		System.out.print("Enter file name:");
		
		// stores input of the user
		String fileName = scnr.nextLine();
		
		// create instance of file with name as entered by user
		File file = new File(fileName);
		
		try {
			 
			// creates an instance of printWrite
			PrintWriter writer = new PrintWriter(file);
			
			// prints the fist line as the number of recipes
			writer.println(recipeList.size());

			// prints recipe name, ingredient and instructions
			for (int i = 0; i < recipeList.size(); i++) {
				writer.println(recipeList.get(i).getName());
				writer.println(recipeList.get(i).getIngridients());
				writer.println(recipeList.get(i).getInstructions());
			}

			// closes printWriter
			writer.close();
		} 
		
		// The corresponding catch block executes if an exception 
		// of a particular type occurs within the try block.
		catch (FileNotFoundException e) {
			
			// prints the following if file not found
			System.out.println("Unable to write to file:" + fileName);
		}

		// prints no. of recipes saved in file
		System.out.println("Saved " + recipeList.size() +
				" recipes to " + fileName);
	}
}
