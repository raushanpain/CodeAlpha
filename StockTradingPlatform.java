import java.util.*;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance;

    public Portfolio(double balance) {
        this.balance = balance;
    }

    public void buyStock(String symbol, int quantity, double price) {
        double cost = quantity * price;
        if (cost <= balance) {
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
            balance -= cost;
            System.out.println("Bought " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void sellStock(String symbol, int quantity, double price) {
        if (holdings.getOrDefault(symbol, 0) >= quantity) {
            holdings.put(symbol, holdings.get(symbol) - quantity);
            balance += quantity * price;
            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Not enough shares to sell!");
        }
    }

    public void viewPortfolio() {
        System.out.println("Portfolio Holdings:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio(10000); // Starting balance
        Map<String, Stock> market = new HashMap<>();

        // Sample market stocks
        market.put("AAPL", new Stock("AAPL", 150.00));
        market.put("GOOGL", new Stock("GOOGL", 2800.00));
        market.put("TSLA", new Stock("TSLA", 700.00));

        while (true) {
            System.out.println("\n1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Market Data:");
                    for (Stock stock : market.values()) {
                        System.out.println(stock.symbol + " - $" + stock.price);
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = scanner.next();
                    if (market.containsKey(buySymbol)) {
                        System.out.print("Enter quantity: ");
                        int buyQuantity = scanner.nextInt();
                        portfolio.buyStock(buySymbol, buyQuantity, market.get(buySymbol).price);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = scanner.next();
                    if (market.containsKey(sellSymbol)) {
                        System.out.print("Enter quantity: ");
                        int sellQuantity = scanner.nextInt();
                        portfolio.sellStock(sellSymbol, sellQuantity, market.get(sellSymbol).price);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;
                case 4:
                    portfolio.viewPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
