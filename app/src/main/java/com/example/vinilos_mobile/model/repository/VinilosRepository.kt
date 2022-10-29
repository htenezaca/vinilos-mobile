package com.example.vinilos_mobile.model.repository

import android.content.Context
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_mobile.model.api.VinilosApiService
import com.example.vinilos_mobile.model.models.Collector

class VinilosRepository {

    fun getCollectors(
        applicationContext: Context,
        onComplete: (resp: List<Collector>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {

        var vinilosApiService = VinilosApiService(applicationContext)

        vinilosApiService.instance.add(VinilosApiService.getCollectors(
            { response ->
                Log.d("TAG1", response.toString())
                val collectorsList = mutableListOf<Collector>()

                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    collectorsList.add(
                        Collector(
                            collectorId = item.getInt("id"),
                            name = item.getString("name"),
                            telephone = item.getString("telephone"),
                            email = item.getString("email")
                        )
                    )
                }

                onComplete(collectorsList)
            },
            {
                Log.d("TAG2", it.toString())
                onError(it)
            }
        ))
    }
}