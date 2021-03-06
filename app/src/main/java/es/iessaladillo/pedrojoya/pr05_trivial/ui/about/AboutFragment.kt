package es.iessaladillo.pedrojoya.pr05_trivial.ui.about


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr05_trivial.R

class AboutFragment : Fragment(R.layout.about_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAppBar()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            //Para que aparezca el icono de atrás
            //Esto solo habilita el icono, pero no va hacia atrás.
            //Para que al pulsar vaya a atrás, hay que sobreescribir el método onSupportNavigateUp() EN LA ACTIVIDAD, indicándole que haga onBackPressed()
            setDisplayHomeAsUpEnabled(true)
            //Para que aparezca el título que quiero mostrar
            setTitle(R.string.about_title)
        }
    }

}
