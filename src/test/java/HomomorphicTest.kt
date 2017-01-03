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

    /*
    @Test
    fun testHomomorphicConstantMultiplication() {
        val plainA = BigInteger.valueOf(14)
        val plainB = BigInteger.valueOf(203)

        val encryptedA = publicKey!!.encrypt(plainA)

        val decryptedPow = keypair!!.decrypt(encryptedA.modPow(plainB,
                publicKey!!.getnSquared()))
        val plainSum = plainA.multiply(plainB).mod(publicKey!!.getN())

        assertEquals(decryptedPow, plainSum)
    }

    @Test
    fun testHomomorphicMultiplication() {
        val plainA = BigInteger.valueOf(23)
        val plainB = BigInteger.valueOf(234)

        val encryptedA = publicKey!!.encrypt(plainA)
        val decryptedPowA = keypair!!.decrypt(encryptedA.modPow(
                plainB, publicKey!!.getnSquared()))
        val plainSumA = plainA.multiply(plainB).mod(publicKey!!.getN())

        assertEquals(decryptedPowA, plainSumA)

        val encryptedB = publicKey!!.encrypt(plainB)
        val decryptedPowB = keypair!!.decrypt(encryptedB.modPow(
                plainA, publicKey!!.getnSquared()))
        val plainSumB = plainA.multiply(plainB).mod(publicKey!!.getN())

        assertEquals(decryptedPowB, plainSumB)

        assertEquals(decryptedPowA, decryptedPowB)
    }

    @Test
    fun testHomomorphicMultiplicationPowG() {
        val plainA = BigInteger.valueOf(230)
        val plainB = BigInteger.valueOf(100)

        val g = publicKey!!.getG()

        val encryptedA = publicKey!!.encrypt(plainA)
        val decryptedPow = keypair!!.decrypt(encryptedA.multiply(g.modPow(
                plainB, publicKey!!.getnSquared()).mod(publicKey!!.getnSquared())))

        val plainSumA = plainA.add(plainB).mod(publicKey!!.getN())

        assertEquals(decryptedPow, plainSumA)
    }
    */
}
