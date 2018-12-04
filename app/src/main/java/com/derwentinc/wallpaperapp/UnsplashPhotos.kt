package com.derwentinc.wallpaperapp

data class UnsplashPhotos(
    val results: List<UnsplashPhotoResult>,
    val total: Int,
    val total_pages: Int
)

data class UnsplashPhotoResult(
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: UnsplashPhotoLinks,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashPhotoUser,
    val width: Int
)

data class UnsplashPhotoUrls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class UnsplashPhotoUser(
    val first_name: String,
    val id: String,
    val instagram_username: String,
    val last_name: String,
    val links: UnsplashPhotoLinks,
    val name: String,
    val portfolio_url: String,
    val profile_image: UnsplashPhotoProfileImage,
    val twitter_username: String,
    val username: String
)

data class UnsplashPhotoLinks(
    val download: String,
    val html: String,
    val likes: String,
    val photos: String,
    val self: String
)

data class UnsplashPhotoProfileImage(
    val large: String,
    val medium: String,
    val small: String
)