package com.example.posts_app.data.mappers

import com.example.posts_app.data.db.entities.UserEntity
import com.example.posts_app.data.models.User
import com.example.posts_app.data.models.dto.user.Address
import com.example.posts_app.data.models.dto.user.Company
import com.example.posts_app.data.models.dto.user.Geo
import com.example.posts_app.data.models.dto.user.UserDto

fun UserDto.toModel() =  User(
    address, company, email, id, name, phone, username, website
)

fun UserEntity.toModel() = User(
    address = Address(city, Geo("",""),street,suit, "") ,
    company = Company("",catchPhrase,company),
    email = email,
    id = id,
    name = name,
    phone = phone,
    username = username,
    website = website
)

fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    username = username,
    email = email,
    street = address.street,
    suit = address.suite,
    city = address.city,
    phone = phone,
    website = website,
    company = company.name,
    catchPhrase = company.catchPhrase
)