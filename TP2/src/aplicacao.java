import java.text.ParseException;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.Date;

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
	) {
		super();
		this.id = id;
		this.name = name;
		this.key = key;
		this.artists = artists;
		this.release_date = release_date;
		this.acousticness = acousticness;
		this.danceability = danceability;
		this.energy = energy;
		this.duration_ms = duration_ms;
		this.instrumentalness = instrumentalness;
		this.valence = valence;
		this.popularity = popularity;
		this.time = time;
		this.liveness = liveness;
		this.loudness = loudness;
		this.speechiness = speechiness;
		this.year = year;
	}	
	
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id + " ## " +
				Arrays.toString(artists).replaceAll("'", "") + " ## " +
				name + " ## " +
				new SimpleDateFormat("dd/MM/yyyy").format(release_date) + " ## " +
				acousticness + " ## " +
				danceability + " ## " +
				instrumentalness + " ## " +
				liveness + " ## " +
				loudness + " ## " +
				speechiness + " ## " +
				energy + " ## " +
				duration_ms;
	}
}

public class aplicacao {

	public static void main(String[] args) throws IOException, ParseException {
		List<Musica> musicas = new ArrayList<>();
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
			for(Musica musica : musicas) {
				if(musica.getId().equals(entrada)) {
					MyIO.println(musica.toString());
					break;
				}
			}
			entrada = MyIO.readLine();
		}

	}

	private static Date convertDate(String string) throws ParseException {
		if (string.length() == 4)
			string += "-01-01";
		else if (string.length() == 7)
			string += "-01";
		return new SimpleDateFormat("yyyy-MM-dd").parse(string);
	}
}