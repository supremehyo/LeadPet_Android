package com.dev6.domain.entitiyRepo

import com.dev6.core.enum.LoginType

interface LoginEntityRepo {
         var type: LoginType
         var uid :  String?
         var email : String?
         var password : String?
}