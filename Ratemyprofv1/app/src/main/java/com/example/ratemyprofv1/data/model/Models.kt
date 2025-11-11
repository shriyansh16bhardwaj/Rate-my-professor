package com.example.ratemyprofv1.data.model

data class AuthRequest(
    val email: String? = null,
    val username: String? = null,
    val password: String? = null,
    val otp: String? = null,
    val fullName: String? = null
)

data class AuthResponse(
    val success: Boolean,
    val token: String? = null,
    val message: String? = null
)

data class Professor(
    val id: String,
    val name: String,
    val department: String,
    val averageRating: Double,
    val averages: RatingBreakdown
)

data class RatingBreakdown(
    val teachingQuality: Double,
    val clarity: Double,
    val helpfulness: Double
)

data class Review(
    val id: String,
    val professorId: String,
    val teachingQuality: Int,
    val clarity: Int,
    val helpfulness: Int,
    val text: String?,
    val createdAt: String
)

data class SubmitReviewRequest(
    val teachingQuality: Int,
    val clarity: Int,
    val helpfulness: Int,
    val text: String?
)

