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
      if(hobby != null) {
        print("Likes to $hobby. ")
      }
      if(referrer != null) {
        print("Has a referrer named ${referrer.name}")
        if(referrer.hobby != null) {
            print(", who likes to ${referrer.hobby}.")
        } else {
            print(".")
        }
      } else {
        print("Doesn't have a referrer.")
      }

      print("\n\n")
    }
}


// Name: Amanda
// Age: 33
// Likes to play tennis. Doesn't have a referrer.

// Name: Atiqah
// Age: 28
// Likes to climb. Has a referrer named Amanda, who likes to play tennis.