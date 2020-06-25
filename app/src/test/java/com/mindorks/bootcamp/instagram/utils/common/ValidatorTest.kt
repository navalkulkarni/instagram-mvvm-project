package com.mindorks.bootcamp.instagram.utils.common

import android.util.Log
import com.mindorks.bootcamp.instagram.R
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.junit.Test

class ValidatorTest {

    @Test
    fun givenValidEmailandPassword_whenValidate_ShouldResultSuccess(){
        val email = "test@gmail.com"
        val password = "password"
        val validations = Validator.validateLoginFields(email,password)
        assertThat(validations,hasSize(2))
        assertThat(
            validations,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

    @Test
    fun givenInValidEmailandValidPassword_whenValidate_shouldReturnErrorForEmail(){
        val email = "test@gmail"
        val password = "password"
        val validations = Validator.validateLoginFields(email,password)
        assertThat(validations,hasSize(2))
        assertThat(
            validations,
            contains(
                Validation(Validation.Field.EMAIL, Resource.error(R.string.email_field_invalid)),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

    @Test
    fun givenValidEmailandInvalidPassword_whenValidate_shouldReturnErrorForPassword(){
        val email = "test@gmail.com"
        val password = "pwd"
        val validations = Validator.validateLoginFields(email,password)
        assertThat(validations,hasSize(2))
        assertThat(
            validations,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.error(R.string.password_field_small_length))
            )
        )
    }
}