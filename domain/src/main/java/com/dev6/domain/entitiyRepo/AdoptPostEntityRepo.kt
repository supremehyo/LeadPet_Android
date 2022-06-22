package com.dev6.domain.entitiyRepo

import com.dev6.core.enum.AnimalType
import com.dev6.core.enum.Gender
import com.dev6.core.enum.Neutering

interface AdoptPostEntityRepo {
     val adoptionPostId: String
     val userId: String?
     val startDate: Int
     val endDate: Int
     val euthanasiaDate: Int
     val title: String
     val contents: String
     val animalType: AnimalType
     val species: String
     val gender: Gender
     val images: List<String>
     val neutering: Neutering
}