package com.dev6.domain.entitiyRepo

interface LoginEntityRepo {
         val type: String
         val uid : String
         val email : String?
         val password : String?
}