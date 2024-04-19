package it.prova.branogeneremaven.dao;

public class MyDAOFactory {

	private static BranoDAO branoDAOInstance = null; 
	private static GenereDAO genereDAOInstance = null;
	
	
	
	public static BranoDAO getBranoDAOInstance() {
		if (branoDAOInstance == null)
			branoDAOInstance = new BranoDAOImpl(); 
		return branoDAOInstance;
	}



	public static GenereDAO getGenereDAOInstance() {
		if (genereDAOInstance == null)
			genereDAOInstance = new GenereDAOImpl(); 
		return genereDAOInstance;
	}
	
	
	
}
