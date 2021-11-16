package com.challenge.elevatorSystem.entities;

import java.util.Objects;

public class Viaje
{
	private int PisoDesde;
	private int PisoHasta;
	private boolean DireccionArriba; //true si sube, false si baja
	// private int cantPersonas;

	public Viaje(int pisoDesde, int pisoHasta)
	{
		this.PisoDesde = pisoDesde;
		this.PisoHasta = pisoHasta;
		this.DireccionArriba = pisoHasta> pisoDesde;
	}

	public boolean validacionViaje(int pisoDesde, int pisoHasta)
	{
		if(pisoDesde == pisoHasta)
			return false;
		
		return true;
	}
	
	public int getPisoDesde() {
		return PisoDesde;
	}

	public void setPisoDesde(int pisoDesde) {
		PisoDesde = pisoDesde;
	}

	public int getPisoHasta() {
		return PisoHasta;
	}

	public void setPisoHasta(int pisoHasta) {
		PisoHasta = pisoHasta;
	}

	public boolean isDireccionArriba() {
		return DireccionArriba;
	}

	public void setDireccionArriba(boolean direccionArriba) {
		DireccionArriba = direccionArriba;
	}

	@Override
	public int hashCode() {
		return Objects.hash(PisoDesde, PisoHasta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		return Objects.equals(PisoDesde, other.PisoDesde) && Objects.equals(PisoHasta, other.PisoHasta);
	}

	@Override
	public String toString() {
		return "Viaje [PisoDesde=" + PisoDesde + ", PisoHasta=" + PisoHasta + ", DireccionArriba=" + DireccionArriba
				+ "]";
	}
	
	
}
