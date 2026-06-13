package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.bookshelfapp.ui.theme.BookshelfAppTheme

@Composable
fun DetailScreen(contentPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
    ) {
        AsyncImage(
            model = "https://books.google.com/books/content?id=3bJZEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.8f / 1f)
        )
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                "Android UI Development with Jetpack Compose",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "Get started with creating intuitive native user interfaces on Android platforms Key Features Understand the difference between the imperative (Android View) and declarative (Jetpack Compose) approach Learn about the structure of a Compose app, built-in Compose UI elements, and core concepts such as state hoisting and composition over inheritance Write, test, and debug composable functions Book DescriptionJetpack Compose is Android’s new framework for building fast, beautiful, and reliable native user interfaces. It simplifies and significantly accelerates UI development on Android using the declarative approach. This book will help developers to get hands-on with Jetpack Compose and adopt a modern way of building Android applications. The book is not an introduction to Android development, but it will build on your knowledge of how Android apps are developed. Complete with hands-on examples, this easy-to-follow guide will get you up to speed with the fundamentals of Jetpack Compose such as state hoisting, unidirectional data flow, and composition over inheritance and help you build your own Android apps using Compose. You'll also cover concepts such as testing, animation, and interoperability with the existing Android UI toolkit. By the end of the book, you'll be able to write your own Android apps using Jetpack Compose.What you will learn Gain a solid understanding of the core concepts of Jetpack Compose Develop beautiful, neat, and immersive UI elements that are user friendly, reliable, and performant Build a complete app using Jetpack Compose Add Jetpack Compose to your existing Android applications Test and debug apps that use Jetpack Compose Find out how Jetpack Compose can be used on other platforms Who this book is for This book is for any mobile app developer looking to understand the fundamentals of the new Jetpack Compose framework and the benefits of native development. A solid understanding of Android app development, along with some knowledge of the Kotlin programming language, will be beneficial. Basic programming knowledge is necessary to grasp the concepts covered in this book effectively.",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    BookshelfAppTheme() {
        DetailScreen(contentPadding = PaddingValues(12.dp))
    }
}