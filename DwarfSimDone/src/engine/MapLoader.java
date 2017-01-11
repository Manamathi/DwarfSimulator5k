package engine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*@formatter:off */
@SuppressWarnings("resource")
public class MapLoader
{

	public static void main(String[] args)
	{
		String fileName = "Maps\\Map1.txt"; // Textfile einen String zuweisen
			FileInputStream fileInputStream;
			try
			{
				fileInputStream = new FileInputStream(fileName);
				readTextFileToArray(fileInputStream);
			} catch(Throwable e){e.printStackTrace();}
	}
	/**
	 * Liest eine Textfile ein und gibt die darin enthaltenen Zahlen als ein
	 * zweidimensionale Int-Array zurueck
	 * Neue Syntax verwendet (Int[]::new)
	 * @ returns Int-Array
	 **/
	public static int[][] readTextFileToArray(InputStream in)
	{
		InputStreamReader iReader = new InputStreamReader(in);
		BufferedReader buffReader = new BufferedReader(iReader);
		HashMap<Integer, ArrayList<String>> HMtmp = new HashMap<Integer, ArrayList<String>> ();
		ArrayList<String> ArrayListStr = new ArrayList<String>();
		if(in == null)
		{
			System.out.println("Keine File uebergeben");
			return null;
		}
		// neue Sytax: String[]::new => neues String-Array wird angelegt
		String[] getText = buffReader.lines().toArray(String[]::new);
		if(getText.length == 0)
		{
			System.out.println("Keine File uebergeben");
			return null;
		}
		for(int n = 0; n< getText.length; n++)
		{
			String[] StringArraytmp = getText[n].split(",");
			ArrayList<String> ArrayListtmp = new ArrayList<String>();
			for(String out: StringArraytmp){ArrayListtmp.add(out);}
			HMtmp.put(n, new ArrayList<String>(ArrayListtmp));
		}
//		for(Map.Entry entries21 : HMtmp.entrySet()){System.out.println("Key = " + entries21.getKey() + ", Value = " + entries21.getValue());}
		int y_Achse = getText.length;
		int x_Achse = HMtmp.get(0).size();
		int[][] res = new int[y_Achse][x_Achse];
		for(int i = 0; i< x_Achse; i++)
		{
			for(int j = 0; j< y_Achse; j++)
			{
				res[j][i] = Integer.parseInt(HMtmp.get(j).get(i).replaceAll("\\D", "").trim());
			}
		}
		return res;
	}

}
