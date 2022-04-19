import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Hirsipuu {
	public static final Scanner lukija = new Scanner(System.in);
	
    //K√§ynnist√§√§ paaValikko-metodin
    public static void main(String args[]){
    	paaValikko();
    	
    }
    //Antaa k√§ytt√§j√§n valita yksinPeli- ja pelaajaVsKone-metodin, ohjeiden ja lopetuksen v√§lill√§
    public static void paaValikko(){
    	int valinta = 0;
    	try {
    		System.out.println("Tervetuloa!");
    		System.out.println("Valitse vaihtoehto:"
    				+ " 1) Yksinpeli "
    				+ "2) Pelaaja vs Kone "
    				+ "3) Ohjeet "
    				+ "4) Lopeta");
    		valinta = lukija.nextInt();
    		if (valinta == (1 | 2 | 3 | 4)) {
    			valinta = lukija.nextInt();
    		}
    	} catch (Exception e) {
    		System.out.println("Tarkista ett‰ syˆtteesi on 1,2,3 tai 4");
    		paaValikko();
  
    	}if (valinta == 1) {
    		yksinPeli();
    		}if ( valinta == 2) {
    			pelaajaVsKone();
    			}if (valinta == 3) {
    				int valmis;
    		//ohjeetLuku = tekstitiedosto jossa ohjeet
    	try {
    		File tiedosto = new File("ohjeetLuku.txt");
    		Scanner scan = new Scanner(tiedosto);
    			//tulosta ohjeet
    			while(scan.hasNextLine()) {
    				System.out.println(scan.nextLine());
    		}
    		//pyyt‰‰ lukua 1 kun ohjeet luettu, 
    		//jolla p‰‰see takaisin p‰‰valikkoon
    		valmis = lukija.nextInt();
    			if (valmis == 1) {
    				paaValikko();
    			}
    	}catch (Exception a) {
    		System.out.println("Virhe havaittu poistutaan p‰‰valikkoon");
    		paaValikko();
    		
    	}
    	} if (valinta == 4) {
    		System.out.println("N‰kemiin");
    	}
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
    								PrintWriter kirjoittaja = new PrintWriter("tulosTiedosto.txt");
//Mik‰ on tiedostoon kirjoitettava tulosrivi? Kierroslaskuri? Sana?
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
    	boolean loytyy = false;
    	//toistetaan sana-muuttujan pituuden verran
    	for (int i = 0; i < sana.length(); i++) {
    		if(sana.charAt(i) == arvattuKirjain) {
    			loytyy = true;
    		}
    	}
    	return loytyy;
    }
    //Vaihtaa sanaTulosteeseen arvattuKirjain-arvon indekseihin, jossa sana-merkkijonossa l√∂ytyy kyseinen merkki 
    public static paljastaKirjaimet(String sana,String sanaTuloste,char arvattuKirjain){
    }

}

