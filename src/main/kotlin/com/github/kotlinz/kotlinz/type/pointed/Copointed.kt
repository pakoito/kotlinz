package com.github.kotlinz.kotlinz.type.pointed

import com.github.kotlinz.kotlinz.K1

interface Copointed<T> {
  fun <A> extract(v: K1<T, A>): A
}
