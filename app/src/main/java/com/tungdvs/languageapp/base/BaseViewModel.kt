package com.tungdvs.languageapp.base

import javax.inject.Inject

abstract class BaseViewModel {
    @Inject
    lateinit var errorHandler: ErrorHandler




}