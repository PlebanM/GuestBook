import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.BookEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AddMessage implements HttpHandler {

    private List<BookEntry> bookEntries;

    public AddMessage() {
        this.bookEntries = new ArrayList<>();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        bookEntries.add(getDataFromForm(httpExchange));
        redirectToResourcePage(httpExchange);

    }

    public List<BookEntry> getBookEntries() {
        return bookEntries;
    }

    private void redirectToResourcePage(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().set("Location", "/guestbook");
        httpExchange.sendResponseHeaders(303,0);


    }

    private BookEntry getDataFromForm(HttpExchange exchange) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        String userCredentials = br.readLine();
        String[] entries = userCredentials.split("&");
        String message = URLDecoder.decode(entries[0].split("=")[1].trim(), StandardCharsets.ISO_8859_1.toString());
        String author = URLDecoder.decode(entries[1].split("=")[1].trim(), StandardCharsets.ISO_8859_1.toString());
        return new BookEntry(message, author);

    }
}
