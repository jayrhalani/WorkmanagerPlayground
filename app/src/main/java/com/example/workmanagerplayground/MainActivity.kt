package com.example.workmanagerplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO (3) -> Create new OneTimeWorkRequest
        val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build()

        button.setOnClickListener {
            // TODO (4) -> Enqueue request using WorkManager.getInstance
            WorkManager.getInstance(this).enqueue(request)
        }

        // TODO (5) -> Get WorkManager Results using getWorkInfoByLiveData
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer { workInfo ->

                val status = workInfo.state.name
                textView.append(status + "\n")
            })
    }
}
