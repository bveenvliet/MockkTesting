package com.example.mockktesting

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var mathFunctionsMock: MathFunctions
    private lateinit var mathFunctionsSpy: MathFunctions

    @Before
    fun setUp() {
        mathFunctionsMock = mockk(relaxed = true)
        mathFunctionsSpy = spyk()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2L)
    }

    @Test
    fun testWithMock1() {
        every {
            mathFunctionsMock.add(
                any(),
                any()
            )
        } answers { "2!" } andThenAnswer { "4!" } andThenAnswer { "15!" }
        assertEquals("2!", mathFunctionsMock.add(1, 1))
        assertEquals("4!", mathFunctionsMock.add(2, 2))
        assertEquals("15!", mathFunctionsMock.add(10, 5))
        assertEquals("15!", mathFunctionsMock.add(0, 0))
    }

    @Test
    fun testWithMock2() {
        // using a spy... so only override specific values 1, 1
        every { mathFunctionsSpy.add(1, 1) } answers { "App" } andThenAnswer { "Apple" }
        assertEquals("App", mathFunctionsSpy.add(1, 1))
        assertEquals("Apple", mathFunctionsSpy.add(1, 1))
        assertEquals("4", mathFunctionsSpy.add(2, 2))
        assertEquals("15", mathFunctionsSpy.add(10, 5))
    }

    @Test
    fun testWithMock3() {
        // using a mock... so it will return bar then always bat
        every { mathFunctionsMock.add(any(), any()) } answers { "Bar" } andThenAnswer { "Bat" }
        assertEquals("Bar", mathFunctionsMock.add(1, 1))
        assertEquals("Bat", mathFunctionsMock.add(1, 1))
        assertEquals("Bat", mathFunctionsMock.add(2, 2))
        assertEquals("Bat", mathFunctionsMock.add(10, 5))
    }

    @Test
    fun testWithMock4() {
        // using a mock... so only override specific values 1, 1
        every { mathFunctionsMock.add(1, 1) } answers { "Bar" } andThenAnswer { "Bat" }
        assertEquals("Bar", mathFunctionsMock.add(1, 1))
        assertEquals("Bat", mathFunctionsMock.add(1, 1))
        assertEquals("", mathFunctionsMock.add(2, 2))
        assertEquals("", mathFunctionsMock.add(10, 5))
    }

}