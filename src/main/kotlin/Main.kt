import com.google.gson.Gson
import java.net.URL



fun main(args: Array<String>) {
    var response = URL("https://ddragon.leagueoflegends.com/cdn/13.17.1/data/en_US/champion.json").readText()
    var gson = Gson()
    var responseJSONObject = gson.fromJson(response, Response::class.java)
    var championJSONObject = responseJSONObject.data?.getAsJsonObject("Aatrox")

        println(championJSONObject?.get("title"))

  //  var data = gson.fromJson(response, Array<Response>:: class.java)
  //  for (x in 0 until data.size)
  //      print(data[x].data)
   // println("Program arguments: ${args.joinToString()}")
}