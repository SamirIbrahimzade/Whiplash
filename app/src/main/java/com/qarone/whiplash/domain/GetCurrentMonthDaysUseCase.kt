package com.qarone.whiplash.domain

import com.qarone.whiplash.domain.model.ProgressDay
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.yearMonth
import kotlin.time.Clock.System.now

class GetCurrentMonthDaysUseCase {

    operator fun invoke(): List<ProgressDay> {
        val currentYearMonth = now().toLocalDateTime(TimeZone.UTC).date.yearMonth
        val numberOfDaysInCurrentMonth = currentYearMonth.numberOfDays
        val startingWeekDayOfFirstDayOfMonth = currentYearMonth.firstDay.dayOfWeek.ordinal
        val daysList = mutableListOf<ProgressDay>()

        for (i in 1..numberOfDaysInCurrentMonth) {
            val day = (startingWeekDayOfFirstDayOfMonth + i) % 7
            val weekDay = if (day == 0) 7 else day

            val week = (startingWeekDayOfFirstDayOfMonth + i) / 7
            val weekNumber = if (day == 0) week - 1 else week
            daysList.add(ProgressDay(day = i, weekNumber = weekNumber, weekDay = weekDay))
        }

        return daysList
    }
}