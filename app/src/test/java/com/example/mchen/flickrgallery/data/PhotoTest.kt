package com.example.mchen.flickrgallery.data

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class PhotoTest {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
    }

    @Test
    fun getUrl() {

        var result = "https://farm3344.staticflickr.com/server/1_secret.jpg"
        // String.format(urlFormat,farm,server,id,secret)

        var photo = Photo()
        photo.id = "1"
        photo.secret = "secret"
        photo.server = "server"
        photo.farm = 3344
        photo.title = "title"
        photo.owner = "owner123"
        assert(photo.getUrl() == result)

    }
}
