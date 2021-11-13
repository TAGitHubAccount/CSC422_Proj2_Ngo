
/*
 * Name: Tram-Anh Ngo
 * Date: 11/12/2021
 * Fall2021 100 CSC 422 
 * Program 2 Description:
 * creating a basic database program for managing information (name and age) about pets.
 * The database allows the user to add pet information to the database, remove pet information.
 * The program will load the pet data file from a text file when it starts and save pet data to the file when the user quits the program.
 * The program will also handle errors:
 * 1)The pet database supports only 5 entries.
 * 2)The valid input for age should be between 1 and 20. None of our pets live move than 20 years.
 * 3)The input when adding pet must have two values: the name and the age.
 * 4)The ID input by the user must be an index of the array.
 * 
 * Will create a Pet class in separate file to represent possible pets.
 */

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class PetDatabase {
	
	static int petCount;
	static Scanner s;
	static Pet[] pets; //create static instance Pet object.
         
	//Main method Contains the main loop of your program. It contains
	//nothing more than a switch statement inside a loop.
	//Each case statement calls a method below based on the user’s selection.
	//The main method uses all the other methods to do all the work.
	
	public static void main(String[] args) {
		
		pets = new Pet[5];
		s =new Scanner(System.in);

		//Print out the Welcome sign to users

		System.out.println("Pet Database Program.");
		System.out.println("" + "\n");
                
                // load data from a file
	       loadData(pets,"petdata.txt");
		
		int option = 0;
		// A while loop to check if the user enter one of the number in the option list different than 7
		
		while(option != 4)
		{
			 option= getUserChoice();
	           
	           switch(option) {// using switch for user choosing options
	           case 1:
	        	   showAllPets(pets);// call the method showAllPets()
	        	   break;
	           case 2:
	        	   addPets();  //call the method addPet()
	        	   break;
	           case 3:	
	        	 removePet();//Call method removePet()
	        	   break;
	           case 4:
	        	   System.out.print("Goodbye!");//user chooses option 7 for stop program
	               break;
	           default:
	               System.out.println("Invalid choice! Please try again!");	   
	           
	           }
	           
		}//end while
                 // close the scanner object
	       s.close();
	       // save database
	       saveData(pets,"petdata.txt",petCount);
	      
	}//end main
        
        private static void saveData(Pet[] pets, String filename, int petCount)
                 {
	       // write pets data to given file
	       try {
	           // open a file to write
	           FileWriter file = new FileWriter(filename);
	           for(int i=0;i<petCount;i++) {
	               file.write(String.format("%s %d\n", pets[i].getName(), pets[i].getAge()));
	           }
	           // close the file
	           file.close();
	       } catch (IOException e) {
	           // print error message
	           System.err.println("Can not save Data!");
	           e.printStackTrace();
	       }
	      
	   }
	
	//the method getUserChoice() Shows user the choice menu, reads and returns the user’s selection.
	// The choice menu lists the actions the user can perform.
	
	private static int getUserChoice() {
		  //print out a list of options
        System.out.println("What would you like to do?\r\n"
                   + "1. View all pets\r\n"
                   + "2. Add new pets\r\n"                  
                   + "3. Remove a pet\r\n"
                   + "4.  Exit program");
		System.out.print("your Choice: ");//ask user enter one of the number of options in the list 
        int option = s.nextInt();
        s.nextLine();
		return option;
		
	}
	
	//The method addPets() Let the user add as many pets as they want.
	
	private static void addPets() {
		int count = 0;
		
		
		while(count < 6)//while animals entered are smaller than 6 pets	
           {
			//A pet is entered as a single line 
			//consisting of a name and an integer which represents the age of the pet.
               System.out.print("add pet (name, age):");
               String data=s.nextLine();
               //The user type “done” to end reading
               if(data.equalsIgnoreCase("done"))
                   break;
               else
               {
                   //Split data using delimiter " "
                   String[] dataArr=data.split(" ");
                   String name=dataArr[0];
                   try {
    	               if(dataArr.length!=2) {
    	                   throw new IllegalArgumentException();
    	               }
                   int  age = Integer.parseInt(dataArr[1]);
                   if(age<1||age>20) {
	                   // print error message
	                   System.out.print("Error: ");
	                   System.out.print(age);
	                   System.out.println(" is not a valid age");
	               }
                   else
                   {  //check to see if more than 5 pets are added
                	   if(petCount < 5)
                	   {
                		   pets[petCount] = new Pet(name, age);
                           petCount++; 
                	   }
                	   else {
	                       // print error message
	                       System.out.print("Error: ");
	                       System.out.println("Database is full.");
	                       break;
	                   }
                           
                   }
                   //count ++;//keep track how many animals
                   } catch (Exception e) {
    	               // print error message
    	               System.out.print("Error: ");
    	               System.out.print(data);
    	               System.out.println(" is not a valid input");
    	           }//catch
               }//else
                   
           }//while
		System.out.print(petCount + " " + "pets added" + "\n");
		System.out.print("" + "\n");
		
	}//Add Method
	//Prints a table of all pets in the database.
	private static void  showAllPets(Pet[] pets) {
	//print header of the table	
		System.out.println("+---------------------------------------+");
	    System.out.printf("|%5s%5s%10s%10s%5s%5s\n", "ID", "|", "NAME", "|", "AGE", "|");
	    System.out.println("+---------------------------------------+");
	    String s = "";
	// for loop print rows	
		 for(int i = 0;i<petCount;i++)
         { 
			 s = String.format("%5s%10s%10s%5d%5s\n", "|", pets[i].getName(), "|", pets[i].getAge(), "|");
			 System.out.printf("|%5d%5s", i, s); 
            
         }
    //print footer of the table 		 
		 System.out.println("+---------------------------------------+");
         System.out.print(petCount + " " + "rows in set" + "\n");
        
		
	}
	
	//removePet() method: remove one pet from the list
	private static void removePet() {
		int i;
		showAllPets(pets);
		
		System.out.print("Enter the pet ID to remove:");
		try {
		int inputRemovedPetID= s.nextInt();
		
		if(pets == null || inputRemovedPetID < 0 || inputRemovedPetID >= pets.length) {
			// print error message
            System.out.print("Error: ID ");
            System.out.print(inputRemovedPetID);
            System.out.println(" does not exist.");
			// inputRemovedPetID= s.nextInt();
		}
		else {
		System.out.print( pets[inputRemovedPetID].getName()+ " " +  pets[inputRemovedPetID].getAge() + " is removed" + "\n");
				
		for(i= inputRemovedPetID; i<petCount - 1; i++)
        {
			pets[i] = pets[i +1];
				
        }
		
		petCount --;
		}
		
		} catch (Exception e) {
	           // print error message
	           System.out.println("Invalid Input!");
	       }
		 
		
	}
	
        //Load data from the file:
         private static void loadData(Pet[] pets, String filename) {
	       // read data from file if file exist
	       // create a scanner object to read data
	       Scanner file;
	       try {
	           file = new Scanner(new File(filename));
	           while(file.hasNextLine()) {
	               // get input data from file
	               String input = file.nextLine();
	               String[] data = input.split(" ");
	               // create a pet object
	               //Pet p = new Pet(data[0], Integer.parseInt(data[1]));
                       
                       pets[petCount] = new Pet(data[0], Integer.parseInt(data[1]));
	               // add pet to database
	               
                       petCount++;
	           }
	           // close the scanner
	           file.close();
	       } catch (FileNotFoundException e) {
	           // file does not exist
	       }
	   }

}
