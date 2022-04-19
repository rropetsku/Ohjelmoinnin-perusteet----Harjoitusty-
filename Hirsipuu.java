import java.io.PrintWriter;
import java.util.Scanner;

public class Hirsipuu {
	public static final Scanner lukija = new Scanner(System.in);
	
    //K√§ynnist√§√§ paaValikko-metodin
    public static void main(String args[]){
    }
    //Antaa k√§ytt√§j√§n valita yksinPeli- ja pelaajaVsKone-metodin, ohjeiden ja lopetuksen v√§lill√§
    public static void paaValikko(){
    }
    //Pelaaja pelaa yksin hirsipuuta
    public static void yksinPeli(){
    	String sana = arvattavaSana();
    	String sanaTuloste = "";
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
    		char arvattuKirjain = lukija.next().charAt(0);
    		try {
    			sanaTuloste = paljastaKirjaimet(sana, sanaTuloste, arvattuKirjain);
    			if (onkoKirjainSanassa(sana, arvattuKirjain) == false) {
    				System.out.println("Kirjainta ei lˆydy.");
    				hirsipuunTila+=1;
        			if (hirsipuunTila == 8) {
        				System.out.println("H‰visit!");
        				paaValikko();
        			}
    			}
    			if (onkoKirjainSanassa(sana, arvattuKirjain) == true) {
    				System.out.println("Oikein, haluatko arvata sanaa? 1) Kyll‰ 2) En");
    				int arvaatko;
    				try {
    				do {
    					arvaatko = lukija.nextInt();
    					if (arvaatko == 1) {
    						System.out.println("Kirjoita sana: ");
    						String sanaArvaus = lukija.nextLine();
    						if (sanaArvaus==sana) {
    							System.out.println("Voitit!");
    							try {
    								//String tulosTiedosto;
    								PrintWriter kirjoittaja = new PrintWriter("tulosTiedosto.txt");
    								//Mik‰ on tiedostoon kirjoitettava tulosrivi?
    								kirjoittaja.println();
    							} catch (Exception e) {
    								System.out.println("Tuloksia ei voida tallentaa");
    								paaValikko();
    							}
    						}
    					}
    					if (arvaatko == 2) {
    						break;
    					}
    				} while (arvaatko == 1 || arvaatko == 2);
    			} catch (Exception e) {
    				System.out.println("Syˆtteess‰ havaittu virhe");
    				paaValikko();
    			}
    			}
    		} catch (Exception e) {
    			System.out.println("Virhe, palataan p‰‰valikkoon.");
    			paaValikko();
    		}
    	
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
    //Vaihtaa sanaTulosteeseen arvattuKirjain-arvon indekseihin, jossa sana-merkkijonossa l√∂ytyy kyseinen merkki 
    public static paljastaKirjaimet(String sana,String sanaTuloste,char arvattuKirjain){
    }

}

