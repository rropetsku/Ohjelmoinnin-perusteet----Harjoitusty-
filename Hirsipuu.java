import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.InputMismatchException;

/**
 * Sisaltaa pelattavan Hirsipuu-pelin 
 */
public class Hirsipuu {
	/**
	 * Sisaltaa pelaajan syotteen pyytamiseen tarvittavat metodit
	 */
	private static final Scanner lukija = new Scanner(System.in);
	
	/**
	 * Hirsipuu-luokan main-metodi aloittaa hirsipuun pelaamisen kutsumalla paaValikko-metodia
	 */
	public static void main(String args[]){
    	paaValikko();
    	
    }
	/**
	 * Antaa pelaajan valita pelaako han yksin vai konetta vastaan, lukeeko han kayttoohjeet vai poistuuko han pelista
	 * @exception InputMismatchException Jos annettu syote ei ole kokonaisluku
	 * @author jenny
	 */
	public static void paaValikko() throws InputMismatchException{
		tyhjennaNaytto();
    	int valinta = 0;
    	System.out.println("Tervetuloa!");
    	System.out.println("Valitse vaihtoehto:"
    				+ " 1) Yksinpeli "
    				+ "2) Pelaaja vs Kone "
    				+ "3) Ohjeet "
    				+ "4) Lopeta");
		do{
    		valinta = lukija.nextInt();
		}while(valinta > 4 || valinta < 1);
		if (valinta == 1) {
    		yksinPeli();
    	}
		else if ( valinta == 2) {
    		pelaajaVsKone();
    	}
		else if (valinta == 3) {
			int valmis;
    		//ohjeetLuku = tekstitiedosto jossa ohjeet
    		try {
    			File tiedosto = new File("ohjeetLuku.txt");
    			Scanner scan = new Scanner(tiedosto);
    				//tulosta ohjeet
    				while(scan.hasNextLine()) {
    					System.out.println(scan.nextLine());
    		}
    		//pyytaa lukua 1 kun ohjeet luettu, 
    		//jolla paasee takaisin paavalikkoon
			do{
				valmis = lukija.nextInt();
    			if (valmis == 1) {
					tyhjennaNaytto();
    				paaValikko();
    			}
			}while(valmis != 1);
    		
    		}catch (Exception a) {
    			System.out.println("Virhe havaittu poistutaan paavalikkoon");
    			paaValikko();
    		
    		}
    	} 
		else if (valinta == 4) {
    		System.out.println("Nakemiin");
    	}
    }
	/**
	 * Suorittaa hirsipuun pelaamisen yksin
	 * @author nanna
	 * @exception InputMismatchException Jos pelaajan syote ei ole oikean tyyppinen
	 */
	public static void yksinPeli() throws InputMismatchException{
    	String sana = arvattavaSana();
    	String sanaTuloste = "";
    	int kierrosLaskuri = 0;
    	int hirsipuunTila = 0;
    	for (int i=0; i<sana.length(); i++) {
    			sanaTuloste += "*";
    		}
    	while(hirsipuunTila <= 9) {
			tyhjennaNaytto();
    		hirsipuunTulostus(hirsipuunTila);
    		kierrosLaskuri += 1;
    		System.out.println(sanaTuloste);
    		System.out.println("Arvaa kirjain: ");
    		char arvattuKirjain = lukija.next().charAt(0);
			int valikkoon = 0;
    		sanaTuloste = paljastaKirjaimet(sana, sanaTuloste, arvattuKirjain);
    		if (onkoKirjainSanassa(sana, arvattuKirjain) == false) {
    			hirsipuunTila+=1;
        		if (hirsipuunTila == 9) {
					tyhjennaNaytto();
					hirsipuunTulostus(hirsipuunTila);
					System.out.println("Havisit!");
					do{
						System.out.println("Syota 1 palataksesi paavalikkoon");
						valikkoon = lukija.nextInt();
					}while(valikkoon != 1);
					if(valikkoon == 1){
						tyhjennaNaytto();
						paaValikko();
					}
					break;
        			}
    		}
    		if (onkoKirjainSanassa(sana, arvattuKirjain) == true) {
				System.out.println(sanaTuloste);
    			System.out.println("Oikein, haluatko arvata sanaa? 1) Kylla 2) En");
    			int arvaatko;
				String sanaArvaus = "";
    			do {
    				arvaatko = lukija.nextInt();
    				if (arvaatko == 1) {
    					System.out.println("Kirjoita sana: ");
						lukija.nextLine();
    					sanaArvaus = lukija.nextLine();
   						if (sanaArvaus.equals(sana)) {
    						System.out.println("Voitit!");
    						try {
    							PrintWriter kirjoittaja = new PrintWriter("tulosTiedosto.txt");
    							kirjoittaja.println();
    						} catch (Exception e) {
    							System.out.println("Tuloksia ei voida tallentaa");
    							paaValikko();
								break;
    							}
							do{
								System.out.println("Syota 1 palataksesi paavalikkoon");
								valikkoon = lukija.nextInt();
							}while(valikkoon != 1);
							if(valikkoon == 1){
								tyhjennaNaytto();
							paaValikko();
							}
							break;
    						}
							
    					}
    					if (arvaatko == 2) {
    						break;
    					}
    				} while (arvaatko != 1  && arvaatko != 2);
					if (sanaArvaus.equals(sana)) {
								break;
					}
    			
    			}

			tyhjennaNaytto();
    	}

    }
	/**
	 * Suorittaa pelaajan ja koneen valisen hirsipuun pelaamisen.
	 * @author roope 
	 * @exception InputMismatchException Jos pelaajan syote ei ole oikean tyyppinen
	 */
	public static void pelaajaVsKone(){
		String sana = arvattavaSana();
    	String sanaTuloste = "";
    	int kierrosLaskuri = 0;
    	int hirsipuunTila = 0;
		boolean kaytArvasi = false;
    	for (int i=0; i<sana.length(); i++) {
    			sanaTuloste += "*";
    		}
    	while(hirsipuunTila <= 9) {
			tyhjennaNaytto();
    		hirsipuunTulostus(hirsipuunTila);
    		kierrosLaskuri += 1;
    		System.out.println(sanaTuloste);
    		System.out.println("Arvaa kirjain: ");
    		char arvattuKirjain =Character.toLowerCase(lukija.next().charAt(0));
			int valikkoon = 0;
			sanaTuloste = paljastaKirjaimet(sana, sanaTuloste, arvattuKirjain);
			if (onkoKirjainSanassa(sana, arvattuKirjain) == false) {
				hirsipuunTila+=1;
    			if (hirsipuunTila == 9) {
					tyhjennaNaytto();
					hirsipuunTulostus(hirsipuunTila);
					System.out.println("Havisit!");
					do{
						System.out.println("Syota 1 antaaksesi koneelle vuoron: ");
						valikkoon = lukija.nextInt();
					}while(valikkoon != 1);
					if(valikkoon == 1){
						tyhjennaNaytto();
					}
					break;
    			}
			}
			if (onkoKirjainSanassa(sana, arvattuKirjain) == true) {
				System.out.println(sanaTuloste);
				System.out.println("Oikein, haluatko arvata sanaa? 1) Kylla 2) En");
				int arvaatko;
				String sanaArvaus = "";
				do {
					arvaatko = lukija.nextInt();
					if (arvaatko == 1) {
						System.out.println("Kirjoita sana: ");
						lukija.nextLine();
						sanaArvaus = lukija.nextLine().toLowerCase();
						if (sanaArvaus.equals(sana)) {
							System.out.println("Arvasit!");
							kaytArvasi = true;
							do{
								System.out.println("Syota 1 antaaksesi koneelle vuoron");
								valikkoon = lukija.nextInt();
							}while(valikkoon != 1);
							if(valikkoon == 1){
								tyhjennaNaytto();
							}
							break;
						}
						
					}
					if (arvaatko == 2) {
						break;
					}
				} while (arvaatko != 1  && arvaatko != 2);
				if (sanaArvaus.equals(sana)) {
							break;
				}
			}
			tyhjennaNaytto();
    	}

		sanaTuloste = "";
		for (int i=0; i<sana.length(); i++) {
    			sanaTuloste += "*";
    		}
		hirsipuunTila = 0;
		int kierrosLaskuriKone = 0;
		int valinta = 0;
		while(hirsipuunTila <= 9){
			boolean koneArvasi = false;
			tyhjennaNaytto();
			hirsipuunTulostus(hirsipuunTila);
			System.out.println(sanaTuloste);
			kierrosLaskuriKone = kierrosLaskuriKone + 1;
			char koneenArvaamaKirjain = koneArvaaKirjaimen();
			if(onkoKirjainSanassa(sana,koneenArvaamaKirjain)){
				sanaTuloste = paljastaKirjaimet(sana,sanaTuloste,koneenArvaamaKirjain);
			}
			else if(onkoKirjainSanassa(sana,koneenArvaamaKirjain) == false){
				hirsipuunTila = hirsipuunTila + 1;
			}
			System.out.println("Kone arvasi kirjainta "+koneenArvaamaKirjain);
			do{
				System.out.println("Syota 1 jatkaaksesi");
				valinta = lukija.nextInt();
			}while(valinta != 1);
			int arvattaviaJaljella = 0;
			for(int i = 0;i < sanaTuloste.length();i++){
				if(sanaTuloste.charAt(i)=='*'){
					arvattaviaJaljella = arvattaviaJaljella + 1;
				}
			}
			String koneenArvaus = koneArvaaSanaa(sanaTuloste);
			if(arvattaviaJaljella <= 3){
				if(sana.equals(koneenArvaus)){
					valinta = 0;
					System.out.println(sanaTuloste);
					System.out.println("Kone arvasi sanan:"+ sana);
					koneArvasi = true;
					if(koneArvasi && kaytArvasi){
						System.out.println("Tasapeli");
					}
					else if(koneArvasi && !(kaytArvasi)){
						System.out.println("Kone voitti");
					}
					do{
						System.out.println("Syota 1 jatkaaksesi");
						valinta = lukija.nextInt();
					}while(valinta != 1);
					paaValikko();
					break;
				}
			}
			if(hirsipuunTila == 9){
				hirsipuunTulostus(hirsipuunTila);
				System.out.println("Kone ei arvannut sanaa");
				if(!koneArvasi && kaytArvasi){
					if(kierrosLaskuri < kierrosLaskuriKone){
						System.out.println("Pelaaja voitti");
					}
					else if(!koneArvasi && !kaytArvasi){
						System.out.println("Tasapeli");
					}
				}
				valinta = 0;
				do{
					System.out.println("Syota 1 jatkaaksesi");
					valinta = lukija.nextInt();
				}while(valinta != 1);
				paaValikko();
				break;
			}
			
		}
		
		
    }
	/**
	 * Hakee ennalta luodusta tekstitiedostosta sanan, jota kaytetaan hirsipuun arvattavana sanana
	 * @author roope
	 *  
	 */
	public static String arvattavaSana(){
		//lisaa loput sanat ja muuta for-loopin ehto oikeaksi
		String sana = "";
		Scanner lukija = new Scanner("");
		try {
			File Sanatiedosto = new File("sanalista.txt");
			lukija = new Scanner(Sanatiedosto);
		}catch(FileNotFoundException f){
			System.out.println("Sanaa ei pystyta muodostamaan. Palataan paavalikkoon");
		}
		Random rand = new Random();
		int sananIndeksi = rand.nextInt(41);
		for(int i = 0; i != sananIndeksi;i++) {
			if(i == sananIndeksi-1) {
				sana = lukija.nextLine();
			}
			else{
				lukija.nextLine();
			}
		}
		lukija.close();
		return sana;
	}
	/**
	 * Tulostaa ruudun ja hirsipuun ruudun sisaan kayttaen parametrina lukua 0-9. Suurempi luku on valmiimpi hirsipuu
	 * @author roope
	 */
	public static void hirsipuunTulostus(int hirsipuunTila){
		if(hirsipuunTila == 0) {
    		System.out.println("_______________");
    		System.out.println("|             |");
    		System.out.println("|             |");
    		System.out.println("|             |");
    		System.out.println("|             |");
    		System.out.println("|             |");
    		System.out.println("_______________");
    	}
    	if(hirsipuunTila == 1) {
    		System.out.println("_______________");
    		System.out.println("|             |");
    		System.out.println("|             |");
    		System.out.println("|             |");
    		System.out.println("|  _____      |");
    		System.out.println("| |     |     |");
    		System.out.println("_______________");
    	}
    	if(hirsipuunTila == 2) {
    		System.out.println("_______________");
    		System.out.println("|             |");
    		System.out.println("|    |        |");
    		System.out.println("|    |        |");
    		System.out.println("|    |        |");
    		System.out.println("|  __|__      |");
    		System.out.println("| |     |     |");
    		System.out.println("_______________");
    	}
    	if(hirsipuunTila == 3) {
    		System.out.println("_______________");
    		System.out.println("|    ______   |");
    		System.out.println("|    |/       |");
    		System.out.println("|    |        |");
    		System.out.println("|    |        |");
    		System.out.println("|  __|__      |");
    		System.out.println("| |     |     |");
    		System.out.println("_______________");
    	}
    	if(hirsipuunTila == 4) {
    		System.out.println("_______________");
    		System.out.println("|    ______   |");
    		System.out.println("|    |/   |   |");
    		System.out.println("|    |    O   |");
    		System.out.println("|    |        |");
    		System.out.println("|  __|__      |");
    		System.out.println("| |     |     |");
    		System.out.println("_______________");
    	}
    	if(hirsipuunTila == 5) {
    		System.out.println("_______________");
    		System.out.println("|    ______   |");
    		System.out.println("|    |/   |   |");
    		System.out.println("|    |    O   |");
    		System.out.println("|    |    |   |");
    		System.out.println("|  __|__      |");
    		System.out.println("| |     |     |");
    		System.out.println("_______________");
    	}
    	if(hirsipuunTila == 6) {
    		System.out.println("_______________");
    		System.out.println("|    ______   |");
    		System.out.println("|    |/   |   |");
    		System.out.println("|    |    O   |");
    		System.out.println("|    |   /|   |");
    		System.out.println("|  __|__      |");
    		System.out.println("| |     |     |");
    		System.out.println("_______________");
    	}
    	if(hirsipuunTila == 7) {
    		System.out.println("_______________");
    		System.out.println("|    ______   |");
    		System.out.println("|    |/   |   |");
    		System.out.println("|    |    O   |");
    		System.out.println("|    |   /|\\  |");
    		System.out.println("|  __|__      |");
    		System.out.println("| |     |     |");
    		System.out.println("_______________");
    	}
    	if(hirsipuunTila == 8) {
    		System.out.println("_______________");
    		System.out.println("|    ______   |");
    		System.out.println("|    |/   |   |");
    		System.out.println("|    |    O   |");
    		System.out.println("|    |   /|\\  |");
    		System.out.println("|  __|__ /    |");
    		System.out.println("| |     |     |");
    		System.out.println("_______________");
    	}
    	if(hirsipuunTila == 9) {
    		System.out.println("_______________");
    		System.out.println("|    ______   |");
    		System.out.println("|    |/   |   |");
    		System.out.println("|    |    O   |");
    		System.out.println("|    |   /|\\  |");
    		System.out.println("|  __|__ / \\  |");
    		System.out.println("| |     |     |");
    		System.out.println("_______________");
    	}
    }
	/**
	 * Palauttaa true, jos toisena parametrina annettu kirjain loytyy ensimmaisen parametrin sanasta
	 * @author jenny
	 */
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
	/**
	 * Ottaa parametreina sanan
	 */
	public static String paljastaKirjaimet(String sana,String sanaTuloste,char arvattuKirjain){
		String palautettavaSana = "";
		for(int i = 0;i < sana.length();i++) {
			if(Character.toLowerCase(arvattuKirjain) == sana.charAt(i)) {
				palautettavaSana = palautettavaSana + arvattuKirjain;
			}
			else {
				palautettavaSana = palautettavaSana + sanaTuloste.charAt(i);
			}
		}
		return palautettavaSana;
    }
	public static void tyhjennaNaytto(){
		if(System.getProperty("os.name").contains("Windows")){
				try{
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
				}catch(Exception e){}
			}
			System.out.println("\033[H\033[2J");
	}
	public static char koneArvaaKirjaimen(){
		char arvattuKirjain;
		Random randomLuku = new Random();
		char [] kirjainLista = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','y','v','x','y','z'};
		boolean joArvattu = true;
		int randomIndeksi = randomLuku.nextInt(kirjainLista.length);
		arvattuKirjain = kirjainLista[randomIndeksi];
		return arvattuKirjain;
	}
	public static String koneArvaaSanaa(String sanaTuloste){
		String arvattavaSana = "";
		String kaava = "";
		Random randomi = new Random();
		for(int i = 0;i<sanaTuloste.length();i++){
			if(sanaTuloste.charAt(i)!='*'){
				kaava = kaava + sanaTuloste.charAt(i);
			}
			else{
				kaava = kaava + '.';
			}
		}
		String [] mahdollisetSanat = new String[41];
		Pattern etsittava = Pattern.compile(kaava);
		Matcher etsija;
		int seuraavaIndeksi = 0;
		Scanner lukija = new Scanner("");
		try {
			File Sanatiedosto = new File("sanalista.txt");
			lukija = new Scanner(Sanatiedosto);
		}catch(FileNotFoundException f){
			System.out.println("Sanatiedostoa ei loydy.");
			paaValikko();
		}
		while(lukija.hasNext()){
			String sanaListasta = lukija.next();
			etsija = etsittava.matcher(sanaListasta);
			if(etsija.matches()){
				mahdollisetSanat[seuraavaIndeksi]=sanaListasta;
				seuraavaIndeksi = seuraavaIndeksi + 1;
			}
		}
		int randomNumero = randomi.nextInt(seuraavaIndeksi + 1);
		arvattavaSana = mahdollisetSanat[randomNumero];
		lukija.close();
		return arvattavaSana;
	}
	
	

}

