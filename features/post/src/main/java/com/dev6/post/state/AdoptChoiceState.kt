package com.dev6.post.state

import com.dev6.core.enum.AnimalType
import com.dev6.core.enum.Gender
import com.dev6.core.enum.Neutering

data class AdoptChoiceState(
    val gender: Gender,
    val age: String,
    val neutering: Neutering
)
