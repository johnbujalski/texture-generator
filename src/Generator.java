package brickGenerator;

import java.util.Random;

import javax.swing.JOptionPane;

public class Generator
{
	static Random random = new Random();
	
	public static void main(String[] args)
	{
		int size = positiveIntInput("Please enter the size for your texture(s).");
		
		int amount = positiveIntInput("Please enter the amount of randomized textures you'd like to generate.", 99);
		
		int brickHeight;
		if(yesNo("Would you like to determine the brick height?"))
		{
			brickHeight = positiveIntInput("Please enter the height of a single brick.", size/2);
		}
		else
		{
			brickHeight = -1;
		}
		
		int seedR, seedG, seedB;
		if(yesNo("Would you like to choose a seed color?"))
		{
			seedR = positiveIntInput("Please enter the RGB red value of your color.", 255);
			seedG = positiveIntInput("Please enter the RGB red value of your color.", 255);
			seedB = positiveIntInput("Please enter the RGB red value of your color.", 255);
		}
		else
		{
			seedR = -1;
			seedG = -1;
			seedB = -1;
		}
		
		for(int x = amount; x > 0; x--)
		{
			new Brick(size, brickHeight, seedR, seedG, seedB, "brick");
		}
		
		if(amount > 1)
		{
			JOptionPane.showMessageDialog(null, "Your " + amount + " brick textures have been generated!");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Your brick texture has been generated!");
		}
	}
	
	public static boolean yesNo(String message)
	{
		String[] options = {"Yes", "No"};
		int choice = JOptionPane.showOptionDialog(null, message, "Random Brick Texture Generator", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);
		if(choice == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static int positiveIntInput(String message, int limit)
	{
		int input = 0;
		
		do
		{
			if(input > limit)
			{
				JOptionPane.showMessageDialog(null, "Please enter a number less than " + (limit + 1) + ".");
			}
			input = positiveIntInput(message);
		}while(input > limit);
		
		return input;
	}
	
	public static int positiveIntInput(String message)
	{
		int input = 1;
		
		do
		{
			if(input < 1)
			{
				JOptionPane.showMessageDialog(null, "Please enter a number greater than 0.");
			}
			input = intInput(message);
		}while(input < 1);
		
		return input;
	}
	
	public static int intInput(String message)
	{
		boolean success = false;
		int output = 0;
		
		while(!success)
		{
			String input = JOptionPane.showInputDialog(null, message, "Random Brick Texture Generator", JOptionPane.QUESTION_MESSAGE);
			try
			{
				output = Integer.parseInt(input);
				success = true;
			}
			catch (NumberFormatException e)
			{
				success = false;
				JOptionPane.showMessageDialog(null, "Please enter a whole number.");
			}
		}
		
		return output;
	}
}