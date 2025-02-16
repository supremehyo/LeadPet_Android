package com.dev6.domain.model

import com.google.common.truth.Truth
import org.junit.Test

class UtilTest{

    //언젠가 kotest도 써보자
    @Test
    fun `인덱스만을 방출하는가?`(){
        val speciesList = listOf<IndexBreed>(
//            IndexBreed("가", listOf("가브리안", "가비투엘", "가주망", "가지주")),
//            IndexBreed("나", listOf("나루투", "나리토", "나미엘", "나무베")),
//            IndexBreed("파", listOf("파파자", "파피주", "파피몬", "파라과이"))
        )

        Truth.assertThat(speciesList.extractIndex()).contains("가")
        Truth.assertThat(speciesList.extractIndex()).contains("나")
        Truth.assertThat(speciesList.extractIndex()).contains("파")
    }
}