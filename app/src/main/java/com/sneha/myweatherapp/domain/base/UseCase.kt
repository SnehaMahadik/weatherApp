package com.sneha.myweatherapp.domain.base

interface UseCase<Q, R> {
     suspend fun executeUseCase(request :Q) : R
}