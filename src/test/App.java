package test;

import test.Pet;
import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		int id = 9999;
		Pet pet = new Pet();
		// Solution to Part(1)
		System.out.println("Creating a new Pet Record ---->");
		pet.postPet();
		System.out.println("--------------------------------------");
		// Solution to Part(2)
		System.out.println("Checking if a pet has been created and with correct data ---->");
		pet.getPet(id, "post");
		System.out.println("--------------------------------------");
		// Solution to part(3)
		System.out.println("Updating the Pet Record ---->");
		pet.putPet();
		System.out.println("--------------------------------------");

		System.out.println("Checking if the pet has been updated and with correct data ---->");
		pet.getPet(id, "put");
		System.out.println("--------------------------------------");
		// Solution to Part(4)
		System.out.println("Deleting the pet ---->");
		pet.delPet(id);
		System.out.println("--------------------------------------");

		System.out.println("Checking if the pet has been deleted ---->");
		pet.getPet(id, "del");
		System.out.println("--------------------------------------");

	}
}
