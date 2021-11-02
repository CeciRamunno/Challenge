package com.challenge.elevatorSystem.entities;

import java.util.Objects;

public class Viaje
{
	private Integer PisoDesde;
	private Integer PisoHasta;
	private boolean DireccionArriba; //true si sube, false si baja
	// private int cantPersonas;

	public Viaje(Integer pisoDesde, Integer pisoHasta)
	{
		this.PisoDesde = pisoDesde;
		this.PisoHasta = pisoHasta;
		this.DireccionArriba = pisoHasta> pisoDesde;
	}

	public Integer getPisoDesde() {
		return PisoDesde;
	}

	public void setPisoDesde(Integer pisoDesde) {
		PisoDesde = pisoDesde;
	}

	public Integer getPisoHasta() {
		return PisoHasta;
	}

	public void setPisoHasta(Integer pisoHasta) {
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
