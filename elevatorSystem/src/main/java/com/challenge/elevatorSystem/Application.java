package com.challenge.elevatorSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.challenge.elevatorSystem.entities.Ascensor;
import com.challenge.elevatorSystem.entities.Edificio;
import com.challenge.elevatorSystem.entities.Viaje;
import com.challenge.elevatorSystem.entities.tipoAscensor;;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		System.out.println("\nHola!");
		System.out.println("Seleccione uno de los dos posibles ascensores: Publico / Carga");
		System.out.println("Marque de 0 a 50 para seleccionar un piso o presione 'Z' para el zótano\n");
		
		Ascensor ascensor = new Ascensor();
						
		ingresoViajes(ascensor);
		
		// Armado "colas" de viajes.
		//List<Viaje> viajesTomados = new ArrayList<Viaje>();
				
		int maxPisoSubida = 0; // primerViaje.getPisoHasta();
		int minPisoBajada = 0; //primerViaje.getPisoDesde();
		
		// Llamado elevator
		viajesARealizar(ascensor, maxPisoSubida, minPisoBajada);
		
		// Inicio Viajes tomados.
		// Ya tomados los llamados del momento t0 comienza a andar el ascensor,
		// en el mientras, indago cada 5 pisos que avanza si hay nuevos llamados.
		//realizacionViajes(minPisoBajada, maxPisoSubida, peso, viajesTomados, llamados, primerViaje);
	}

	private static String seleccionAscensor()
	{
		System.out.println("Tipo ascensor que desea tomar: \n1 - al público\n2 - de carga:\n");
		System.out.println("Ingrese valor: \n");
		Scanner inputAscensor = new Scanner(System.in);
		
		if(inputAscensor.nextInt() == 1)
			return "publico";
		else if(inputAscensor.nextInt() == 2)
			return "carga";
		else
		{
			System.out.println("Tipo de ascensor incorrecto.");
			return "error";	
		}
	}

	private static void viajesARealizar(Ascensor ascensor, int maxPisoSubida, int minPisoBajada)
	{
		int[] pesoPiso;
		boolean pesoOK = false;
		List<Viaje> lstLlamados = new ArrayList<>();
		
		if(ascensor.getPrimerViaje().isDireccionArriba()) // dirección primer viaje (marca dirección para resto de viajes).
		{
			for (Integer pisoDicc : ascensor.getColaLlamados().keySet())
			{
				if(pisoDicc >= ascensor.getPrimerViaje().getPisoDesde() && pisoDicc <= ascensor.getPrimerViaje().getPisoHasta())
				{
					lstLlamados = ascensor.getColaLlamados().get(pisoDicc);
					for (Viaje viaje : lstLlamados)			
					{
						if(viaje.isDireccionArriba())
						{
							pesoOK = asignarPesoElevator(viaje.getPisoDesde(), viaje.getPisoHasta(), ascensor);
							if(pesoOK)
							{
								Map<Integer, List<Viaje>> vjTomados = new HashMap<Integer, List<Viaje>>();
								vjTomados.put(pisoDicc, lstLlamados); //MAL DEBERÍA AREGAR SOLO UN VIAJE.
								ascensor.setLlamadosTomados(vjTomados);
								
								//viajesTomados.add(viaje);
								
								maxPisoSubida = maxPisoSubida < viaje.getPisoHasta() ? maxPisoSubida : viaje.getPisoHasta();
								//llamados.get(pisoDicc).remove(viaje); // ERROR ACÁ	
								ascensor.getColaLlamados().get(pisoDicc).remove(viaje);
							}							
							else
								System.out.println("Ascensor Sobrecargado, aguarde unos minutos.");
						}
					}		
				}
			}
		}
		else // dirAbajo
		{
//			for (Integer pisoDicc : llamados.keySet())
//			{
//				if(pisoDicc <= primerViaje.getPisoDesde() && pisoDicc >= primerViaje.getPisoHasta())
//				{
//					lstLlamados = llamados.get(pisoDicc);
//					for (Viaje viaje : lstLlamados)
//					{
//						if(!viaje.isDireccionArriba())
//						{
//							pesoOK = asignarPesoElevator(peso, viaje.getPisoHasta(), viaje.getPisoDesde());
//							if(pesoOK)
//							{
//								viajesTomados.add(viaje);
//								minPisoBajada = minPisoBajada > viaje.getPisoHasta() ? viaje.getPisoHasta() : maxPisoSubida;
//								//llamados.get(pisoDicc).remove(viaje);	
//							}
//							else
//								System.out.println("Ascensor Sobrecargado, aguarde unos minutos.");
//						}
//					}		
//				}
//			}
		}
	}
	
	private static void ingresoViajes(Ascensor ascensor)
	{
		Integer pisoDesde = 0;
		Integer pisoHasta  = 0;
		
		String solicitaViaje = "si";
		String sPrimerViaje = "es primer viaje";
		
		List<Viaje> llamados = new ArrayList<>();
		Map<Integer, List<Viaje>> aux = new HashMap<Integer, List<Viaje>>();
		while(solicitaViaje.toLowerCase().equals("si"))
		{
			// ASCENSOR
			String tipoAscensor = seleccionAscensor();
			
			if(tipoAscensor.equals("error")) continue;
			
			if(tipoAscensor.equals("publico")) 
				ascensor.setTipoAscensor(com.challenge.elevatorSystem.entities.tipoAscensor.PUBLICO);
			else
				ascensor.setTipoAscensor(com.challenge.elevatorSystem.entities.tipoAscensor.CARGA);
			
			// PISOS DESDE Y HASTA
			pisoDesde = solicitudPiso("actual");
			pisoHasta = solicitudPiso("destino");
			
			boolean pisosOK = validacionesPisosDsdHst(pisoDesde, pisoHasta);
			
			if(!pisosOK) continue;
			
			Viaje viaje = new Viaje(pisoDesde, pisoHasta);
			if(sPrimerViaje.equals("es primer viaje"))
			{
				Viaje primerViaje = new Viaje(pisoDesde, pisoHasta);
				ascensor.setPrimerViaje(primerViaje);	
				sPrimerViaje = "ok";
			}
			
			registroViaje(llamados, viaje, ascensor);

			solicitaViaje = askNuevoViaje();
		}
		
		solicitaViaje = "si";
		sPrimerViaje = "es primer viaje";
	}


	private static String askNuevoViaje()
	{
		System.out.println("\nDesea solicitar otro llamado? (Si/No)");
		Scanner inputSolicitaViaje = new Scanner(System.in);
		return inputSolicitaViaje.nextLine().toLowerCase();
	}

	private static boolean asignarPesoElevator(Integer pisoDesde, Integer pisoHasta, Ascensor ascensor)
	{
		if(ascensor.getTipoAscensor().equals(tipoAscensor.PUBLICO))
		{
			int[] peso = new int[Edificio.getCantPisos()];
			for (int i = pisoDesde; i <= pisoHasta; i++)
			{
				peso[i+1] += 1;
				if(peso[i+1] == ascensor.getLimitePeso())
					return false;	
			}
			ascensor.setPesoPiso(peso);
		}
		return true;
	}

	private static void registroViaje(List<Viaje> llamados, Viaje viaje, Ascensor ascensor)
	{
		Map<Integer, List<Viaje>> aux = new HashMap<Integer, List<Viaje>>();
		llamados.add(viaje);
		
		if(!ascensor.getColaLlamados().containsKey(viaje.getPisoDesde())) //error
		{
			aux.put(viaje.getPisoDesde(), llamados);
			ascensor.setColaLlamados(aux);			
		}
		else
		{
			aux.putIfAbsent(viaje.getPisoDesde(), llamados);
			ascensor.setColaLlamados(aux);
		}

	
//		if(ascensor.getColaLlamados().containsKey(viaje.getPisoDesde()))
//		{
//			llamados.add(viaje);
//			Map<Integer, List<Viaje>> auxDicc = new HashMap<Integer, List<Viaje>>();
//			ascensor.setColaLlamados(auxDicc);
//		}
//		else
//		{
//			List<Viaje> viajes = new ArrayList<Viaje>();
//			viajes.add(viaje);
//			llamados.add(viaje);
//			Map<Integer, List<Viaje>> auxDicc = new HashMap<Integer, List<Viaje>>();
//			ascensor.setColaLlamados(auxDicc);
//		}
	}

	private static Integer solicitudPiso(String pisoTxt)
	{
		Scanner inputPiso;
		System.out.println("\nIngrese su piso "+ pisoTxt + ":");
		inputPiso = new Scanner(System.in);
				
		return validacionPiso(inputPiso);
	}

	private static Integer validacionPiso(Scanner inputPiso)
	{
		if(inputPiso.hasNextInt())
		{
			Integer inputInteger = inputPiso.nextInt();
			if(inputInteger >= 0 && inputInteger <= Edificio.getCantPisos())
				return inputInteger;
			else
			{
				System.out.println("Valor piso no válido.");
				System.out.println("Vuelva a ingresar pisos.\n");
				return -999;
			}
		}
		else if (inputPiso.hasNextLine())
		{
			String inputString = inputPiso.nextLine();
			if(inputString.toLowerCase().equals("z"))
				return -1;
			else
			{
				System.out.println("Valor piso no válido.");
				System.out.println("Vuelva a ingresar pisos.\n");
				return -999;	
			}
		}
		else
			return -999;
	}
	
	private static boolean validacionesPisosDsdHst(Integer pisoDesde, Integer pisoHasta)
	{
		if(pisoDesde == pisoHasta)
		{
			System.out.println("El piso actual no debe coincidir con el de destino.");
			return false;
		}
		
		if(pisoDesde == -999 && pisoHasta == -999)
		{
			System.out.println("Marque de 0 a 50 para seleccionar un piso o presione 'Z' para el zótano");
			return false;
		}
		
		return true;
	}
}









// ANTERIOR
//
//private static Viaje ingresoViajes(Map<Integer, List<Viaje>> llamados, Viaje primerViaje)
//{
//	Integer pisoDesde = 0;
//	Integer pisoHasta  = 0;
//	
//	String solicitaViaje = "si";
//	String sPrimerViaje = "es primer viaje";
//	
//	while(solicitaViaje.toLowerCase().equals("si"))
//	{
//		String tipoAscensor = seleccionAscensor();
//		
//		if(tipoAscensor.equals("error"))
//			continue;
//					
//		pisoDesde = solicitudPiso("actual");
//		pisoHasta = solicitudPiso("destino");
//		
//		if(pisoDesde == pisoHasta)
//		{
//			System.out.println("El piso actual no debe coincidir con el de destino.");
//			continue;
//		}
//		
//		if(pisoDesde != -999 && pisoHasta != -999)
//		{
//			Viaje viaje = new Viaje(pisoDesde, pisoHasta);
//			
//			if(sPrimerViaje.equals("es primer viaje"))
//				primerViaje = new Viaje(pisoDesde, pisoHasta);
//			
//			registroViaje(llamados, viaje, ascensor);
//
//			solicitaViaje = askNuevoViaje();
//			sPrimerViaje = "ok";
//		}
//		else
//		{
//			System.out.println("Marque de 0 a 50 para seleccionar un piso o presione 'Z' para el zótano");
//			continue;
//		}
//	}
//	solicitaViaje = "si";
//	sPrimerViaje = "es primer viaje";
//	return primerViaje;
//}

//private static void registroViaje(Map<Integer, List<Viaje>> llamados, Viaje viaje, Ascensor ascensor)
//if(llamados.containsKey(viaje.getPisoDesde()))
