// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract Bank {

    mapping(address => uint) public balance;

    // Deposit money
    function deposit() public payable {
        require(msg.value > 0, "Enter some ETH");
        balance[msg.sender] += msg.value;
    }

    // Withdraw money
    function withdraw(uint amount) public {
        require(amount > 0, "Invalid amount");
        require(balance[msg.sender] >= amount, "Insufficient balance");

        balance[msg.sender] -= amount;

        (bool success, ) = payable(msg.sender).call{value: amount}("");
        require(success, "Transfer failed");
    }

    // Show balance
    function showBalance() public view returns (uint) {
        return balance[msg.sender];
    }
}