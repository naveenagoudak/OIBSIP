# ATM Interface (Java)

A console-based ATM Simulation application developed in Java for the Oasis Infobyte Internship (OIBSIP).

## Features
- **User Authentication:** Login with User ID and PIN (locked after 3 failed attempts).
- **Transactions:** Supports cash withdrawals, deposits, and money transfers between accounts.
- **Balance Verification:** Checks for sufficient balance before executing withdrawals and transfers.
- **Transaction History:** Real-time log tracking of all credits and debits using an ArrayList.
- **Object-Oriented Design:** Structured using separate classes (`ATM`, `Account`, `Transaction`, `Bank`, `ATMInterface`).

## Default Credentials (Demo)
- **User ID:** `admin`
- **PIN:** `1234`
- **Recipient ID:** `user2`

## Tech Stack
- Java (JDK 8+)
- Java Core & OOP Principles

## How to Run
1. Compile the program:
   `javac ATMInterface.java`
2. Run the program:
   `java ATMInterface`
