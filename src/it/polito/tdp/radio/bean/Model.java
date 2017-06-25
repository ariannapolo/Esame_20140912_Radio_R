package it.polito.tdp.radio.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.radio.db.RadioDAO;

public class Model {
	
	private Map<Integer, Citta> citta;
	private Map<Integer, Ponte> ponti;
	private Set<Ponte> ponteBest;
	public Collection<Citta> getAllCitta(){
		RadioDAO dao = new RadioDAO();
		if(citta==null){
			citta = new HashMap<>();
			for(Citta c : dao.getAllCitta()){
				citta.put(c.getIdCitta(), c);
			}
		}
		List<Citta> l = new ArrayList<>(citta.values());
		Collections.sort(l);
		return l;
	}
	
	public Collection<Ponte> getAllPonti(){
		RadioDAO dao = new RadioDAO();
		if(ponti==null){
			ponti = new HashMap<>();
			for(Ponte p : dao.getAllPonte()){
				ponti.put(p.getIdPonte(), p);
			}
		}
		return ponti.values();
	}
	
	
	public List<Ponte> cercaPonti(Citta c1, Citta c2){
		this.getAllPonti();
		RadioDAO dao = new RadioDAO();
		return dao.getPontiInComune(c1, c2, ponti);
	}
	
	public Set<Ponte> coperturaOttima(Citta c1, Citta c2, Citta c3){
		this.getAllCitta();
		this.getAllPonti();
		List<Ponte> ponteC = new ArrayList<>();
		ponteBest = new HashSet<>();
		List<Citta> citta = new ArrayList<Citta>();
		RadioDAO dao = new RadioDAO();
		dao.setPonti(c1, ponti);
		dao.setPonti(c2, ponti);
		dao.setPonti(c3, ponti);
		ponteBest.addAll(c1.getPonti());
		ponteBest.addAll(c2.getPonti());
		ponteBest.addAll(c3.getPonti());
		citta.add(c1);
		citta.add(c2);
		citta.add(c3);
		
		recursive(c1,ponteC, 0, citta);
		System.out.println("Copertura Ottima: "+ponteBest);
		return ponteBest;
	}

	private void recursive(Citta c, List<Ponte> ponteC, int livello, List<Citta> citta) {
		Set<Ponte> s = new HashSet<>();
		s.addAll(ponteC);
		
		if(s.size()!=0 && s.size()<=ponteBest.size() && ponteC.size()==3){
			ponteBest.clear();
			ponteBest.addAll(ponteC);
			System.out.println(ponteBest);
		}
		if(livello>2){
			return;
		}
		for(Ponte p : c.getPonti()){
			ponteC.add(p);
			if(citta.indexOf(c)<citta.size()-1)
				recursive(citta.get(citta.indexOf(c)+1),ponteC,livello+1,citta);
			else
				recursive(citta.get(0),ponteC,livello+1,citta);
			ponteC.remove(ponteC.size()-1);
		}
		
	}
	
	public static void main(String[] args) {
		Model model = new Model();
		model.getAllCitta();
		model.getAllPonti();
		model.coperturaOttima(model.citta.get(4),model.citta.get(6), model.citta.get(5));
		
	}
}
