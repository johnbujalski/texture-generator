package brickGenerator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Brick
{
	BufferedImage output;
	Graphics2D graphics;
	

	int size, brickHeight, extra, brickRandom;
	int lastStep2, lastStart;
	
	int r, g, b;
	
	Color brick, line, shading;
	
	String name;
	
	Brick(int size, int brickHeight, int r, int g, int b, String name)
	{
		this.r = randomizer(r, 255);
		this.g = randomizer(g, 255);
		this.b = randomizer(b, 255);
		
		this.size = size;
		
		this.brickHeight = randomizer(brickHeight, this.size/2);
		
		this.extra = size % brickHeight;
		
		this.name = name;
		
		
		this.brickRandom = 1;
		
		painter();
	}
	
	public static int randomizer(int param, int range)
	{
		if(param == -1)
		{
			param = Generator.random.nextInt(range) + 1;
		}
		return param;
	}
	
	public void painter()
	{
		brick = new Color(r, g, b);
		shading = brick.darker();
		line = brick.darker().darker();
		
		output = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		graphics = output.createGraphics();
		graphics.setColor(brick);
		graphics.fillRect(0, 0, size, size);
		
		graphics.setColor(line);
		
		lastStart = (brickHeight * 2) - Generator.random.nextInt(brickHeight * 2);
		
		for(int step = 0; step < size; step += brickHeight)
		{
			
			graphics.setColor(line);
			graphics.drawLine(0, step, output.getWidth(), step);
			
			lastStep2 = -1;
			
			for(int step2 = startCheck(lastStart, brickHeight); step2 < size; step2 += ((brickHeight * 2) + Generator.random.nextInt(brickRandom)))
			{
				graphics.setColor(line);
				graphics.drawLine(step2, step, step2, step + brickHeight);
				
				graphics.setColor(shading);
				graphics.drawLine(lastStep2 + 1, step + brickHeight - 1, step2 - 1, step + brickHeight - 1);
				
				lastStep2 = step2;
			}
			
			graphics.setColor(shading);
			graphics.drawLine(lastStep2 + 1, step + brickHeight - 1, output.getWidth(), step + brickHeight -1);

		}
		
		graphics.setColor(line);
		graphics.drawLine(0, output.getHeight() - 1, output.getWidth(), output.getHeight() - 1);
		
		try
		{
			int fileNumber = 0;
			boolean success = false;
			File outputFile;
			
			while(!success)
			{
				outputFile = new File(name + fileNumber + ".png");
				if(!outputFile.exists() && !outputFile.isDirectory())
				{
					ImageIO.write(output, "png", outputFile);
					success = true;
				}
				fileNumber++;
			}
		}
		catch (IOException e)
		{
			System.out.println("GAH! IT BROKE!");
		}
	}
	
	public int startCheck(int last, int height)
	{	
		int current;
		do
		{
			current = (height * 2) - Generator.random.nextInt(height * 2);
		}while(current < last + (height/2) && current > last - (height/2));
		
		lastStart = current;
		
		return current;
	}
}