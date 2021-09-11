package br.com.wolking.moneyinvestmvvm

import br.com.wolking.moneyinvestmvvm.viewmodel.HomeViewModel
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class HomeFragmentViewModelTest {
    private val homeViewModel: HomeViewModel = HomeViewModel()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun isValidReturnTrueTest() {
        homeViewModel.value.set("100")
        homeViewModel.percent.set("10")
        homeViewModel.years.set("2")
        assertTrue(homeViewModel.valid())
    }

    @Test
    fun isValidReturnFalseTest() {
        homeViewModel.value.set("")
        homeViewModel.percent.set("")
        homeViewModel.years.set("")
        assertFalse(homeViewModel.valid())
    }

    @Test
    fun validHandleCalculation() {
        homeViewModel.value.set("100")
        homeViewModel.percent.set("10")
        homeViewModel.years.set("2")
        homeViewModel.calculate()
        assertEquals("Total: R$ 121", homeViewModel.result.get())
    }

    @Test
    fun handleCalculateButtonClickCallHandleCalculate() {
        homeViewModel.value.set("100")
        homeViewModel.percent.set("10")
        homeViewModel.years.set("2")
        val spy = spy(homeViewModel)
        spy.handleCalculateButtonClick()
        verify(spy, Mockito.times(1)).calculate()
    }
}