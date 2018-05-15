package org.learningconcurrency.playground.ch1

import scala.util.{Success, Try}

object ex3 {

  import org.learningconcurrency.log

  def main(args: Array[String]): Unit = {
    val x = check0(0 until 10)(40 / _ > 0)
    println(x)
  }

  def check0[T](xs: Seq[T])(pred: T => Boolean): Boolean = {
    val securePred: T => Boolean = t =>
      Try(pred(t)) match {
        case Success(v) => v
        case _ => false
      }
    xs.foldLeft(false)((r, e) => r && securePred(e))
  }

  def check1[T](xs: Seq[T])(pred: T => Boolean): Boolean =
    xs.foldLeft(true) { (r, e) =>
      val v2 = Try(pred(e)) match {
        case Success(v) => v
        case x =>
          log("error: " + x.toString)
          false
      }
      r && v2
    }


  def check2[T](xs: Seq[T])(pred: T => Boolean): Boolean =
    xs.forall(e =>
      Try(pred(e)) match {
        case Success(r) => r
        case e =>
          log(e.toString)
          false
      })
}
