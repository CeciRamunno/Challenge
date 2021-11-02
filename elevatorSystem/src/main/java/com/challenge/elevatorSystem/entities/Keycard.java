package com.challenge.elevatorSystem.entities;

import java.util.Objects;

public class Keycard
{
	private int id;
	private boolean autoriza;
	
	public Keycard(int id)
	{
		if(id == 1)
			this.autoriza = true;
		else
			this.autoriza = false;
	}

	public int getId() {
		return id;
	}

	public boolean isAutoriza() {
		return autoriza;
	}

//	public void setAutoriza(boolean autoriza) {
//		this.autoriza = autoriza;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Keycard other = (Keycard) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Keycard [id=" + id + ", autoriza=" + autoriza + "]";
	}
}
