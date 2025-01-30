import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestApiClient {
    public static void main(String[] args) {
        String imageUrl = "https://example.com/image.jpg"; // Replace with actual API URL
        String outputFileName = "output.jpg";
        
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = connection.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(outputFileName)) {
                    
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    System.out.println("Image downloaded successfully: " + outputFileName);
                }
            } else {
                System.out.println("Failed to download image. HTTP Response Code: " + connection.getResponseCode());
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
