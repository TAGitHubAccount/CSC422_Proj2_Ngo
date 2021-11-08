
/* Name: Tram-Anh Ngo
 *  Date: 11/04/2021
 * Fall2021 100 CSC 422 
 * Pet Class to represent possible pets.
 */

public class Pet {
	private String name;
	private int age;
	public Pet(String name, int age) {
		this.name = name;
		this.age = age;	
	}
	//set Name
	public void setName(String name) {
		this.name = name;
		
	}
	//set age
	public void setAge(int age) {
		this.age = age;
		
	}
	//get Name
	public String getName() {
		return name;
		
	}
	//get age
	public int getAge() {
		return age;
		
	}

}
