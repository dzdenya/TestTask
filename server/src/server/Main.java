package server;

import bank.Bank;
import java.lang.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;


public class Main {

    private List<Bank> banks;
    Properties props = new Properties();
    private String line;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            props.load(new FileReader("server.ini"));
            ServerSocket ss = new ServerSocket(1234);
            banks = readInfo(props.getProperty("filename"));
            while(true) {
                Socket socket = ss.accept();
                ClientHandler clientHandler = new ClientHandler(socket, this);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Bank> readInfo(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Bank>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public String getList() {
        StringBuilder sb = new StringBuilder("<Список вкладов>~");
        for (Bank bank : banks) {
            sb.append(bank.toString()).append("~");
        }
        return sb.toString();
    }

    public String getSum() {
        StringBuilder sb1 = new StringBuilder("<Общая сумма вкладов>~");
        double sum = 0;
        for (Bank bank : banks) {
            sum += bank.getAmountOnDeposit();
        }
        sb1.append("Общая сумма вкладов: ").append(sum).append("~");
        return sb1.toString();
    }

    public String getCount() {
        StringBuilder sb2 = new StringBuilder("<Количество вкладов>~");
        sb2.append("Количество вкладов: ").append(banks.size());
        return sb2.toString();
    }

    public String getInfoAccount(String line) {
//        int last = line.length()-1;
//        String worldInfoAccount = null;
//        int chislo = Integer.parseInt(worldInfoAccount);
        List<Bank> result = new ArrayList<>();
        StringBuilder sb3 = new StringBuilder("<Информация указаного вклада>~");
        for (Bank bank : banks) {
//            if (bank.getAccountId().equals(line));
                result.add(bank);
            }

        return result.toString();
        }


    public String getinfoDepositor (String line) {
        List<Bank> result = new ArrayList<>();
        StringBuilder sb3 = new StringBuilder("<Список всех вкладчиков указаного типа>~");
        for (Bank bank : banks) {
            if (bank.getDepositor().equals(line)) {
                result.add(bank);
                return result.toString();
            } else return String.valueOf("По запросу ничего не найдено. " +
                    "Введите в формате:info depositor <depositor>");
        }
        return result.toString();
    }

    public String getshowType(String line) {
        List<Bank> result = new ArrayList<>();
        StringBuilder sb3 = new StringBuilder("<Список всех банков указаного типа>~");
        for (Bank bank : banks) {
            if (bank.getType().equals(line)) {
                result.add(bank);
                return result.toString();
            } else return String.valueOf("По запросу ничего не найдено. " +
                    "Введите в формате:show type <type>");
        }
        return result.toString();
    }

    public String getshowBank(String line) {
        List<Bank> result = new ArrayList<>();
        StringBuilder sb3 = new StringBuilder("<Список всех банков указаного типа>~");
        for (Bank bank : banks) {
            if (bank.getNameBank().equals(line)) {
                result.add(bank);
                return result.toString();
            }else return String.valueOf("По запросу ничего не найдено. " +
                    "Введите в формате:show bank <bank>");
        }
        return result.toString();
    }

    public boolean addBank(String line) {
        try {
            Bank bank = Bank.parseBank(line);
            banks.add(bank);
            storeBanks();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteBank(String line) {
        StringBuilder sb3 = new StringBuilder("<Список всех банков указаного типа>~");
        for (Bank bank : banks) {
            bank.getAccountId();
            banks.remove(bank);
            storeBanks();
            sb3.append(bank.toString()).append("~");
        }
        out.println(sb3);
        return Boolean.parseBoolean(sb3.toString());
    }

    private void storeBanks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(props.getProperty("filename"))))) {
            oos.writeObject(banks);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

