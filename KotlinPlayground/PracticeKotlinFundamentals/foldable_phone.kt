fun main*(){
  
}

open class Phone(var isScreenLightOn: Boolean = false){
    fun switchOn() {
        isScreenLightOn = true
    }
    
    fun switchOff() {
        isScreenLightOn = false
    }
    
    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var isFolded: Boolean = true): Phone(isScreenLightOn = false){
  fun toggleFolded(){
    isFolded = !isFolded
    if(isFolded) super.switchOff();
    else super.switchOn();
  }
}