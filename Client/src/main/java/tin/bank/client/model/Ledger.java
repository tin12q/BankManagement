package tin.bank.client.model;

public class Ledger {
    private int transactionId;
    private int sourceCustomerId;
    private String transactionType;
    private double amount;
    private String transactionDate;
    private String description;
    private int destinationCustomerId;

    public Ledger(int transactionId, int sourceCustomerId, String transactionType, double amount,
            String transactionDate, String description, int destinationCustomerId) {
        this.transactionId = transactionId;
        this.sourceCustomerId = sourceCustomerId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.destinationCustomerId = destinationCustomerId;
        this.description = description;

    }

    public Ledger(int transactionId, int sourceCustomerId, String transactionType, double amount,
            String transactionDate, int destinationCustomerId) {
        this.transactionId = transactionId;
        this.sourceCustomerId = sourceCustomerId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.destinationCustomerId = destinationCustomerId;
    }

    public String getDestinationCustomerId() {
        return String.valueOf(destinationCustomerId);
    }

    public String getAmount() {
        return String.valueOf(amount);
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getDescription() {
        return description;
    }

}
