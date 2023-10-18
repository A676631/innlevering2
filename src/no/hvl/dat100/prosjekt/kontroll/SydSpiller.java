package no.hvl.dat100.prosjekt.kontroll;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.HandlingsType;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortSamling;
/**
 * Klasse som for å representere en vriåtter syd-spiller. Strategien er å lete
 * gjennom kortene man har på hand og spille det første som er lovlig.
 *
 */
public class SydSpiller extends Spiller {

	/**
	 * Konstruktør.
	 * 
	 * @param spiller
	 *            posisjon for spilleren (nord eller syd).
	 */
	public SydSpiller(Spillere spiller) {
		super(spiller);
	}

	/**
	 * Metode for å implementere strategi. Strategien er å spille det første
	 * kortet som er lovlig (også en åtter selv om man har andre kort som også
	 * kan spilles). Dersom man ikke har lovlige kort å spille, trekker man om
	 * man ikke allerede har trukket maks antall ganger. I så fall sier man
	 * forbi.
	 * 
	 * @param topp
	 *            kort som ligg øverst på til-bunken.
	 */
	@Override
	public Handling nesteHandling(Kort topp) {
//henter alle kortene til syd sin hand
		Kort[]hand = getHand().getAllekort();
// går gjennom kvart kort på handa
		for (Kort kort : hand) {
			//sjekker om kortet kan legges ned (ved å følge reglene)
			if ( Regler.kanLeggeNed(kort, topp)) {
				//Hvis kortet er lovlig, returneres en handling for å legge dette kortet
				if(Regler.atter(kort)) {
				return new Handling(HandlingsType.LEGGNED, kort);
					}
			else {
				
				return new Handling(HandlingsType.LEGGNED, kort);
				
			}
			}}
		
		//hvis ingen kort kan legges ned, sjekk om Syd kan trekke et nytt kort
		if(getAntallTrekk()< Regler.maksTrekk()){
			//return en handling for å trekke nytt kort
			return new Handling (HandlingsType.TREKK, null);
		} else {
	//hvis syd har trukket maks antall kort, og ikke kan legge, si "foribi"
		return new Handling(HandlingsType.FORBI, null);
	
		
	
		}
	}
}
