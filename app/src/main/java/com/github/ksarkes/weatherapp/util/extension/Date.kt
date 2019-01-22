package com.github.ksarkes.weatherapp.util.extension

import com.github.ksarkes.weatherapp.util.DateFormat
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

fun Long.humanize() = LocalDateTime
    .ofInstant(Instant.ofEpochSecond(this), ZoneId.systemDefault())
    .format(DateFormat.DEFAULT_DATE_TIME)
