package com.challenge.elevatorSystem.entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Elevator
{
	//private tipoAscensor tipoAscensor;
	private int currentFloor;
	private boolean direction;
	private int weigthLimit;
	private boolean keyRequired;
	private int[] weightFloor;
	private boolean firstTrip; //innecesario
	private boolean isTransportingPeople;
	private Viaje firstUser;
	private ArrayList<Viaje> queueUp = new ArrayList<Viaje>();
	private ArrayList<Viaje> queueDown = new ArrayList<Viaje>();


	public Elevator() {
		// TODO Auto-generated constructor stub
	}
	
	public Elevator(String kindElevator)
	{
		this.firstTrip = true;
		if(kindElevator.equals("public"))
		{
			this.keyRequired = true;
			this.weigthLimit = 1000; // 10 personas de 100kg en prom
		}
		else if(kindElevator.equals("freight"))
		{
			this.keyRequired = false;
			this.weigthLimit = 3000; // 30 personas de 100kg
		}
		else
			System.out.println("Invalid elevator.");
	}
	
	public boolean weightFloorAcum(int floorNumber, double userWeight)
	{
		if(weightFloor[floorNumber] <= this.weigthLimit)
		{
			weightFloor[floorNumber] += userWeight;
			return true;
		}
		else
		{
			System.out.println("Access denied: weight limit exceed"); //Beep
			return false;
		}
	}
	
	public int distElevatorUser(int userFloor)
	{
		int distance = this.currentFloor - userFloor;
		if(distance < 0 && direction == true)
			return distance;
		else
			return 1000;
	}
	
	public int getWeigthLimit() {
		return weigthLimit;
	}

	public void setWeigthLimit(int weigthLimit) {
		this.weigthLimit = weigthLimit;
	}

	public boolean isKeyRequired() {
		return keyRequired;
	}

	public void setKeyRequired(boolean keyRequired) {
		this.keyRequired = keyRequired;
	}

	public int[] getWeightFloor() {
		return weightFloor;
	}

	public void setWeightFloor(int[] weightFloor) {
		this.weightFloor = weightFloor;
	}

	public boolean isFirstTrip() {
		return firstTrip;
	}

	public void setFirstTrip(boolean firstTrip) {
		this.firstTrip = firstTrip;
	}

	public Viaje getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(Viaje firstUser) {
		this.firstUser = firstUser;
	}

	public ArrayList<Viaje> getQueueUp() {
		return queueUp;
	}

	public void setQueueUp(Viaje viaje) {
		this.queueUp.add(viaje);
	}

	public ArrayList<Viaje> getQueueDown() {
		return queueDown;
	}

	public void setQueueDown(Viaje viaje) {
		this.queueDown.add(viaje);
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public boolean isTransportingPeople() {
		return isTransportingPeople;
	}

	public void setTransportingPeople(boolean isTransportingPeople) {
		this.isTransportingPeople = isTransportingPeople;
	}

	@Override
	public String toString() {
		return "Elevator [currentFloor=" + currentFloor + ", direction=" + direction + ", weigthLimit=" + weigthLimit
				+ ", keyRequired=" + keyRequired + ", weightFloor=" + Arrays.toString(weightFloor) + ", firstTrip="
				+ firstTrip + ", isTransportingPeople=" + isTransportingPeople + ", firstUser=" + firstUser
				+ ", queueUp=" + queueUp + ", queueDown=" + queueDown + "]";
	}
}
	// nec hashcode ??
