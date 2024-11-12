import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import java.net.http.HttpClient;

public class ViaCepService {
    public Endereco buscarEnderecoPorCep(String cep) throws Exception{
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null){
            response.append(line);
        }
        reader.close();

        return new Gson().fromJson(response.toString(), Endereco.class);
    }
}
