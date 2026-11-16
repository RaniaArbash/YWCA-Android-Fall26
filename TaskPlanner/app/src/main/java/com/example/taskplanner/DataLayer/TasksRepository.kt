package com.example.taskplanner.DataLayer

import android.util.Log

// MVVM
class TasksRepository {
// the source of truth

    // get all tasks
    // Get the tasks list from db
    // from api
    // cloud db

    // add new task
    // insert into database
    // add into api

    var tasks = mutableListOf<Task>()


    fun getAllTask(): List<Task>{
        // from database
        // or api
        tasks = mutableListOf(
            Task(
            1,
            "Kotlin Assignment",
            "Complete exercises 1-5",
            "Nov 10",
            "University",
            false
        ),
            Task(
                2,
                "Study Compose",
                "Review Compose layouts",
                "Nov 11",
                "Study",
                true
            ),
            Task(
                3,
                "Prepare Presentation",
                "Prepare slides for class",
                "Nov 12",
                "University",
                false
            ),
            Task(
                4,
                "Read Chapter 3",
                "Review the textbook",
                "Nov 13",
                "Study",
                false
            ),
            Task(
                5,
                "Submit Lab",
                "Upload the completed project",
                "Nov 14",
                "University",
                true
            )
        )
        return tasks
    }

    fun addOneTask(task: Task){
        // add to datasource
        //tasks.add(task)
        tasks.add (task)
        Log.d("list", tasks.toString())
    }

    fun deleteOneTask(task: Task) {
         tasks.remove(task)
        Log.d("list", tasks.toString())

    }


}