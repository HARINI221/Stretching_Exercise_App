package com.example.stretchingexercisesapp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_exercise.*

class ExerciseFragment : Fragment() {

    private var timer: CountDownTimer? = null

    companion object {
        fun newInstance(): ExerciseFragment {
            return ExerciseFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStartTimer.setOnClickListener {
            startTimer(60000) // 1 minute timer
        }
    }

    private fun startTimer(millisInFuture: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                tvTimer.text = String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60)
            }

            override fun onFinish() {
                tvTimer.text = "00:00"
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
    }
}
