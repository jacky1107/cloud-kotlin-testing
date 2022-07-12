package nextdrive.cloud.testing

import java.io.File
import java.io.IOException
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class MockFooFinder : FooFinder {

    private var target = ""
    private fun findTarget(foo: List<Foo>, label: String) {
        foo.forEach { item ->
            if (item.label == label) target = item.value ?: target
            item.children?.let { findTarget(it, label) }
        }
    }

    override fun find(label: String): String? {
        try {
            val jsonfile = File("src/main/resources/foo.json").readText()
            val mapper = jacksonObjectMapper()
            val foo: List<Foo> = mapper.readValue(jsonfile)
            findTarget(foo, label)

        } catch (ioException:IOException) {
            ioException.printStackTrace()
            return null
        }

        return target
    }

}