package com.andresual.nexmedisassesment.presentation.screen

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.andresual.nexmedisassesment.R
import com.andresual.nexmedisassesment.databinding.ActivityMainBinding
import com.andresual.nexmedisassesment.util.Constants
import com.andresual.nexmedisassesment.util.isDarkColor
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (getIsFirstLaunch()) showAlertDialog()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavBar.setupWithNavController(navController)

        updateThemeAndStatusBarOnDestinationChange(binding, navController)
    }

    private fun getIsFirstLaunch(): Boolean = runBlocking {
        dataStore.data.first()[PreferencesKey.IS_FIRST_LAUNCH] ?: true
    }

    private fun setIsFirstLaunch(): Unit = runBlocking {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.IS_FIRST_LAUNCH] = false
        }
    }

    private fun showAlertDialog() {
        MaterialAlertDialogBuilder(this)
            .setIcon(AppCompatResources.getDrawable(this, R.drawable.ic_launcher_background))
            .setTitle(getString(R.string.assesment_attribution_title))
            .setMessage(getString(R.string.assesment_attribution_message))
            .setPositiveButton(getString(R.string.assesment_attribution_button_text)) { dialog, _ ->
                setIsFirstLaunch()
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun updateThemeAndStatusBarOnDestinationChange(
        binding: ActivityMainBinding,
        navController: NavController
    ) {
        with(WindowInsetsControllerCompat(window, window.decorView)) {
            navController.addOnDestinationChangedListener { _, destination, bundle ->
                window.statusBarColor = when (destination.id) {
                    R.id.homeFragment, R.id.favoriteFragment -> {
                        binding.bottomNavBar.visibility = View.VISIBLE
                        setTheme(R.style.Theme_MealApp)
                        WindowCompat.setDecorFitsSystemWindows(window, true)
                        isAppearanceLightStatusBars = true

                        when (Build.VERSION.SDK_INT) {
                            21, 22 -> Color.BLACK
                            else -> ContextCompat.getColor(this@MainActivity, R.color.day_night_inverse)
                        }
                    }

                    else -> {
                        binding.bottomNavBar.visibility = View.GONE
                        val backgroundColor = bundle?.getInt(Constants.BACKGROUND_COLOR) ?: 0
                        val isDarkBackground = backgroundColor.isDarkColor()

                        window.statusBarColor = backgroundColor
                        WindowCompat.setDecorFitsSystemWindows(window, false)
                        setTheme(if (isDarkBackground) R.style.DetailDarkTheme else R.style.DetailLightTheme)
                        isAppearanceLightStatusBars = !isDarkBackground
                        Color.TRANSPARENT
                    }
                }
            }
        }
    }

    private object PreferencesKey {
        val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
    }
}