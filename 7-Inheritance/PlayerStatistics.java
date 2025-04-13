// Main PlayerStats class (superclass)
class PlayerStats {
    String playerName;
    int age;
    String team;
    int gamesPlayed;

    public PlayerStats(String playerName, int age, String team) {
        this.playerName = playerName;
        this.age = age;
        this.team = team;
        this.gamesPlayed = 0;
    }

    public void playGame() {
        gamesPlayed++;
        System.out.println(playerName + " played a game. Total games: " + gamesPlayed);
    }

    public void displayBasicInfo() {
        System.out.println("Player: " + playerName);
        System.out.println("Age: " + age);
        System.out.println("Team: " + team);
        System.out.println("Games Played: " + gamesPlayed);
    }
}

// FootballStats subclass
class FootballStats extends PlayerStats {
    int goalsScored;
    int assists;
    int yellowCards;

    public FootballStats(String playerName, int age, String team) {
        super(playerName, age, team);
        this.goalsScored = 0;
        this.assists = 0;
        this.yellowCards = 0;
    }

    public void scoreGoal() {
        goalsScored++;
        System.out.println(playerName + " scored a goal! Total goals: " + goalsScored);
    }

    public void makeAssist() {
        assists++;
        System.out.println(playerName + " made an assist! Total assists: " + assists);
    }

    public void getYellowCard() {
        yellowCards++;
        System.out.println(playerName + " got a yellow card. Total yellow cards: " + yellowCards);
    }

    public void displayFootballStats() {
        displayBasicInfo();
        System.out.println("Goals: " + goalsScored);
        System.out.println("Assists: " + assists);
        System.out.println("Yellow Cards: " + yellowCards);
    }
}

// CricketStats subclass
class CricketStats extends PlayerStats {
    int runsScored;
    int wicketsTaken;
    int centuries;

    public CricketStats(String playerName, int age, String team) {
        super(playerName, age, team);
        this.runsScored = 0;
        this.wicketsTaken = 0;
        this.centuries = 0;
    }

    public void scoreRuns(int runs) {
        runsScored += runs;
        if (runs >= 100) {
            centuries++;
            System.out.println(playerName + " scored a century!");
        }
        System.out.println(playerName + " scored " + runs + " runs. Total runs: " + runsScored);
    }

    public void takeWicket() {
        wicketsTaken++;
        System.out.println(playerName + " took a wicket! Total wickets: " + wicketsTaken);
    }

    public void displayCricketStats() {
        displayBasicInfo();
        System.out.println("Runs Scored: " + runsScored);
        System.out.println("Wickets Taken: " + wicketsTaken);
        System.out.println("Centuries: " + centuries);
    }
}

// BasketballStats subclass
class BasketballStats extends PlayerStats {
    int pointsScored;
    int rebounds;
    int assists;

    public BasketballStats(String playerName, int age, String team) {
        super(playerName, age, team);
        this.pointsScored = 0;
        this.rebounds = 0;
        this.assists = 0;
    }

    public void scorePoints(int points) {
        pointsScored += points;
        System.out.println(playerName + " scored " + points + " points. Total points: " + pointsScored);
    }

    public void getRebound() {
        rebounds++;
        System.out.println(playerName + " got a rebound! Total rebounds: " + rebounds);
    }

    public void makeAssist() {
        assists++;
        System.out.println(playerName + " made an assist! Total assists: " + assists);
    }

    public void displayBasketballStats() {
        displayBasicInfo();
        System.out.println("Points Scored: " + pointsScored);
        System.out.println("Rebounds: " + rebounds);
        System.out.println("Assists: " + assists);
    }
}

// Driver class to test the program
public class PlayerStatistics {
    public static void main(String[] args) {
        System.out.println("=== Player Statistics Program ===");

        // Create football player
        FootballStats messi = new FootballStats("Lionel Messi", 35, "PSG");
        messi.playGame();
        messi.scoreGoal();
        messi.makeAssist();
        messi.getYellowCard();
        System.out.println("\nFootball Player Stats:");
        messi.displayFootballStats();

        // Create cricket player
        CricketStats kohli = new CricketStats("Virat Kohli", 33, "India");
        kohli.playGame();
        kohli.playGame();
        kohli.scoreRuns(45);
        kohli.scoreRuns(102); // Century!
        kohli.takeWicket();
        System.out.println("\nCricket Player Stats:");
        kohli.displayCricketStats();

        // Create basketball player
        BasketballStats james = new BasketballStats("LeBron James", 37, "Lakers");
        james.playGame();
        james.scorePoints(28);
        james.getRebound();
        james.makeAssist();
        System.out.println("\nBasketball Player Stats:");
        james.displayBasketballStats();
    }
}