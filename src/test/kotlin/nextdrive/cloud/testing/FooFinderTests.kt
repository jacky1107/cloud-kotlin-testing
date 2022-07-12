package nextdrive.cloud.testing

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FooFinderTests {

    @Test
    fun `should find proper`() {
        val label = "8fc5d53a-df9e-4f73-8a36-5901e8ae0de3"
        val expected = "09a6c4a4-eecd-4445-8b26-301ab2f8f391"
        val mockFooFinder = MockFooFinder()
        val actual = mockFooFinder.find(label)
        assertEquals(expected, actual)
    }
}
