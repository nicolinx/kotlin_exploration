fun main() {    
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)
    
    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
      println("Name: $name")
      println("Age: $age")
      println("Likes to play $hobby. ${if(referrer == null) "Doesn't have a referrer." else "Has a referrer named ${referrer.name}, who likes to play ${referrer.hobby}."}")
    }
}


// Name: Amanda
// Age: 33
// Likes to play tennis. Doesn't have a referrer.

// Name: Atiqah
// Age: 28
// Likes to climb. Has a referrer named Amanda, who likes to play tennis.