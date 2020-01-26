package es.iessaladillo.pedrojoya.pr05_trivial.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.title.TitleFragment

class ConfirmationDialogFragment : DialogFragment(){

    //Necesitamos un viewModel compartido por la actividad
    private val viewModel: ViewModelDialog by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }


    @Suppress("UNREACHABLE_CODE")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.main_confirmation))
            .setMessage(getString(R.string.main_quit_game))
            .setPositiveButton(getString(R.string.main_yes)) { _, _ ->
                navigationToTitle()
                viewModel.reply(true)
            }
            .setNegativeButton(getString(R.string.main_no)) { _, _ ->
                viewModel.reply(false)
            }
            .create()
    }

    private fun navigationToTitle() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fcDetail,TitleFragment())
        }
    }

    //Tenemos que crear el viewModel como una clase interna donde vamos a guardar la respuesta del dialogo
    class ViewModelDialog : androidx.lifecycle.ViewModel() {
        private val _response: MutableLiveData<Boolean> = MutableLiveData()

        val response: LiveData<Boolean>
            get() = _response

        fun reply(value: Boolean){
            _response.value = value
        }

    }


}