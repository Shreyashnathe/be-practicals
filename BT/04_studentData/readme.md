Solidity StudentData Smart Contract – Code Explanation
1. Overview
This Solidity smart contract stores student information on the Ethereum blockchain.

It allows users to:
1. Add student details.
2. Retrieve a student's information.
3. Get the total number of students.
It uses a Struct to store student details and a Dynamic Array to store multiple students.
2. Explanation of each line
Block 1: License and Solidity version
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;


- //: Used to write comments. Comments are ignored by the compiler.
- SPDX-License-Identifier: MIT: Specifies the software license.
- pragma: Provides compiler instructions.
- solidity: Indicates the programming language.
- ^0.8.20: Allows compatible compiler versions from 0.8.20 up to, but not including, 0.9.0.
Block 2: Contract declaration
contract StudentData {


- contract: Keyword used to create a smart contract.
- StudentData: Name of the contract.
- { }: Defines the contract body.
A smart contract is a program deployed on a blockchain that executes predefined instructions.
Block 3: Structure
struct Student {
    uint id;
    string name;
    uint age;
}


A struct is a user-defined data type used to group multiple related variables.

Here, Student stores three details:
- uint id: Student's ID. uint means unsigned integer (non-negative number).
- string name: Student's name. string stores text.
- uint age: Student's age.
Example:

A Student structure can store:
Field	Value
id	101
name	Rahul
age	21

Block 4: Dynamic array
Student[] public students;


- Student[]: Array that stores multiple Student structures.
- []: Represents an array.
- public: Automatically creates a getter function to access array elements.
- students: Name of the array.
What is a dynamic array?

A dynamic array can grow or shrink in size during program execution.

For example, when a new student is added, the array size increases.
Block 5: Fallback function
fallback() external payable {}


A fallback function is a special function that executes when a contract receives a call that does not match any existing function, or when calldata is provided without matching a function.
- fallback(): Special function name.
- external: Can be called from outside the contract.
- payable: Allows the function to receive ETH.
- {}: Empty function body; it performs no additional operation.
Block 6: Receive function
receive() external payable {}


The receive function executes when the contract receives ETH through a direct transfer with empty calldata.
- receive(): Special function for receiving ETH.
- external: Can be called from outside the contract.
- payable: Allows ETH to be received.
- {}: Empty function body.
Difference between fallback and receive:
Receive function	Fallback function
Executes when ETH is sent with empty calldata.	Executes when no function matches the call, or in certain cases when calldata is present.
Must be payable.	Can be payable or non-payable.

Block 7: Add student
function addStudent(uint _id, string memory _name, uint _age) public {
    students.push(Student(_id, _name, _age));
}


This function adds a new student to the array.

Explanation:
- function: Defines a function.
- addStudent: Function name.
- uint _id: Accepts student ID.
- string memory _name: Accepts student name.
- uint _age: Accepts student age.
- public: Anyone can call this function.
- memory: Temporary data location used while the function executes.
- students.push(): Adds a new element to the end of the dynamic array.
- Student(_id, _name, _age): Creates a Student structure using the provided values.
Example:
addStudent(101, "Rahul", 21);


This adds Rahul's details to the students array.
Block 8: Get student details
function getStudent(uint _id)
    public
    view
    returns (uint, string memory, uint)
{
    require(_id < students.length, "Student ID does not exist.");

    Student memory student = students[_id];

    return (student.id, student.name, student.age);
}


This function retrieves the details of a student using their array index.

Explanation:
- getStudent(uint _id): Accepts an index to identify the student.
- public: Anyone can call this function.
- view: Reads contract data without modifying it.
- returns (uint, string memory, uint): Returns student ID, name and age.
- require(): Checks whether the given index exists.
- students.length: Gives the total number of elements in the array.
- Student memory student: Creates a temporary copy of the selected student in memory.
- students[_id]: Accesses the student at the given array index.
- return: Returns the student's ID, name and age.
Example:
getStudent(0);


Returns the details of the first student.

Important
 Here, _id is an array index, not the student's actual ID. Array indexing starts from 0.

For example, if you added student ID 101 first, you must call getStudent(0) to retrieve that student's details.
Block 9: Get total students
function getStudentCount() public view returns (uint) {
    return students.length;
}


This function returns the total number of students stored in the array.
- getStudentCount(): Function name.
- public: Anyone can call it.
- view: Reads data without modifying it.
- returns (uint): Returns an unsigned integer.
- students.length: Returns the total number of students.
- return: Sends the result back.
Example: If the array contains 5 students, this function returns 5.
Block 10: Closing contract
}


Closes the StudentData contract.

3. Important Solidity terms
Term	Meaning
struct	Groups related variables into one custom data type.
uint	Unsigned integer.
string	Stores text.
dynamic array	Array whose size can change.
public	Makes a function or variable externally accessible.
memory	Temporary data storage during function execution.
view	Reads data without modifying contract state.
push()	Adds an element to an array.
length	Returns the number of array elements.
require()	Checks a condition and reverts if false.
fallback()	Handles calls that do not match a function.
receive()	Handles ETH transfers with empty calldata.

4. Example execution
Suppose we add two students:
addStudent(101, "Rahul", 21);
addStudent(102, "Priya", 22);


The array will contain:
Array index	Student ID	Name	Age
0	101	Rahul	21
1	102	Priya	22

Now:
- getStudent(0) → Returns (101, "Rahul", 21).
- getStudent(1) → Returns (102, "Priya", 22).
- getStudentCount() → Returns 2.
5. Important viva questions
Q1. What is Solidity?

Solidity is a programming language used to develop smart contracts, mainly on Ethereum.

Q2. What is a struct?

A struct is a user-defined data type that groups multiple related variables.

Q3. What is a dynamic array?

An array whose size can change during execution.

Q4. What is the use of push()?

It adds a new element at the end of a dynamic array.

Q5. What is the difference between memory and storage?
- memory: Temporary data storage during function execution.
- storage: Permanent data storage on the blockchain.
Q6. What is the purpose of view?

It allows a function to read blockchain data without modifying the contract state.

Q7. What is the difference between fallback and receive?

receive() handles ETH transfers with empty calldata, while fallback() handles unmatched function calls.

Q8. Why do we use require()?

To validate conditions and stop execution if they are not satisfied.

Q9. What does students.length return?

It returns the total number of elements in the students array.

Q10. Why do we use memory with the student name?

Because the input string is temporarily used during function execution before being stored in the array.
6. Algorithm workflow
1. Deploy the StudentData contract.
2. Define the Student structure.
3. Create a dynamic array to store students.
4. Call addStudent() to insert student details.
5. Call getStudent() to retrieve student information using its array index.
6. Call getStudentCount() to get the total number of students.
7. Conclusion
This smart contract demonstrates how to use structures and dynamic arrays in Solidity to store and manage student information on the Ethereum blockchain. It also demonstrates how to retrieve records and count the total number of students.