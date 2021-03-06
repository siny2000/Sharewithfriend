package kumarnsit.darin.lab5;

import java.util.Scanner;
public class GuessNumberGameVer2 extends GuessNumberGameVer1  {
    protected static int[] guesses;   //all num user guess
    protected static int numGuesses = 0; //round that user play
    protected static int MAX_GUESSES = 20;
    protected static int guess_input;
    protected String ask_playagain;

    public GuessNumberGameVer2() {
        super();
        guesses = new int[MAX_GUESSES];
    }

    public GuessNumberGameVer2(int minNum, int maxNum) {
        super(minNum, maxNum);
        guesses = new int[MAX_GUESSES];
    }

    public GuessNumberGameVer2(int minNum, int maxNum, int maxTries) {
        super(minNum, maxNum, maxTries);
        guesses = new int[MAX_GUESSES];
    }

    @Override
    public void playGame() {
        for (numGuesses = 1; numGuesses <= maxTries; numGuesses++) {
            //use for loop to set the times to loop operation
            Scanner scan = new Scanner(System.in);
            System.out.print("Please enter a guess (" + minNum + " - " + maxNum + "): ");
            guess_input = scan.nextInt();
            //keep number that user guess in guesses array
            guesses[numGuesses - 1] = guess_input;

            //reports if user didn't entered number in the range.
            if (guess_input > maxNum || guess_input < minNum) {
                System.out.println("The guess number must be in the range " + minNum + " and " + maxNum);
                numGuesses--;
                continue;}

            //if user guess the correct number so print Congratulations! and quit the program.
            if (guess_input == correctNum){
                System.out.println("Congratulations!  That's correct");
                askPlayagain();
            }

            //if user guess the number lower than the correct number so print hint to try again
            else if (guess_input < correctNum) {
                System.out.println("Please type a higher number! Number of remaining tries: " + (maxTries - numGuesses));
            }

            //if user guess the number higher than the correct number so print hint to try again
            else if (guess_input > correctNum) {
                System.out.println("Please type a lower number! Number of remaining tries: " + (maxTries - numGuesses));
            }

            //ask user if user guess the number more than time to guess
            if (numGuesses >= maxTries) {
                askPlayagain();
            }
        }
    }

    public void askPlayagain() {
        Scanner ask_playagain_input = new Scanner(System.in);
        System.out.println('\n' +"If you want to play again? type 'y' to continue or 'q' to quit: "+ '\n' +
                "Type 'a' to see all your guesses or 'g' to see a guess on a specific play. ");
        ask_playagain = ask_playagain_input.next();

        if (ask_playagain.equalsIgnoreCase("y")){
            this.correctNum = minNum + (int) (Math.random() * ((maxNum - minNum) + 1));
            playGames();

        }if (ask_playagain.equalsIgnoreCase("a")){
            showGuesses();
            askPlayagain();

        }if (ask_playagain.equalsIgnoreCase("g")){
            showSpecific();
            askPlayagain();

        }if (ask_playagain.equalsIgnoreCase("q")){
            System.exit(0);

        }else{
            askPlayagain();}
    }

    public void showGuesses() {
        //show all number(in range) that user guess
        for (int i = 0; i < numGuesses; i++) {
            System.out.print(guesses[i] + " ");
        }
    }

    public void showSpecific() {
        //Show specific Guess
        while (true) {
            System.out.println("Enter which guess in the range(" + 1 + "-" + numGuesses + ")");
            Scanner specific_scan = new Scanner(System.in);
            int specific_input = specific_scan.nextInt();
            if (specific_input <= numGuesses && specific_input >= 1) {
                System.out.println("Guess number " + specific_input + " is " + guesses[specific_input-1]);
                break;
            }
        }
    }

    public void playGames() {
        playGame();
        askPlayagain();
        showSpecific();
        showGuesses();
    }

}
