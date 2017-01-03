import java.math.BigInteger
import java.security.SecureRandom

object Pailler {
    val KEY_SIZE = 512

    val ONE = BigInteger("1")
    val TWO = BigInteger("2")

    val RANDOM_GENERATOR = SecureRandom()

    fun encrypt(m: BigInteger, n: BigInteger): BigInteger {
        val r = BigInteger(KEY_SIZE, RANDOM_GENERATOR)
        return (ONE + n).modPow(m, n*n) * r.modPow(n, n*n)
    }

    fun decrypt(c: BigInteger, n: BigInteger, privateKey: BigInteger): BigInteger {

        val r = c.modPow(privateKey, n)

        return ((c*r.modPow(-n, n*n) - ONE)/n).mod(n)
    }

    fun primalTestFermat(m: BigInteger): Boolean {
        return TWO.modPow(m - ONE, m) == ONE
    }

    /**
     * @return Pair of Public Key (n) and Private Key (n.modInverse(phi_n)
    */
    fun keygen(): Pair<BigInteger, BigInteger> {

        var p = BigInteger(KEY_SIZE, RANDOM_GENERATOR)
        while (!primalTestFermat(p))
            p = p.nextProbablePrime()

        var q = BigInteger(KEY_SIZE, RANDOM_GENERATOR)
        while (!primalTestFermat(q))
            q = q.nextProbablePrime()

        val n = p * q
        val phi_n = (p - ONE) * (q - ONE)

        val privateKey = n.modInverse(phi_n)

        return Pair(n, privateKey)
    }
}