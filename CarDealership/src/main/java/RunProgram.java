import java.util.List;
import java.util.Scanner;

import controller.InventoryHelper;
import model.DealerInventory;

/**
 * @author Jeff Johnson - jjohnson190 CIS175 - Spring 2024 Jan 26, 2024
 */

public class RunProgram {

	static Scanner in = new Scanner(System.in);
	static InventoryHelper iH = new InventoryHelper();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			startMenu();
		} catch (Exception e) {
			System.out.println("Invalid selection, program terminated.");
		} finally {
			iH.closeIH();
			in.close();
		}

	}

	private static void startMenu() {
		// TODO Auto-generated method stub

		boolean exitProgram = false;

		System.out.println("Car Dealership Main Menu");
		System.out.println();
		
		while (exitProgram == false) {

			System.out.println("Please select an option:");
			System.out.println("1.) Add a Car");
			System.out.println("2.) Edit a Car");
			System.out.println("3.) Delete a Car");
			System.out.println("4.) Show Car List");
			System.out.println("5.) Exit program");
			System.out.print("Your Choice: ");

			int userChoice = in.nextInt();
			in.nextLine();

			if (userChoice == 1) {
				addItem();

			} else if (userChoice == 2) {
				editItem();

			} else if (userChoice == 3) {
				deleteItem();

			} else if (userChoice == 4) {
				showList();

			} else if (userChoice == 5) {
				iH.closeIH();
				in.close();
				exitProgram = true;

			} else {
				System.out.println();
				System.out.println("I'm sorry, that wasn't a choice.");
			}
		}
		
		System.out.println();
		System.out.println("Thanks for using my program!");
	}



	private static void addItem() {
		// TODO Auto-generated method stub
		System.out.print("Please enter the Make of the vehicle: ");
		String make = in.nextLine();
		System.out.print("Please enter the model of the vehicle: ");
		String model = in.nextLine();
		
		DealerInventory inventoryToAdd = new DealerInventory(make,model);
		iH.addInventory(inventoryToAdd);
		
	}
	
	private static void editItem() {
		// TODO Auto-generated method stub
		System.out.println("What should we search by?");
		System.out.println("1.) Make");
		System.out.println("2.) Model");
		
		int searchBy = in.nextInt();
		in.nextLine();
		
		List<DealerInventory> foundInventory;
		
		if(searchBy == 1) {
			System.out.print("Please enter the Vehicle Make: ");
			String vehicleMake = in.nextLine();
			foundInventory = iH.searchForVehicleByMake(vehicleMake);
		} else {
			System.out.print("Please enter the Vehicle Model: ");
			String vehicleModel = in.nextLine();
			foundInventory = iH.searchForVehicleByModel(vehicleModel);
		}
		
		if(!foundInventory.isEmpty()) {
			System.out.println("Success!  Posting Results:");
			
			for (DealerInventory d : foundInventory) {
				System.out.println(d.toString());
			}
			System.out.println("Enter ID you would like to change: ");
			int vehicleIdToEdit = in.nextInt();
			
			DealerInventory vehicleToEdit = iH.searchForItemByID(vehicleIdToEdit);
			System.out.println("Pulled :  Make- " + vehicleToEdit.getVehicleMake() + " Model- " + vehicleToEdit.getVehicleModel());
			System.out.println("1.) Change Make");
			System.out.println("2.) Change Model");
			
			int updateChoice = in.nextInt();
			in.nextLine();
			
			if (updateChoice == 1) {
				System.out.print("New Make: ");
				String newMake = in.nextLine();
				vehicleToEdit.setVehicleMake(newMake);
			} else if (updateChoice == 2) {
				System.out.print("New Model: ");
				String newModel = in.nextLine();
				vehicleToEdit.setVehicleModel(newModel);
			}
			
			iH.editInventory(vehicleToEdit);
		} else {
			System.out.println("No Vehicle found with Specified Data");
		}
	}
	
	private static void deleteItem() {
		// TODO Auto-generated method stub
		System.out.print("Please enter the Make of the vehicle: ");
		String make = in.nextLine();
		System.out.print("Please enter the model of the vehicle: ");
		String model = in.nextLine();
		
		DealerInventory toRemove = new DealerInventory(make,model);
		iH.deleteInventory(toRemove);
	}
	
	private static void showList() {
		// TODO Auto-generated method stub
		List<DealerInventory> completeInventory = iH.showAllInventory();
		
		for(DealerInventory singleCar : completeInventory) {
			System.out.println(singleCar.toString());
		}
	}
}