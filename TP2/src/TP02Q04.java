
/*import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TP02Q04 {
	
	private static Date convertDate(String string) throws ParseException {
		if (string.length() == 4)
			string += "-01-01";
		else if (string.length() == 7)
			string += "-01";
		return new SimpleDateFormat("yyyy-MM-dd").parse(string);
	}

	public static void main(String args[]) throws IOException, ParseException{
		MyIO.setCharset("UTF-8");
		List<Musica> musicas = new ArrayList<>();
		ArrayList<Musica> subConjMusicas = new ArrayList<>();
		FileInputStream stream = new FileInputStream("/tmp/dataAEDs.csv");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		
		
		br.readLine();
		String linha= br.readLine();

		while(linha!= null) {

			boolean entrei = false;
			String linha2="";
			boolean tratoArtista= false;

			for(int i = 0 ; i < linha.length(); i++) {
				char elemento = linha.charAt(i);
				char elementoAnterior = i > 0 ? linha.charAt(i-1): ' ';
				char elementoPosterior = i < linha.length()-1 ? linha.charAt(i+1): ' ';
				if (elemento == '[' && tratoArtista==false ) {
					entrei= true;
				}else if(elemento == ']' && tratoArtista == false){
					entrei= false;
					tratoArtista = true;
				} else if (elemento == '[' && elementoAnterior == '"' && tratoArtista==true ) {
					entrei= true;				
				} else if (elemento == ']' && elementoPosterior == '"' && tratoArtista==true ) {
					entrei= false;				
				} else if(elemento == ',' && entrei==true) {
					elemento= ';';
				}
				
				linha2+=elemento;
			}
			// ler areuivo encontrar vigula e criar o vetor com ela

			String array[] = linha2.split(",");
			Musica musica = new Musica(
					Double.parseDouble(array[0]),
					Integer.parseInt(array[1]),
					Double.parseDouble(array[2]),
					array[3].replace("[", "").replace("]", "").split(";"),
					Double.parseDouble(array[4]),
					Integer.parseInt(array[5]),
					Double.parseDouble(array[6]),
					array[8],
					Double.parseDouble(array[9]),
					array[10],
					Double.parseDouble(array[11]),
					Double.parseDouble(array[12]),
					array[14].substring(2, array[14].length()-2),
					Integer.parseInt(array[15]),
					convertDate(array[16]),
					Double.parseDouble(array[17]),
					Double.parseDouble(array[18])
			);
			musicas.add(musica);

			linha= br.readLine();
		}
	
		String entrada = MyIO.readLine();
		
		while (!entrada.equals("FIM")) {
			for (int i = 0; i < musicas.size(); i++) {
		        if (musicas.get(i).getId().equals(entrada)) {
		          subConjMusicas.add(musicas.get(i));
		          break;
		        }
		      }
			entrada = MyIO.readLine();
		}
		
		HeapSort ob = new HeapSort();
        ob.heapSort(subConjMusicas);
		
        for (int i = 0; i < subConjMusicas.size(); i++) {
            System.out.println(subConjMusicas.get(i).toString());
          }
        
    }
	
}
class HeapSort {
	public void heapSort(ArrayList<Musica> subConjMusicas) {
	    int n = subConjMusicas.size();
	    ArrayList<Musica> temp = new ArrayList<Musica>();
	    temp.add(subConjMusicas.get(0));

	  
	    for (int i = n / 2 - 1; i >= 0; i--) heapify(subConjMusicas, n, i);

	  
	    for (int i = n - 1; i > 0; i--) {
	     
	      temp.set(0, subConjMusicas.get(0));
	      subConjMusicas.set(0, subConjMusicas.get(i));
	      subConjMusicas.set(i, temp.get(0));

	    
	      heapify(subConjMusicas, i, 0);
	    }
	  }
	  public static void heapify(ArrayList<Musica> subConjMusicas, int n, int i) {
		    ArrayList<Musica> swap = new ArrayList<Musica>();
		    swap.add(subConjMusicas.get(0));

		    int largest = i; 
		    int l = 2 * i + 1; 
		    int r = 2 * i + 2; 

		   
		    if (l < n && subConjMusicas.get(l).getRelease_date().compareTo(subConjMusicas.get(largest).getRelease_date()) > 0)
		      largest = l;

		   
		    if (r < n && subConjMusicas.get(r).getRelease_date().compareTo(subConjMusicas.get(largest).getRelease_date()) > 0)
		      largest = r;

		   
		    if (largest != i) {
		      swap.set(0, subConjMusicas.get(i));
		      subConjMusicas.set(i, subConjMusicas.get(largest));
		      subConjMusicas.set(largest, swap.get(0));

		     
		      heapify(subConjMusicas, n, largest);
		    }
		  }
    }

class Musica {
	 private String id;
	 private String name;
	 private   String key;
	 private   String artists[];
	 private   Date release_date;
	 private  double acousticness;
	 private  double danceability;
	 private  double energy;
	 private  int duration_ms;
	 private  double instrumentalness;
	 private  double valence;
	 private int popularity;
	 private  double time;
	 private  double liveness;
	 private  double loudness;
	 private  double speechiness;
	 private  int year;
	 
	public Musica(
			double valence,
			int year,
			double acousticness,
			String[] artists,
			double danceability,
			int duration_ms,
			double energy,
			String id,
			double instrumentalness,
			String key,
			double liveness,
			double loudness,
			String name,
			int popularity,
			Date release_date,
			double speechiness, 
			double time
	) 
	{
		super();
		this.id = id;
		this.name = name;
		this.setKey(key);
		this.artists = artists;
		this.release_date = release_date;
		this.acousticness = acousticness;
		this.danceability = danceability;
		this.energy = energy;
		this.duration_ms = duration_ms;
		this.instrumentalness = instrumentalness;
		this.setValence(valence);
		this.setPopularity(popularity);
		this.setTime(time);
		this.liveness = liveness;
		this.loudness = loudness;
		this.speechiness = speechiness;
		this.setYear(year);
	}

	public String getId() {
       return id;
   }
	  // Gets
	 
	  public String getNome() {
	    return this.name;
	  }

	  public String getKey() {
	    return this.key;
	  }

	  public String[] getArtists() {
	    return this.artists;
	  }

	  public double getDanceability() {
	    return this.danceability;
	  }

	  public double getAcousticness() {
	    return this.acousticness;
	  }

	  public double getEnergy() {
	    return this.energy;
	  }

	  public double getInstrumentalness() {
	    return this.instrumentalness;
	  }

	  public double getValence() {
	    return this.valence;
	  }

	  public double getLiveness() {
	    return this.liveness;
	  }

	  public double getLoudness() {
	    return this.loudness;
	  }

	  public double getSpeechiness() {
	    return this.speechiness;
	  }

	  public double getTime() {
	    return this.time;
	  }

	  public int getDuration_ms() {
	    return this.duration_ms;
	  }

	  public int getPopularity() {
	    return this.popularity;
	  }

	  public int getYear() {
	    return this.year;
	  }

	  public Date getRelease_date() {
	    return this.release_date;
	  }

	  // Sets
	  public void setId(String id) {
	    this.id = id;
	  }

	  public void setNome(String nome) {
	    this.name = nome;
	  }

	  public void setKey(String key) {
	    this.key = key;
	  }

	  public void setArtists(String[] artists) {
	    this.artists = artists;
	  }

	  public void setDanceability(Double danceability) {
	    this.danceability = danceability;
	  }

	  public void setAcousticness(Double acousticness) {
	    this.acousticness = acousticness;
	  }

	  public void setEnergy(Double energy) {
	    this.energy = energy;
	  }

	  public void setInstrumentalness(Double instrumentalness) {
	    this.instrumentalness = instrumentalness;
	  }

	  public void setValence(Double valence) {
	    this.valence = valence;
	  }

	  public void setLiveness(Double liveness) {
	    this.liveness = liveness;
	  }

	  public void setLoudness(Double loudness) {
	    this.loudness = loudness;
	  }

	  public void setSpeechiness(Double speechiness) {
	    this.speechiness = speechiness;
	  }

	  public void setTime(Double tempo) {
	    this.time = tempo;
	  }

	  public void setDuration_ms(int duration_ms) {
	    this.duration_ms = duration_ms;
	  }

	  public void setPopularity(int popularity) {
	    this.popularity = popularity;
	  }

	  public void setYear(int year) {
	    this.year = year;
	  }

	  public void setRelease_date(Date release_date) {
	    this.release_date = release_date;
	  }

	  


	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return id + " ## " +
		Arrays.toString(artists).replaceAll("'", "").replaceAll(",\\s", ",") + " ## " +
		name.replaceAll(";", ",") + " ## " +
		sdf.format(release_date) + " ## " +
		acousticness + " ## " +
		danceability + " ## " +
		instrumentalness + " ## " +
		liveness + " ## " +
		loudness + " ## " +
		speechiness + " ## " +
		energy + " ## " +
		duration_ms;
	}
	
	
}*/
