import java.math.BigInteger

fun main(args: Array<String>) {
    val keys: Pair<BigInteger, BigInteger> = Pailler.keygen()

    val message = BigInteger("1337")

    val cypher = Pailler.encrypt(m = message, n = keys.first)

    println(cypher)

    println(Pailler.decrypt(c = cypher, n = keys.first, privateKey = keys.second))
}
