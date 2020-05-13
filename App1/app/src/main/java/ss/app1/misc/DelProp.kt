package ss.app1.misc

import kotlin.reflect.KProperty

data class Person(var map: MutableMap<String, Any?> = mutableMapOf()) {

    var firstName: String by map
    var lastName: String by map
    var age: String by map

    var x: String by Delegate()
    var y: String by Delegate()
    var z: String by Delegate()



}



fun main(args: Array<String>) {
    val p = Person()
    p.firstName = "Dave"
    p.lastName = "Ford"
    println(p.lastName + " " + p.firstName)

    println(p.x)

}




class Delegate {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }

}


class AppCtx {

//
//    get() {
//        someOtherOieceOfCode()
//    }
//    set(value) {
//        someOtherOieceOfCode(value)
//    }

//    val dataSource:DataSource by lazy {
//        MysqlDataSourse()
//    }
//
//    val entityManager:EventListener by lazy {
//        EntMgr(dataSource)
//    }


//    var dataSourse:DataSource? = null
//
//    fun getDataSource():DataSource{
//        if(dataSourse == null){
//            dataSourse =  MysqlDataSourse()
//        }
//        return dataSourse
//    }


}