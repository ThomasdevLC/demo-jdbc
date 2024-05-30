package fr.diginamic.props;

import java.util.Iterator;
import java.util.ResourceBundle;

public class TestConfigurationProps {

	public static void main(String[] args) {
		ResourceBundle config  = ResourceBundle.getBundle("config");
		String url = config.getString("database.url");
		
		Iterator<String> iterateur = config.getKeys().asIterator();
		while (iterateur.hasNext())	{
			String key = iterateur.next();
			System.out.println(key);
			
		}
		System.out.println(url);


	}

}
