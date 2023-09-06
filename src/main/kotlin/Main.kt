import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


fun main(args: Array<String>) {
    // grab all champions response from Riot API
    var response = URL("https://ddragon.leagueoflegends.com/cdn/13.17.1/data/en_US/champion.json").readText()
    var gson = Gson()
    var responseJSONObject = gson.fromJson(response, Response::class.java)

    // convert json string response into a list
    val iter: MutableSet<String>? = responseJSONObject.data?.keySet()
    val jsonArrayString = gson.toJson(iter)

    // converts jsonArray string into a jsonArray
    val myChampionGson = GsonBuilder().create()
    val myChampionArray = myChampionGson.fromJson<ArrayList<String>>(jsonArrayString, object : TypeToken<ArrayList<String>>(){}.type)


    // Create an object of scanner class
    var stopper: Boolean = true

    while(stopper) {
        val randomizer = Randomizer()
        val randomChampionNumber: Int = randomizer.randomize()

        val selectedChampionInfo = gson.fromJson(responseJSONObject.data?.get(myChampionArray[randomChampionNumber]), Champion::class.java)
        val selectedChampionName: String = myChampionArray[randomChampionNumber].uppercase()

        var input = Scanner(System.`in`)
        println("Guess my Champion:")
        println(selectedChampionInfo.title)
        print("Enter: ")

        var championGuess: String = input.next().uppercase()
        if (championGuess == selectedChampionName) {
            println("You got it! Try again?: ")
            input = Scanner(System.`in`)
            if (input.next().uppercase() == "NO")
                stopper = false
        }
        else {
            println("You got it wrong, want to try again?")
            if (input.next().uppercase() == "NO")
                stopper = false
        }
    }

}