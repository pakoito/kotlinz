package data.maybe

import K1
import type.monad.Monad

interface MaybeMonad: Monad<Maybe.µ>, MaybeApplicative, MaybeFunctor {
  override fun <A> join(v: K1<Maybe.µ, K1<Maybe.µ, A>>): Maybe<A> {
    val maybe = Maybe.narrow(v)
    return when (maybe) {
      is Maybe.Just -> Maybe.narrow(maybe.value)
      is Maybe.None -> Maybe.None()
    }
  }

  override fun <A, B> bind(f: (A) -> K1<Maybe.µ, B>, v: K1<Maybe.µ, A>): Maybe<B> {
    return Maybe.narrow(super.bind(f, v))
  }
}