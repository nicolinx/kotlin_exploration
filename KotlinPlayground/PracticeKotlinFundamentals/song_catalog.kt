fun main(){
  val popularSong = Song("Balonku", "Justin", 2006, 2000)
  val unpopularSong = Song("Bintang Kecil", "Dustin", 2016, 100)
  
  popularSong.printDetail()
  unpopularSong.printDetail()
}

class Song(val title: String, val artist: String, val yearPublished:Int, val playCount: Int){
  var isPopular = false
  get() = playCount >= 1000

  fun printDetail(){
    println("$title, performed by $artist, was released in $yearPublished. IsPopular: $isPopular");
  }
}