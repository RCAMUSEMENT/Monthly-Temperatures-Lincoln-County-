/**
 * Student Name: Ryley Carlson
 * CSC320 Module 5 Critical Thinking - Option 2
 * Program: Lincoln County Temperature System
 * Description: Utilizes parallel arrays to store and retrieve monthly temperature data.
 * Includes a fuzzy-search algorithm to handle user typos and provides a
 * annual statistical report (Average, High, Low) upon request.
 */


import java.util.Scanner;

public class LincolnCountyTemp {
    public static void main(String[] args) {
        String[] months = {"January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"};
        double[] temps = {24.0, 27.5, 37.2, 45.8, 54.1, 62.5, 71.0, 69.4, 59.2, 46.7, 34.3, 24.5};

        try (Scanner input = new Scanner(System.in)) {
            boolean continueProgram = true;

            System.out.println("--- Ryley Carlson's Year-at-a-Glance: Lincoln County Temperature System ---");

            while (continueProgram) {
                System.out.print("\nPlease enter a month or type 'year' if you want a full report of the temperatures: ");
                String userInput = input.nextLine().trim();

                if (userInput.equalsIgnoreCase("year")) {
                    printYearlyReport(months, temps);
                } else {
                    handleMonthlyInput(months, temps, userInput);
                }

                // Input Validation Loop for "Search Again"
                continueProgram = askToContinue(input);
            }
            
            System.out.println("You are now exiting the program. Thank you for using Ryley Carlson's Temperature System! Goodbye!");
        }
    }

    /**
     * Searches for month or suggests the closest match if not found by reasonable typo (up to 2 edits away)
     *  and prints the temperature for that month if found
     */
    public static void handleMonthlyInput(String[] months, double[] temps, String target) {
        int bestMatchIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(target)) {
                System.out.printf("\n--- %s: %.2f°F ---\n", months[i], temps[i]);
                return;
            }

            // Calculate the "distance" to find closest typo match for user input and month names (case-insensitive)
            int distance = calculateDistance(target.toLowerCase(), months[i].toLowerCase());
            if (distance < minDistance) {
                minDistance = distance;
                bestMatchIndex = i;
            }
        }

        System.out.println("\nError: '" + target + "' not found.");
        if (bestMatchIndex != -1 && minDistance <= 2) {
            System.out.println("Did you mean '" + months[bestMatchIndex] + "'?");
        }
    }

    /**
     * Calculates Levenshtein distance for typo matching between user input and
     * month names reasonably close to the input (up to 2 edits away)
     */
    private static int calculateDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * Prints out the full yearly report including stats for average, highest, and lowest temperatures by month
     */
    public static void printYearlyReport(String[] months, double[] temps) {
        double sum = 0, highest = temps[0], lowest = temps[0];
        System.out.println("\n--- Annual Temperature Report ---");
        System.out.println("---------------------------------");
        
        for (int i = 0; i < months.length; i++) {
            System.out.printf("%-12s : %.2f°F\n", months[i], temps[i]);
            sum += temps[i];
            if (temps[i] > highest) highest = temps[i];
            if (temps[i] < lowest) lowest = temps[i];
        }
        
        System.out.println("---------------------------------");
        System.out.printf("Average: %.2f°F\n", (sum / months.length));
        System.out.printf("Highest: %.2f°F\n", highest);
        System.out.printf("Lowest : %.2f°F\n", lowest);
        System.out.println("---------------------------------");
    }

    private static boolean askToContinue(Scanner input) {
        while (true) {
            System.out.print("\nWould you like to search again? (yes/no): ");
            String choice = input.nextLine().trim().toLowerCase();
            if (choice.equals("yes") || choice.equals("y")) return true;
            if (choice.equals("no") || choice.equals("n")) return false;
            System.out.println("Your input is invalid. Please only enter 'yes' or 'no'.");
        }
    }
}
