package edu.nextstep.camp.calculator

import android.content.res.Resources
import com.github.dodobest.domain.InputHandler
import com.github.dodobest.domain.Result

class MainPresenter(
    private val view: MainContract.View,
    private val resultList: ArrayList<Result>,
    private val inputHandler: InputHandler = InputHandler()
) : MainContract.Presenter {

    override fun handleInputNum(inputNum: String) {
        inputHandler.handleInputNum(inputNum)
        view.refreshTextView(inputHandler.getString())
    }

    override fun handleInputArithmetic(inputOperation: String) {
        inputHandler.handleInputArithmetic(inputOperation)
        view.refreshTextView(inputHandler.getString())
    }

    override fun handleInputDelete() {
        inputHandler.handleInputDelete()
        view.refreshTextView(inputHandler.getString())
    }

    override fun handleEquals() {
        if (inputHandler.checkExpressionCanCalculated()) {
            val expression = inputHandler.getString()
            inputHandler.handleEquals()
            val result = inputHandler.getString()

            view.refreshTextView(result)
            resultList.add(Result(expression, "= $result"))
            return
        }

        view.showToastMessage(Resources.getSystem().getString(R.string.incomplete_expression))
    }
}