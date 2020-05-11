package ss.app1.model

import org.junit.Assert.fail
import org.junit.Test
import ss.app1.util.VF

//fun profile(f:  () -> Unit) {
fun profileLame(f: VF) {
    val t1 = System.currentTimeMillis()
    f()
    val t2 = System.currentTimeMillis()
    println("Delta: " + (t2 - t1))
}


fun profileInt(f: () -> Int): Int {
    val t1 = System.currentTimeMillis()
    val retVal = f()
    val t2 = System.currentTimeMillis()
    println("Delta: " + (t2 - t1))
    return retVal
}
//List<Int>


fun <T> profile(f: () -> T): T {
    val t1 = System.currentTimeMillis()
    val retVal = f()
    val t2 = System.currentTimeMillis()
    println("Delta: " + (t2 - t1))
    return retVal
}

fun ass(test:Boolean,errorMessage:String){
    if(!test){
        throw IllegalArgumentException(errorMessage)
    }
}

class LambdaTest {

    @Test
    fun lambda() {
//        profileLame { doStuff() }  //333ms
//        profileInt(  computeStuff(5)  )  //333ms
//        profileInt  { computeStuff(5) }    //333ms


        profile { doStuff() }  //333ms
        profile  { computeStuff(5) }    //333ms
        profile  { computeStr(5) }    //333ms

        ass(3 < 10,"Bad value")

        try {
            ass(10 < 3,"Bad value")
            fail()
        }
        catch (e:IllegalArgumentException){
            //ok
        }

    }


    fun doStuff():Unit {
        println("stuff")
    }

    fun computeStuff(x: Int): Int {
        println("computeStuff")
        return x * x
    }
    fun computeStr(x: Int): String {
        println("computeStuff")
        return "Result:  ${x * x}"
    }
    val computeStuff2:(Int)->Int = { it:Int ->
        it * it
    }


    val computeStuff3:(Int)->Int = { it * it }


}