package no.hvl.dat100.prosjekt.kontroll;

import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.HandlingsType;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortSamling;
import no.hvl.dat100.prosjekt.modell.KortUtils;

public class Spill {

	private ISpiller nord;
	private ISpiller syd;
	private Bord bord;

	public Spill(ISpiller nord, ISpiller syd) {
		this.nord = nord;
		this.syd = syd;
		this.bord = new Bord();
	}

	public Bord getBord() {
		return bord;
	}

	public ISpiller getSyd() {
		return syd;
	}

	public ISpiller getNord() {
		return nord;
	}

	public void start() {
	    KortUtils.stokk(bord.getBunkeFra());
	    delutKort();
	    Kort x = bord.taOversteFraBunke();
	    bord.getBunkeTil().leggTil(x);
	}

	private void delutKort() {
	    for (int i = 0; i < ANTALL_KORT_START; i++) {
	        Kort kortTilNord = bord.taOversteFraBunke();
	        nord.leggTilKort(kortTilNord);
	        Kort kortTilSyd = bord.taOversteFraBunke();
	        syd.leggTilKort(kortTilSyd);
		}
	}

	public Kort trekkFraBunke(ISpiller spiller) {
		if (bord.getBunkeFra().erTom()) {
			bord.snuTilBunken();
		}
		Kort trekk = bord.taOversteFraBunke();
		spiller.leggTilKort(trekk);
		spiller.setAntallTrekk(0);
		return trekk;
	}

	public Handling nesteHandling(ISpiller spiller) {
		return spiller.nesteHandling(bord.seOversteBunkeTil());
	}

	public boolean leggnedKort(ISpiller spiller, Kort kort) {
		KortSamling hand = spiller.getHand();
		KortSamling bunkeTil = spiller.getBunkeTil();

		if (hand.inneholder(kort)) {
			if (Regler.kanLeggeNed(kort, bord.getToppkort())) {
				hand.fjern(kort);
				bunkeTil.leggTil(kort);
				spiller.setAntallTrekk(0);
				return true;
			}
		}
		return false;
	}

	public void forbiSpiller(ISpiller spiller) {
		spiller.setAntallTrekk(0);
	}

	public Kort utforHandling(ISpiller spiller, Handling handling) {
		Kort kort = null;

		if (handling.getType() == HandlingsType.FORBI) {
			forbiSpiller(spiller);
		} else if (handling.getType() == HandlingsType.LEGGNED) {
			kort = handling.getKort();
			leggnedKort(spiller, kort);
		} else if (handling.getType() == HandlingsType.TREKK) {
			kort = trekkFraBunke(spiller);
		}
		return kort;
	}
}
