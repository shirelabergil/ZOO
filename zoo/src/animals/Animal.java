package animals;

import mobility.Mobile;
import mobility.Point;
import food.IEdible;
import diet.IDiet;
import food.EFoodType;
import utilities.MessageUtility;
import diet.Carnivore;
import diet.Herbivore;
import diet.Omnivore;
//Mark test

/**
 * This class defines the characteristics and behavior of an animal in general.
 * 
 * @author 
 * Shirel ghanah:206645103 
 * Noa Asulin:213250749
 * Ashdod Campus
 * 
 * @see Lion,Bear,Elephent,Giraffe,Turtle
 */
public abstract class Animal extends Mobile implements IEdible , IDiet  {
	
	
	/**
	 * The animal name
	 */
	private String name;
	
	/**
	 * The animal weight
	 */
	private double weight;
	
	/**
	 * An object's instance who can eat and can check what food type he can eat.
	 */
	//private IDiet diet; //Not currently in use
	
	/**
	 * The current class name.
	 * This method check what is the name of the class.
	 * 
	 * @return the name of the class
	 */
	private String className = this.getClass().getSimpleName();
	
	/**
	 * This method checks what is the name of the current class
	 * 
	 * @return the name of the current class
	 */
	public String getClasssName() {
		
		
		MessageUtility.logGetter(this.name,"getClassName",this.className);
		return this.className;
	}
	
	/**
	 * 	This method check what is the animal name 
	 * 
	 * @return the animal name
	 */
	public String getName() {
		
		MessageUtility.logGetter(this.name,"getName",this.name);
		return this.name;
	}
	
	/**
	 *The method changes the name of the animal.
	 *
	 * @param name 
	 * 		  the new name of the animal
	 * 
	 * @return true or false( if the name were changed or not )
	 */
	public boolean setName(String name) {
	
		this.name = name;
		MessageUtility.logSetter(this.name,"setName",name,true);
		return true;
	}
	
	/**
	 *The method changes the weight of the animal.
	 *
	 * @param weight
	 * 		  the new weight of the animal
	 * 
	 * @return true or false(if the weight were changed or not  )
	 */
	public boolean setWeight(double weight) {
		
		if(weight <= 0) {
			
			MessageUtility.logSetter(this.name,"setWeight",weight,false);
			return false;
		}
		else {
			
			this.weight = weight;
			MessageUtility.logSetter(this.name,"setWeight",weight,true);
			return true;
			
		}
		
	}
	
	/**
	 * This function checks what is the weight of the animal
	 * 
	 * @return the weight of the animal
	 */
	public double getWeight() {
	
		MessageUtility.logGetter(this.name,"getWeight",this.weight);
		return this.weight;
		
	}

	 /**
    * the method receives an animal and food and it checks if the animal can eat the food.
    * 
      @param animal 
      		  The animal that eats
      		  
      @param food 
      		  The desired food for feeding
      		  
      @return The weight gained by the animal after feeding
    */
	public double eat(Animal animal, IEdible food) {
		
		
		if (animal.canEat(food.getFoodtype())) {
			
			if (food.getFoodtype()== EFoodType.MEAT) {
				this.makeSound();
				this.weight *= 1.1;
				return weight*0.1;
			}
			
			if (food.getFoodtype()== EFoodType.VEGETABLE) {
				this.makeSound();
				this.weight *= 1.07;
				return weight*0.07;
			}
		}
		
		return 0;
	}
	
	/**
	 * The method accepts a food type, and checks if the food is edible for the animal.
	 * 
	  * @param food (MEAT,VETABLE OR NOTFOOD)
	  *        The desired food for feeding
	  * 	   
	  * @return true or false . whether the animal can eat the food received or not
	 */
	public boolean canEat(EFoodType food) {
	
		
		if ( this instanceof Carnivore) {
			
			if ( food == EFoodType.MEAT ) {
				return true;
			}
		}
			
		if ( this instanceof Omnivore) {
			
			if ( food == EFoodType.MEAT ||  food == EFoodType.VEGETABLE) {
				return true;
			}
		}
		if ( this instanceof Herbivore) {
				
			if ( food == EFoodType.VEGETABLE ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * The method receives a location and moves the animal to the location it received
	 * @param p
	 * 		  the new location of the animal
	 * 
	 * @return the distance the animal made from where it was to the new location.
	 */
	public double move(Point p) {
		
			double dis = calcDistance(p);
			this.setLocation(p);
			this.setWeight(this.getWeight()-this.getTotalDistance()*this.weight*0.00025);
			MessageUtility.logBooleanFunction(this.name,"move", p, Point.checkBoundaries(p) );
			return dis;
		}
	
	/**
	 * The method checks what kind of food the animal is
	 * 
	 * @return  The food kind (MEAT,VETABLE OR NOTFOOD) .
	 */
	public EFoodType getFoodtype() {
		
		if (this instanceof Lion ) {
			
			return EFoodType.NOTFOOD;
		}
		return EFoodType.MEAT;	
	}
	
	/**
	 * The constructor initializes the fields of the animal according
	    to the parameters entered.
	    
	 * @param name 
	 * 		  the name of the animal
	 * 
	 * @param location
	 * 		  the location of the animal
	 */
	public Animal(String name , Point location) {
		super(location);
		this.name = name;
	}
	
	/**
	 * The abstract method prints the sound that the animal makes
	 */
	public abstract void makeSound();
	
	/**
	 * The method checks if the animal was able to eat the food it was given
	 * 
	 * @param food 
	 * 		  The desired food for feeding
	 * 
	 * @return true or false. whether the animal was able to eat the food or not.
	 */
	public boolean eat(IEdible food) {
		
		MessageUtility.logBooleanFunction(this.name, "eat", food, this.canEat(food.getFoodtype()));
		return this.canEat(food.getFoodtype());
	}
	
	/**
	 * Prints the description of the animal .
	 */
	public String toString() {
		
		return "["+this.className+"]" +" "+ this.name;
	}
}

