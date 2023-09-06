import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import java.net.URL


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

    val randomizer = Randomizer()
    println(myChampionArray[randomizer.randomize()])

}