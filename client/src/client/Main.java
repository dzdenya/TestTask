package client;
import java.lang.*;
import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("props.ini"));
            String host = properties.getProperty("host");
            int port = Integer.parseInt(properties.getProperty("port"));
            Socket s = new Socket(host,port);
            String line;
            try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
                 PrintWriter serverWriter = new PrintWriter(s.getOutputStream(), true);
                 BufferedReader serverReader = new BufferedReader(new InputStreamReader(s.getInputStream()));) {
                while (true) {
                    String[] split = serverReader.readLine().split("~");
                    printLines(split);
                    System.out.print(">");
                    line = inputReader.readLine();
                    serverWriter.println(line);
                    if ("bye".equals(line)) break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printLines(String[] split) {
        for (String s : split) {
            System.out.println(s);
        }
    }
}
