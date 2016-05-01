package data.state

import K1
import K2

class State<S, A>(val value: (S) -> Pair<A, S>): K2<State.µ, S, A> {
  class µ {}

  companion object {
    fun <A, S> narrow(v: K1<K1<µ, S>, A>) = v as State<S, A>
    fun <A, S> pure(v: A): State<S, A> = (object: StateMonad<S> {}).pure(v)
  }

  infix fun <B> fmap(f: (A) -> B): State<S, B> = monad.fmap(f, this)
  infix fun <B> ap(f: State<S, (A) -> B>): State<S, B> = monad.ap(f, this)
  infix fun <B> bind(f: (A) -> State<S, B>): State<S, B> = monad.bind(f, this)

  private val monad = object: StateMonad<S> {}
}