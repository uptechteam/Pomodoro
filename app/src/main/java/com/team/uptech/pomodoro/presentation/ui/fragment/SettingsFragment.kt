package com.team.uptech.pomodoro.presentation.ui.fragment

import android.os.Bundle
import android.preference.PreferenceFragment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.team.uptech.pomodoro.ActivityComponent
import com.team.uptech.pomodoro.R
import com.team.uptech.pomodoro.getAppComponent
import com.team.uptech.pomodoro.presentation.model.PomodoroType
import com.team.uptech.pomodoro.presentation.presenter.SettingsPresenter
import com.team.uptech.pomodoro.presentation.ui.SettingsView
import com.team.uptech.pomodoro.presentation.ui.activity.BaseActivity
import javax.inject.Inject


/**
 * Created on 27.04.17.
 */
class SettingsFragment : PreferenceFragment(), SettingsView {

    private var activityComponent: ActivityComponent? = null
    @Inject lateinit var presenter: SettingsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = activity.getAppComponent().activityComponent()
        activityComponent?.inject(this)

        addPreferencesFromResource(R.xml.settings)

        initPreferences()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bind(this)
        presenter.getPomodoroTime(PomodoroType.WORK)
        presenter.getPomodoroTime(PomodoroType.BREAK)
    }

    override fun showWorkTime(workTime: Int) {
        val workTimePreference = findPreference("work_time")
        workTimePreference.summary = workTime.toString()
        workTimePreference.setOnPreferenceChangeListener { preference, any ->
            showError("Preference Changed")
            true
        }
    }

    override fun showRelaxTime(relaxTime: Int) {
        val relaxTimePreference = findPreference("relax_time")
        relaxTimePreference.summary = relaxTime.toString()
        relaxTimePreference.setOnPreferenceChangeListener { preference, any ->
            showError("Preference Changed")
            true
        }
    }

    override fun showDialog(messageId: Int, shouldCloseScreen: Boolean) {
        if ((activity as BaseActivity).isAlive()) {
            AlertDialog.Builder(activity).setMessage(messageId)
                    .setPositiveButton(android.R.string.ok, { dialog, _ ->
                        dialog.dismiss()
                        if (shouldCloseScreen) {
                            activity.onBackPressed()
                        }
                    }).create().show()
        }
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showError(message: String) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

    fun initPreferences() {
//        val workTimePreference = findPreference("work_time")
//        workTimePreference.summary =
//                workTimePreference.setOnPreferenceChangeListener { preference, any ->
//                    showError("Preference Changed")
//                    true
//                }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbind()
    }

    override fun onDestroy() {
        activityComponent = null
        super.onDestroy()
    }
}