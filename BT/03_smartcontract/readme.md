1. Overview
This Solidity smart contract implements a simple Banking System on the Ethereum blockchain.

It allows users to:
1. Deposit ETH into the smart contract.
2. Withdraw their deposited ETH.
3. Check their deposited balance.
Each user's balance is stored separately using their Ethereum wallet address.
2. Explanation of each line
Block 1: License and Solidity version
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;


- //: Used to write a single-line comment. It is ignored by the compiler.
- SPDX-License-Identifier: MIT: Specifies the license under which the code is distributed. MIT is an open-source license.
- pragma: Specifies compiler-related instructions.
- solidity: Indicates that this code is written in Solidity.
- ^0.8.20: Allows compatible Solidity compiler versions from 0.8.20 up to, but not including, 0.9.0.
Block 2: Contract declaration
contract Bank {


- contract: Keyword used to create a smart contract.
- Bank: Name of the smart contract.
- { }: Defines the beginning and end of the contract body.
A smart contract is a program deployed on a blockchain that executes according to predefined rules.
Block 3: Store user balances
mapping(address => uint) public balance;


This is used to store the balance of each user.

Explanation:
- mapping: A key-value data structure, similar to a HashMap in Java.
- address: Represents an Ethereum wallet or smart contract address.
- =>: Connects the key type with the value type.
- uint: Represents an unsigned integer (a non-negative number).
- public: Automatically creates a getter function so anyone can read a user's stored balance.
- balance: Name of the mapping.
Example:
User address	Balance
0xABC...	2 ETH
0xDEF...	5 ETH

The balance is internally stored in Wei, not ETH.
Block 4: Deposit money
function deposit() public payable {
    require(msg.value > 0, "Enter some ETH");
    balance[msg.sender] += msg.value;
}


Explanation:
- function: Used to define a function.
- deposit(): Function name. It does not take any parameters.
- public: Anyone can call this function.
- payable: Allows the function to receive ETH.
- require(): Checks a condition. If it is false, the transaction is reverted.
- msg.value: Amount of ETH sent with the transaction, measured in Wei.
- msg.sender: Address of the user who called the function.
- +=: Adds a value to the existing balance.
Working:

Suppose a user deposits 2 ETH.
1. The user calls deposit() and sends 2 ETH.
2. require() checks that the deposited amount is greater than zero.
3. balance[msg.sender] += msg.value adds 2 ETH to that user's balance.
Block 5: Withdraw money
function withdraw(uint amount) public {
    require(amount > 0, "Invalid amount");
    require(balance[msg.sender] >= amount, "Insufficient balance");

    balance[msg.sender] -= amount;

    (bool success, ) = payable(msg.sender).call{value: amount}("");
    require(success, "Transfer failed");
}


This function allows users to withdraw their deposited ETH.

Explanation:
- withdraw(uint amount): Accepts the withdrawal amount as an unsigned integer.
- require(amount > 0, ...): Ensures the withdrawal amount is greater than zero.
- balance[msg.sender] >= amount: Checks whether the user has enough balance.
- -=: Subtracts the withdrawal amount from the user's balance.
- payable(msg.sender): Converts the caller's address into a payable address so it can receive ETH.
- .call{value: amount}(""): Sends the specified ETH to the user's address.
- bool success: Stores whether the ETH transfer succeeded.
- require(success, ...): Reverts the transaction if the transfer fails.
What is call()?

call() is a low-level Solidity function used to interact with another address. Here, it transfers ETH to the user's wallet.

Why subtract balance before transferring?

The contract follows the Checks-Effects-Interactions pattern:
1. Checks: Validate the withdrawal amount and balance.
2. Effects: Update the user's balance.
3. Interactions: Transfer ETH to the user.
This helps protect against reentrancy attacks.
Block 6: Show balance
function showBalance() public view returns (uint) {
    return balance[msg.sender];
}


This function displays the balance of the person who calls it.
- showBalance(): Function name.
- public: Anyone can call it.
- view: Indicates that the function reads blockchain data but does not modify state.
- returns (uint): Specifies that the function returns an unsigned integer.
- return: Sends the stored balance back as the function result.
Example: If your wallet has deposited 3 ETH, this function returns the equivalent Wei value of 3 ETH.
Block 7: Closing contract
}


Closes the Bank contract.

3. Important Solidity terms
Term	Meaning
ETH	Cryptocurrency used on Ethereum
Wei	Smallest unit of ETH; 1 ETH = \(10^{18}\) Wei
msg.sender	Address that called the function
msg.value	ETH sent with the current transaction
payable	Allows a function or address to receive ETH
require()	Validates a condition and reverts if false
mapping	Stores key-value pairs
view	Reads data without modifying contract state
call()	Low-level function used to interact with another address

4. Example execution
Suppose a user performs these operations:
Operation	User's balance
Initially	0 ETH
Deposit 5 ETH	5 ETH
Withdraw 2 ETH	3 ETH
Show balance	3 ETH

5. Important viva questions
Q1. What is Solidity?

Solidity is a programming language used to develop smart contracts on Ethereum.

Q2. What is a smart contract?

A smart contract is a program deployed on a blockchain that executes predefined instructions.

Q3. What is the difference between public and payable?
- public controls who can access a function.
- payable allows a function to receive ETH.
Q4. What is msg.sender?

It represents the address of the account that called the current function.

Q5. What is msg.value?

It represents the amount of ETH sent with the current transaction, in Wei.

Q6. Why do we use require()?

To validate conditions and stop execution when a condition is not satisfied.

Q7. What is the difference between view and payable?
- view allows reading contract data without modifying state.
- payable allows receiving ETH.
Q8. Why is balance reduced before transferring ETH?

To follow the Checks-Effects-Interactions pattern and reduce reentrancy risk.

Q9. What happens if a withdrawal fails?

require(success, "Transfer failed") reverts the transaction, including the earlier balance update.

Q10. What is the difference between ETH and Wei?

ETH is the main currency unit, while Wei is its smallest unit.
6. Conclusion
This smart contract implements basic banking operations using Solidity. It uses mappings to store individual user balances, payable functions to receive ETH, and call() to transfer ETH during withdrawals.

Important: This is a learning example, not a production-ready banking contract. A production version should also consider reentrancy protection, testing, and additional security controls.