package com.example.courseselection.DataLayer
class CourseRepository {

    var coursesList = mutableListOf<Course>()
    var favIdsSet = mutableSetOf<Int>()

    fun getAllFavIds(): Set<Int>{
        return favIdsSet
    }

    fun getAllCourses(): List<Course> {
        coursesList = mutableListOf(
            Course(
                1, "CS 101",
                "Introduction to Programming",
                3, "Dr. Smith",
                "Monday / Wednesday",
                "10:00 - 11:30 AM",
                "Room 204"
            ),
            Course(
                2, "CS 205",
                "Data Structures",
                4, "Dr. Johnson",
                "Tuesday / Thursday",
                "11:00 AM - 12:30 PM",
                "Room 310"
            ),
            Course(
                3, "MATH 201",
                "Discrete Mathematics",
                3, "Dr. Brown",
                "Monday / Wednesday",
                "1:00 - 2:30 PM",
                "Room 105"
            ),
            Course(
                4, "CS 310",
                "Software Engineering",
                3, "Dr. Wilson",
                "Sunday / Tuesday",
                "9:00 - 10:30 AM",
                "Room 220"
            ),
            Course(
                5, "CS 320",
                "Mobile Application Development",
                4, "Dr. Davis",
                "Monday / Wednesday",
                "3:00 - 4:30 PM",
                "Lab 12"
            ),
            Course(
                6, "CS 350",
                "Database Systems",
                3, "Dr. Miller",
                "Tuesday / Thursday",
                "2:00 - 3:30 PM",
                "Room 115"
            ),
            Course(
                7, "CS 410",
                "Artificial Intelligence",
                3, "Dr. Taylor",
                "Sunday / Tuesday",
                "11:00 AM - 12:30 PM",
                "Room 305"
            ),
            Course(
                8, "CS 430",
                "Computer Networks",
                3, "Dr. Anderson",
                "Monday / Wednesday",
                "9:00 - 10:30 AM",
                "Room 210"
            )
        )
        return coursesList
    }

    fun updateIDs(id: Int){
      if (id in favIdsSet){
            favIdsSet -= id
        }
        else {
            favIdsSet += id
        }
    }
}