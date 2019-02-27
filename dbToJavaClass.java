
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


 class dbToJavaClass {

	 private static ArrayList<Integer> indexList = new ArrayList<>();
	 private static final int splitCount = 30;//5000 is good value, also 10_000 working
	 private static final String className = "data";
	 private static final String readingFile = "file.txt";


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
     //===
		try
		{
			_initialize();
			BufferedReader in = new BufferedReader(new FileReader(readingFile) );
			String line;
			int index = 0;
			ArrayList<String> list = new ArrayList();
			while((line = in.readLine())!=null)
			{
				String[] chr = line.split(",");
				for(String ch : chr)
				{
					if(ch.length() == 4)
					{
						index++;
						String c = ch.replace("0x", "");
						c = String.valueOf((byte) ((Character.digit(c.charAt(0), 16) << 4) + Character.digit(c.charAt(1), 16)));

						list.add(c);
						if((index % splitCount) == 0)
						{
							showOut(list, (index/splitCount));
							list.clear();
						}
					}
					else
					{
						throw new Exception("Error in reading data file value, data values should be as hex value ('0xFF')");
					}

				}
			}
			if((index % splitCount) != 0) {
				showOut(list, (index/splitCount) + 1);//last section
			}
			_finalize(index);

		} catch (Exception e) {
			e.printStackTrace();
		}
			 //===

          }
}