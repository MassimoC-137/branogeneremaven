package it.prova.branogeneremaven.dao;

import java.util.List;

import it.prova.branogeneremaven.model.Brano;
import it.prova.branogeneremaven.model.Genere;

public interface BranoDAO extends IBaseDAO<Brano>{
	
	public List<Brano> caricaBraniPerGenere(Long idBranoInput) throws Exception; 
	
	public void rimuoviBranoMaPrimaScollega(Long idBranoInput) throws Exception; 
	
	public List<Genere> descrizioneGeneriAssociatiBrano(Long idBranoInput) throws Exception; 

	public List<Brano> conDescrizioneDaPiuDieci() throws Exception; 
	
}
