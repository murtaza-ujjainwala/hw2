# Expense Tracker App

## Overview

The Expense Tracker App is a simple Java application that allows users to add daily transactions.

## Supported Features

- **Add Transactions:** Users can input the amount of money corresponding to the transaction, as well as the category it belongs to.
    - The amount of a transaction is restricted to being greater than 0 USD and less than 1000 USD.
    - The category of a transaction is restricted to being one of the following: food, travel, bills, entertainment, or other. Capitalization of letters does not matter.
- **View Transactions:** Users will be able to view their transaction history from the table as well as the timestamp of when each transaction was entered.
- **Total Sum:** Users will be able to view the total sum of their transaction amounts at the bottom of the table.
- **Filter Transactions:** Users can filter the transaction history by either amount or category.
    - When filtering by **amount**, all transactions matching the exact amount are displayed.
    - When filtering by **category**, all transactions in the specified category are displayed.
    - Input validation is applied for both amount and category filter fields.
- **Open-box Extensibility:** The app can be easily extended or modified to add features.
