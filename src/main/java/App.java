import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws Exception{
        AddMessage addMessage = new AddMessage();
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);

        server.createContext("/guestbook", new NewGuestBook(addMessage));
        server.createContext("/static", new Static());
        server.setExecutor(null);

        server.start();


    }
}
