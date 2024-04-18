package main.java.it.prova.branogeneremaven.dao;

import main.java.it.prova.branogeneremaven.model.Genere;

import java.util.List;

import main.java.it.prova.branogeneremaven.model.Brano;

public interface BranoDAO extends IBaseDAO<Brano>{

	public List<Brano> caricaBraniPerGenere(Genere genereInstance); 
	
	public List<Genere> estraiListaDescrizioneGeneriAssociateAdUnBrano(Brano branoInstance); 
	
	public List<Brano> listaBraniConGeneriConPiuDiDieciCaratteri(Genere genereInstance); 
	
	
}
