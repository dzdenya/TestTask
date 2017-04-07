package server;
import bank.Bank;

import java.lang.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import static javax.print.attribute.standard.MediaSizeName.D;


public class ClientHandler extends Thread {
    Socket socket;
    Main main;
    private List<Bank> banks;

    private char pathSeparator,
            extensionSeparator;



    public ClientHandler(Socket socket, Main main) {
        this.socket = socket;
        this.main = main;
    }

    @Override
    public void run() {
        try (OutputStream outputStream = socket.getOutputStream();
             PrintWriter out = new PrintWriter(outputStream, true);
             InputStream inputStream = socket.getInputStream();
             Scanner scanner = new Scanner(inputStream)){
            out.println("Введите команду:");
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                System.out.println(line);
                if ("list".equals(line)) {
                    String strList = main.getList();
                    out.println(strList);
                }
                if ("sum".equals(line)){
                    String sSum = main.getSum();
                    out.println(sSum);
                }
                if ("count".equals(line)){
                    String sCount = main.getCount();
                    out.println(sCount);
                }
                if (line.startsWith("info account ")){
                    int last = line.length()-1;
                    String worldInfoAccount = line.substring(14, last);
                    System.out.println("Ищем по:"+worldInfoAccount);
                    String sInfoAccount = main.getInfoAccount(worldInfoAccount);
                    out.println(sInfoAccount);
                    }
                if (line.startsWith("info depositor ")){
                    int last = line.length()-1;
                    String worldDepositor = line.substring(16, last);
                    System.out.println("Ищем по:"+worldDepositor);
                    String sDepositor = main.getinfoDepositor(worldDepositor);
                    out.println(sDepositor);
                }
                if (line.startsWith("show type ")){
                    int last = line.length()-1;
                    String worldType = line.substring(11, last);
                    String sType = main.getshowType(worldType);
                    out.println(sType);
                }
                if (line.startsWith("show bank ")) {
                    int last = line.length()-1;
                    String worldBank = line.substring(11, last);
                    String sBank = main.getshowBank(worldBank);
                    out.println(sBank);
                }
                if (line.startsWith("add ")) {
                    if (main.addBank(line)) {
                        out.println("ok");
                    } else {
                        out.println("Введите в формате:add Name_Bank Country Type "
                                + "Depositor Account_id Amount_on_deposit Profitabilyty Time_constraints");
                    }
                }
                if (line.startsWith("delete ")) {
                    if (main.deleteBank(line)) {
                        out.println("Удалено: " + line);
                    } else {
                        out.println("Введите в формате:delete <accounr id>");
                    }
                }
                if ("bye".equals(line)) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
