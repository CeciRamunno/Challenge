package com.challenge.elevatorSystem.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Ascensor
{
	private tipoAscensor tipoAscensor;
	private int limitePeso;
	private boolean requiereKey;
	private int[] pesoPiso;
	private Keycard key;
	private Viaje primerViaje;
	private Map<Integer, List<Viaje>> colaLlamados;
	private Map<Integer, List<Viaje>> llamadosTomados;

	public Ascensor() {
		// TODO Auto-generated constructor stub
	}

	public tipoAscensor getTipoAscensor() {
		return tipoAscensor;
	}

	public void setTipoAscensor(tipoAscensor tipoAscensor) {
		this.tipoAscensor = tipoAscensor;
	}

	public int getLimitePeso() {
		return limitePeso;
	}

	public boolean isRequiereKey() {
		return requiereKey;
	}

	public int[] getPesoPiso() {
		return pesoPiso;
	}

	public void setPesoPiso(int[] pesoPiso) {
		this.pesoPiso = pesoPiso;
	}

	public Keycard getKey() {
		return key;
	}

	public void setKey(Keycard key) {
		this.key = key;
	}

	public Viaje getPrimerViaje() {
		return primerViaje;
	}

	public void setPrimerViaje(Viaje primerViaje) {
		this.primerViaje = primerViaje;
	}

	public Map<Integer, List<Viaje>> getColaLlamados() {
		return colaLlamados;
	}

	public void setColaLlamados(Map<Integer, List<Viaje>> colaLlamados) {
		this.colaLlamados = colaLlamados;
	}
	
	public Map<Integer, List<Viaje>> getLlamadosTomados() {
		return llamadosTomados;
	}

	public void setLlamadosTomados(Map<Integer, List<Viaje>> llamadosTomados) {
		this.llamadosTomados = llamadosTomados;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(colaLlamados, tipoAscensor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ascensor other = (Ascensor) obj;
		return Objects.equals(colaLlamados, other.colaLlamados) && tipoAscensor == other.tipoAscensor;
	}

	@Override
	public String toString() {
		return "Ascensor [tipoAscensor=" + tipoAscensor + ", limitePeso=" + limitePeso + ", requiereKey=" + requiereKey
				+ ", pesoPiso=" + Arrays.toString(pesoPiso) + ", key=" + key + ", primerViaje=" + primerViaje
				+ ", colaLlamados=" + colaLlamados + ", llamadosTomados=" + llamadosTomados + "]";
	}



	
	
	
	
	
	
//	public Ascensor(tipoAscensor tipoAscensor, Viaje primerViaje)
//	{
//		//this.piso = piso;
//		this.tipoAscensor = tipoAscensor;
//		
//		if(tipoAscensor == tipoAscensor.CARGA)
//		{
//			this.pesoLimite = 3000;
//		}
//		else if(tipoAscensor == tipoAscensor.PUBLICO)
//		{
//			this.pesoLimite = 1000;
//			this.requiereKey = requiereKey;
//		}
//		else
//		{
//			System.out.println("Ingrese una selección de ascensor válida");
//		}
//
//		
//		//this.peso = peso;
//		this.primerViaje = primerViaje;
}


