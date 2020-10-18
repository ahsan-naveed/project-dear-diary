import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Comparator;


public class Traders {
    static public class Trader {
        private final String name;
        private final String city;
        
        public Trader(String n, String c) {
            this.name = n;
            this.city = c;
        }

        public String getName() { return this.name; }
        public String getCity() { return this.city; }

        @Override
        public String toString() { 
            return "Trader:"+this.name + " in " + this.city;
        }
    }

    static public class Transaction {
        private final Trader trader;
        private final int year;
        private final int value;
        
        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }
        
        public Trader getTrader() { return this.trader; }
        public int getYear() { return this.year; }
        public int getValue() { return this.value; }

        @Override
        public String toString(){
            return "{" + this.trader + ", " + "year: "+this.year+", " + "value:" + this.value +"}";
        }
    }

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        // q1: Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> txnsIn2011 = 
            transactions
                .stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println(txnsIn2011);


        // q2: What are all the unique cities where the traders work?
        List<String> uniqueCities = 
            transactions
                .stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        
        System.out.println(uniqueCities);

        // q3: Find all traders from Cambridge and sort them by name.
        List<Trader> cambridgeTraders = 
            transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity() == "Cambridge")
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        
        System.out.println(cambridgeTraders);

        // q4: Return a string of all traders’ names sorted alphabetically.
        List<String> allTraderNames = 
            transactions
                .stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(String.join(" ", allTraderNames));  

        // q5: Are any traders based in Milan
        transactions
            .stream()
            .filter(t -> t.getTrader().getCity() == "Milan")
            .findAny()
            .ifPresent(System.out::println);
        
        // q6: print the values of all txns from the traders living in Cambridge
        List<Integer> cambridgeValues = 
            transactions
                .stream()
                .filter(t -> t.getTrader().getCity() == "Cambridge")
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        
        System.out.println(cambridgeValues);

        // q7: What’s the highest value of all the transactions?
        Optional<Integer> highestValue = 
            transactions
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println(highestValue);

        // q8: Find the transaction with the smallest value.
        Optional<Transaction> smallestTxn = 
            transactions
                .stream()
                .min(Comparator.comparing(Transaction::getValue));

        System.out.println(smallestTxn);

        // collectors
        
    }
}