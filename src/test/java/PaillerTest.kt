import org.junit.Before
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class PaillerTest {
    internal var keys: Pair<BigInteger, BigInteger>? = null
    internal val plainData = BigInteger.valueOf(1337)

    @Before
    fun init() {
        keys = Pailler.keygen()
    }

    @Test
    fun testEncryption() {
        val encryptedData = Pailler.encrypt(m = plainData, n = keys!!.first)

        assertNotEquals(plainData, encryptedData)
    }

    @Test
    fun testDecyption() {


        val encryptedData = Pailler.encrypt(m = plainData, n = keys!!.first)
        val decryptedData = Pailler.decrypt(c = encryptedData, n = keys!!.first, privateKey = keys!!.second)

        assertEquals(plainData, decryptedData)
    }
}