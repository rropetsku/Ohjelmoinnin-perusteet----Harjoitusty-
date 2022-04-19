import java.util.Scanner;

public class Hirsipuu {
	public static final Scanner lukija = new Scanner(System.in);
	
    //Käynnistää paaValikko-metodin
    public static void main(String args[]){
    }
    //Antaa käyttäjän valita yksinPeli- ja pelaajaVsKone-metodin, ohjeiden ja lopetuksen välillä
    public static void paaValikko(){
    }
    //Pelaaja pelaa yksin hirsipuuta
    public static void yksinPeli(){
    	String sana = arvattavaSana();
    	String sanaTuloste;
    	int kierrosLaskuri = 0;
    	int hirsipuunTila = 0;
    	
    	while(hirsipuunTila <= 8) {
    		hirsipuunTulostus(hirsipuunTila);
    		for (int i=0; i<sana.length(); i++) {
    			sanaTuloste += "*";
    		}
    		kierrosLaskuri += 1;
    		System.out.println(sanaTuloste);
    		System.out.println("Arvaa kirjain: ");
    		String arvattuKirjain = lukija.nextLine();
    	}

    }
    //Pelaaja ja kone arvaavat vuorotellen
    public static void pelaajaVsKone(){
    }
    //Hakee tiedostosta arvattavan sanan ja palauttaa sen
    public static String arvattavaSana(){
    }
    //Tulostaa annetun luvun mukaan hirsipuun tilan
    public static void hirsipuunTulostus(int hirsipuunTila){
    }
    //Palauttaa boolean arvon sen mukaan onko arvattuKirjain sanassa
    public static boolean onkoKirjainSanassa(String sana,char arvattuKirjain){
    }
    //Vaihtaa sanaTulosteeseen arvattuKirjain-arvon indekseihin, jossa sana-merkkijonossa löytyy kyseinen merkki 
    public static paljastaKirjaimet(String sana,String sanaTuloste,char arvattuKirjain){
    }

}

