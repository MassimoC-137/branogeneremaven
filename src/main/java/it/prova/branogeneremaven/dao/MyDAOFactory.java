package main.java.it.prova.branogeneremaven.dao;

public class MyDAOFactory {

	private static BranoDAO brano_dao_instance = null; 
	private static GenereDAO genere_dao_instance = null; 
	
	public static BranoDAO getBranoDAOInstance() {
		if (brano_dao_instance == null)
			brano_dao_instance = new BranoDAOImpl(); 
		return brano_dao_instance; 
	}
	
	public static GenereDAO getGenereDAOInstance() {
		if (genere_dao_instance == null)
			genere_dao_instance = new GenereDAOImpl();
		return genere_dao_instance; 
	}
}
