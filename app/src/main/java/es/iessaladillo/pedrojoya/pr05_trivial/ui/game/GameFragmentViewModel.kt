package es.iessaladillo.pedrojoya.pr05_trivial.ui.game

import androidx.lifecycle.ViewModel
import data.Question
import data.*

class GameFragmentViewModel : ViewModel() {


    //.Shuffler para barajar las preguntas
    private val questionList :List<Question> = listOf(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10, question11, question12, question13, question14, question15, question16, question17, question18, question19, question20).shuffled()

    var currentQuestionIndex: Int = 0

    var totalQuestions: Int = 10 //Con esta variable guardamos el total de las preguntas establecido en el settings

    /*Metodo para que cargue la lista de un numero de preguntas configuradas en el settings*/
    fun  getNumbersQuestions(): List<Question> {
        return questionList.subList(0, totalQuestions)
    }

}
