import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hirsipuu {
	private static final Scanner lukija = new Scanner(System.in);
	
    //Kaynnistaa paaValikko-metodin
    public static void main(String args[]){
    	paaValikko();
    	
    }
    //Antaa kayttajan valita yksinPeli- ja pelaajaVsKone-metodin, ohjeiden ja lopetuksen valilla
    public static void paaValikko(){
		tyhjennaNaytto();
    	int valinta = 0;
    	try {
    		System.out.println("Tervetuloa!");
    		System.out.println("Valitse vaihtoehto:"
    				+ " 1) Yksinpeli "
    				+ "2) Pelaaja vs Kone "
    				+ "3) Ohjeet "
    				+ "4) Lopeta");
			do{
    			valinta = lukija.nextInt();
			}while(valinta > 4 || valinta < 1);
    	} catch (Exception e) {
    		System.out.println("Tarkista etta syotteesi on 1,2,3 tai 4");
    		paaValikko();
  
    	}
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
     * Pelaaja pelaa yksin hirsipuuta
     * 
     * @return void - ei mitään
     */
    public static void yksinPeli(){
    	String sana = arvattavaSana(); //maaritetaan kasiteltavaksi sanaksi metodin arvattavaSana() palauttama sana
    	String sanaTuloste = "";
    	int kierrosLaskuri = 0;
    	int hirsipuunTila = 0;
    	for (int i=0; i<sana.length(); i++) { //piilotetaan sana korvaamalla sen jokainen kirjain *-merkilla
    			sanaTuloste += "*";
    		}
    	while(hirsipuunTila <= 9) { //pelin rakenne, mita toistetaan kunnes hirsipuu on rakennettu loppuun
			tyhjennaNaytto();
    		hirsipuunTulostus(hirsipuunTila); //tulostetaan hirsipuun rakenne
    		kierrosLaskuri += 1; //lisataan kierroksen lukumaaraa
    		System.out.println(sanaTuloste); //tulostetaan *-merkeilla korvattu sana
    		System.out.println("Arvaa kirjain: ");
    		char arvattuKirjain = lukija.next().charAt(0); //maaritetaan arvattuKirjain vastaamaan kayttajan syottamaa ensimmaista kirjainta
			int valikkoon = 0;
    		try {
    			sanaTuloste = paljastaKirjaimet(sana, sanaTuloste, arvattuKirjain);
    			if (onkoKirjainSanassa(sana, arvattuKirjain) == false) { //jos kirjainta ei loydy lisataan kierrosLaskuriin 1 lisaa ja tarkastetaan hirsipuun tila
    				System.out.println("Kirjainta ei loydy.");
    				hirsipuunTila+=1;
        			if (hirsipuunTila == 9) { //Hirsipuun tullessa valmiiksi tulostetaan "Havisit"
						tyhjennaNaytto();
						hirsipuunTulostus(hirsipuunTila);
						System.out.println("Havisit!");
						do{ //pyydetaan kayttajalta 1 palatakseen paavalikkoon
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
    			if (onkoKirjainSanassa(sana, arvattuKirjain) == true) { //kirjain loytyy sanasta
					System.out.println(sanaTuloste); //tulostetaan *-merkeilla korvattu sana paljastaen oikein arvattu kirjain
    				System.out.println("Oikein, haluatko arvata sanaa? 1) Kylla 2) En"); //kysytaan haluaako kayttaja arvata sanan
    				int arvaatko;
					String sanaArvaus = "";
    				try {
    				do { //kysytaan kayttajalta 1 tai 2 sanan arvaukseen, niin kauan, etta jompikumpi syotetaan
    					arvaatko = lukija.nextInt(); 
    					if (arvaatko == 1) { //kayttaja haluaa arvata sanan
    						System.out.println("Kirjoita sana: "); //kysytaan kayttajalta sana
							lukija.nextLine();
    						sanaArvaus = lukija.nextLine(); //sanaArvaus on kayttajan syottama sana
    						if (sanaArvaus.equals(sana)) { //sanat tasmaa, tulostetaan "Voitit"
    							System.out.println("Voitit!"); 
    								kirjoitaTulokset(sana, kierrosLaskuri); //kutsutaan kirjoitaTulokset-metodia, joka kirjoittaa tiedostoon tulokset
								do{
									System.out.println("Syota 1 palataksesi paavalikkoon"); //pyydetaan kayttajalta 1 palatakseen paavalikkoon
									valikkoon = lukija.nextInt();
								}while(valikkoon != 1); //pyydetaan lukua niin kauan, etta se on 1
								if(valikkoon == 1){
									tyhjennaNaytto();
									paaValikko();
								}
								break;
    						}
    					}
    					if (arvaatko == 2) { //kayttaja ei halua arvata sanaa, joten jatketaan eteenpain
    						break;
    					}
    					if (sanaTuloste.equals(sana)) {
    						break;
    					}
    				} while (arvaatko != 1  && arvaatko != 2);
    				} catch (Exception e) { //napataan virhe, mika tapahtuu sanan arvauksessa
    					System.out.println("Syotteessa havaittu virhe");
    					paaValikko();
    				}
    			}
    		} catch (Exception e) { //napataan virhe, mika tapahtuu kirjaimen arvauksessa
    			System.out.println("Virhe, palataan paavalikkoon.");
    			paaValikko();
    		}
			tyhjennaNaytto();
    	}

    }//yksinPeli
    
    //Pelaaja ja kone arvaavat vuorotellen
    public static void pelaajaVsKone(){
    }
    //Hakee tiedostosta arvattavan sanan ja palauttaa sen
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
    //Tulostaa annetun luvun mukaan hirsipuun tilan
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
    //Vaihtaa sanaTulosteeseen arvattuKirjain-arvon indekseihin, jossa sana-merkkijonossa loytyy kyseinen merkki 
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
	
	/**
	 * Kirjoittaa yksinPelin paattyessa tiedostoon arvatun sanan ja kierrosmäärän
	 * 
	 * @param sana
	 * @param kierrosLaskuri
	 * @return void - ei mitään
	 */
	public static void kirjoitaTulokset(String sana, int kierrosLaskuri) {
		try {
			PrintWriter kirjoittaja = new PrintWriter("tulosTiedosto.txt"); //luodaan kirjoittaja, mika kirjoittaa tiedostoon "tulosTiedosto.txt"
			kirjoittaja.println("Oikein arvattu sana: "+sana+" kierroksella: "+kierrosLaskuri); //maaritetaan tiedostoon kirjoitettava teksti
			kirjoittaja.close();
		} catch (Exception e) { //virheen sattuessa napataan se kiinni
			System.out.println("Tuloksia ei voida tallentaa"+ e); //tulostus virheen sattuessa
			paaValikko(); //kutsutaan paaValikko
		}
	}//kirjoitaTulokset
	
	

}

