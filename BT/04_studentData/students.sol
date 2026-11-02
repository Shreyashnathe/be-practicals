
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract StudentData {

    // Structure to hold student information
    struct Student {
        uint id;
        string name;
        uint age;
    }

    // Dynamic array to store students
    Student[] public students;

    // Fallback function
    fallback() external payable {}

    // Receive function
    receive() external payable {}

    // Function to add a student
    function addStudent(string memory _name, uint _age) public {
        uint studentId = students.length;

        students.push(Student(studentId, _name, _age));
    }

    // Function to retrieve student information
    function getStudent(uint _id)
        public
        view
        returns (uint, string memory, uint)
    {
        require(_id < students.length, "Student ID does not exist.");

        Student memory student = students[_id];

        return (student.id, student.name, student.age);
    }

    // Function to get total number of students
    function getStudentCount() public view returns (uint) {
        return students.length;
    }
}
