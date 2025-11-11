package com.example.ratemyprofv1.data.remote

import com.example.ratemyprofv1.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("/register")
    suspend fun register(@Body body: AuthRequest): Response<AuthResponse>

    @POST("/verify-otp")
    suspend fun verifyOtp(@Body body: AuthRequest): Response<AuthResponse>

    @POST("/login")
    suspend fun login(@Body body: AuthRequest): Response<AuthResponse>

    @GET("/professors")
    suspend fun getProfessors(@Query("name") name: String? = null): Response<List<Professor>>

    @GET("/professors/{id}")
    suspend fun getProfessorDetails(@Path("id") id: String): Response<Professor>

    @POST("/professors/{id}/reviews")
    suspend fun submitReview(
        @Path("id") id: String,
        @Body body: SubmitReviewRequest
    ): Response<AuthResponse>

    @GET("/users/{id}/reviews")
    suspend fun getMyReviews(@Path("id") userId: String): Response<List<Review>>

    @PUT("/reviews/{id}")
    suspend fun editReview(@Path("id") reviewId: String, @Body body: SubmitReviewRequest): Response<AuthResponse>

    @DELETE("/reviews/{id}")
    suspend fun deleteReview(@Path("id") reviewId: String): Response<AuthResponse>
}


