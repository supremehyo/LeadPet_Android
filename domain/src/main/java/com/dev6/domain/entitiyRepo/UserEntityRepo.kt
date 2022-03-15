package com.dev6.domain.entitiyRepo

interface UserEntityRepo {
         val type: String
         val uid : String
         val email : String?
         val password : String?
}