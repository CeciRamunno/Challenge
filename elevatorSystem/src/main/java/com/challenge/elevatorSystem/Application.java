package com.challenge.elevatorSystem;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.challenge.elevatorSystem.entities.Edificio;
import com.challenge.elevatorSystem.entities.Elevator;
import com.challenge.elevatorSystem.entities.Viaje;;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		Elevator publicElevator = new Elevator("public");
		Elevator freightElevator = new Elevator("freight");
						
		System.out.println("Welcome!");

		int currentFloor = 0;
		publicElevator.setTransportingPeople(false);
		freightElevator.setTransportingPeople(false);

		Scanner inputInfo = new Scanner(System.in);
		
		for (int floorNumber = currentFloor + 1; floorNumber <= Edificio.getCantPisos(); floorNumber++)
		{
			publicElevator.setCurrentFloor(floorNumber);
			freightElevator.setCurrentFloor(floorNumber);
			
			boolean ok = true;
			boolean callNext = true;
			while(callNext)
			{
				ok = callElevator(publicElevator, freightElevator, callNext, inputInfo, currentFloor);
				
				if(!ok)
					callNext = false;
			}
		}
		inputInfo.close();
	}

	private static boolean callElevator(Elevator publicElevator, Elevator freightElevator, boolean callNext, Scanner inputInfo, int currentFloor)
	{
		int elevatorSelect, userFloor, finalFloor;
		elevatorSelect = askUser("\nChoose an Elevator: (1: public | 2: freight | 3: first one)", 1, inputInfo);
		userFloor = askUser("\nWhich floor are you?: ", 2, inputInfo);
		finalFloor = askUser("\nEnter the floor you are going to: ", 2, inputInfo);
		callNext = askUser("\nAnybody else? 0(no)/1(yes)", 3, inputInfo) == 1;
		
		Viaje viaje = new Viaje(userFloor, finalFloor);
		if(!viaje.validacionViaje(userFloor, finalFloor))
			return false;
		
		if(elevatorSelect == 1 || elevatorSelect == 3)
			addInfoInElevators(viaje, publicElevator);
		else if(elevatorSelect == 2)
			addInfoInElevators(viaje, freightElevator);
		else
		{
			if(publicElevator.distElevatorUser(userFloor) > freightElevator.distElevatorUser(userFloor))
				addInfoInElevators(viaje, freightElevator);
			else
				addInfoInElevators(viaje, publicElevator);
		}	
    
		return true;
	}

	private static void addInfoInElevators(Viaje viaje, Elevator elevator) {
		if(elevator.isFirstTrip())
		{
			elevator.setFirstUser(viaje);
			elevator.setDirection(viaje.isDireccionArriba());
			elevator.setFirstTrip(false);
		}
		if(elevator.isDirection())
			elevator.setQueueUp(viaje);
		else
			elevator.setQueueDown(viaje);
		
	}

	private static int askUser(String string, int i, Scanner inputInfo)
	{
		System.out.println(string);
		int number = 0;

		try
		{
			if(inputInfo.hasNextInt())
				number = inputInfo.nextInt();
//			else if(inputInfo.hasNextLine())
//				if(inputInfo.nextLine() == "y")
//					number = 1000; 
//				else
//					number = -999;
			else
				System.out.println("Error");
			//int number = inputInfo.nextInt();	
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		if(i == 1)
		{
			// validar nros 1,2,3 or set default (1)
		}
		else if(i == 2)
		{
			// validar sea numerico o si es z asig -1
			// validar sea diferente al piso que está
			// ver carga
		}
		else if(i == 3)
		{
			if(number != 0 && number != 1)	
			{
				// validar sea y
				//
				System.out.println("error");
				number = 0;
			}
		}
		else
		{
			System.out.println("Error in askUser()");
		}
		
		return number;
	}
}