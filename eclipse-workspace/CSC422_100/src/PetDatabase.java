
/*
 * Name: Tram-Anh Ngo
 * Date: 11/04/2021
 * Fall2021 100 CSC 422 
 * Program 5 Description:
 * creating a basic database program for managing information (name and age) about pets.
 * The database allows the user to add pet information to the database, remove pet information,
 * updating pet information, and searching for pets by name or by age. Assume the user
 * only input pet names consisting of a single word. 
 * Will create a Pet class in separate file to represent possible pets.
 */


import java.util.Scanner;


public class PetDatabase {
	static int petCount;
	static Scanner s;
	static Pet[] pets; //create static instance Pet object.

	//Main method Contains the main loop of your program. It contains
	//nothing more than a switch statement inside a loop.
	//Each case statement calls a method below based on the user’s selection.
	//The main method uses all the other methods to do all the work.
	
	public static void main(String[] args) {
		
		pets = new Pet[100];
		s =new Scanner(System.in);

		//Print out the Welcome sign to users

		System.out.println("Pet Database Program.");
		System.out.println("" + "\n");
		
		int option = 0;
		// A while loop to check if the user enter one of the number in the option list different than 7
		
		while(option != 7)
		{
			 option= getUserChoice();
	           
	           switch(option) {// using switch for user choosing options
	           case 1:
	        	   showAllPets();// call the method showAllPets()
	        	   break;
	           case 2:
	        	   addPets();  //call the method addPet()
	        	   break;
	           case 3:
	        	   updatePet();// call the method updatePet()
	        	   break;
	           case 4:	
	        	 removePet();//Call method removePet()
	        	   break;
	           case 5:
	        	searchPetsByName();// call method searchPetsByName()
	        	   break;
	           case 6:
	        	  searchPetsByAge(); //call searchPetsByAge()
	        	   break;
	           case 7:
	        	   System.out.print("Goodbye!");//user chooses option 7 for stop program
	               break;
	           default:
	               System.out.println("Invalid choice! Please try again!");	   
	           
	           }
	           
		}
		
	}//end main
	
	//the method getUserChoice() Shows user the choice menu, reads and returns the user’s selection.
	// The choice menu lists the actions the user can perform.
	
	private static int getUserChoice() {
		  //print out a list of options
        System.out.println("What would you like to do?\r\n"
                   + "1. View all pets\r\n"
                   + "2. Add more pets\r\n"
                   + "3. Update an existing pet\r\n"
                   + "4. Remove an existing pet\r\n"
                   + "5. Search pets by name\r\n"
                   + "6. Search pets by age\r\n"
                   + "7.  Exit program");
		System.out.print("your Choice: ");//ask user enter one of the number of options in the list 
        int option = s.nextInt();
        s.nextLine();
		return option;
		
	}
	
	//the method addPets() Let the user add as many pets as they want.
	
	private static void addPets() {
		int count = 0;
		
		
		while(count < 100)//while animals entered are smaller than 100	
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
                   int  age = Integer.parseInt(dataArr[1]);
                  pets[petCount] = new Pet(name, age);
                  petCount++;
                   count ++;//keep track how many animals
               }
           }
		System.out.print(count + " " + "pets added" + "\n");
		System.out.print("" + "\n");
		
	}
	//Prints a table of all pets in the database.
	private static void  showAllPets() {
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
	//UpdatePet Method: Shows the user a table of all the pets 
	//and reads the pet ID that the user wants to update
	
	private static void updatePet() {
		showAllPets();
		
		System.out.print("Enter the pet ID you can to update:");//ID is the array index of the object
        String inputUpdatePetID= s.nextLine();
        int  inputUpdatePetIDInt = Integer.parseInt(inputUpdatePetID);
        System.out.print("Enter new name and new age:");//ask the user to enter the new name and new age
        String inputUpdatePetNameAge=s.nextLine();
        String[] updateNameAgeArray = inputUpdatePetNameAge.split(" ");//split name & age by space 
        String updatedName = updateNameAgeArray[0];
        int updatedAge = Integer.parseInt(updateNameAgeArray[1]);
	
        for(int i=0;i<petCount;i++)
        {
        	if(i == inputUpdatePetIDInt) {
        		 System.out.print( pets[i].getName()+ " " +  pets[i].getAge() + " changed to ");
        		pets[i] = new Pet(updatedName, updatedAge);//use Pet object to uodate new name  & age
        		System.out.print( pets[i].getName()+ " " +  pets[i].getAge() + "\n");
        		
        	}
        }
       
	}
	//removePet() method: remove one pet from the list
	private static void removePet() {
		int i;
		showAllPets();
		
		System.out.print("Enter the pet ID to remove:");
		int inputRemovedPetID= s.nextInt();
		
		
		if(pets == null || inputRemovedPetID < 0 || inputRemovedPetID >= pets.length) {
			 System.out.print("Invalid ID:");
			 inputRemovedPetID= s.nextInt();
		}
		System.out.print( pets[inputRemovedPetID].getName()+ " " +  pets[inputRemovedPetID].getAge() + " is removed" + "\n");
		
		
		for(i= inputRemovedPetID; i<petCount;i++)
        {
			pets[i] = pets[i +1];
				
        }
		
		petCount --;
		
	}
	//searchPetsByName(): Prompts the user for a name and then 
	//displays a table showing all pets matching the name.
	private static void searchPetsByName() {
		int count = 0;
		System.out.print("Enter a name to search:");
		String inputSearchPetName=s.nextLine();
		
		System.out.println("+---------------------------------------+");
	    System.out.printf("|%5s%5s%10s%10s%5s%5s\n", "ID", "|", "NAME", "|", "AGE", "|");
	    System.out.println("+---------------------------------------+");
	    String s = "";
		
		
		for(int i =0; i< petCount; i++) {
			if (pets[i].getName().equalsIgnoreCase(inputSearchPetName)) {//The name is case insensitive
				
				s = String.format("%5s%10s%10s%5d%5s\n", "|", pets[i].getName(), "|", pets[i].getAge(), "|");
				System.out.printf("|%5d%5s", i, s);

				count ++;
			}
		}
		System.out.print(count + " " + "rows in set" + "\n");
		
	}
	//searchPetsByAge() method
	private static void searchPetsByAge() {
        //showAllPets();
        int count = 0;
		System.out.print("Enter age to search:");//promp the user for an age
		int inputSearchPetAge=s.nextInt();
		
		System.out.println("+---------------------------------------+");
	    System.out.printf("|%5s%5s%10s%10s%5s%5s\n", "ID", "|", "NAME", "|", "AGE", "|");
	    System.out.println("+---------------------------------------+");
	    String s = "";
		
		
		for(int i =0; i< petCount; i++) {
			if (pets[i].getAge() == inputSearchPetAge) {
				
				//display a table consists of pets of that age
				s = String.format("%5s%10s%10s%5d%5s\n", "|", pets[i].getName(), "|", pets[i].getAge(), "|");
				System.out.printf("|%5d%5s", i, s);

				count++;
			}
		}
		System.out.print(count + " " + "rows in set" + "\n");
	}

}//class
