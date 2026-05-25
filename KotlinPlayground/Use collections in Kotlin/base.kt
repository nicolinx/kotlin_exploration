val rockPlanets = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")
val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")

val solarSystem = rockPlanets + gasPlanets

fun main(){
  // val solarSystem = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
  // println(solarSystem.size) 
  // println(solarSystem[2])
  // println(solarSystem.get(3))

  // println(solarSystem.indexOf("Earth"))
  // println(solarSystem.indexOf("Pluto"))

  // for (planet in solarSystem) {
  //   println(planet)
  // }

  // val solarSystem = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
  // solarSystem.add("Pluto")
  // solarSystem.add(3, "Theia")

  // solarSystem[3] = "Future Moon"
  // println(solarSystem[3])
  // println(solarSystem[9])

  // solarSystem.removeAt(9)
  // solarSystem.remove("Future Moon")
  // println(solarSystem.contains("Pluto"))
  // println("Future Moon" in solarSystem)

  // SET
  // val solarSystem = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
  // println(solarSystem.size)
  // solarSystem.add("Pluto")
  // println(solarSystem.size)
  // println(solarSystem.contains("Pluto"))

  // solarSystem.add("Pluto")
  // println(solarSystem.size)
  // solarSystem.remove("Pluto")
  // println(solarSystem.size)
  // println(solarSystem.contains("Pluto"))

  // MAP
  val solarSystem = mutableMapOf(
    "Mercury" to 0,
    "Venus" to 0,
    "Earth" to 1,
    "Mars" to 2,
    "Jupiter" to 79,
    "Saturn" to 82,
    "Uranus" to 27,
    "Neptune" to 14
  )

  println(solarSystem.size)
  solarSystem["Pluto"] = 5
  println(solarSystem.size)
  println(solarSystem["Pluto"])
  println(solarSystem.get("Theia"))

  solarSystem["Jupiter"] = 78
  println(solarSystem["Jupiter"])
}