import java.io.*;
import java.util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class imge
{
	public static void main(String[] args) throws IOException
	{
		FileOutputStream filewrite;
		Scanner eingabe = new Scanner(System.in);
		
		String ausgabe = "";
		
		String dateiname = "";
		
		String eingabestring;
		
		while (true)
		{
			
			System.out.println("Type 'detect' to start the program or type 'end' to stop the application:");
			
			eingabestring = eingabe.next();
			
			if (eingabestring.equals("detect"))
			{
				
				System.out.println("Give the path of the 256-color bitmap you want to detect the edges in:");
				String path = eingabe.next();
				System.out.println("Give the filename including extension:");
				String filename = eingabe.next();
				byte[] fileArray = Files.readAllBytes(Paths.get(path, filename));
				byte[] newArray = Files.readAllBytes(Paths.get(path, filename));
				
				int width = fileArray[18];
				int height = fileArray[22];
				int startbyte = fileArray[10];
				
				
				for (int currentbyte = startbyte;currentbyte < fileArray.length-1;currentbyte = currentbyte + 1)
				{
					int currentcolor = fileArray[currentbyte];
					int colorright   = fileArray[currentbyte+1];
					
					if(currentcolor != colorright)
					{
						
						newArray[currentbyte]     = (byte) 0b11111111;
					}
					else
					{
						newArray[currentbyte]     = (byte) 0b0;
					}
					
				}
				System.out.println("Give the filename plus '.bmp' you want to save this in:");
				eingabestring = eingabe.next();
				long starttime = System.currentTimeMillis();
				filewrite = new FileOutputStream(eingabestring);
				
				for (int y=0; y<newArray.length; y++)
				{
					filewrite.write(newArray[y]);
				}
				filewrite.close();
				eingabestring = "";
				long endtime = System.currentTimeMillis();
				long time = endtime - starttime;
				System.out.println("Took " + time + " milliseconds");
			}
			
			else if(eingabestring.equals("end"))
			{
				System.exit(0);
			}
		}
		
	}
}
