import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {
    var deviceStatus = "online"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }

    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)
    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun decreaseSpeakerVolume() {
        speakerVolume--
        println("Speaker volume decreased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }

    fun previousChannel() {
        channelNumber--
        println("Channel number decreased to $channelNumber.")
    }

    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }

    fun decreaseBrightness() {
        brightnessLevel--
        println("Brightness decreased to $brightnessLevel.")
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off")
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {
    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        if (smartTvDevice.deviceStatus == "on") return
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        if (smartTvDevice.deviceStatus != "on") return
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        if (smartTvDevice.deviceStatus != "on") return
        smartTvDevice.increaseSpeakerVolume()
    }

    fun decreaseTvVolume() {
        if (smartTvDevice.deviceStatus != "on") return
        smartTvDevice.decreaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        if (smartTvDevice.deviceStatus != "on") return
        smartTvDevice.nextChannel()
    }

    fun changeTvChannelToPrevious() {
        if (smartTvDevice.deviceStatus != "on") return
        smartTvDevice.previousChannel()
    }

    fun turnOnLight() {
        if (smartLightDevice.deviceStatus == "on") return
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        if (smartLightDevice.deviceStatus != "on") return
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        if (smartLightDevice.deviceStatus != "on") return
        smartLightDevice.increaseBrightness()
    }

    fun decreaseLightBrightness() {
        if (smartLightDevice.deviceStatus != "on") return
        smartLightDevice.decreaseBrightness()
    }

    fun printSmartTvInfo() {
        smartTvDevice.printDeviceInfo()
    }

    fun printSmartLightInfo() {
        smartLightDevice.printDeviceInfo()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    val tv = SmartTvDevice("Android TV", "Entertainment")
    val lampu = SmartLightDevice("Google Light", "Utility")
    val home = SmartHome(tv, lampu)

    println("=== STARTING COMPREHENSIVE UNIT TESTS ===")
    println("----------------------------------------")

    // ==========================================
    // 1. INITIAL STATE TESTS (HAPPY PATH)
    // ==========================================
    val test1 = home.deviceTurnOnCount == 0
    printResult("Initial turn-on count must be 0", test1, "0", "${home.deviceTurnOnCount}")

    // ==========================================
    // 2. EVIL PATH: ABUSE OFF BUTTONS AT START
    // ==========================================
    home.turnOffTv()
    home.turnOffTv()
    val test2 = home.deviceTurnOnCount == 0
    printResult("Turning off an already OFF TV multiple times (Count must stay 0)", test2, "0", "${home.deviceTurnOnCount}")

    // ==========================================
    // 3. HAPPY PATH: NORMAL TURN ON
    // ==========================================
    home.turnOnTv()
    val test3 = home.deviceTurnOnCount == 1 && tv.deviceStatus == "on"
    printResult("Turn ON TV normally (Count becomes 1, Status 'on')", test3, "Count: 1, Status: on", "Count: ${home.deviceTurnOnCount}, Status: ${tv.deviceStatus}")

    // ==========================================
    // 4. EVIL PATH: ABUSE ON BUTTONS
    // ==========================================
    home.turnOnTv()
    val test4 = home.deviceTurnOnCount == 1
    printResult("Turning ON an already ON TV (Count must stay 1, no double counting)", test4, "1", "${home.deviceTurnOnCount}")

    // ==========================================
    // 5. HAPPY PATH: TURN ON SECOND DEVICE
    // ==========================================
    home.turnOnLight()
    val test5 = home.deviceTurnOnCount == 2 && lampu.deviceStatus == "on"
    printResult("Turn ON Light normally (Count becomes 2, Both devices 'on')", test5, "Count: 2", "Count: ${home.deviceTurnOnCount}")

    // ==========================================
    // 6. EVIL PATH: ABUSE LIGHT ON BUTTON
    // ==========================================
    home.turnOnLight()
    val test6 = home.deviceTurnOnCount == 2
    printResult("Turning ON an already ON Light (Count must stay 2)", test6, "2", "${home.deviceTurnOnCount}")

    // ==========================================
    // 7. HAPPY PATH: MASS TURN OFF
    // ==========================================
    home.turnOffAllDevices()
    val test7 = home.deviceTurnOnCount == 0 && tv.deviceStatus == "off" && lampu.deviceStatus == "off"
    printResult("Call turnOffAllDevices() (Count must reset to 0, All status 'off')", test7, "Count: 0, TV: off, Light: off", "Count: ${home.deviceTurnOnCount}, TV: ${tv.deviceStatus}, Light: ${lampu.deviceStatus}")

    // ==========================================
    // 8. EVIL PATH: ABUSE MASS TURN OFF WHEN ALREADY OFF
    // ==========================================
    home.turnOffAllDevices()
    val test8 = home.deviceTurnOnCount == 0
    printResult("Call turnOffAllDevices() again when everything is already OFF (Count must stay 0)", test8, "0", "${home.deviceTurnOnCount}")

    println("----------------------------------------")
    println("=== TESTS COMPLETED ===")
}

fun printResult(testName: String, isPassed: Boolean, expected: String, actual: String) {
    if (isPassed) {
        println("✅ PASSED | $testName")
    } else {
        println("❌ FAILED | $testName")
        println("          | Expected : $expected")
        println("          | Actual   : $actual")
    }
}