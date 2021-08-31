package com.example.retrofit_postdata

import com.google.gson.annotations.SerializedName

data class UserInfo (
    @SerializedName("deviceType") val deviceType: String?,
    @SerializedName("deviceId") val deviceId: Int?,
    @SerializedName("deviceVersion") val deviceVersion: Int?,
    @SerializedName("language") val language: String?,
    @SerializedName("appVersion") val appVersion: Int?,
    @SerializedName("iPAddress") val iPAddress: Int?,
    @SerializedName("cabLatitude") val cabLatitude: Int?,
    @SerializedName("cabLongitude") val cabLongitude: Int?,
    @SerializedName("pushNotificationToken") val pushNotificationToken: String?,
    @SerializedName("deviceMac") val deviceMac: String?,
    @SerializedName("deviceModel") val deviceModel: String?,
    @SerializedName("deviceName") val deviceName: Int?,
    @SerializedName("buildId") val buildId: Int?,
    @SerializedName("customerType") val customerType: String?,
    @SerializedName("loginType") val loginType: String?,
    @SerializedName("referalCode") val referalCode: String?
)







