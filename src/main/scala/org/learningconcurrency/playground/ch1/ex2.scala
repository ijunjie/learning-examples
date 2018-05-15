package org.learningconcurrency.playground.ch1

object ex2 {
  def main(args: Array[String]): Unit = {
    val r = fuse2(Option(1), Option(2))
    println(r)
  }

  def fuse[A, B](a: Option[A], b: Option[B]): Option[(A, B)] =
    for {
      x <- a
      y <- b
    } yield (x, y)

  def fuse2[A, B](a: Option[A], b: Option[B]): Option[(A, B)] =
    a.flatMap(x => b.map(y => (x, y)))
}
