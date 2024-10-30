package ru.netology.nmedia.dto

import java.text.DecimalFormat

object Count {

    fun formatNumber(number: Int): String {
        return when {
            number < 1000 -> number.toString()
            number < 10_000 -> {
                val decimalPart = (number % 1000) / 100
                val integerPart = number / 1000
                if (decimalPart == 0) {
                    String.format("%dK", integerPart)
                } else {
                    String.format("%d,%dK", integerPart, decimalPart)
                }
            }

            number < 1_000_000 -> {
                val decimalPart = (number % 1000) / 100
                val integerPart = number / 1000
                if (decimalPart == 0) {
                    String.format("%dK", integerPart)
                } else {
                    String.format("%dK", integerPart, decimalPart)
                }
            }

            number < 100_000_000 -> {
                val decimalPart = (number % 1_000_000) / 100_000
                val integerPart = number / 1_000_000
                if (decimalPart == 0) {
                    String.format("%dM", integerPart)
                } else {
                    String.format("%d,%dM", integerPart, decimalPart)
                }
            }

            else -> String.format("%.1fM", number / 1_000_000.0)
        }
    }
}


























  //  fun CountLike(like: Int):String {
  //      return when (like){
  //          in 0..999 -> like.toString()
  //          in 1_000..1_099 -> "1K"
  //          in 1_100..9_999 -> calcLike(like, 1)+"K"
  //          in 10_000..999_999 -> calcLike(like, 0)+"K"
  //          in 1_000_000..999_999_999 -> calcLike(like, 1)+"M"
   //         else -> "Более 1 Billion"
  //      }
  //  }
  //  fun CountSharung(sharing: Int):String {
  //      return when (sharing){
   //         in 0..999 -> sharing.toString()
  //          in 1_000..1_099 -> "1K"
 //           in 1_100..9_999 -> calcLike(sharing, 1)+"K"
 //           in 10_000..999_999 -> calcLike(sharing, 0)+"K"
 //           in 1_000_000..999_999_999 -> calcLike(sharing, 1)+"M"
 //           else -> "Более 1 Billion"
 //       }
 //   }
 //   fun CountViews(views:Int):String{
 //       return when (views){
  //          in 0..999 -> views.toString()
 //           in 1_000..1_099 -> "1K"
 //           in 1_100..9_999 -> calcLike(views, 1)+"K"
  //          in 10_000..999_999 -> calcLike(views, 0)+"K"
  //          in 1_000_000..999_999_999 -> calcLike(views, 1)+"M"
 //           else -> "Более 1 Billion"
  //      }
  //  }

   // fun calcLike(like: Int, places: Int): String {
   //      //при больших цифрах, отображаемое округление
    //    val df: DecimalFormat
    //
    //    if (places==1){
    //         df = DecimalFormat("###.#")
    //     }else{
    //           df = DecimalFormat("###")
   //       }
    //      val liked: Double
    //      liked = like.toDouble() / 1000
    //       return df.format(liked)
    //   }


