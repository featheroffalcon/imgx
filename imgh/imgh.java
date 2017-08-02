import java.io.*;
import java.util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class imgh
{
	public static void main(String[] args) throws IOException
	{
		FileOutputStream filewrite;
		Scanner eingabe = new Scanner(System.in);
		System.out.println("least significant bit -- Benedict Wetzel");
		
		String ausgabe = "";
		
		String dateiname = "";
		
		String eingabestring;
		
		System.out.println("-------------------------------------------------------------------------------" + "\n" +
						   "  ---                                                       ---" + "\n" +
						   " |   |                                                     |   |" + "\n" +
						   " |   |                                                     |   |" + "\n" +
						   "  ---                                                      |   |" + "\n" +
						   " ----     ---	------   ------         --------------     |    |----------" + "\n" +
						   "|    |   |   | |       V        |      |              |    |                |" + "\n" +
						   " |   |   |   ||                  |    |   --------     |   |     -------     |" + "\n" +
						   " |   |   |      ----      ---     |   |  |        |    |   |    |       |    |" + "\n" +
						   " |   |   |     |    |    |   |    |   |  |        |    |   |   |         |   |" + "\n" +
						   " |   |   |    |      |  |     |   |   |   --------     |   |   |         |   |" + "\n" +
						   "|    |   |    |      |  |     |   |    |               |   |   |         |   |" + "\n" +
						   " ----     ----       --       ---      ------------    |    ---           ---" + "\n" +
						   "---------------------------------------------------|   |-----------------------" + "\n" +
						   "                                                   |   |" + "\n" +
						   "                                       ------------    |" + "\n" +
						   "                                      |               |" + "\n" +
						   "                                       --------------"  + "\n");
						   
		while (true)
		{
			
			System.out.println("-------------------------------------------------------------------------------" + "\n" +
							"| MENU: |                                                                     |" + "\n" +
							"|-------                                                                      |" + "\n" +
							"|                                                                             |" + "\n" +
							"|  'write'  : Hides a text in picture.                                        |" + "\n" +
							"|                                                                             |" + "\n" +
							"|  'read'   : Extracts text out of a picture.                                 |" + "\n" +
							"|                                                                             |" + "\n" +
							"|  'end'    : Closes the programm.                                            |" + "\n" +
							"|                                                                             |" + "\n" +
							"|  'compare': Compares two files with each other.                             |" + "\n" +
							"|                                                                             |" + "\n" +
							"|                                                                             |" + "\n" +
							"|                                                                             |" + "\n" +
							"|                                                                             |" + "\n" +
							"|                                                                             |" + "\n" +
							"|                                                                             |" + "\n" +
							"-------------------------------------------------------------------------------");
			
			System.out.print("|  ");	
			eingabestring = eingabe.next();
			
			if (eingabestring.equals("write"))
			{
				
				String path = "C:/Users/Benedict/Documents/imagehide";
				String filename = "test.txt";
				
				
				
				System.out.println("|                                                                             |"+"\n" + 
								   "-------------------------------------------------------------------------------" + "\n" + 
								   "| WRITE: |                                                                    |" + "\n" +
								   "|--------                                                                     |");
								   		   
				System.out.println("| Path of the image you want to hide something in:");
				System.out.print("|  ");	
				path = eingabe.next();
				
				System.out.println("| Filename of the image you want to hide something in:");
				System.out.print("|  ");	
				filename = eingabe.next();
		
				Path file = Paths.get(path, filename);
				byte[] fileArray = Files.readAllBytes(file);
		
				for(int i=0; i<fileArray.length-1; i = i+16)
				{
					ausgabe = " ";
								
					for(int h=0; h<16; h++)
					{
						try
						{
							if (fileArray[i+h] < 0)
							{
								ausgabe = ausgabe + (256+fileArray[i+h]) + "|";
							}
							else if (fileArray[i+h] < 10)
							{
								ausgabe = ausgabe + "00" + fileArray[i+h] + "|" ;
							}
							else if (fileArray[i+h] < 100)
							{
								ausgabe = ausgabe + "0" + fileArray[i+h] + "|" ;
							}
							else
							{
								ausgabe = ausgabe + fileArray[i+h] + "|" ;
							}
					
						}
						catch (Exception e)
						{
							ausgabe = ausgabe + "xxx" + "|";
						}
					}
				
					System.out.println(ausgabe);
				}
				
				System.out.println("" + "\n" +
								   "-------------------------------------------------------------------------------");
		
				byte colorstart = fileArray[11];
				int maxbyteone = (fileArray.length - fileArray[10]) / 8;
				int maxbytetwo = (fileArray.length - fileArray[10]) / 4;
			
				System.out.println("| Maximum amount of data able to hide in the least significant bit     : " + maxbyteone);
				System.out.println("| Maximum amount of data able to hide in the two least significant bits: " + maxbytetwo + "\n" +
								   "-------------------------------------------------------------------------------");
		
				System.out.println("| Path of the text file you want to hide in the picture:                      |");
				System.out.print("|  ");
				String textpath = eingabe.next();
		
				System.out.println("| Name including extension of the textfile you want to hide in the picture:   |");
				System.out.print("|  ");
				String textname = eingabe.next();
		
				byte[] textarray = Files.readAllBytes(Paths.get(textpath, textname));
		
				byte[] newbytearray = Files.readAllBytes(file);
		
				int textbit = 0;
				int textbyte = 0;
		
				for (int k=newbytearray[10]; k < newbytearray.length  && k - newbytearray[10] < maxbyteone * 8 && k-newbytearray[10] <= textarray.length * 8; k = k + 8)
				{
						boolean x = false;//(fileArray[k] & 0x80) !=0;
						int limit = 8;
						boolean[] currentbyte = new boolean[8];
				
						try{ currentbyte[7] = (textarray[textbyte] & 0x1) !=0;}
						catch (Exception e) { limit = 7;}
				
						try {currentbyte[6] = (textarray[textbyte] & 0x2) !=0;}
						catch (Exception e) { limit = 6;}
				
						try {currentbyte[5] = (textarray[textbyte] & 0x4) !=0;}
						catch (Exception e) { limit = 5;}
				
						try {currentbyte[4] = (textarray[textbyte] & 0x8) !=0;}
						catch (Exception e) { limit = 4;}
				
						try {currentbyte[3] = (textarray[textbyte] & 0x10) !=0;}
						catch (Exception e) { limit = 3;}
				
						try {currentbyte[2] = (textarray[textbyte] & 0x20) !=0;}
						catch (Exception e) { limit = 2;}
				
						try {currentbyte[1] = (textarray[textbyte] & 0x40) !=0;}
						catch (Exception e) { limit = 1;}
					
						try {currentbyte[0] = (textarray[textbyte] & 0x80) !=0;}
						catch (Exception e) { limit = 0;}
				
						textbyte++;
				
						for (int j = 0; j < limit; j++)
						{
							x = (fileArray[k+j] & 0x1)!=0;
							if (x == true && currentbyte[j] == false)
							{
								newbytearray[k+j] = (byte) (newbytearray[k+j] - 1); 
								System.out.println(x + "|" + currentbyte[j] + "|" + newbytearray[k+j] + "| 0");
							}
							else if (x == false && currentbyte[j] == true)
							{
								newbytearray[k+j] = (byte) (newbytearray[k+j] + 1);
								System.out.println(x + "|" + currentbyte[j] + "|" + newbytearray[k+j] + "| 1");
						
							}
							else if(currentbyte[j] == true)
							{
								System.out.println(x + "|" + currentbyte[j] + "|" + newbytearray[k+j] + "| 1");
							}
							else if(currentbyte[j] == false)
							{
								System.out.println(x + "|" + currentbyte[j] + "|" + newbytearray[k+j] + "| 0");
							}
						}
					
					System.out.println("-------");
					System.out.println(k+ "|"+textbyte);
					System.out.println("----------------------------------------------------");
				}
		
				ausgabe = "";
			
			
				for(int i=0; i<newbytearray.length-1; i = i+16)
				{
					ausgabe = " ";
								
					for(int h=0; h<16; h++)
					{
						try
						{
							if (newbytearray[i+h] < 0)
							{
								ausgabe = ausgabe + (256+newbytearray[i+h]) + "|";
							}
							else if (newbytearray[i+h] < 10)
							{
								ausgabe = ausgabe + "00" + newbytearray[i+h] + "|" ;
							}
							else if (newbytearray[i+h] < 100)
							{
								ausgabe = ausgabe + "0" + newbytearray[i+h] + "|" ;
							}
							else
							{
								ausgabe = ausgabe + newbytearray[i+h] + "|" ;
							}
					
						}
						catch (Exception e)
						{
							ausgabe = ausgabe + "xxx" + "|";
						}
					}
				
					System.out.println(ausgabe);
				
				
				}
				
				System.out.println("" + "\n" +
								   "-------------------------------------------------------------------------------" + "\n" +
								   "| Name including extension of the file you want to save that in:              |");
				
				System.out.print("|  ");
				filewrite = new FileOutputStream(eingabe.next());
				
				for (int y=0; y<newbytearray.length; y++)
				{
					filewrite.write(newbytearray[y]);
				}
				filewrite.close();
			
				System.out.println("| Done! Back to menu:                                                         |");
		
			}
			else if (eingabestring.equals("read"))
			{
				String path = "C:/Users/Benedict/Documents/imagehide";
				String filename = "test.bmp";
				
				System.out.println("|                                                                             |"+"\n" + 
								   "-------------------------------------------------------------------------------" + "\n" + 
								   "| READ: |                                                                     |" + "\n" +
								   "|-------                                                                      |");
				
				System.out.print(  "| Path of the image you want to read:                                         |" + "\n"+
								   "|  ");
				path = eingabe.next();
				System.out.print(  "| Filename and extension of the image you want to read:                       |" + "\n"+
								   "|  ");
				filename = eingabe.next();
				
				System.out.println("-------------------------------------------------------------------------------" + "\n");
				
				Path file = Paths.get(path, filename);
				byte[] fileArray = Files.readAllBytes(file);
				byte[] output = new byte[(fileArray.length-fileArray[10])/8];
				byte startbyte = fileArray[10];
				int bit = 0;
			
			
				for(int i=0; i<fileArray.length-1; i = i+16)
				{
					ausgabe = " ";
								
					for(int h=0; h<16; h++)
					{
						try
						{
							if (fileArray[i+h] < 0)
							{
								ausgabe = ausgabe + (256+fileArray[i+h]) + "|";
							}
							else if (fileArray[i+h] < 10)
							{
								ausgabe = ausgabe + "00" + fileArray[i+h] + "|" ;
							}
							else if (fileArray[i+h] < 100)
							{
								ausgabe = ausgabe + "0" + fileArray[i+h] + "|" ;
							}
							else
							{
								ausgabe = ausgabe + fileArray[i+h] + "|" ;
							}
					
						}
						catch (Exception e)
						{
							ausgabe = ausgabe + "xxx" + "|";
						}
					}
			
					System.out.println(ausgabe);
				}
				
				System.out.println("-------------------------------------------------------------------------------" );
				
				for (int x=fileArray[10]; x<fileArray.length; x=x+8)
				{
					boolean a = (fileArray[x+0] & 0x1) !=0;
					boolean b = (fileArray[x+1] & 0x1) !=0;
					boolean c = (fileArray[x+2] & 0x1) !=0;
					boolean d = (fileArray[x+3] & 0x1) !=0;
					boolean e = (fileArray[x+4] & 0x1) !=0;
					boolean f = (fileArray[x+5] & 0x1) !=0;
					boolean g = (fileArray[x+6] & 0x1) !=0;
					boolean h = (fileArray[x+7] & 0x1) !=0;
				
					
					System.out.println(a + "|" + b+ "|"  + c+ "|"  + d+ "|"  + e+ "|"  + f+ "|"  + g+ "|"  + h);
					int outputbyte = 0;
				
					if (a)
						outputbyte = outputbyte + 128;
					if (b)
						outputbyte = outputbyte + 64;
					if (c)
						outputbyte = outputbyte + 32;
					if (d)
						outputbyte = outputbyte + 16;
					if (e)
						outputbyte = outputbyte + 8;
					if (f)
						outputbyte = outputbyte + 4;
					if (g)
						outputbyte = outputbyte + 2;
					if (h)
						outputbyte = outputbyte + 1;
				
					output[bit] = (byte)outputbyte;
					System.out.println(output[bit]+"|"+bit);
					bit++;
				
				}
				
				System.out.println("-------------------------------------------------------------------------------" );
			
				for(int i=0; i<output.length-1; i = i+16)
				{
					ausgabe = " ";
					
					for(int h=0; h<16; h++)
					{
						try
						{
							if (output[i+h] < 0)
							{
								ausgabe = ausgabe + (256+output[i+h]) + "|";
							}
							else if (output[i+h] < 10)
							{
								ausgabe = ausgabe + "00" + output[i+h] + "|" ;
							}
							else if (output[i+h] < 100)
							{
								ausgabe = ausgabe + "0" + output[i+h] + "|" ;
							}
							else
							{
								ausgabe = ausgabe + output[i+h] + "|" ;
							}
					
						}
						catch (Exception e)
						{
							ausgabe = ausgabe + "xxx" + "|";
						}
					}
				
					System.out.println(ausgabe);
				}
				
				System.out.println("-------------------------------------------------------------------------------" );
				
				for(int i=0; i<output.length-1; i = i+16)
				{
					ausgabe = " ";
					
					for(int h=0; h<16; h++)
					{
						try
						{
							if (output[i+h] < 0)
							{
								ausgabe = ausgabe + ((char)256+output[i+h]);
							}
							else if (output[i+h] < 10)
							{
								ausgabe = ausgabe +  (char) output[i+h];
							}
							else if (output[i+h] < 100)
							{
								ausgabe = ausgabe +  (char) output[i+h];
							}
							else
							{
								ausgabe = ausgabe + (char) output[i+h];
							}
					
						}
						catch (Exception e)
						{
							ausgabe = ausgabe + "x";
						}
					}
				
					System.out.println(ausgabe);
				}
				
				System.out.println("-------------------------------------------------------------------------------" );
				System.out.print(  "| Name of the file you want to save that in:                                  |" + "\n" + "|  ");
				filewrite = new FileOutputStream(eingabe.next());
				
				for (int y=0; y<output.length; y++)
				{
					filewrite.write(output[y]);
				}
				filewrite.close();
				
				
				System.out.println("-------------------------------------------------------------------------------" );
				System.out.println("| Done! Back to menu:                                                         |");
			}
			
			else if (eingabestring.equals("compare"))
			{
				String path1;
				String filename1;
				
				String path2;
				String filename2;
				
				System.out.println("|                                                                             |"+"\n" + 
								   "-------------------------------------------------------------------------------" + "\n" + 
								   "| COMPARE: |                                                                  |" + "\n" +
								   "|----------                                                                   |");
				
				System.out.print(  "| Path of textfile number one:                                                |" + "\n"+
								   "|  ");
				path1 = eingabe.next();
				System.out.print(  "| Name of textfile number one:                                                |" + "\n"+
								   "|  ");
				filename1 = eingabe.next();
				
				System.out.print(  "| Path of textfile number two:                                                |" + "\n"+
								   "|  ");
				path2 = eingabe.next();
				System.out.print(  "| Name of textfile number two:                                                |" + "\n"+
								   "|  ");
				filename2 = eingabe.next();
				System.out.println("-------------------------------------------------------------------------------" );
				
				byte[] fileone = Files.readAllBytes(Paths.get(path1, filename1));
				byte[] filetwo = Files.readAllBytes(Paths.get(path2, filename2));
				
				int mistakes = 0;
				
				for (int a=0; a < fileone.length && a < filetwo.length; a++)
				{
					if(!(fileone[a] == filetwo[a]))
					{
						System.out.println(a + "|" + fileone[a] + "|" + filetwo[a]);
						mistakes++;
					}
				}
				System.out.println("-------------------------------------------------------------------------------" );
				System.out.println("| There are " + mistakes + " mistakes.                                                       |");
				System.out.println("| Going back to menu:                                                         |");
			}
		
			else if (eingabestring.equals("end"))
			{
				System.out.println("|                                                                             |"+"\n" + 
								   "-------------------------------------------------------------------------------");
				System.exit(0);
			}
		
		}
		
	}
	
}
