import com.sun.net.httpserver.HttpServer;

import javax.xml.ws.spi.http.HttpHandler;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws Exception{

        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);

//        server.createContext("/guestBook", new GuestBook());
        server.createContext("/static", new Static());
        server.createContext("/form", new Form());
        server.createContext("/temp", new GuestBook());
        server.createContext("/new", new NewGuestBook());

        server.setExecutor(null);

        server.start();


    }
}
