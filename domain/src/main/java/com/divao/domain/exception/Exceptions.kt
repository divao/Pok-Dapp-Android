package com.divao.domain.exception

sealed class GenericException() : RuntimeException()
class UnexpectedException() : GenericException()
class NoInternetException : GenericException()

abstract class FieldValidationException : RuntimeException()
class InvalidFormFieldException : FieldValidationException()
class EmptyRequiredFieldException : FieldValidationException()