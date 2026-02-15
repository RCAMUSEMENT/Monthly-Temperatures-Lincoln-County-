# 🌡️ Lincoln County Temperature System
**Author:** Ryley Carlson

**Project:** CSC320 Module 5 Critical Thinking - Option 2

**Implementation:** Java (Object-Oriented Logic)


---


## 📖 Project Overview
The **Lincoln County Temperature System** is a robust command-line utility designed for the storage, retrieval, and analysis of monthly temperature data. By leveraging **parallel arrays** the system maintains a direct relationship between 12 months of historical climate data and their respective names, providing users with an interactive interface to query specific statistics or generate a comprehensive annual report.


### Key Features
*   **Fuzzy Search Engine:** Employs a **Levenshtein Distance** algorithm to handle user typos (e.g., suggesting "January" if the user types "Janury").
*   **Annual Statistical Reporting:** Automatically calculates and displays the yearly average, record high, and record low temperatures.
*   **Case-Insensitive Retrieval:** Ensures user input is processed flexibly (e.g., "MARCH," "March," or "march" all resolve correctly).
*   **Robust Control Loop:** A persistent "Search Again" validation loop that manages program state and prevents accidental termination.


---


## 🛠️ Technical Architecture


### Data Management
The system utilizes two synchronized **parallel arrays** to ensure data integrity and high-speed access:
1.  `String[] months`: A 12-element array containing the full names of the calendar months.
2.  `double[] temps`: A 12-element array containing the average temperature (°F) for each month.


### Algorithm: Levenshtein Distance (Edit Distance)
To provide a superior user experience, the system calculates the "cost" of transforming the user's input string into a valid month name using **Dynamic Programming (DP)**.
*   **The Logic:** The system constructs a 2D matrix (`dp[m+1][n+1]`) to track the minimum number of deletions, insertions, and substitutions required.
*   **The Threshold:** If the minimum number of edits required to match a month is **2 or fewer**, the system intelligently suggests the closest match as a correction.


---


## 💻 Source Code Structure


### Prerequisites
*   **Java Development Kit (JDK):** Version 8 or higher.
*   **Terminal/IDE:** Any environment capable of running Java (IntelliJ, Eclipse, VS Code, or Command Prompt).


### Module Breakdown
*   `main()`: Coordinates the primary control loop, manages the `Scanner` resource, and routes user requests.
*   `handleMonthlyInput()`: Orchestrates the search logic, comparing the user target against the data arrays using both direct and fuzzy matching.
*   `calculateDistance()`: The mathematical engine that computes the string similarity score (Levenshtein).
*   `printYearlyReport()`: An analytical module that iterates through the data to compute the **sum**, **average**, **highest**, and **lowest** values.
*   `askToContinue()`: A utility module designed to validate "Yes/No" responses for program continuation.


---


## 📊 Sample Execution


**Corrective Suggestion (Typos):**
```text
Please enter a month or type 'year': Juny
Error: 'Juny' not found.
Did you mean 'June'?


---


--- Annual Temperature Report ---
---------------------------------
January      : 24.00°F
February     : 27.50°F
...
Average: 43.27°F
Highest: 71.00°F
Lowest : 24.00°F


----------------------------


*Created as part of a Java Programming project for CSC320 Programming 1*



