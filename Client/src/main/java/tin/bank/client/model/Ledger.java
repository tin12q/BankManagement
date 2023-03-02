package tin.bank.client.model;

public class Ledger {
    private int transactionId;
    private int sourceCustomerId;

    public int getTransactionId() {
        return transactionId;
    }

    public int getSourceCustomerId() {
        return sourceCustomerId;
    }

    private String transactionType;

    private double amount;
    private String transactionDate;
    private String description;
    private int destinationCustomerId;
    private String destinationName;

    public String getDestinationName() {
        return destinationName;
    }

    public Ledger(int transactionId, int sourceCustomerId, String transactionType, double amount,
            String transactionDate, String description, int destinationCustomerId, String destinationName) {
        this.transactionId = transactionId;
        this.sourceCustomerId = sourceCustomerId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.destinationCustomerId = destinationCustomerId;
        this.description = description;
        this.destinationName = destinationName;

    }

    public Ledger(int transactionId, int sourceCustomerId, String transactionType, double amount,
            String transactionDate, int destinationCustomerId, String destinationName) {
        this.transactionId = transactionId;
        this.sourceCustomerId = sourceCustomerId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.destinationCustomerId = destinationCustomerId;
        this.destinationName = destinationName;
    }

    public int getDestinationCustomerId() {
        return destinationCustomerId;
    }

    public String getAmount() {
        return String.valueOf(amount);
    }

    public Double getAmountDouble() {
        return amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getDate() {
        return transactionDate.substring(5, 10);
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return transactionType;
    }
}
