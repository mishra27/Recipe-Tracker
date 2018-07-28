////////////////////////////////////////////////////////////////////////////////
//         
// Title:            RecipeWrangler
// Files:            RecipeWrangler.java, Recipe.java
//
////////////////////////////////////////////////////////////////////////////////

/**
 * The Recipe class represents a recipe.
 * its name, ingredients and instructions
 *
 *The RecipeWrangler class instantiates this class when to create a new recipe
 * object
 * 
 * Bugs: none known
 *
 * @author Akshay Mishra
 */
public class Recipe implements Comparable<Recipe> {

	// creates a string to store recipe name
	private String name;

	// creates a string to store recipe ingredient
	private String ingridients;

	// creates a string to store recipe instruction
	private String instructions;

	/**
	 * @param name
	 *            - a string containing the names of recipes
	 * @param ingridients
	 *                  - a string containing the ingredients of recipes
	 * @param instructions
	 *                   - a string containing the instruction of recipes                       
	 */
	public Recipe(String name, String ingridients, String instructions) {

		// converts name to upper case
		this.name=name.toUpperCase();

		// initializes ingredients
		this.ingridients = ingridients;

		// initializes instructions
		this.instructions = instructions;


	}

	/**
	 * returns name of the recipe
	 *                        
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns ingredients of the recipe
	 *                        
	 */
	public String getIngridients() {
		return ingridients;
	}

	/**
	 * set ingredients of the recipe to new ingredient
	 *       
	 * @param ingridients
	 *                  - a string containing the new ingredients
	 *                    of recipes
	 */
	public void setIngridients(String ingridients) {
		this.ingridients = ingridients;
	}

	/**
	 * returns instructions of the recipe
	 *                        
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * set instruction of the recipe to new instructions
	 *       
	 * @param instructions
	 *                  - a string containing the new instructions 
	 *                    of recipe
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * overrides compareTo method to work for recipe object
	 *       
	 * @param other
	 *             - an object of Recipe
	 */
	public int compareTo(Recipe other) {

		// compares this recipe name to other recipe name 
		// returns 0 in both are same
		// returns -ve int if other name > this name alphabetically
		// returns +ve int if other name < this name alphabetically
		return this.getName().compareTo(other.getName());

	}

	/**
	 * overrides toString method to print recipe name, ingredients
	 * and instructions
	 *
	 */
	public String toString(){

		return ("Recipe name:" + name +"\nIngredients:"+ 
				ingridients +"\nInstructions:"+ instructions);

	}

}
