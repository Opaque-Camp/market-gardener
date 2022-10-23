package camp.opaque.marketgardener

const val TICKS_PER_SECOND = 20L

val Int.seconds get() = this * TICKS_PER_SECOND
