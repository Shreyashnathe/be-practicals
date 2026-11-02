
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract StudentData {

    // Structure
    struct Student {
        uint id;
        string name;
        uint age;
    }

    // Dynamic array
    Student[] public students;

    // Fallback function
    fallback() external payable {}

    // Receive function
    receive() external payable {}

    // Add student
    function addStudent(uint _id, string memory _name, uint _age) public {
        students.push(Student(_id, _name, _age));
    }

    // Get student details
    function getStudent(uint _id)
        public
        view
        returns (uint, string memory, uint)
    {
        require(_id < students.length, "Student ID does not exist.");

        Student memory student = students[_id];

        return (student.id, student.name, student.age);
    }

    // Get total students
    function getStudentCount() public view returns (uint) {
        return students.length;
    }
}
