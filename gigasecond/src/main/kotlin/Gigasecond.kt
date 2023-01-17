import java.time.LocalDateTime
import java.time.LocalDate
import java.time.LocalTime
import java.time.Duration

val gigaSecond: Duration = Duration.ofSeconds(1_000_000_000)

class Gigasecond(originalDate: LocalDateTime) {

    val date: LocalDateTime = originalDate + gigaSecond
    constructor(originalDate: LocalDate): this(LocalDateTime.of(originalDate, LocalTime.MIN))
}