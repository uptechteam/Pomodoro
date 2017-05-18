package com.team.uptech.pomodoro.domain.interactor

import com.team.uptech.pomodoro.presentation.model.Pomodoro
import io.reactivex.Single

/**
 * Created on 27.04.17.
 */
interface StartTimerUseCase {
    fun changeStartStop(): Single<Pomodoro>
}