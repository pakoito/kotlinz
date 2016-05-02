package data.identity

import K1

data class Identity<A>(val value: A): K1<Identity.µ, A> {
  class µ {}

  companion object {
    fun <A> narrow(i: K1<µ, A>): Identity<A> = i as Identity<A>

    // Monad
    fun <A> pure(v: A): Identity<A> = monad.pure(v)
    fun <A> join(v: K1<µ, K1<µ, A>>): Identity<A> = monad.join(v)
    private val monad = object: IdentityMonad {}

    // ApplicativeOps
    fun <A, B> liftA(f: (A) -> B) = applicativeOps.liftA(f)
    fun <A, B, C> liftA2(f: (A, B) -> C) = applicativeOps.liftA2(f)
    fun <A, B, C, D> liftA3(f: (A, B, C) -> D) = applicativeOps.liftA3(f)
    private val applicativeOps = object: IdentityApplicativeOps {}

    // MonadOps
    fun <A, B> liftM(f: (A) -> B) = monadOps.liftM(f)
    fun <A, B, C> liftM2(f: (A,  B) -> C) =  monadOps.liftM2(f)
    fun <A, B, C, D> liftM3(f: (A, B, C) -> D) =  monadOps.liftM3(f)
    private val monadOps = object: IdentityMonadOps {}
  }

  // Copointed
  fun extract(): A = copointed.extract(this)
  private val copointed = object: IdentityCopointed {}

  // Monad
  infix fun <B> fmap(f: (A) -> B): Identity<B> = monad.fmap(f, this)
  infix fun <B> ap(f: Identity<(A) -> B>): Identity<B> = monad.ap(f, this)
  infix fun <B> bind(f: (A) -> Identity<B>): Identity<B> = monad.bind(f, this)
  private val monad = object: IdentityMonad {}
}

