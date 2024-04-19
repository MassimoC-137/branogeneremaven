package it.prova.branogeneremaven.service;

import it.prova.branogeneremaven.dao.MyDAOFactory;

public class MyServiceFactory {

	private static BranoService branoServiceInstance = null;
	private static GenereService genereServiceInstance = null; 

	public static BranoService getBranoServiceInstance() {
		if (branoServiceInstance == null) 
			branoServiceInstance = new BranoServiceImpl(); 
		branoServiceInstance.setBranoDAO(MyDAOFactory.getBranoDAOInstance());
		return branoServiceInstance;
	}

	public static GenereService getGenereServiceInstance() {
		if (genereServiceInstance == null)
			genereServiceInstance = new GenereServiceImpl(); 
		genereServiceInstance.setGenereDAO(MyDAOFactory.getGenereDAOInstance());
		return genereServiceInstance;
	}

}
