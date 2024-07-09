package com.android.app.mvvmcomposeapp.network.client

import com.android.app.mvvmcomposeapp.data.remote.models.MedicationResponse
import com.android.app.mvvmcomposeapp.network.Routes
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url

class ServicesImpl(private val httpClient: HttpClient) : Services {
    override suspend fun getMedications(): MedicationResponse {
        return try {
            httpClient.get {
                url(Routes.MEDICATIONS)
            }
        } catch (e: RedirectResponseException) {
            //3xx - responses
            println("Error:" + e.response.status.description)
            MedicationResponse()
        } catch (e: ClientRequestException) {
            //4xx - responses
            println("Error:" + e.response.status.description)
            MedicationResponse()
        } catch (e: ServerResponseException) {
            //5xx - responses
            println("Error:" + e.response.status.description)
            MedicationResponse()
        } catch (e: Exception) {
            //5xx - responses
            println("Error:" + e.message)
            MedicationResponse()
        }
    }
}