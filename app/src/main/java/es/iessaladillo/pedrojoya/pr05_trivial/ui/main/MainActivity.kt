package es.iessaladillo.pedrojoya.pr05_trivial.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.dialog.ConfirmationDialogFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.title.TitleFragment

class MainActivity : AppCompatActivity(){

    //Creacion del objeto setting para obtener los valores del preference.xml
    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if(savedInstanceState == null){
            showInitialDestination()
        }
    }


    private fun showInitialDestination() {
        showOptionsInitial()
    }

    private fun showOptionsInitial() {
        //El supportFragmentManager es el objeto importado de las dependencias de kotlin que nos proporcionara los metodos para trabajar con los fragmentos.
        supportFragmentManager.commit {
            replace(R.id.fcDetail, TitleFragment())
        }
    }

    //Indicamos que en icono de la flecha "atrás" de la appbar realmente sea ir hacia atrás
    //Por defecto tiene otra funcionalidad (ir hacia arriba) pero nosotros queremos que vaya hacia atrás
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        //Obtenemos el valor booleano de setting cada vez que pulsamos atras para saber como esta almacenado dicha configuracion
        val confirmationSwitchIsChecked = settings.getBoolean(
            getString(R.string.SwitchChk_key),
            resources.getBoolean(R.bool.prefConfirmSave_defaultValue)
        )
        //Obtenemos el hueco y comprobamos si en el esta cargado el GameFragment
        if(supportFragmentManager.findFragmentById(R.id.fcDetail) is GameFragment && confirmationSwitchIsChecked){
            showConfirmationDialog()
        }else{
            super.onBackPressed()
        }
    }

    private fun showConfirmationDialog() {
        ConfirmationDialogFragment()
            .show(supportFragmentManager, "ConfirmationDialog")
    }




}