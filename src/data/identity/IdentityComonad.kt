package data.identity

import K1
import type.monad.Comonad

interface IdentityComonad: Comonad<Identity.µ>, IdentityFunctor {
  override fun <A> extract(v: K1<Identity.µ, A>): A = Identity.narrow(v).value

  override fun <A> duplicate(v: K1<Identity.µ, A>): Identity<K1<Identity.µ, A>> {
    return Identity(Identity.narrow(v))
  }

  override fun <A, B> extend(f: (K1<Identity.µ, A>) -> B, v: K1<Identity.µ, A>): Identity<B> {
    return Identity.narrow(fmap(f, duplicate(v)))
  }
}
