package com.example.ratemyprofv1.data

import com.example.ratemyprofv1.data.model.*
import com.example.ratemyprofv1.data.remote.ApiService

class Repository(private val api: ApiService) {
    suspend fun register(fullName: String, email: String, username: String, password: String) =
        api.register(AuthRequest(fullName = fullName, email = email, username = username, password = password))

    suspend fun verifyOtp(email: String, otp: String) =
        api.verifyOtp(AuthRequest(email = email, otp = otp))

    suspend fun login(username: String, password: String) =
        api.login(AuthRequest(username = username, password = password))

    suspend fun getProfessors(query: String?) = api.getProfessors(query)

    suspend fun getProfessorDetails(id: String) = api.getProfessorDetails(id)

    suspend fun submitReview(
        professorId: String,
        teaching: Int,
        clarity: Int,
        helpfulness: Int,
        text: String?
    ) = api.submitReview(
        professorId,
        SubmitReviewRequest(teaching, clarity, helpfulness, text)
    )

    suspend fun getMyReviews(userId: String) = api.getMyReviews(userId)

    suspend fun editReview(reviewId: String, teaching: Int, clarity: Int, helpfulness: Int, text: String?) =
        api.editReview(reviewId, SubmitReviewRequest(teaching, clarity, helpfulness, text))

    suspend fun deleteReview(reviewId: String) = api.deleteReview(reviewId)
}


