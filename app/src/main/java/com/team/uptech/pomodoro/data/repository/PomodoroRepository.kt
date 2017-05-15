package com.team.uptech.pomodoro.data.repository

import com.team.uptech.pomodoro.data.model.PomodoroData
import com.team.uptech.pomodoro.data.model.PomodoroTypeData
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created on 28.04.17.
 */
interface PomodoroRepository {
    fun getPomodoroType(): PomodoroTypeData
    fun getPomodoroTime(): Int
    fun getPomodoroIsRunning(): Boolean
    fun getPomodoro(): Observable<PomodoroData>
    fun savePomodoro(pomodoro: PomodoroData): Completable
}