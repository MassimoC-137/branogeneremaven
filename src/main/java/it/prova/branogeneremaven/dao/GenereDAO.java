package main.java.it.prova.branogeneremaven.dao;

import java.util.Date;
import java.util.List;

import main.java.it.prova.branogeneremaven.model.Brano;
import main.java.it.prova.branogeneremaven.model.Genere;

public interface GenereDAO extends IBaseDAO<Genere>{

	public List<Genere> cercaPerDescrizione(String descrizione); 
	
	public List<Brano> braniPubblicatiTra(Date dataInizio, Date dataFine); 
}
