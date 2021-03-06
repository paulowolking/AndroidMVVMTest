package br.com.wolking.moneyinvestmvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import kotlin.math.pow

class HomeViewModel : ViewModel() {

    val value: ObservableField<String> = ObservableField()
    val percent: ObservableField<String> = ObservableField()
    val years: ObservableField<String> = ObservableField()
    val result: ObservableField<String> = ObservableField()
    val toastShow = MutableLiveData<Boolean>()

    fun handleCalculateButtonClick() {
        calculate()
        toastShow.postValue(true)
    }

    fun calculate() {
        if (valid()) {

            val initial = value.get()?.toDouble() ?: 0.0
            val tax = (percent.get()?.toDouble() ?: 0.0) / 100
            val months = years.get()?.toInt() ?: 0

            val total = initial * (1 + tax).pow(months)
            val df = DecimalFormat("#.##")
            df.maximumFractionDigits = 0

            result.set("Total: R$ ${df.format(total)}")
        }
    }

    fun valid(): Boolean {
        return value.get() != ""
                && percent.get() != ""
                && years.get() != ""

    }
}