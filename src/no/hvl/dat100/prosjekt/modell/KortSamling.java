package no.hvl.dat100.prosjekt.modell;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;

/**
 * Struktur for å lagre ei samling kort. Kan lagre hele kortstokken. Det finnes
 * en konstant i klassen Regler som angir antall kort i hver av de 4 fargene. Når
 * programmet er ferdig settes denne til 13, men under utvikling / testing kan
 * det være praktisk å ha denne mindre.
 * 
 */
public class KortSamling {

	private final int MAKS_KORT = 4 * Regler.MAKS_KORT_FARGE;

	private Kort[] samling;
	private int antall;

	/**
	 * Oppretter en tom Kortsamling med plass til MAKS_KORT (hele kortstokken).
	 */
	public KortSamling() {
		
	 	 samling = new Kort [MAKS_KORT];
	 	 antall = 0;
	 	 
	 	 
	}

	/**
	 * Returnerer en tabell med kortene i samlinga. Tabellen trenger ikke være
	 * full. Kortene ligger sammenhengende fra starten av tabellen. Kan få
	 * tilgang til antallet ved å bruke metoden getAntallKort(). Metoden er
	 * først og fremst ment å brukes i testklasser. Om man trenger
	 * kortene utenfor, anbefales metoden getAlleKort().
	 * 
	 * @return tabell av kort.
	 */
	public Kort[] getSamling() {
		
		return samling;
		
	}
	
	
	public int getAntalKort() {
		
		return antall;
		
	}
	
	
	public boolean erTom() {
		
		boolean erTom = false;
		if (antall == 0) {
			erTom = true;
		} else {
			erTom = false;
		}
		return erTom;
	}

	
	public void leggTil(Kort kort) {
		
	     if (antall < MAKS_KORT) {
	    	 samling[antall/*vi kunne skrive antallher*/] = kort;
	    	 antall++;
	     }
		
	}
	
	
	public void leggTilAlle() {
		
		
		antall = 0;
		for(Kortfarge f : Kortfarge.values()) {
			for (int i = 0; i <= Regler.MAKS_KORT_FARGE; i++) {
				leggTil(new Kort (f,i));
				
			}
		}
}
	public void fjernAlle() {
		
		
		for(int i = 0; i < MAKS_KORT; i++) {
			samling[i] = null;
		}
		antall = 0;
		
		
	}
	
	
	public Kort seSiste() {
		
		
		if (antall > 0) {
			return samling[antall-1];
		} else {
			return null;
		}
		
		
	}

	
	public Kort taSiste() {
		
			
		if (antall > 0) {
			Kort last = samling[antall-1];
			samling[antall-1] = null;
			antall--;
			return last;
		} else { 
			return null;
		}
		
	}
	
	
	public boolean har(Kort kort) {
		
		// TODO - START
		if (kort == null) {
			return false;
		}
		for(int i = 0; i < antall; i++) {
			if (samling[i].equals(kort)) {
				return true;} 
			}
		return false;
		}
		
	
			 
	public boolean fjern(Kort kort) {
		boolean funnet = false;
		for(int i = 0; i < antall; i++) {
			 if(samling[i] == (kort)) {
				 samling[i] = null;
				 funnet = true;}
		 } return funnet;
	}

	
	public Kort[] getAllekort() {
		
		// TODO - START
		Kort[] alleKort = new Kort[antall];
		for(int i = 0; i < antall; i++){
		     alleKort[i] = samling[i];
		     }
		return alleKort;
		
		
	}
	
}