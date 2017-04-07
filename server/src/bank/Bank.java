package bank;


import java.io.Serializable;

public class Bank implements Serializable {
    private String nameBank;
    private String country;
    private String type;
    private String depositor;
    private int accountId;
    private double amountOnDeposit;
    private double profitability;
    private double timeConstraints;



    public Bank(String nameBank, String country, String type, String depositor, int accountId, double amountOnDeposit, double profitability, double timeConstraints) {
        this.nameBank = nameBank;
        this.country = country;
        this.type = type;
        this.depositor = depositor;
        this.accountId = accountId;
        this.amountOnDeposit = amountOnDeposit;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
    }





    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(double amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public double getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(double timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "nameBank='" + nameBank + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", depositor='" + depositor + '\'' +
                ", accountId=" + accountId +
                ", amountOnDeposit=" + amountOnDeposit +
                ", profitability=" + profitability +
                ", timeConstraints=" + timeConstraints +
                '}';
    }

    public static Bank parseBank(String line) {
        String[] split = line.split(" ");
        String nameBank = split[1];
        String country = split[2];
        String type = split[3];
        String depositor = split[4];
        int accountId = Integer.parseInt(split[5]);
        double amountOnDeposit = Integer.parseInt(split[6]);
        double profitability = Integer.parseInt(split[7]);
        double timeConstraints = Integer.parseInt(split[8]);
        Bank res = new Bank(nameBank, country, type, depositor, accountId, amountOnDeposit, profitability, timeConstraints);
        return res;
    }
}
