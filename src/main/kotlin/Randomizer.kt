import kotlin.random.Random

class Randomizer {
    fun randomize(): Int {
        return Random.nextInt(0, 163)
    }
}