package nextdrive.cloud.testing

import java.io.File
import java.io.IOException
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class MockFooFinder : FooFinder {

    override fun find(label: String): String? {
        var target = ""
        try {
            val jsonfile = File("src/main/resources/foo.json").readText()
            val mapper = jacksonObjectMapper()
            val foo: List<Foo> = mapper.readValue(jsonfile)

            foo.forEach { item ->
                val temp = item.children?.find { it.label == label }
                target = temp?.value ?: target
            }
        } catch (ioException:IOException) {
            ioException.printStackTrace()
            return null
        }

        return target
    }

}