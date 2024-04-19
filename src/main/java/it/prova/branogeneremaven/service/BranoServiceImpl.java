package it.prova.branogeneremaven.service;

import java.util.List;

import it.prova.branogeneremaven.dao.BranoDAO;
import it.prova.branogeneremaven.model.Brano;
import it.prova.branogeneremaven.model.Genere;

public class BranoServiceImpl implements BranoService {

	private BranoDAO branoDAO; 
	@Override
	public void setBranoDAO(BranoDAO branoDAO) {
		this.branoDAO = branoDAO; 
	}
	
	@Override
	public List<Brano> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Brano get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Brano branoInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Brano branoInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Brano> caricaBraniPerGenere(Long idBranoInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rimuoviBranoMaPrimaScollega(Long idBranoInput) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Genere> descrizioneGeneriAssociatiBrano(Long idBranoInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Brano> conDescrizioneDaPiuDieci() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
