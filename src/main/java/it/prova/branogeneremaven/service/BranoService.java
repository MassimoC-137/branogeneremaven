package it.prova.branogeneremaven.service;

import java.util.List;

import it.prova.branogeneremaven.dao.BranoDAO;
import it.prova.branogeneremaven.model.Brano;
import it.prova.branogeneremaven.model.Genere;

public interface BranoService {
	
	public void setBranoDAO(BranoDAO branoDAO);
	

	public List<Brano> list() throws Exception;

	public Brano get(Long id) throws Exception;

	public void update(Brano branoInstance) throws Exception;

	public void insert(Brano branoInstance) throws Exception;

	public void delete(Long id) throws Exception;
	

	public List<Brano> caricaBraniPerGenere(Long idBranoInput) throws Exception;

	public void rimuoviBranoMaPrimaScollega(Long idBranoInput) throws Exception;

	public List<Genere> descrizioneGeneriAssociatiBrano(Long idBranoInput) throws Exception;

	public List<Brano> conDescrizioneDaPiuDieci() throws Exception;

}
