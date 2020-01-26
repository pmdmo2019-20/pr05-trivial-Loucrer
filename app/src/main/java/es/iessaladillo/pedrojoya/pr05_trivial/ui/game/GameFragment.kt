package es.iessaladillo.pedrojoya.pr05_trivial.ui.game

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import data.Question
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.dialog.ConfirmationDialogFragment
import kotlinx.android.synthetic.main.game_fragment.*
import ui.game_over.GameOverfragment
import ui.game_won.GameWonFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.title.TitleFragment


class GameFragment : Fragment(R.layout.game_fragment) {

    //ViewModel para el juego
    private val viewModel: GameFragmentViewModel by viewModels()

    //Para comprobar la pregunta
    private lateinit var currentQuestion: Question
    //Indice de la respuesta seleccionada se inicializa a ninguna marcada
    private var indexAnswerSelected: Int = -1


    //Creacion del objeto setting para obtener los valores del preference.xml
    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }

    //Variable  para obtener el valor del numero de preguntas desde el preference.xml,
    private val defaultQuestionsTotal by lazy {
        settings.getInt(
            getString(R.string.SeekPref_key),
            resources.getInteger(R.integer.prefConfirmQuestions_defaultValue) //Usamos resources para obtener el valor correspondiente a la key
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupAppBar()
    }




    //***Configuraciones***

    //Configuracion del frame
    private fun setupViews() {
        //Establecemos las preguntas con sus respuestas.
        currentQuestion = viewModel.getNumbersQuestions()[viewModel.currentQuestionIndex]
        makeFirstQuestion()
        btnSubmit.setOnClickListener { checkAnswer() }
    }

    //Cargar la toolbar
    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            viewModel.totalQuestions = defaultQuestionsTotal
            title = getString(R.string.game_question_title,viewModel.currentQuestionIndex+1,viewModel.totalQuestions)//Lo de viewmodel tengo que cambiarlo para que coja la preferencia del seekBar de preference.xml
        }
    }

    private fun makeFirstQuestion() {
        makeQuestion(currentQuestion)
    }



    //***Configuracion de la partida***

    private fun checkAnswer() {
        val optionSelectedId = rdnGroup.checkedRadioButtonId
        if (optionSelectedId != -1) {
            val radioGroupViewSelected: View = requireActivity().findViewById(optionSelectedId)
            indexAnswerSelected = rdnGroup.indexOfChild(radioGroupViewSelected)
            if (indexAnswerSelected+1 == currentQuestion.correctAnswer) {
                //La siguiente condicion sera para saber cuando estamos en la ultima pregunta.
                if(viewModel.currentQuestionIndex+1 != viewModel.getNumbersQuestions().count()){
                    viewModel.currentQuestionIndex++//Obtenemos el index de la siguiente pregunta.
                    currentQuestion = viewModel.getNumbersQuestions()[viewModel.currentQuestionIndex]//Establecemos dicha  pregunta para mostrarla
                    nextQuestion()//Cargamos la pregunta del currentQuestion.
                }else{
                    navigateToFragmentWin()
                }
            }else{
                navigateToFragmentLose()
            }
        } else {
            Toast.makeText(requireContext(), "No has pulsado nada", Toast.LENGTH_SHORT).show()
        }

    }


    //Metodos de navegacion entre los fragmentos
    private fun navigateToFragmentLose() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fcDetail, GameOverfragment())
        }
    }

    private fun navigateToFragmentWin() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fcDetail, GameWonFragment())
        }
    }


    //***Para la configuracion de cada pregunta***

    //Pasar siguiente pregunta
    private fun nextQuestion() {
        rdnGroup.clearCheck()//Para deseleccionar el check.
        makeQuestion(currentQuestion)
        setupAppBar()
    }


    //Crear pregunta
    private fun makeQuestion(question: Question) {
        //Question
        lblQuestion.text = question.question
        //Answers
        radBtn1.text = question.answers[0]
        radBtn2.text = question.answers[1]
        radBtn3.text = question.answers[2]
        radBtn4.text = question.answers[3]
    }

}
