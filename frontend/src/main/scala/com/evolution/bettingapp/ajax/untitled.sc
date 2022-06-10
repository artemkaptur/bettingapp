import com.evolution.bettingapp.Blabla
import io.circe.Json

val strToJson = "{\"id\":1}"

println("111111111")
val gamesEither = Json.fromString(strToJson).as[Blabla].fold(l => Left(l.printStackTrace()),  Right(_))
println("2222222")
gamesEither match {
  case Left(value)  => println("error-------->>>>"+value)
  case Right(game) => println("game-------->>>>"+game)
}
println("333333333")