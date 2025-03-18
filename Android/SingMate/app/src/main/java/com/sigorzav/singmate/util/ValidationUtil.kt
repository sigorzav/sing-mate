package com.sigorzav.singmate.util

import java.util.Calendar

object ValidationUtil {

    /**
     * 생년월일(YYYYMMDD) 유효성 검사
     */
    fun isValidBirthDate(birthDate: String): Boolean {
        if (birthDate.length != 8) return false

        val year = birthDate.substring(0, 4).toIntOrNull() ?: return false
        val month = birthDate.substring(4, 6).toIntOrNull() ?: return false
        val day = birthDate.substring(6, 8).toIntOrNull() ?: return false

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        if (year !in 1900..currentYear) return false // 연도 범위 체크

        if (month !in 1..12) return false // 월 범위 체크

        val daysInMonth = when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (isLeapYear(year)) 29 else 28
            else -> return false
        }

        return day in 1..daysInMonth // 일 범위 체크
    }

    /**
     * 윤년 체크 함수
     */
    fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    /**
     * 생년월일(YYYYMMDD)로 나이 계산
     */
    fun calculateAge(birthDate: String): Int {
        if (!isValidBirthDate(birthDate)) return -1

        val birthYear = birthDate.substring(0, 4).toInt()
        val birthMonth = birthDate.substring(4, 6).toInt()
        val birthDay = birthDate.substring(6, 8).toInt()

        val today = Calendar.getInstance()
        val currentYear = today.get(Calendar.YEAR)
        val currentMonth = today.get(Calendar.MONTH) + 1
        val currentDay = today.get(Calendar.DAY_OF_MONTH)

        var age = currentYear - birthYear
        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
            age--
        }

        return age
    }

    /**
     * 이메일 유효성 검사 (기본적인 정규식 사용)
     */
    fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
    }

    /**
     * 비밀번호 유효성 검사 (영문 + 숫자 조합, 최소 8자 이상)
     */
    fun isValidPassword(password: String): Boolean {
        return password.length >= 8 && password.any { it.isDigit() } && password.any { it.isLetter() }
    }

    /**
     * 닉네임 유효성 검사 (특수문자 제한, 최소 2자 이상)
     */
    fun isValidNickname(nickname: String): Boolean {
        return nickname.length >= 2 && nickname.matches(Regex("^[A-Za-z0-9가-힣]+$"))
    }
}