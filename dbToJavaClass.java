import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


 class dbToJavaClass {

	 private static ArrayList<Integer> indexList = new ArrayList<>();
	 private static  int splitCount = 4000;//5000 is good value, also 10_000 working
	 private static  String className = "data";
	 private static  String readingFile = "file.txt";
	 private static  boolean isReadBinary = true;// if this was true, the 'readingFile' parameter should be as a hex value (copied from Win Hex), else auto read binary


	 private static  void _initialize() {
		 //System.out.println("package dbTojavaClass;");//optional package name
		 System.out.println("public class " + className + " {");
	 }

	 private static void showOut(ArrayList<String> list, int index)
	 {
		 indexList.add(index);
		 System.out.print("private static byte[] sc"+index+"(){ return new byte[]{");
		 for(String ch : list)
		 {
			 System.out.print(ch + ",");
		 }
		 System.out.println("};}");
	 }

	 private static void _finalize(int totalBytes)
	 {
		 System.out.println("/// Return data section from 0 - 'indexSize' (total bytes: "+totalBytes+").");
		 System.out.println("public static byte[] getSection(int section){ switch (section){ ");
			for (int i=0; i<indexList.size();i++) {
				System.out.println("case " + i + ":" + " return sc"+(i+1)+"();");
			}
		 System.out.println("default: return new byte[]{};}");//end of swich
		 System.out.println("}");//end of 'getSection' function
		 System.out.println("private static int indexSize = "+indexList.size()+";");
		 System.out.println("public static int getSectionsSize(){ return indexSize;}");
		 System.out.println("}");//end of class
	 }


 	public static void main (String args[]) {
            //   System.out.println("Start...");

		if(args.length == 4)// custom args
		{
			splitCount = Integer.valueOf(args[0]);
			className = args[1];
			readingFile = args[2];
			isReadBinary = (args[3].equalsIgnoreCase("binary") ? true : false);
		}

		//System.out.println("//"+ args[0]);
		//System.out.println("//"+ args[1]);
		//System.out.println("//"+ args[2]);
		//System.out.println("//"+ args[3]);

		//=if(true)
		//	return;


     //===
		try
		{
			if(isReadBinary)
			{
				//=== read binary file
				_initialize();
				int index = 0;
				ArrayList<String> list = new ArrayList();
				FileInputStream fin = new FileInputStream(readingFile);

				int len;
				byte data[] = new byte[256];//256 buff or other

				// Read bytes until EOF is encountered.
				do {
					len = fin.read(data);
					for (int j = 0; j < len; j++)
					{
						//=System.out.printf("%02X ", data[j]);

						index++;
						String c = String.format("%02X", data[j]);
						c = String.valueOf((byte) ((Character.digit(c.charAt(0), 16) << 4) + Character.digit(c.charAt(1), 16)));// c = byte vale as string (such as -10)

						list.add(c);
						if((index % splitCount) == 0)
						{
							showOut(list, (index/splitCount));
							list.clear();
						}

					}
				} while (len != -1);

				if((index % splitCount) != 0) {
					showOut(list, (index/splitCount) + 1);//last section
				}
				_finalize(index);
				//=== read binary file

			}
			else
			{
				//=== read hex file
				_initialize();
				BufferedReader in = new BufferedReader(new FileReader(readingFile));
				String line;
				int index = 0;
				ArrayList<String> list = new ArrayList();
				while ((line = in.readLine()) != null) {
					String[] chr = line.split(",");
					for (String ch : chr) {
						if (ch.length() == 4) {
							index++;
							String c = ch.replace("0x", "");
							c = String.valueOf((byte) ((Character.digit(c.charAt(0), 16) << 4) + Character.digit(c.charAt(1), 16)));

							list.add(c);
							if ((index % splitCount) == 0) {
								showOut(list, (index / splitCount));
								list.clear();
							}
						} else {
							throw new Exception("Error in reading data file value, data values should be as hex value ('0xFF')");
						}

					}
				}
				if ((index % splitCount) != 0) {
					showOut(list, (index / splitCount) + 1);//last section
				}
				_finalize(index);
				//=== read hex file
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
			 //===

          }
}
