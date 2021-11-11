//package com.app.myapplication.utils
//
//import android.os.FileUtils
//import android.util.Base64
//import java.io.File
//import java.io.FileOutputStream
//import java.util.*
//import javax.swing.text.Utilities
//
//
//object EncodedUtils {
//
//    const val ONE_KB: Long = 1024
//
//    /**
//     * The number of bytes in a megabyte.
//     */
//    const val ONE_MB = ONE_KB * ONE_KB
//
//    /**
//     * The number of bytes in a gigabyte.
//     */
//    const val ONE_GB = ONE_KB * ONE_MB
//
//     fun encodeFileToBase64Binary(audioFile: File): String? {
////        val size = yourFile.length().toInt()
////        val bytes = ByteArray(size)
////        try {
////            val buf = BufferedInputStream(FileInputStream(yourFile))
////            buf.read(bytes, 0, bytes.size)
////            buf.close()
////        } catch (e: FileNotFoundException) {
////            e.printStackTrace()
////        } catch (e: IOException) {
////            e.printStackTrace()
////        }
////        return Base64.encodeToString(bytes, Base64.NO_WRAP)
//
//
//
//         val bytes: ByteArray = FileUtils.readFileToByteArray(audioFile)
//
//         val encoded = Base64.encodeToString(bytes, 0)
//       //  javax.swing.text.Utilities.log("Encoded String: ", encoded)
//
//         val decoded = Base64.decode(encoded, 0)
//         //javax.swing.text.Utilities.log("Decoded String: ", Arrays.toString(decoded))
//
//         try {
//             val file2 = File("fileName.wav")
//             val os = FileOutputStream(file2, true)
//             os.write(decoded)
//             os.close()
//         } catch (e: Exception) {
//             e.printStackTrace()
//         }
//
//
//     }
//
//
//}