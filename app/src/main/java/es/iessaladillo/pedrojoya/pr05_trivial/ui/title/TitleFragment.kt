package es.iessaladillo.pedrojoya.pr05_trivial.ui.title

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.title_fragment.*
import es.iessaladillo.pedrojoya.pr05_trivial.ui.about.AboutFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import ui.rules.RulesFragment


class TitleFragment : Fragment(R.layout.title_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Indicamos que sí tiene menu propio
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //Aunque ya nos den el inflater, para inflar es exactamente igual que en la actividad.
        inflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupAppBar()
    }


    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            //Tambien tenemos que setear el nombre de la toolbar a la creacion
            //del fragmento, para cuando le demos atras en uno de los otros fragmento no se quede el titulo de los otros.
            setTitle(R.string.app_name)
        }
    }

    private fun setupViews() {
        btnStartGame.setOnClickListener {
            beginGame()
        }
    }

    //Indica qué funcionalidad tiene cada item de menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuInfo -> navigateToInfo()
            R.id.mnuRules -> navigateToRules()
            R.id.action_settings -> navigateToSettings()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun navigateToSettings() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fcDetail, SettingsFragment())
            addToBackStack("")
        }
    }

    private fun navigateToRules() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fcDetail, RulesFragment())
            addToBackStack("")
        }
    }

    private fun navigateToInfo() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fcDetail, AboutFragment())
            addToBackStack("")
        }
    }


    private fun beginGame() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fcDetail, GameFragment())
            addToBackStack("")
        }
    }

}
