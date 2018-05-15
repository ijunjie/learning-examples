package org.learningconcurrency.playground.ch1

object ex1 {
  def main(args: Array[String]): Unit = {

  }

  def compose[A, B, C](g: B => C, f: A => B):A => C = g.compose(f)
}
