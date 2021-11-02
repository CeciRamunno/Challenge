package com.challenge.elevatorSystem.entities;

public class Edificio
{
	private static final Integer cantPisos = 52; // zótano + pb + 50 pisos.
	// -1 -> 0 zótano, 0 -> 1 pb, 1 -> 2 1er piso, ..., 50 -> 51 50vo piso
	
	public Edificio() {
		// TODO Auto-generated constructor stub
	}

	public static Integer getCantPisos() {
		return cantPisos;
	}

	
	@Override
	public String toString() {
		return "Edificio [cantPisos=" + cantPisos + "]";
	}
}
