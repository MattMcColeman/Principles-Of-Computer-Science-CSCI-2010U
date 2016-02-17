import java.util.Scanner;
import java.io.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

class Temperature{
	private double Celi;
	private double Far;
	private double Kel;

	public Temperature (double newFar){
		Far = newFar;
	}

	public void SetFahrenheit(double Fah){
		Far = Fah;
	}

	public double getFar(){
		return Far;
	}

	public double getCelsius(){
		double Farhenhiet;
		Farhenhiet = getFar();
		Celi = (Farhenhiet - 32.0)*(5.0/9.0);
		return Celi;
	}

	public double getKelvin(){
		double Farhenhiet;
		Farhenhiet = getFar();
		Kel = (Farhenhiet + 459.67)*(5.0/9.0);
		return Kel;
	}
}

class a1q3 {
	public static void main(String[] args) {
		Temperature b = new Temperature(25);

		b.SetFahrenheit(30);
		System.out.println("Temperature in Fahrenhiet is: " + b.getFar());
		System.out.println("Temperature in Celsius is: " + b.getCelsius());
		System.out.println("Temperature in Kelvin is: " + b.getKelvin());

		//System.out.println(b); 
	}
}