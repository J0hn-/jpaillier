import org.junit.Assert.assertEquals

import java.math.BigInteger

import org.junit.Before
import org.junit.Test

class HomomorphicTest {

    private var keys: Pair<BigInteger, BigInteger> = Pailler.keygen()

    @Before
    fun init() {
        this.keys = Pailler.keygen()
    }

    @Test
    fun testHomomorphicAddition() {
        val plainA = BigInteger.valueOf(102)
        val plainB = BigInteger.valueOf(203)

        val encryptedA = Pailler.encrypt(plainA, keys.first)
        val encryptedB = Pailler.encrypt(plainB, keys.first)

        val decryptedProduct = Pailler.decrypt(c = encryptedA * encryptedB % (keys.first* keys.first),
                n = keys.first, privateKey = keys.second)
        val plainSum = (plainA + plainB) % (keys.first)

        assertEquals(decryptedProduct, plainSum)
    }

    @Test
    fun testHomomorphicConstantMultiplication() {
        val plainA = BigInteger.valueOf(14)
        val plainB = BigInteger.valueOf(203)

        val encryptedA = Pailler.encrypt(plainA, keys.first)

        val decryptedPow = Pailler.decrypt(encryptedA.modPow(plainB, keys.first*keys.first), keys.first, keys.second)
        val plainSum = (plainA * plainB) % keys.first

        assertEquals(decryptedPow, plainSum)
    }

    @Test
    fun testHomomorphicMultiplication() {
        val plainA = BigInteger.valueOf(23)
        val plainB = BigInteger.valueOf(234)

        val encryptedA = Pailler.encrypt(plainA, keys.first)
        val decryptedPowA = Pailler.decrypt(encryptedA.modPow(plainB, keys.first*keys.first), keys.first, keys.second)
        val plainSumA = (plainA * plainB) % keys.first

        assertEquals(decryptedPowA, plainSumA)

        val encryptedB = Pailler.encrypt(plainB, keys.first)
        val decryptedPowB = Pailler.decrypt(encryptedB.modPow(plainA, keys.first*keys.first), keys.first, keys.second)
        val plainSumB = (plainA * plainB) % keys.first

        assertEquals(decryptedPowB, plainSumB)

        assertEquals(decryptedPowA, decryptedPowB)
    }

}
